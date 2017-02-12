import java.awt.*;


import javax.swing.*;

public class J_SplitPane extends JFrame {
       	private static final long serialVersionUID = 1L;
	private JSplitPane splitPaneA;
	private JSplitPane splitPaneB;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JTextArea textArea;
	

	/**
	 * 
	 */
	

	public J_SplitPane() {
		setBackground( Color.gray);
		
		 JPanel topPanel = new JPanel();
		   topPanel.setLayout( new BorderLayout() );
		   getContentPane().add(topPanel);
		   
		   					// Create the panels
		    create_Panel1();
		    create_Panel2();
		    create_Panel3();

		    				// Create a splitter pane
		    splitPaneA = new JSplitPane( JSplitPane.VERTICAL_SPLIT);
		    topPanel.add( splitPaneA, BorderLayout.CENTER );

		    splitPaneB = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
		    splitPaneB.setLeftComponent( panel1 );
		    splitPaneB.setRightComponent( panel2 );
		    panel2.setLayout(new BorderLayout(0, 0));
		    {
		    	textArea = new JTextArea();
		    	panel2.add(textArea);
		    }

		    splitPaneA.setLeftComponent( splitPaneB );
		    splitPaneA.setRightComponent( panel3 );
	}
	public void create_Panel1() { // left panel 
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout() );
		
		
	}
	
	public void create_Panel2() { // right panel
		panel2 = new JPanel();
	
		
		
	}
	
	public void create_Panel3() { // bottom panel
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout() );
		panel3.setPreferredSize( new Dimension( 400, 100 ) );
	    panel3.setMinimumSize( new Dimension( 100, 50 ) );
		
	    panel3.add( new JLabel( "This is the control panel" ), BorderLayout.NORTH );
	    panel3.add( new JTextArea(), BorderLayout.CENTER );
	}
	
	
	
	/**
	 * 
	 */
	
	public static void main( String args[] ){
	    try {
	      
	    } catch (Exception evt) {}
	    
	    // Create an instance of the test application
	    J_SplitPane mainFrame = new J_SplitPane();
	    mainFrame.pack();
	    mainFrame.setVisible( true );
	}

}
