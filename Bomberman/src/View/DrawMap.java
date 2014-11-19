package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Controller.Map;
import Model.Bomberman;
import Model.Indestructible;
import Model.Tile;



public class DrawMap extends JComponent{
	private JFrame gameFrame;
	private DrawingArea canvas;
	private Image bombermanSprite;
	private Image HardBlock;
	private Image Floor;
	private Image Brick;
	private Image Enemy;
	private Image Bomb;
	private static DrawMap instance = new DrawMap();
	private static DrawMenu menuFrame;
	
	private DrawMap(){
		gameFrame = new JFrame("Bomberman");
		menuFrame = DrawMenu.getInstance();
		bombermanSprite = Toolkit.getDefaultToolkit().getImage("Bomberman.gif");
		HardBlock = Toolkit.getDefaultToolkit().getImage("HardBlock.png");
		Floor = Toolkit.getDefaultToolkit().getImage("Tile.jpg");
		Brick = Toolkit.getDefaultToolkit().getImage("Brick.jpg");
		Enemy = Toolkit.getDefaultToolkit().getImage("Enemy.png");
		Bomb = Toolkit.getDefaultToolkit().getImage("Bomb.gif");

	}

	public static DrawMap getInstance(){
		return instance;
	}
	

	public void run(){
		canvas = new DrawingArea();
		makeFrame();
		gameFrame.getContentPane().add(canvas);
		gameFrame.setVisible(true);
	}

	public void makeFrame(){
		gameFrame.setSize(751,673);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		gameFrame.setLocation(dim.width/2-gameFrame.getSize().width/2, dim.height/2-gameFrame.getSize().height/2);

		gameFrame.getContentPane().add(new DrawMap());
		gameFrame.setVisible(true);
		
		gameFrame.addWindowListener(new WindowAdapter(){
		    public void windowClosing(WindowEvent e){
		    	menuFrame.viewFrame(true);
		        viewFrame(false);
		    }
		});
	}


	public class DrawingArea extends JPanel{ //what does this do
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			int width = Map.getWidth();
			int height = Map.getHeight();
			Bomberman bombman = Map.getBomberman();
			
			

			//draw tiles
			int i = 0;
			for(int x = 0; x<15; x++){
				for(int y = 0; y<13; y++){
					g.setColor(Color.BLACK);
					//g.drawRect(Map.getTile(i).getXval()*width,Map.getTile(i).getYval()*height,width,height);
					g.drawImage(Floor,Map.getTile(i).getXval()*width,Map.getTile(i).getYval()*height,width,height,this);
					i++;
				}
			}
			
			int k = 0;
			for(int x = 0; x<15; x++){
				for(int y = 0; y<13; y++){
					g.setColor(Color.BLACK);
					//g.drawRect(Map.getTile(i).getXval()*width,Map.getTile(i).getYval()*height,width,height);
					if(Map.getDestructible(k) != null){
						g.drawImage(Brick,Map.getDestructible(k).getXval()*width,Map.getDestructible(k).getYval()*height,width,height,this);
					}
					k++;
				}
			}


			//draw indestructible blocks on Map
			int j = 0;
			for(int x=0; x<15; x++){
				for(int y=0; y<13; y++){
					if( (x == 0 || y == 0 || y == 12 || x == 14) || (x%2 == 0 && y%2 == 0)){
						g.setColor(Color.GRAY);
						//g.fillRect(Map.getIndestructible(j).getXval()*width,Map.getIndestructible(j).getYval()*height,width,height);
						g.drawImage(HardBlock, Map.getIndestructible(j).getXval()*width,Map.getIndestructible(j).getYval()*height,width,height,this);
						j++;
					}
				}
			}
			
			for(int q = 0; q < 7; q++){
				g.setColor(Color.BLACK);
				g.drawImage(Enemy,(int)Map.getEnemy(q).getXval(),(int)Map.getEnemy(q).getYval(),width,height,this);
			}


			//draw Bomberman
			if(Map.getBomb().isActive()){
				g.drawImage(Bomb, Map.getBomb().getXval(), Map.getBomb().getYval(), 50,50,this);
			}
			g.drawImage(bombermanSprite,(int)bombman.getXval(),(int)bombman.getYval(),50,50,this);
		}

	}


	public DrawingArea getCanvas(){
		return canvas;
	}

	public void update(){
		canvas.repaint();
	}
	
	public void viewFrame(boolean b){
		gameFrame.setVisible(b);
	}
}