import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonFrame1 extends JFrame{
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private static final int FRAME_WIDTH = 600;
		private static final int FRAME_HEIGHT = 400;
		
		public ButtonFrame1(){
			createComponents();
			setSize(FRAME_WIDTH, FRAME_HEIGHT);
		}
		
		private void createComponents(){
			JButton button = new JButton("Roll dice");
			JPanel panel = new JPanel();
			panel.setBackground(Color.YELLOW);
			JLabel lab = new JLabel("Click to Roll");
			panel.add(lab);
			panel.add(button);
			setLayout(new GridBagLayout());
			add(panel);

			
			ActionListener listener = new ClickListener();
			button.addActionListener(listener);
		}
}
