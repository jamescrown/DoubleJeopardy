import javax.swing.JFrame;
public class ButtonViewer1 {

	public static Board s;

	public static void main(String[] args) {
		JFrame frame = new ButtonFrame2();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		s=new Board();
        s.setDefaultCloseOperation(Board.EXIT_ON_CLOSE);
		s.setVisible(true);
	}

}