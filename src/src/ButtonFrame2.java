import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonFrame2 extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	
	public static JButton button;
	static JLabel label;
	
	private static final int FRAME_WIDTH = 100;
	private static final int FRAME_HEIGHT = 100;

	public ButtonFrame2(){
		//creates a new frame to contain the control panel, which we set as a button instead of text input
		createComponents();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	static class ClickListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
			//when the button is pressed the dice is rolled
			Random rand = new Random();
			int  n = rand.nextInt(5) + 1;	//first dice
			int n2 = rand.nextInt(5) + 1;	//second
			int n3 = n + n2;	//total value of roll
			//test
			//n3 = 12;
			
			Board.movePieces(n3, n, n2);	//calls movePieces to change the coordinates of the pieces in accordance with the roll value
		}
	}
	public static JPanel createComponents() {
		
		button = new JButton("Roll Dice!");
		ActionListener listener = new ClickListener();
		//the action event is when the user presses the roll button
		button.addActionListener(listener);
		JPanel panel = new JPanel();
		panel.add(button);
		return panel;
		
	}
}
