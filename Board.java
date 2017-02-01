import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Board extends JFrame {
	//class BackgroundImageJFrame extends JFrame
	//{
	JButton b1;
	JLabel l1;
		public Board()
		{
		setTitle("Background Color for JFrame");
		setSize(800,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("C:\\Users\\cloda\\OneDrive\\Pictures\\1f0146d66abc474e942a973915a20bad.jpg")));
		setLayout(new FlowLayout());
		l1=new JLabel("Here is a button");
		b1=new JButton("I am a button");
		add(l1);
		add(b1);
		// Just for refresh :) Not optional!
		setSize(799,799);
		setSize(800,800);
		}
		public static void main(String args[])
		{
		new Board();
		}
	}
  