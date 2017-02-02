import java.awt.*;
import javax.swing.*;

public class Board  {
	//class BackgroundImageJFrame extends JFrame
	//{
	JButton b1;
	JLabel l1;
	JPanel p1;
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
		
		l1 = new JLabel("This is our information panel");
		p1.add(l1);
		
		f.add(p1);
		p1.setLocation(300, 400);
		p1.setSize(200, 100);
		}
		public static void main(String args[])
		{
			new Board();
		}
	}
  