package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Model.Bomberman;


public class DrawMap extends JComponent{
	private JFrame gameFrame;
    private DrawingArea canvas;
    private Bomberman b;
	
	public DrawMap(){
		gameFrame = new JFrame("Bomberman");
		b = new Bomberman();
	}
	
	public static void main (String [] args){
		DrawMap test = new DrawMap();
		test.run();
	}
	
	public void run(){
        canvas = new DrawingArea();
        makeFrame();
        gameFrame.getContentPane().add(canvas);
        gameFrame.setVisible(true);
    }
	
	public void makeFrame(){
		gameFrame.setSize(751,673);
		gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		gameFrame.setLocation(dim.width/2-gameFrame.getSize().width/2, dim.height/2-gameFrame.getSize().height/2);
		
		gameFrame.getContentPane().add(new DrawMap());
		gameFrame.setVisible(true);
	}
	
	public class DrawingArea extends JPanel{ //what does this do
		public void paintComponent(Graphics g){
            super.paintComponent(g);
            int width = 50;
    		int height = 50;
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
            g.setColor(Color.ORANGE);
            g.fillOval(b.getXval(), b.getYval(), 50, 50); //change to rocket
        }
		
	}
	
	public Bomberman getBomberman(){
		return b;
	}
	
	public DrawingArea getCanvas(){
		return canvas;
	}
	
	public void update(){
		canvas.repaint();
	}
}