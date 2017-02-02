import java.awt.*;
import javax.swing.*;

public class Board  {
	//class BackgroundImageJFrame extends JFrame
	//{
	JButton b1;
	JLabel l1;
	JPanel p1;
	JPanel player1;
	JPanel player2;
	JPanel player3;
	JLabel pll1;
	JLabel pll2;
	JLabel pll3;
	JFrame f;

		public Board()
		{
		
		f = new JFrame();
		f.setTitle("Monopoly Board");
		f.setSize(800,800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	
		f.setLayout(null);
		f.setContentPane(new JLabel(new ImageIcon("C:\\Users\\cloda\\OneDrive\\Pictures\\1f0146d66abc474e942a973915a20bad.jpg")));
		f.setSize(799,799);
		f.setSize(800,800);
		
		p1 = new JPanel();
		p1.setBackground(Color.GRAY);
		
		//player1
		player1 = new JPanel();
		player1.setBackground(Color.YELLOW);
		player1.setLocation(700, 710);
		player1.setSize(18, 18);
		pll1 = new JLabel("P1");
		player1.add(pll1);

		//player2
		player2 = new JPanel();
		player2.setBackground(Color.RED);
		player2.setLocation(700, 690);
		player2.setSize(18, 18);
		pll2 = new JLabel("P2");
		player2.add(pll2);
		
		//player3
		player3 = new JPanel();
		player3.setBackground(Color.PINK);
		player3.setLocation(700, 670);
		player3.setSize(18, 18);
		pll3 = new JLabel("P3");
		player3.add(pll3);
				
		l1 = new JLabel("This is our information panel");
		p1.add(l1);
		
		f.add(p1);
		f.add(player1);
		f.add(player2);
		f.add(player3);
		p1.setLocation(300, 400);
		p1.setSize(200, 100);
		}
		public static void main(String args[])
		{
			new Board();
		}
	}
  