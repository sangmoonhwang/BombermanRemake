package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Map;
import Model.Bomberman;

public class DrawGameObject extends JPanel{

	private Image bombermanSprite;
	private Image HardBlock;
	private Image Brick;
	private Image Enemy;
	private Image Bomb;
	private Image Explode;
	private Image Exit;
	private Image ExtraBombs;
	private Image bombPass;
	private Image detonator;
	private Image flamePass;
	private Image flames;
	private Image mystery;
	private Image speed;
	private Image wallPass;
	private int previousPosOfBomberman;
	private int xVisible;
		
	public DrawGameObject(){

		bombermanSprite = Toolkit.getDefaultToolkit().getImage("Bomberman.gif");
		HardBlock = Toolkit.getDefaultToolkit().getImage("HardBlock.png");
		Brick = Toolkit.getDefaultToolkit().getImage("Brick.jpg");
		Enemy = Toolkit.getDefaultToolkit().getImage("Enemy.png");
		Bomb = Toolkit.getDefaultToolkit().getImage("Bomb.gif");
		Explode = Toolkit.getDefaultToolkit().getImage("Explosion.jpg");
		Exit = Toolkit.getDefaultToolkit().getImage("Exit.jpg");
		ExtraBombs = Toolkit.getDefaultToolkit().getImage("Bomberman_Bombs.png");
		bombPass = Toolkit.getDefaultToolkit().getImage("Bomberman_Bombpass.png");
		detonator = Toolkit.getDefaultToolkit().getImage("Bomberman_Detonator.png");
		flamePass = Toolkit.getDefaultToolkit().getImage("Bomberman_Flamepass.png");
		flames = Toolkit.getDefaultToolkit().getImage("Bomberman_Flames.png");
		mystery = Toolkit.getDefaultToolkit().getImage("Bomberman_Mystery.png");
		speed = Toolkit.getDefaultToolkit().getImage("Bomberman_Speed.png");
		wallPass = Toolkit.getDefaultToolkit().getImage("Bomberman_Wallpass.png");
		
		previousPosOfBomberman = 0;
		xVisible = 0;
	}
	
	public void paintComponent(Graphics g){
		Bomberman bombman = Map.getBomberman();

		super.paintComponent(g);
		
		
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
		} else if(xVisible == -750 && bombman.getXval() > 1100) {
			g.translate(-750, 0);
		} else {
			g.translate(0,0);
		}
		
		
		//draw explosions
		if(Map.getExplosion(0).isExploding()){
			for(int i = 0; i < 5; i++){
				int explosionX = Map.getExplosion(i).getXval();
				int explosionY = Map.getExplosion(i).getYval();
				if(i == 1){
					g.drawImage(Explode, explosionX, explosionY, 50*Bomberman.flames,50,this);
				}
				else if(i == 2){
					g.drawImage(Explode, explosionX, explosionY, -50*Bomberman.flames,50,this);
				}
				else if(i == 3){
					g.drawImage(Explode, explosionX, explosionY, 50,50*bombman.flames,this);
				}
				else if(i == 3 || i ==4){
					g.drawImage(Explode, explosionX, explosionY, 50,-50*bombman.flames,this);
				}
				else{
					g.drawImage(Explode, explosionX, explosionY, 50,50,this);
				}
			}
		}
		
		//draw powerUp
		int xPowerup = Map.getPowerup().getXval();
		int yPowerup = Map.getPowerup().getYval();
		String powerUp = Map.getPowerup().getIdentity();
		g.drawImage(getImage(powerUp),xPowerup, yPowerup,50,50,this);
		
		
/*		//draw upBombs
		int upBombX = Map.getUpBombs().getXval();
		int upBombY = Map.getUpBombs().getYval();
		g.drawImage(ExtraBombs,upBombX, upBombY,50,50,this);
*/		
		//draw Door
		int doorx = Map.getDoor().getXval();
		int doory = Map.getDoor().getYval();
		g.drawImage(Exit, doorx, doory, 50,50,this);
		
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
		for(int i = 0; i < Map.getEnemy().size(); i++){
			int enemyx = Map.getEnemy().get(i).getXval();
			int enemyy = Map.getEnemy().get(i).getYval();
			g.setColor(Color.BLACK);
			g.drawImage(Enemy, enemyx, enemyy, 50, 50, this);
		}

		for(int i = 0; i < Map.getActiveBombs().size(); i++){
			if(Map.getActiveBombs().get(i).getActive()){
				int bombx = Map.getActiveBombs().get(i).getXval();
				int bomby = Map.getActiveBombs().get(i).getYval();
				g.drawImage(Bomb, bombx, bomby, 50, 50, this);
			}
		}
		
		//draw Bomberman
		int bombermanX = bombman.getXval();
		int bombermanY = bombman.getYval();
		int bombermanWidth = bombman.getWidth();
		int bombermanHeight = bombman.getHeight();
		g.drawImage(bombermanSprite, bombermanX, bombermanY, bombermanWidth, bombermanHeight, this);

	}

	public Image getImage(String name) {
		if(name.equals("Bombpass")) {
			return bombPass;
		} else if(name.equals("Detonator")) {
			return detonator;
		} else if(name.equals("Flamepass")) {
			return flamePass;
		} else if(name.equals("Flames")) {
			return flames;
		} else if(name.equals("Mystery")) {
			return mystery;
		} else if(name.equals("Speed")) {
			return speed;
		} else if(name.equals("UpBombs")) {
			return ExtraBombs;
		} else {
			return wallPass;
		}
	}
}
