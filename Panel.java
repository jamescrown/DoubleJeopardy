import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
 
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
 

public class Panel extends JFrame {
     
    private JLabel labelPlayer1 = new JLabel(" Player 1: ");
    private JLabel labelPlayer2 = new JLabel(" Player 2: ");
    private JLabel labelPlayer3 = new JLabel(" Player 3: ");
    
    private JTextField textPlayer1 = new JTextField(25);
    private JTextField textPlayer2 = new JTextField(25);
    private JTextField textPlayer3 = new JTextField(25);
    
    private JButton buttonStart = new JButton("Start Game");
     
    public Panel() {
        super("Monopoly");
         
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
     
    public static void main(String[] args) {
        // set look and feel to the system look and feel
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
    }
}