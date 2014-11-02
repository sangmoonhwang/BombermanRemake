package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class DrawMap extends JComponent{
 private JFrame gameFrame;
 
 public DrawMap(){
  gameFrame = new JFrame("Bomberman");
 }

 public void makeFrame(){
  gameFrame.setSize(676,608);
  gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
  gameFrame.setLocation(dim.width/2-gameFrame.getSize().width/2, dim.height/2-gameFrame.getSize().height/2);
  
  gameFrame.getContentPane().add(new DrawMap());
  gameFrame.setVisible(true);
 }
 
 public void paint(Graphics g){  
  int width = 45;
  int height = 45;
  for(int x=0; x<15; x++){
      for(int y=0; y<13; y++){
          if( (x == 0 || y == 0 || y == 12) || (x%2 == 0 && y%2 == 0)){
           g.setColor(Color.GRAY);
           g.fillRect(x*width,y*height,width,height);
          }
          g.setColor(Color.BLACK);
          g.drawRect(x*width,y*height,width,height);
      }
  }
 }
}
