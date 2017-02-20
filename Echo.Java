import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Echo extends JFrame{

    private class OnOffButoon extends JButton{
        /* render and display the on off button and its functionality */
    }

    public Echo(){
        setTitle("Echo");
        setContentPane(new JLabel(new ImageIcon("Echo.png")));
        setLayout(null);

        /* code for creating bounds for button */
    }

    public static void main(Stringp[] args){
        JFrame frame = new Echo();
        frame.setLocationRelativeTo(null);
        frame.setSize(173, 412);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}