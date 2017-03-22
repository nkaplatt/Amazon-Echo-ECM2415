import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.net.URLEncoder;

/**
 * Created on 13/03/2017.
 * Takes a question and returns an answer by entering the
 * question into the wolfram alpha search engine.
 *
 */
public class Computational {
    final static String APPID = "3Q9E2T-KTXP5E4EEQ";

    /*
     * Solve answer to the question by connecting to wolfram
     * Alpha.
     */
    public static String solve( String input ) {
        final String method = "POST";
        final String url
                = ( "http://api.wolframalpha.com/v2/query"
                + "?" + "appid"  + "=" + APPID
                + "&" + "input"  + "=" + Encode( input )
                + "&" + "output" + "=" + "JSON"
        );
        final String[][] headers
                = { { "Content-Length", "0" }
        };
        final byte[] body = new byte[0];
        byte[] response   = HttpConnect.httpConnect( method, url, headers, body );
        String xml        = new String( response );
        return xml;
    }

    /*
     * URL encode string using utf-8.
     */
    private static String Encode( String s ) {
        try {
            return URLEncoder.encode( s, "utf-8" ); //encode sting using utf-8
        } catch ( Exception ex ) {
            System.out.println( ex );
            System.exit( 1 );
            return null;
        }
    }

    /*
     * Answers question.
     */
    public static void main( String[] argv ) {

    }
}
