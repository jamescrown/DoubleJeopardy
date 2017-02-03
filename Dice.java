import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dice extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		f.setTitle("Monopoly Board");
		f.setSize(400,400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		JPanel p1 = new JPanel();
		p1.setBackground(Color.GRAY);
		JLabel l5;
	
		f.add(p1);
		p1.setLocation(100, 100);
		p1.setSize(100, 100);
		
		Random rand = new Random();
		int  n = rand.nextInt(5) + 1;
		int n2 = rand.nextInt(5) + 1;
		int n3 = n + n2;
		if(n == n2){
			l5 = new JLabel("Doubles!You rolled " + String.valueOf(n) + " + " + String.valueOf(n2) + "= " + String.valueOf(n3));
		}
		else{
			l5 = new JLabel("You rolled " + String.valueOf(n) + "+" + String.valueOf(n2) + "= " + String.valueOf(n3));
		}
		p1.add(l5);
	}

}
