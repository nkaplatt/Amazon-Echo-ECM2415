import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by tilz on 19/02/2017.
 */
public class ButtonNoise {
    private final static String FILENAME = "hello.wav.wav";

    static void Stream (){
        try {
            File file = new File(FILENAME);
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        Stream();
    }
}