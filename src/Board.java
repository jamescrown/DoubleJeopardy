import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
//first attempt

class Board extends JFrame{

    public Board(){
    	
    
    	JPanel panel=new JPanel();
   	 getContentPane().add(panel);
        setSize(770, 660);
   
//        JFrame frame = new JFrame();
//    	frame.setSize(1680, 1080);
//     frame.add(panel, BorderLayout.CENTER);
//        frame.setLayout(new BorderLayout());
//    	frame.setVisible(true);  
//       
       
        //panel.setExtendedState(JPanel.MAXIMIZED_BOTH); 
      
       /* JLabel title = new JLabel("monopoly");
        getContentPane().add(title);
        title setVisible(true);
        */
   
        //  getContentPane().add(panel);
       // JButton button =new JButton("press");
       // panel.add(button);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Line2D lin = new Line2D.Float(0, 600, 770, 600);
        Line2D lin1 = new Line2D.Float(0, 60, 770, 60);
        Line2D lin2 = new Line2D.Float(70, 0, 70, 660);
        Line2D lin3 = new Line2D.Float(700, 0, 700, 660);

        g2.draw(lin);
        g2.draw(lin1);
        g2.draw(lin2);
        g2.draw(lin3);

        
        Line2D linBOT0 = new Line2D.Float(140, 660, 140, 600);
        Line2D linBOT1 = new Line2D.Float(210, 660, 210, 600);
        Line2D linBOT2 = new Line2D.Float(280, 660, 280, 600);
        Line2D linBOT3 = new Line2D.Float(350, 660, 350, 600);
        Line2D linBOT4 = new Line2D.Float(420, 660, 420, 600);
        Line2D linBOT5 = new Line2D.Float(490, 660, 490, 600);
        Line2D linBOT6 = new Line2D.Float(560, 660, 560, 600);
        Line2D linBOT7 = new Line2D.Float(630, 660, 630, 600);
        Line2D linBOT8 = new Line2D.Float(700, 660, 700, 600);
        Line2D linBOT9 = new Line2D.Float(770, 660, 770, 600);
        g2.draw(linBOT0);
        g2.draw(linBOT1);
        g2.draw(linBOT2);
        g2.draw(linBOT3);
        g2.draw(linBOT4);
        g2.draw(linBOT5);
        g2.draw(linBOT6);
        g2.draw(linBOT7);
        g2.draw(linBOT8);
        g2.draw(linBOT9);
     
        
        Line2D linTOP0 = new Line2D.Float(140, 0, 140, 60);
        Line2D linTOP1 = new Line2D.Float(210, 0, 210, 60);
        Line2D linTOP2 = new Line2D.Float(280, 0, 280, 60);
        Line2D linTOP3 = new Line2D.Float(350, 0, 350, 60);
        Line2D linTOP4 = new Line2D.Float(420, 0, 420, 60);
        Line2D linTOP5 = new Line2D.Float(490, 0, 490, 60);
        Line2D linTOP6 = new Line2D.Float(560, 0, 560, 60);
        Line2D linTOP7 = new Line2D.Float(630, 0, 630, 60);
        Line2D linTOP8 = new Line2D.Float(700, 0, 700, 60);
        Line2D linTOP9 = new Line2D.Float(770, 0, 770, 60);
        g2.draw(linTOP0);
        g2.draw(linTOP1);
        g2.draw(linTOP2);
        g2.draw(linTOP3);
        g2.draw(linTOP4);
        g2.draw(linTOP5);
        g2.draw(linTOP6);
        g2.draw(linTOP7);
        g2.draw(linTOP8);
        g2.draw(linTOP9);
        
        
        
        Line2D linRIT0 = new Line2D.Float(700, 600, 770, 600);
        Line2D linRIT1 = new Line2D.Float(700, 540, 770, 540);
        Line2D linRIT2 = new Line2D.Float(700, 480, 770, 480);
        Line2D linRIT3 = new Line2D.Float(700, 420, 770, 420);
        Line2D linRIT4 = new Line2D.Float(700, 360, 770, 360);
        Line2D linRIT5 = new Line2D.Float(700, 300, 770, 300);
        Line2D linRIT6 = new Line2D.Float(700, 240, 770, 240);
        Line2D linRIT7 = new Line2D.Float(700, 180, 770, 180);
        Line2D linRIT8 = new Line2D.Float(700, 120, 770, 120);
        Line2D linRIT9 = new Line2D.Float(700, 60, 770, 60);
        g2.draw(linRIT0);
        g2.draw(linRIT1);
        g2.draw(linRIT2);
        g2.draw(linRIT3);
        g2.draw(linRIT4);
        g2.draw(linRIT5);
        g2.draw(linRIT6);
        g2.draw(linRIT7);
        g2.draw(linRIT8);
        g2.draw(linRIT9);
        
        Line2D linLEFT0 = new Line2D.Float(0, 600, 70, 600);
        Line2D linLEFT1 = new Line2D.Float(0, 540, 70, 540);
        Line2D linLEFT2 = new Line2D.Float(0, 480, 70, 480);
        Line2D linLEFT3 = new Line2D.Float(0, 420, 70, 420);
        Line2D linLEFT4 = new Line2D.Float(0, 360, 70, 360);
        Line2D linLEFT5 = new Line2D.Float(0, 300, 70, 300);
        Line2D linLEFT6 = new Line2D.Float(0, 240, 70, 240);
        Line2D linLEFT7 = new Line2D.Float(0, 180, 70, 180);
        Line2D linLEFT8 = new Line2D.Float(0, 120, 70, 120);
        Line2D linLEFT9 = new Line2D.Float(0, 60, 70, 60);
        g2.draw(linLEFT0);
        g2.draw(linLEFT1);
        g2.draw(linLEFT2);
        g2.draw(linLEFT3);
        g2.draw(linLEFT4);
        g2.draw(linLEFT5);
        g2.draw(linLEFT6);
        g2.draw(linLEFT7);
        g2.draw(linLEFT8);
        g2.draw(linLEFT9);
    }

    public static void main(String []args){
        Board s=new Board();
        s.setVisible(true);
    
       
    }
}