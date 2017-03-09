import java.io.File;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;


/**
 * Created by tilz on 19/02/2017.
 */
public class ButtonNoise {
    private final static String FILENAME = "../sound/hellosound.wav";
    private final static String byeSound = "../sound/byesound.wav";

    /*
     * This method sets up the stream for the sound file
     */
    static AudioInputStream setupStream( String name ) {
        try {
            File sound = new File( name );
            AudioInputStream stream  = AudioSystem.getAudioInputStream( sound );
            return stream;
        } catch ( Exception ex ) {
            System.out.println( ex ); System.exit( 1 ); return null;
        }
    }

    /*
     * This method reads the sound stream
     */
    static ByteArrayOutputStream readStream( AudioInputStream stream ) {
        try {
            AudioFormat audio  = stream.getFormat();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int  bufferSize = (int) audio.getSampleRate() * audio.getFrameSize();
            byte buffer[]   = new byte[ bufferSize ];

            for (;;) {
                int n = stream.read( buffer, 0, buffer.length );
                if ( n > 0 ) {
                    bos.write( buffer, 0, n );
                } else {
                    break;
                }
            }

            return bos;
        } catch ( Exception ex ) {
            System.out.println( ex ); System.exit( 1 ); return null;
        }
    }

    /*
     * This method will play the stream.
     */
    static void playStream(AudioInputStream stream, ByteArrayOutputStream bos) {
        try {
            AudioFormat audio = stream.getFormat();
            byte[] ba = bos.toByteArray();
            DataLine.Info info = new DataLine.Info( SourceDataLine.class, audio);
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine( info );

            line.open(audio);
            line.start();
            line.write(ba, 0, ba.length);
        } catch (Exception ex) {
            System.out.println(ex); System.exit(1);
        }
    }

    /*
     * Main method to play sound.
     */
    public static void main(String[] argv) {

    }

    static void startup() {
        AudioInputStream stream = setupStream( FILENAME );
        playStream(stream, readStream(stream));
    }

    static void shutDown() {
      AudioInputStream stream = setupStream( byeSound );
      playStream(stream, readStream(stream));
    }

    static void playSound(String filename) {
      AudioInputStream stream = setupStream( filename );
      playStream(stream, readStream(stream));
    }
}
