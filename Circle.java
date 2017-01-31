import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.color.*;

public class Circle extends JPanel{
	
	public void paint(Graphics g){
		
		setSize(500,500); //width and height of JPAnel
		g.drawOval(100, 100, 50, 50); //draws at these coordinates a circle of height and width 50
		
	}

	public static void main(String [] args){
		
		JFrame GameBoard = new JFrame();
		GameBoard.setSize(600, 600);
		
		Circle CirclePanel = new Circle();
		
		//add panel into JFrame
		GameBoard.add(CirclePanel);
		
		GameBoard.setVisible(true);
	}
	
	
	
	
}