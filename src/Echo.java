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
    private String[] filepaths = {"../sound/output.wav", "../sound/output2.wav", "../sound/output3.wav"};
    private boolean setup_required = true;

    /*
    * Nested class creates the Button for interactively turning the
    * echo on and off.
    */
    private class OnOffButton extends JButton {
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
                        SoundThread.stop = true;

                    } else {
                        /* code to turn on echo */
                        setIcon(new ImageIcon("../images/power_button_on.png")); // displays green power button for on
                        ButtonNoise.startup(); // play the start up sound
                        Echo.state = true;


                        if (setup_required) {
                          // Start 3 threads if they havent been set up yet
                          // currently only working properly with 1 thread as they arent offset
                          for (int i = 0; i < 1; i++) {
                            SoundThread.create_thread(filepaths[i]);
                          }
                          setup_required = false; // will not try to re-setup threads when echo turned back on
                        } else {
                          System.out.println("this thread will restart");
                        }
                    }
                }
            });
        }
    }

    /*
    * Method create the echo interface and object - image and button are
    * set up that later provides functionality of recording sound and
    * responding with answers from wolfram alpha.
    */
    public Echo() {
        setTitle("Echo");
        setContentPane(new JLabel(new ImageIcon("../images/Echo_off.png"))); // displays the echo in the off state originally
        setLayout(null);

        /* code for creating bounds for button */
        powerButton.setOpaque(false);
        powerButton.setContentAreaFilled(false);
        powerButton.setBounds(160, 580, 40, 45);
        add(powerButton);
    }

    /*
    * Method calls RecordSound class to start a recording to an output
    * file for later use with the API toolkit.
    */
    public static void listen(AudioInputStream stm, String OUTPUT) {
      System.out.println("Echo will now record for 10 seconds to output.wav");
      RecordSound.recordSound(OUTPUT, RecordSound.readStream(stm)); // records sound to file path
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
