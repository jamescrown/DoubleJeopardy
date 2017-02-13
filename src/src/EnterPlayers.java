import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EnterPlayers extends JFrame {
	
	public static String playerOne; //names of the players
	public static String playerTwo;
	public static String playerThree;
	public static Board s;
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {  // invokeLater will excute a runnable(EnterPlayers)later which is currently on a queue right now.
			public void run() {
				try {
					  EnterPlayers window = new EnterPlayers(); // initialising name of object.
					  window.frame.setVisible(true); // displays "EnterPlayers" window. 
				}   catch (Exception e) {
					e.printStackTrace(); // tracks an Exception.
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
	    
	    //this Jpanel allows you to enter the names of the paluers
	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Player 1:"));
	    myPanel.add(playerone);
	    myPanel.add(Box.createHorizontalStrut(5)); // a spacer
	    myPanel.add(new JLabel("Player 2:"));
	    myPanel.add(playertwo);
	    myPanel.add(Box.createHorizontalStrut(5)); // a spacer
	    myPanel.add(new JLabel("Player 3:"));
	    myPanel.add(playerthree);

	    JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Player Names", JOptionPane.OK_CANCEL_OPTION);

		frame.getContentPane().add(myPanel); //adds the panel to the frame
		myPanel.setLayout(null);
	    
	    
	    playerOne = playerone.getText();	//allows us to keep track of the names
	    playerTwo = playertwo.getText();
	    playerThree = playerthree.getText();
		
		JLabel lblNewLabel = new JLabel("MONOPOLY");	//welcome message to start game
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
		
		
		btnStartGame.setBounds(90, 200, 200, 30); // this sets the position of the "StartGame" button.. SetBounds(x,y,width,height)
		panel.add(btnStartGame); // adds button to panel.
		
		
	}
}
