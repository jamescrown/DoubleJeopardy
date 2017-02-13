import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EnterPlayers extends JFrame {
	
	public static String playerOne;
	public static String playerTwo;
	public static String playerThree;
	public static Board s;
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					  EnterPlayers window = new EnterPlayers();
					  window.frame.setVisible(true);
				}   catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public EnterPlayers() {
		initialize();
	}

	
	
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 400, 400);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextField playerone = new JTextField(5);
	    JTextField playertwo = new JTextField(5);
	    JTextField playerthree = new JTextField(5);
	    

	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Player 1:"));
	    myPanel.add(playerone);
	    myPanel.add(Box.createHorizontalStrut(5)); // a spacer
	    myPanel.add(new JLabel("Player 2:"));
	    myPanel.add(playertwo);
	    myPanel.add(Box.createHorizontalStrut(5)); // a spacer
	    myPanel.add(new JLabel("Player 3:"));
	    myPanel.add(playerthree);

	    int result = JOptionPane.showConfirmDialog(null, myPanel,
	        "Please Enter Player Names", JOptionPane.OK_CANCEL_OPTION);

		frame.getContentPane().add(myPanel);
		myPanel.setLayout(null);
	    
	    if (result == JOptionPane.OK_OPTION) {
	      System.out.println("Player 1: " + playerone.getText());
	      System.out.println("Player 2: " + playertwo.getText());
	      System.out.println("Player 3: " + playerthree.getText());
	    }
	    
	    playerOne = playerone.getText();
	    playerTwo = playertwo.getText();
	    playerThree = playerthree.getText();
		
		JLabel lblNewLabel = new JLabel("MONOPOLY");
		lblNewLabel.setBounds(150, 100, 200, 100);
		panel.add(lblNewLabel);
		
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			
			// click "Start Game" and opens the Monopoly Game.
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
				JFrame frame = new ButtonFrame2();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
				s = new Board();
				s.setDefaultCloseOperation(Board.EXIT_ON_CLOSE);
				s.setVisible(true);
				
			}
		});
		
		btnStartGame.setBounds(90, 200, 200, 30);
		panel.add(btnStartGame);
		
		
	}
}
