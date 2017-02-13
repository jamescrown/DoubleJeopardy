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
	public static JButton button2;
	public static JButton button3;
	public static JButton button4;
	static JLabel label;
	
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
			//test
			//n3 = 12;
			
			Board.movePieces(n3, n, n2);
		}
	}
	static class PurchaseListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
			Random rand = new Random();
			int  n = rand.nextInt(5) + 1;
			int n2 = rand.nextInt(5) + 1;
			int n3 = n + n2;
			//test
			//n3 = 12;
			
			Board.movePieces(n3, n, n2);
		}
	}
	public static JPanel createComponents() {
		
		
		
		button = new JButton("Roll Dice!");
		ActionListener listener = new ClickListener();
		button.addActionListener(listener);
	
		
		button2 = new JButton("Buy");
		ActionListener Purchaselistener1 = new PurchaseListener();
		button2.addActionListener(Purchaselistener1);
		
		
		button3 = new JButton("Sell");
		ActionListener Purchaselistener2 = new PurchaseListener();
		button3.addActionListener(Purchaselistener2);
		
		button4 = new JButton("Trade");
		ActionListener Purchaselistener3 = new PurchaseListener();
		button4.addActionListener(Purchaselistener3);
		
		JPanel panel1 = new JPanel();
		//panel1.setLayout(new GridLayout(2, 2));
//		JLabel lab1 = new JLabel(EnterPlayers.player1 + " Your turn!", JLabel.LEFT);
//        panel.add(lab1);
		panel1.add(button);
		panel1.add(button2);
		panel1.add(button3);
		panel1.add(button4);
		return panel1;

	}
}
