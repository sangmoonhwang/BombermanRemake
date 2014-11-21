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

import Controller.Map;
import Model.Bomberman;



public class DrawMap extends JComponent{
	private JFrame gameFrame;
	private DrawingArea canvas;
	private Image bombermanSprite;
	private Image HardBlock;
	private Image Brick;
	private Image Enemy;
	private Image Bomb;
	private Image Explode;
	private static DrawMap instance = new DrawMap();
	private static DrawMenu menuFrame;
	
	private DrawMap(){
		gameFrame = new JFrame("Bomberman");
		menuFrame = DrawMenu.getInstance();
		bombermanSprite = Toolkit.getDefaultToolkit().getImage("Bomberman.gif");
		HardBlock = Toolkit.getDefaultToolkit().getImage("HardBlock.png");
		Brick = Toolkit.getDefaultToolkit().getImage("Brick.jpg");
		Enemy = Toolkit.getDefaultToolkit().getImage("Enemy.png");
		Bomb = Toolkit.getDefaultToolkit().getImage("Bomb.gif");
		Explode = Toolkit.getDefaultToolkit().getImage("Explosion.jpg");

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
		gameFrame.setSize(1251,673);
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
			

//			if(Map.getExplosion(0).isExploding()){
//				for(int i =0; i<4; i++){
//					g.drawImage(Explosion, Map.getExplosion(i).getXval(), Map.getExplosion(i).getYval(), Map.getExplosion(i).getWidth(),Map.getExplosion(i).getHeight(),this);
//				}
//			}
			
			
			if(Map.getExplosion(0).isExploding()){
				for(int i = 0; i < 5; i++){
					int explosionX = Map.getExplosion(i).getXval();
					int explosionY = Map.getExplosion(i).getYval();
					g.drawImage(Explode, explosionX, explosionY, Map.getExplosion(i).getWidth(),Map.getExplosion(i).getHeight(),this);
				}
			}
			
			
			/*if(Map.getExplosion().isExploding()){
				g.drawImage(Explode, Map.getBomb().getXval(), Map.getBomb().getYval(), Map.getExplosion().getWidth(),Map.getExplosion().getHeight(),this);
				g.drawImage(Explode, Map.getBomb().getXval()+50, Map.getBomb().getYval(), Map.getExplosion().getWidth(),Map.getExplosion().getHeight(),this);
				g.drawImage(Explode, Map.getBomb().getXval()-50, Map.getBomb().getYval(), Map.getExplosion().getWidth(),Map.getExplosion().getHeight(),this);
				g.drawImage(Explode, Map.getBomb().getXval(), Map.getBomb().getYval()+50, Map.getExplosion().getWidth(),Map.getExplosion().getHeight(),this);
				g.drawImage(Explode, Map.getBomb().getXval(), Map.getBomb().getYval()-50, Map.getExplosion().getWidth(),Map.getExplosion().getHeight(),this);
			}*/
			

			//draw destructible blocks
			for (int i = 0; i < Map.getDestructible().size() - 1; i++){
				int brickx = Map.getDestructible().get(i).getXval();
				int bricky = Map.getDestructible().get(i).getYval();
				g.drawImage(Brick, brickx, bricky, 50, 50, this);

			}

			//draw indestructible blocks
			for (int i = 0; i < Map.getIndestructible().size() - 1; i++){
				int indestructiblex = Map.getIndestructible().get(i).getXval();
				int indestructibley = Map.getIndestructible().get(i).getYval();
				g.setColor(Color.GRAY);
				g.drawImage(HardBlock, indestructiblex, indestructibley, 50, 50, this);
			}
			
			//draw enemies
			for(int i = 0; i < Map.getEnemy().size() - 1; i++){
				int enemyx = Map.getEnemy().get(i).getXval();
				int enemyy = Map.getEnemy().get(i).getYval();
				g.setColor(Color.BLACK);
				g.drawImage(Enemy, enemyx, enemyy, 50, 50, this);
			}


			//draw Bomb
			if(Map.getBomb().getActive()){
				int bombx = Map.getBomb().getXval();
				int bomby = Map.getBomb().getYval();
				g.drawImage(Bomb, bombx, bomby, 50, 50, this);
			}
			
			//draw Bomberman
			g.drawImage(bombermanSprite, (int)bombman.getXval(), (int)bombman.getYval(), 50, 50, this);
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