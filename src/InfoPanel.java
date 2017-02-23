import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class InfoPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int TEXT_AREA_HEIGHT = 40;
	private static final int CHARACTER_WIDTH = 39;
	private static final int FONT_SIZE = 14;

	JTextArea textArea = new JTextArea(TEXT_AREA_HEIGHT, CHARACTER_WIDTH);
	JScrollPane scrollPane = new JScrollPane(textArea); // 
	DefaultCaret caret = (DefaultCaret)textArea.getCaret();
	
	InfoPanel () {
		textArea.setEditable(false);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, FONT_SIZE));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		return;
	}
	
	public void addText (String text ) {
		textArea.setText(textArea.getText()+"\n"+text);
		
		String input = textArea.getText();
		
		
		if(text.equals("Roll")) {
			  		
			// Enter Dice Code
		}
			// System.out.println("ROLL"); // person has moved 2 spots.
		textArea.setText(" \n  ROLL  "+input); 
			
		if (text.equals("Buy")) {
			
			textArea.setText(" \n BUY " +input); 
			
		}
		
		
		 if (text.equals("Sell")) {
			
			
			textArea.setText(" \n SELL "+input); 
		}
		
		
		if(text.equals("Balance")) {
			
			textArea.setText(" \n Balance "+input); 
		}
		
		if(text.equals("HELP")) {
			
			
			
			textArea.setText(" \n Help "+input); 
		}
		
		
		
		return;
		
	}

}
