import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;

public class ClickListener implements ActionListener {
	int i = 1;	
	public void actionPerformed(ActionEvent event){
			
		Random rand = new Random();
		int  n = rand.nextInt(5) + 1;
		int n2 = rand.nextInt(5) + 1;
		int n3 = n + n2;
		if(n == n2){
			System.out.println("Doubles! You rolled " + String.valueOf(n) + " + " + String.valueOf(n2) + " = " + String.valueOf(n3));
			JFrame frame = new ButtonFrame1();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		else{
			System.out.println("You rolled " + String.valueOf(n) + " + " + String.valueOf(n2) + " = " + String.valueOf(n3));
		}
		
		}

}
