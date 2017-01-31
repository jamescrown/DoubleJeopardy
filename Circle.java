import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Circle extends JPanel{
	
	public void paint(Graphics g){
		
		setSize(1000,600); //width and height of JPAnel
		g.drawOval(920, 500, 50, 50); //draws at these coordinates a circle of height and width 50
		
		//g.fillOval(100, 100, 50, 50); //color
		
	}

	public static void main(String [] args){
		
		JFrame GameBoard = new JFrame();
		GameBoard.setSize(1000, 600);
		//meBoard.setIconImage();
		
		Circle CirclePanel = new Circle();
		
		//add panel into JFrame
		GameBoard.add(CirclePanel);
		
		GameBoard.setVisible(true);
	}
	
	
	
	
}