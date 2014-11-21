package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Map;
import Model.Bomberman;



public class DrawMap extends JFrame{
	private Image bombermanSprite;
	private Image HardBlock;
	private Image Brick;
	private Image Enemy;
	private Image Bomb;
	private Image Explode;
	private static DrawMap instance = new DrawMap();
	private static DrawMenu menuFrame;
	private int xVisible;
	private int previousPosOfBomberman;

	private Image dbImage;
	private Graphics dbg;

	private DrawMap(){
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
		makeFrame();
		setVisible(true);
	}

	public void makeFrame(){
		setSize(815,688);
		setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);

		setVisible(true);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				menuFrame.viewFrame(true);
				setVisible(false);
			}
		});

		createBufferStrategy(2);

	}

	public void drawStuff(){
		BufferStrategy bf = getBufferStrategy();
		Graphics g = null;

		try{
			g = bf.getDrawGraphics();
			super.paint(g);
			paintComponent(g);
		}finally{
			g.dispose();
		}
		bf.show();

		Toolkit.getDefaultToolkit().sync();
	}

	public void paintComponent(Graphics g){
		int width = Map.getWidth();
		int height = Map.getHeight();
		Bomberman bombman = Map.getBomberman();

		//scrolls the map
		if(bombman.getXval() > 350 && bombman.getXval() <= 1100) {
			if(Map.getBombermanState() == 1) {
				previousPosOfBomberman = bombman.getXval();
				xVisible = 350 - previousPosOfBomberman;
				g.translate(xVisible,0);
			} else {
				int leftView = xVisible + previousPosOfBomberman - bombman.getXval();
				g.translate(leftView,0);
			}
		} else if(xVisible == -750) {
			g.translate(-750, 0);
		} else {
			g.translate(0,0);
		}
		
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
		
		//draw explosions
		if(Map.getExplosion(0).isExploding()){
			for(int i = 0; i < 5; i++){
				int explosionX = Map.getExplosion(i).getXval();
				int explosionY = Map.getExplosion(i).getYval();
				g.drawImage(Explode, explosionX, explosionY, Map.getExplosion(i).getWidth(),Map.getExplosion(i).getHeight(),this);
			}
		}


		//draw Bomb
		if(Map.getBomb().getActive()){
			int bombx = Map.getBomb().getXval();
			int bomby = Map.getBomb().getYval();
			g.drawImage(Bomb, bombx, bomby, 50, 50, this);
		}
		//draw Bomberman
		int bombermanX = bombman.getXval();
		int bombermanY = bombman.getYval();
		int bombermanWidth = bombman.getWidth();
		int bombermanHeight = bombman.getHeight();
		g.drawImage(bombermanSprite, bombermanX, bombermanY, bombermanWidth, bombermanHeight, this);

	}

}