import java.io.File;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

/*
 * Record sound.
 */
class RecordSound {
    private static final int TIMER = 10; /* secs */
    private static final int SAMPLE_RATE = 16000; /* MHz  */
    private static final int SAMPLE_SIZE = 16;    /* bits */
    private static final int SAMPLE_CHANNELS = 1;     /* mono */

    /*
     * Set up stream.
     */
    static AudioInputStream setupStream() {
        try {
            AudioFormat af =
                    new AudioFormat( SAMPLE_RATE
                            , SAMPLE_SIZE
                            , SAMPLE_CHANNELS
                            , true /* signed */
                            , true /* little-endian */
                    );
            DataLine.Info    info = new DataLine.Info( TargetDataLine.class, af );
            TargetDataLine   line = (TargetDataLine) AudioSystem.getLine( info );
            AudioInputStream stm  = new AudioInputStream( line );
            line.open( af );
            line.start();
            return stm;
        } catch ( Exception ex ) {
            System.out.println( ex );
            System.exit( 1 );
            return null;
        }
    }

    /*
     * Read stream.
     */
    static ByteArrayOutputStream readStream( AudioInputStream stm ) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int  bufferSize = SAMPLE_RATE * stm.getFormat().getFrameSize();
            byte buffer[]   = new byte[ bufferSize ];

            for ( int counter = TIMER; counter > 0; counter-- ) {
                int n = stm.read( buffer, 0, buffer.length );
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
     * Record sound.
     */
    static void recordSound( String name, ByteArrayOutputStream bos ) {
        try {
            AudioFormat af =
                    new AudioFormat( SAMPLE_RATE
                            , SAMPLE_SIZE
                            , SAMPLE_CHANNELS
                            , true /* signed */
                            , true /* little-endian */
                    );
            byte[]           ba   = bos.toByteArray();
            InputStream      is   = new ByteArrayInputStream( ba );
            AudioInputStream ais  = new AudioInputStream( is, af, ba.length );
            File             file = new File( name );
            AudioSystem.write( ais, AudioFileFormat.Type.WAVE, file );
        } catch ( Exception ex ) {
            System.out.println( ex ); System.exit( 1 );
        }
    }

    /*
     * Record sound.
     */
    public static void main( String[] argv ) {

    }
}
