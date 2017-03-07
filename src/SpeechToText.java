import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.util.UUID;
/*
 * Speech to text conversion using Microsoft Cognitive Services.
 *
 * See http://www.microsoft.com/cognitive-services/en-us/speech-api

 */
public class SpeechToText {
    final static String LANG  = "en-US";
    final static String INPUT = "../sound/recording.wav";

    final static String KEY1 = "4b90f6fd41164a1cb90085c9380ae42b";
  /* final static String KEY2 = "9285aafce928425997d23885f8e6b100"; */

    /*
     * Renew an access token --- they expire after 10 minutes.
     */
    static String renewAccessToken( String key1 ) {
        final String method = "POST";
        final String url =
                "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
        final byte[] body = {};
        final String[][] headers
                = { { "Ocp-Apim-Subscription-Key", key1                          }
                , { "Content-Length"           , String.valueOf( body.length ) }
        };
        byte[] response = HttpConnect.httpConnect( method, url, headers, body );
        return new String( response );
    }

    /*
     * Recognize speech.
     */
    static String recognizeSpeech( String token, byte[] body ) {
        final String method = "POST";
        final String url
                = ( "https://speech.platform.bing.com/recognize"
                + "?" + "version"    + "=" + "3.0"
                + "&" + "format"     + "=" + "json"
                + "&" + "device.os"  + "=" + "wp7"
                + "&" + "scenarios"  + "=" + "smd"
                + "&" + "locale"     + "=" + LANG
                + "&" + "appid"      + "=" + "D4D52672-91D7-4C74-8AD8-42B1D98141A5"
                + "&" + "instanceid" + "=" + UUID.randomUUID().toString()
                + "&" + "requestid"  + "=" + UUID.randomUUID().toString()
        );
        final String[][] headers
                = { { "Content-Type"   , "audio/wav; samplerate=16000"  }
                , { "Content-Length" , String.valueOf( body.length )  }
                , { "Authorization"  , "Bearer " + token              }
        };
        byte[] response = HttpConnect.httpConnect( method, url, headers, body );
        return new String( response );
    }

    /*
     * Read data from file.
     */
    static byte[] readData( String name ) {
        try {
            File file = new File( name );
            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
            byte[] buffer = new byte[ (int) file.length() ];
            dis.readFully( buffer );
            dis.close();
            return buffer;
        } catch ( Exception ex ) {
            System.out.println( ex ); System.exit( 1 ); return null;
        }
    }

    /*
     * Convert speech to text.
     */
    public static void main( String[] argv ) {
        final String token  = renewAccessToken( KEY1 );
        final byte[] speech = readData( INPUT );
        //
        final String text = recognizeSpeech( token, speech ); // for Tillys part
        //

        boolean result = SearchText.findWakeWord(text); // Searches for wake word in the speach sent to API
        System.out.println(result);

        System.out.println( text );
    }
}
