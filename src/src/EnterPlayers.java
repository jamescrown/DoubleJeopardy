import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EnterPlayers extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	
	
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

	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 640, 480);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MONOPOLY");
		lblNewLabel.setBounds(220, 18, 212, 62);
		panel.add(lblNewLabel);
		
		JLabel lblPleaseEnterThe = new JLabel("Please enter the name of players");
		lblPleaseEnterThe.setBounds(64, 103, 203, 16);
		panel.add(lblPleaseEnterThe);
		
		JLabel Player1 = new JLabel("Player 1:");
		Player1.setBounds(64, 159, 61, 16);
		panel.add(Player1);
		
		JLabel Player2 = new JLabel("Player 2:");
		Player2.setBounds(64, 212, 61, 16);
		panel.add(Player2);
		
		JLabel Player3 = new JLabel("Player 3:");
		Player3.setBounds(64, 264, 61, 16);
		panel.add(Player3);
		
		textField_1 = new JTextField();   // text field for player 1
		textField_1.setBounds(256, 156, 191, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(); // text field for player 2 
		textField_2.setBounds(256, 206, 191, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField(); // text field for player 3
		textField_3.setBounds(256, 261, 191, 19);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			
			// click "Start Game" and opens the Monopoly Game.
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				Board board_monopoly = new Board();
				board_monopoly.setVisible(true);
				
			}
		});
		
		btnStartGame.setBounds(143, 311, 200, 30);
		panel.add(btnStartGame);
		
		
	}
}
