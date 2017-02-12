
import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


public class InfoPanel extends JFrame {
	private static final long serialVersionUID = 1L;

		
	public InfoPanel() {
		
	JTextArea text = new JTextArea(38,20); //  H X WIDTH
	JScrollPane scroll = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
	
	setLayout(new FlowLayout() );
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	add(scroll);
	setSize(800,660);
	setLocationRelativeTo(null);
	setVisible(true);
		
	}
	
	public static void main(String args[]) {
		new InfoPanel();
	}
	
	
}
