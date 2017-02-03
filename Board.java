import java.awt.*;
import javax.swing.*;

public class Board extends JFrame {
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
		 private JLabel labelPlayer1 = new JLabel(" Player 1: ");
		    private JLabel labelPlayer2 = new JLabel(" Player 2: ");
		    private JLabel labelPlayer3 = new JLabel(" Player 3: ");
		    
		    private JTextField textPlayer1 = new JTextField(25);
		    private JTextField textPlayer2 = new JTextField(25);
		    private JTextField textPlayer3 = new JTextField(25);
		    
		    private JButton buttonStart = new JButton("Start Game");
		     
		    public void Panel() {
		        
		         
		        // create a new panel with GridBagLayout manager
		        JPanel newPanel = new JPanel(new GridBagLayout());
		         
		        GridBagConstraints constraints = new GridBagConstraints();
		        constraints.anchor = GridBagConstraints.WEST;
		        constraints.insets = new Insets(10, 10, 10, 10);
		         
		        // add components to the panel
		        constraints.gridx = 0;
		        constraints.gridy = 0;     
		        newPanel.add(labelPlayer1, constraints);
		 
		        
		        constraints.gridx = 1;
		        newPanel.add(textPlayer1, constraints);
		        
		        constraints.gridx = 0;
		        constraints.gridy = 1;     
		        newPanel.add(labelPlayer2, constraints);
		       
		        constraints.gridx = 1;
		        newPanel.add(textPlayer2, constraints);
		        
		        constraints.gridx = 0;
		        constraints.gridy = 2;     
		        newPanel.add(labelPlayer3, constraints);
		        

		        constraints.gridx = 1;
		        newPanel.add(textPlayer3, constraints);
		        
		        constraints.gridx = 0;
		        constraints.gridy = 3;
		        constraints.gridwidth = 3;
		        constraints.anchor = GridBagConstraints.CENTER;
		        
		        newPanel.add(buttonStart, constraints);
		         
		        // set border for the panel
		        newPanel.setBorder(BorderFactory.createTitledBorder(
		                BorderFactory.createEtchedBorder(), "Welcome to Monopoly"));
		         
		        // add the panel to this frame
		        add(newPanel);
		         
		        pack();
		        setLocationRelativeTo(null);
		    }
		public static void main(String args[])
		{
			 try {
		            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		         
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                new Panel().setVisible(true);
		            }
		        });
			new Board();
		}
	}
  