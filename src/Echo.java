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

import javax.swing.Timer;

public class Echo extends JFrame {
    private static boolean state;
    private OnOffButton powerButton = new OnOffButton();
    private static String[] filepaths = {"../sound/waitforecho.wav","../sound/output.wav", "../sound/output2.wav"};
    private static String[] echoimagepaths = {"../images/Echo_off.png","../images/Echo.png"};
    private static JFrame frame = new Echo(); // Creates the frame for the echo image to reside
    public static volatile boolean isClicked = false;

    /*
    * Nested class creates the Button for interactively turning the
    * echo on and off.
    */
    private class OnOffButton extends JButton implements Runnable {
        OnOffButton() {
            setBorder(null);
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me){
                  isClicked = true;
                  if (WakeWordThread.finishedRunning && SoundThread.finishedRunning && isClicked){
                    isClicked = false;
                    try{
                      if (Echo.state) {
                          /* code to turn off echo */
                          Echo.state = false;
                      } else {
                          /* code to turn on echo */
                          Echo.state = true;
                      }
                      Thread t1 = new Thread(powerButton);
                      Thread t2 = new Thread(new ButtonNoise());
                      t1.start();
                      t2.start();

                      if (!Echo.state) {
                        WakeWordThread.pause = true;
                        SoundThread.pause = true;
                      } else {
                        WakeWordThread.pause = false;
                        synchronized(WakeWordThread.lock){
                          WakeWordThread.lock.notify();
                        }
                        SoundThread.pause = true;
                      }
                    } catch (Exception ex){}
                  }
              }
            });
        }

        public void run(){
          try{
              if(!Echo.state) {
                System.out.println("Test 1");
                setIcon(new ImageIcon("../images/power_button_off.png")); // displays red power button for off
              } else {
                System.out.println("Test 2");
                //frame.setContentPane(new JLabel(new ImageIcon(echoimagepaths[1])));
                //frame.remove(powerButton);
                setIcon(new ImageIcon("../images/power_button_on.png")); // displays green power button for on
              }
          } catch (Exception ex) {
            System.out.println("Button changing error");
          }
        }
    }

    /*
    * Method create the echo interface and object - image and button are
    * set up that later provides functionality of recording sound and
    * responding with answers from wolfram alpha.
    */
    public Echo() {
      setTitle("Echo");
      setContentPane(new JLabel(new ImageIcon(echoimagepaths[0]))); // displays the echo in the off state originally
      setLayout(null);
      powerButton.setOpaque(false);
      powerButton.setContentAreaFilled(false);
      powerButton.setBounds(160, 580, 40, 45);
      /* code for creating bounds for button */
      (new Thread(powerButton)).start();
      add(powerButton);
    }

    /*
    * Method calls RecordSound class to start a recording to an output
    * file for later use with the API toolkit.
    */
    public static void listen(AudioInputStream stm, String OUTPUT, int timer) {
        System.out.println("Echo will now record for " + timer + " seconds");
        RecordSound.recordSound(OUTPUT, RecordSound.readStream(stm, timer)); // records sound to file path
        System.out.println("Echo has finished recording");
    }

    public static void main(String[] args) {
        frame.setLocationRelativeTo(null);
        frame.setSize(375, 800);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AudioInputStream stream_tmp = RecordSound.setupStream(); //Setup audio stream
        // Start thread listening for wake word
        WakeWordThread.create_wakeword_thread(filepaths[0],stream_tmp);
        // Start threads listening for question
        SoundThread.create_thread(filepaths[1],stream_tmp);
        SoundThread.create_thread(filepaths[2],stream_tmp);
    }

}
