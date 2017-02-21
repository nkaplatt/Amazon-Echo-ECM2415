import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Rickster on 20/02/2017.
 */
public class OnOff{
	public static void main(String[] args){
		//Set up container.
		JFrame frame = new JFrame("Amazon Echo");
		frame.setLayout(new GridLayout(2,0));
		frame.setTitle("Welcome to Amazon Echo!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(500,600);
		frame.setVisible(true);

		//Set up buttons.
		JPanel panel = new JPanel();
		JButton onButton = new JButton("On");
		JButton offButton = new JButton("Off");
		frame.add(panel, BorderLayout.NORTH);
		panel.add(onButton);
		panel.add(offButton);
		onButton.addActionListener(new OnAction());
		offButton.addActionListener(new OffAction());

		//Set up echo image.
		JPanel panel2 = new JPanel();
		JButton testButton = new JButton("Test");		//Replace test Code
		frame.add(panel2, BorderLayout.SOUTH);
		panel2.add(testButton);											//Replace test Code

		//Find a way to add image to panel2
		/*
			ImageIcon echoIcon = new ImageIcon("Echo.png");
			JLabel echoImage = new JLabel(echoIcon);
			panel2.add(echoImage);
		*/
	}

	static class OnAction implements ActionListener{
		//Create new frame when action performed
		public void actionPerformed(ActionEvent e){
			JFrame frame2 = new JFrame("");
			frame2.setLocationRelativeTo(null);
			frame2.setVisible(true);
			frame2.setSize(100,80);
			JLabel label = new JLabel("Echo is now on!");
			JPanel panel = new JPanel();
			frame2.add(panel);
			panel.add(label);
			ButtonNoise.main(null);
		}
	}

	static class OffAction implements ActionListener{
		//Create new frame when action performed
		public void actionPerformed(ActionEvent e){
			JFrame frame2 = new JFrame("");
			frame2.setLocationRelativeTo(null);
			frame2.setVisible(true);
			frame2.setSize(100,80);
			JLabel label = new JLabel("Echo is now Off!");
			JPanel panel = new JPanel();
			frame2.add(panel);
			panel.add(label);
		}
	}
}
