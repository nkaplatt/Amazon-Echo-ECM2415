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


    private class OnOffButton extends JButton{
        OnOffButton(){
            setIcon(new ImageIcon("C:\\Users\\Spencer\\Desktop\\SoftEnd Clone\\src\\echooff.png"));
            setBorder(null);
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me){
                    if(Echo.state){
                        /* code to turn off echo */
                        setIcon(new ImageIcon("C:\\Users\\Spencer\\Desktop\\SoftEnd Clone\\src\\echooff.png"));
                        Echo.state = false;

                    } else {
                        /* code to turn on echo */
                        ButtonNoise.startup();
                        setIcon(new ImageIcon("C:\\Users\\Spencer\\Desktop\\SoftEnd Clone\\src\\echoon.png"));
                        Echo.state = true;
                    }
                }
            });
        }
    }

    public Echo(){
        setTitle("Echo");
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\Spencer\\Desktop\\SoftEnd Clone\\src\\Echo.png")));
        setLayout(null);

        /* code for creating bounds for button */
        powerButton.setBounds(50, 287, 50, 43);
        add(powerButton);
    }

    public static void main(String[] args){
        JFrame frame = new Echo();
        frame.setLocationRelativeTo(null);
        frame.setSize(173, 412);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}