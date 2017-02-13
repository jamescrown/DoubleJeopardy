import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class J_SplitPane {
	 
    /**
     * @wbp.parser.entryPoint
     * 
     */
    public static void main(String[] args) {
    	
    	new J_SplitPane();
        SwingUtilities.invokeLater(runJSplitPaneLater);
    }
     
    static Runnable runJSplitPaneLater = new Runnable(){
 
     
        public void run() 
        {
            MyJFrame myJFrame = new MyJFrame();
            myJFrame.setVisible(true);
        }
    };
     
    public static class MyJFrame extends JFrame{
         
    	
    	
        JSplitPane jsp, jsp2;
        JPanel Panel1, Panel2, Panel3;
         
        public MyJFrame()
        {
          
            this.setSize(1000, 800);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
            
            JMenuBar menubar = new JMenuBar(); 
            this.setJMenuBar(menubar);
            
            JMenu file = new JMenu("File");
            menubar.add(file);
            
            JMenuItem board = new JMenuItem("open board");
            file.add(board);
            
            Panel1 = new JPanel();
            Panel2 = new JPanel();
            Panel3 = new JPanel();
 
           
            jsp2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, Panel2, Panel3); 
            
            
            jsp2.setOneTouchExpandable(true);
            jsp2.setDividerLocation(500);
             
            jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
                    Panel1,jsp2);
            
            jsp.setOneTouchExpandable(true);
           jsp.setDividerLocation(800);
             
            getContentPane().add(jsp);
       
           board.addActionListener(new ActionListener()  
           {
        	   
            	public void actionPerformed(ActionEvent e ) 
            	{
            		
            		setVisible(false);
    				Board board_monopoly = new Board();
    				board_monopoly.setVisible(true);
            	}
           });
            	
        }
    }
}
