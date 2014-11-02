package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Model.Bomberman;
import Model.Indestructible;


public class DrawMap extends JComponent{
	private JFrame gameFrame;
	private DrawingArea canvas;
	private Bomberman bombman;
	public Indestructible[] indestructibles;
	private Image bombermanSprite;

	public DrawMap(){
		gameFrame = new JFrame("Bomberman");
		bombman = new Bomberman();
		bombermanSprite = Toolkit.getDefaultToolkit().getImage("Bomberman.GIF");
		indestructibles = new Indestructible[101];
		for (int i = 0; i < 100; i++){
			indestructibles[i] = new Indestructible();
		}
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
					/*if( (x == 0 || y == 0 || y == 12) || (x%2 == 0 && y%2 == 0)){
               g.setColor(Color.GRAY);
               g.fillRect(x*width,y*height,width,height);
              }*/
					g.setColor(Color.BLACK);
					g.drawRect(x*width,y*height,width,height);
				}
			}
			int i = 0;
			for(int x=0; x<15; x++){
				for(int y=0; y<13; y++){
					if( (x == 0 || y == 0 || y == 12) || (x%2 == 0 && y%2 == 0)){
						indestructibles[i].setYval(y);
						indestructibles[i].setXval(x);
						g.setColor(Color.GRAY);
						g.fillRect(indestructibles[i].getXval()*width,indestructibles[i].getYval()*height,width,height);
						i++;
					}
				}
			}



			g.setColor(Color.ORANGE);
			//g.fillRect((int)bombman.getXval(), (int)bombman.getYval(), 40, 50); //change to rocket
			g.drawImage(bombermanSprite,(int)bombman.getXval(),(int)bombman.getYval(),50,50,this);
		}

	}

	public Bomberman getBomberman(){
		return bombman;
	}

	public DrawingArea getCanvas(){
		return canvas;
	}

	public void update(){
		canvas.repaint();
	}

	//public Indestructible[] getindestructible() {
	//	// TODO Auto-generated method stub
	//	return this.indestructibles;
	//}
}