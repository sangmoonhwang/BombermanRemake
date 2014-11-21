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



public class DrawMap{
	private DrawGameObject gameFrame;
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
		gameFrame = new DrawGameObject();
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
		gameFrame.setVisible(true);
	}

	public void makeFrame(){
		gameFrame.setSize(800,648);
		gameFrame.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		gameFrame.setLocation(dim.width/2-gameFrame.getSize().width/2, dim.height/2-gameFrame.getSize().height/2);

		gameFrame.setVisible(true);

		gameFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				menuFrame.viewFrame(true);
				gameFrame.setVisible(false);
			}
		});

		gameFrame.createBufferStrategy(2);

	}

	public void draw(){
		BufferStrategy bf = gameFrame.getBufferStrategy();
		Graphics g = null;
		try{
			g = bf.getDrawGraphics();
			gameFrame.paintComponent(g);
		}finally{
			if(g!=null){
				g.dispose();
			}
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	public JFrame getFrame(){
		return gameFrame;
	}

}