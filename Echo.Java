import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class Echo extends JFrame{
    private static boolean state;
    private OnOffButton powerButton = new OnOffButton();

    /*
    *
    */
    private class OnOffButton extends JButton {
        OnOffButton(){
            setIcon(new ImageIcon("echooff.png")); // File path for linux can be short hand
            setBorder(null);
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me){
                    if (Echo.state) {
                        /* code to turn off echo */
                        setIcon(new ImageIcon("echooff.png")); // File path for linux can be short hand
                        Echo.state = false;

                    } else {
                        /* code to turn on echo */
                        setIcon(new ImageIcon("echoon.png")); // File path for linux can be short hand
                        ButtonNoise.startup();
                        Echo.state = true;
                    }
                }
            });
        }
    }

    public Echo(){
        setTitle("Echo");
        setContentPane(new JLabel(new ImageIcon("Echo.png"))); // File path for linux can be short hand
        setLayout(null);

        /* code for creating bounds for button */
        powerButton.setBounds(160, 580, 50, 43);
        add(powerButton);
    }

    public static void main(String[] args){
        JFrame frame = new Echo(); // Creates the frame for the echo image to reside
        frame.setLocationRelativeTo(null);
        frame.setSize(375, 800);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
