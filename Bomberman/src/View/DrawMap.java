package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;



import Model.Bomberman;
import Model.Indestructible;
import Model.Tile;



public class DrawMap extends JComponent{
	private JFrame gameFrame;
	private DrawingArea canvas;
	private Bomberman bombman;
	public Indestructible[] indestructibles;
	private Tile[] tiles;
	private Image bombermanSprite;
	private static DrawMenu d;

	public DrawMap(){
		gameFrame = new JFrame("Bomberman");

		//create bomberman
		bombman = new Bomberman();
		bombermanSprite = Toolkit.getDefaultToolkit().getImage("Bomberman.gif");

		//create an array of 101 indestructible blocks
		indestructibles = new Indestructible[101];
		for (int i = 0; i < 100; i++){
			indestructibles[i] = new Indestructible();
		}
		tiles = new Tile[195];
		for (int i = 0; i < 194; i++){
			tiles[i] = new Tile();
		}

	}

	public DrawMap(JFrame main) {
		gameFrame = main;

		bombman = new Bomberman();
		bombermanSprite = Toolkit.getDefaultToolkit().getImage("Bomberman.gif");

		//create an array of 101 indestructible blocks
		indestructibles = new Indestructible[101];
		for (int i = 0; i < 100; i++){
			indestructibles[i] = new Indestructible();
		}
		tiles = new Tile[195];
		for (int i = 0; i < 194; i++){
			tiles[i] = new Tile();
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
		//gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		gameFrame.setLocation(dim.width/2-gameFrame.getSize().width/2, dim.height/2-gameFrame.getSize().height/2);

		gameFrame.getContentPane().add(new DrawMap());
		gameFrame.setVisible(true);
		gameFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent){
				d = new DrawMenu();
				d.run();
			}
		});
	}


	public class DrawingArea extends JPanel{ //what does this do
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			int width = 50;
			int height = 50;

			//draw tiles
			int i = 0;
			for(int x = 0; x<15; x++){
				for(int y = 0; y<13; y++){
					tiles[i].setYval(y);
					tiles[i].setXval(x);
					g.setColor(Color.BLACK);
					g.drawRect(tiles[i].getXval()*width,tiles[i].getYval()*height,width,height);
				}
			}


			//draw indestructible blocks on Map
			int j = 0;
			for(int x=0; x<15; x++){
				for(int y=0; y<13; y++){
					if( (x == 0 || y == 0 || y == 12 || x == 14) || (x%2 == 0 && y%2 == 0)){
						indestructibles[j].setYval(y);
						indestructibles[j].setXval(x);
						g.setColor(Color.GRAY);
						g.fillRect(indestructibles[j].getXval()*width,indestructibles[j].getYval()*height,width,height);
						j++;
					}
				}
			}


			//draw Bomberman
			g.setColor(Color.ORANGE);
			//g.fillRect((int)bombman.getXval(), (int)bombman.getYval(), 42, 42); //change to rocket
			g.drawImage(bombermanSprite,(int)bombman.getXval(),(int)bombman.getYval(),50,50,this);
		}

	}

	public Bomberman getBomberman(){
		return bombman;
	}

	public Tile getTile(){
		return tiles[0];
	}

	public Indestructible getIndestructible(int i){
		return indestructibles[i];
	}

	public DrawingArea getCanvas(){
		return canvas;
	}

	public void update(){
		canvas.repaint();
	}

	//public Indestructible[] getindestructible() {
	// // TODO Auto-generated method stub
	// return this.indestructibles;
	//}
}