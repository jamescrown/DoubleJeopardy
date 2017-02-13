
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class InfoCommandPanel extends  JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	protected JTextField textfield;
	protected JTextArea textarea;
	private final static String newline = "\n";
	
	public InfoCommandPanel() {
		
		super(new GridBagLayout());
		
	textfield = new JTextField(20); //TextField for input (command panel) 
	textfield.addActionListener(this); 
	
	textarea = new JTextArea(100,30);  // TextArea for output(information panel) 
	textarea.setEditable(false);
	JScrollPane scroll = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
	
	GridBagConstraints p = new GridBagConstraints(); // adds components to the panel.
	p.gridwidth = GridBagConstraints.REMAINDER;
	
	p.fill = GridBagConstraints.HORIZONTAL; // dimensions.
    add(textfield, p);

    p.fill = GridBagConstraints.BOTH;
    p.weightx = 1.0;
    p.weighty = 1.0;
    add(scroll, p); // display scrollpane
		 
	}
	

	public void actionPerformed(ActionEvent evt) {
	        String text = textfield.getText();
	        textarea.append(text + newline);
	        textfield.selectAll();

	        // to make sure the new text is visible.
	        textarea.setCaretPosition(textarea.getDocument().getLength());
	    }

 private static void createAndShowGUI() { // this creates and shows the GUI
	 
	 JFrame frame = new JFrame (" ") ; // this creates and sets up the window.
	 
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	 frame.add(new InfoCommandPanel()); // adds the contents to the window.
	 
	 frame.pack(); // this displays the window.
	 frame.setVisible(true);
 }
	 public static void main(String[] args) {
		
	     // for dispatch thread. 
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() { // creates and shows the GUI.
                createAndShowGUI();
            }
        });
	}
	
	}
