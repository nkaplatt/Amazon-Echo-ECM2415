import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.sound.sampled.AudioInputStream;



public class Echo extends JFrame {
    private static boolean state;
    private OnOffButton powerButton = new OnOffButton();
    private static final String OUTPUT = "../sound/output.wav"; // Record sound to output file


    /*
    * Nested class creates the Button for interactively turning the
    * echo on and off.
    */
    private class OnOffButton extends JButton {
        AudioInputStream stm = RecordSound.setupStream(); // Set up stream for recording
        OnOffButton() {
            setIcon(new ImageIcon("../images/power_button_off.png")); // initialises power as off
            setBorder(null);
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me){
                    if (Echo.state) {
                        /* code to turn off echo */
                        setIcon(new ImageIcon("../images/power_button_off.png")); // displays red power button for off
                        ButtonNoise.shutDown(); // play off sound
                        Echo.state = false;

                    } else {
                        /* code to turn on echo */
                        setContentPane(new ImageIcon("../images/Echo_on.png")); // File path for linux can be short hand
                        setIcon(new ImageIcon("../images/power_button_on.png")); // displays green power button for on
                        ButtonNoise.startup(); // play the start up sound
                        Echo.state = true;

                        listen(stm);

                    }
                }
            });
        }
    }

    public Echo() {
        setTitle("Echo");
        setContentPane(new JLabel(new ImageIcon("../images/Echo_off.png"))); // File path for linux can be short hand
        setLayout(null);

        /* code for creating bounds for button */
        powerButton.setOpaque(false);
        powerButton.setContentAreaFilled(false);
        powerButton.setBounds(160, 580, 40, 45);
        add(powerButton);
    }

    public static void listen(AudioInputStream stm) {
      System.out.println("about to record");
      RecordSound.recordSound(OUTPUT, RecordSound.readStream(stm));

      System.out.println("finished recording");
      ButtonNoise.playSound(OUTPUT); // play back the recording
    }

    public static void main(String[] args) {
        JFrame frame = new Echo(); // Creates the frame for the echo image to reside
        frame.setLocationRelativeTo(null);
        frame.setSize(375, 800);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
