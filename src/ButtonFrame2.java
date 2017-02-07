import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonFrame2 extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JButton button;
	private static JLabel label;
	
	private static final int FRAME_WIDTH = 100;
	private static final int FRAME_HEIGHT = 100;

	public ButtonFrame2(){
		createComponents();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	static class ClickListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
			Random rand = new Random();
			int  n = rand.nextInt(5) + 1;
			int n2 = rand.nextInt(5) + 1;
			int n3 = n + n2;
			if(n == n2){
				label.setText("Doubles! You rolled " + String.valueOf(n) + " + " + String.valueOf(n2) + "= " + String.valueOf(n3));
				//call move pieces
				
				//movePieces(n3);
			}
			else{
				label.setText("You rolled " + String.valueOf(n) + "+" + String.valueOf(n2) + "= " + String.valueOf(n3));
				//call move pieces
			}
		}
	}
	public static JPanel createComponents() {
		
		button = new JButton("Roll Dice!");
		ActionListener listener = new ClickListener();
		button.addActionListener(listener);
		
		label  = new JLabel("Your turn!");
		 
		JPanel panel = new JPanel();
		panel.add(button);
		panel.add(label);
		return panel;
		
	}
}
