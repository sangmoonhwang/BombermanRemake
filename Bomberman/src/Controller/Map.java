// next step http://stackoverflow.com/questions/7537570/eliminating-initial-keypress-delay


package Controller;

import java.awt.Color;
import java.awt.event.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import Model.Bomb;
import Model.Bomberman;
import Model.Destructible;
import Model.Indestructible;
import Model.Movable;
import Model.Tile;
import Model.Enemies.Enemy;
import View.DrawMap;

public class Map implements KeyListener, FocusListener{
	public JFrame main;
	private static DrawMap d;
	private static Bomberman bombman;
	private static Indestructible[] indestructibles;
	private static Tile[] tiles;
	private static Destructible[] bricks;
	private static Enemy[] enemies;
	private static Bomb bomb;
	private float xVel = 0;
	private float yVel = 0;
	private static int width;
	private static int height;
	private Timer explodeTimer;
	static boolean running = false;
	private CollissionDetection detect;
	
	public Map(){
		
		detect = new CollissionDetection();
		width = 50;
		height = 50;
		bombman = new Bomberman();
		
		bomb = new Bomb();
		
		enemies = new Enemy[8];
		for(int i = 0; i < 7; i++){

			Random r = new Random();
			int x = r.nextInt(13)+1;
			int y = r.nextInt(13)+1;
			enemies[i] = new Enemy();
			enemies[i].setXval(50*x);
			enemies[i].setYval(50*y);
		}
		
		bricks = new Destructible[201];
		for(int i =0; i < 200; i++){
			bricks[i] = new Destructible();
			Random r = new Random();
			int ans = r.nextInt(100) + 1;
			if(ans < 32){
				bricks[i].setExists(true);
			} else {
				bricks[i].setExists(false);
			}
		}
		
		indestructibles = new Indestructible[101];
		for (int i = 0; i < 100; i++){
			indestructibles[i] = new Indestructible();
		}
		
		tiles = new Tile[401];
		for (int i = 0; i < 400; i++){
			tiles[i] = new Tile();
		}
		
		
		int i = 0;
		for(int x = 0; x<15; x++){
			for(int y = 0; y<13; y++){
				tiles[i].setYval(y);
				tiles[i].setXval(x);
				i++;
			}
		}
		
		int k = 0;
		for(int x = 0; x<15; x++){
			for(int y = 0; y<13; y++){
				if(bricks[k] != null){
					bricks[k].setYval(y);
					bricks[k].setXval(x);
				}
				k++;
			}
		}

		int j = 0;
		for(int x=0; x<15; x++){
			for(int y=0; y<13; y++){
				if( (x == 0 || y == 0 || y == 12 || x == 14) || (x%2 == 0 && y%2 == 0)){
					indestructibles[j].setYval(y);
					indestructibles[j].setXval(x);
					j++;
				}
			}
		}
		
		d = DrawMap.getInstance();
		running = true;
		this.run();
	}


	public Map(JFrame mainFrame) {
		main = mainFrame;
		d = DrawMap.getInstance();
		running = true;
		this.run();
	}

	public void run(){
		d.run();
		d.getCanvas().addFocusListener(this);
		d.getCanvas().addKeyListener(this);
		d.getCanvas().requestFocus();

		long start = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		while(running) {
			long now = System.nanoTime();
			delta += (now - start) / ns;
			start = now;

			if(delta >= 1) {
				tick();
				delta--;
			}
			d.update();

		}
	}

	//react to keyPress by moving Bomberman
	public void keyPressed ( KeyEvent e ){
		int value = e.getKeyCode();
		if (value == KeyEvent.VK_DOWN && value !=KeyEvent.VK_UP){
			setVelY(2);
		}
		else if(value != KeyEvent.VK_DOWN && value ==KeyEvent.VK_UP){
			setVelY(-2);
		}
		else if(value == KeyEvent.VK_LEFT && value !=KeyEvent.VK_RIGHT){
			setVelX(-2);
		}
		else if(value == KeyEvent.VK_RIGHT && value !=KeyEvent.VK_LEFT){
			setVelX(2);
		}
		else if(value == KeyEvent.VK_SPACE){
			bomb.setXval((int)bombman.getXval());
			bomb.setYval((int)bombman.getYval());
			bomb.setActive(true);
			int delay = 2000;
			explodeTimer = new Timer();
			explodeTimer.schedule(new TimerTask(){
				public void run(){
					bomb.setActive(false);
				};
			},2000);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	//stop moving when key is released
	public void keyReleased(KeyEvent e) {
		int value = e.getKeyCode();
		if (value == KeyEvent.VK_DOWN){
			if(yVel == 2)
				setVelY(0);
		}
		else if(value ==KeyEvent.VK_UP){
			if(yVel == -2)
				setVelY(0);
		}
		else if(value == KeyEvent.VK_LEFT){
			if(xVel == -2)
				setVelX(0);
		}
		else if(value == KeyEvent.VK_RIGHT){
			if(xVel == 2)
				setVelX(0);
		}
		else{
			setVelX(0);
			setVelY(0);
		}
	}    

	public void focusGained(FocusEvent e) {
	}

	public void focusLost(FocusEvent e) {
	}

	public void tick() {
		float bombermanXtemp = xVel;
		float bombermanYtemp = yVel;
		
//		float enemyX = 2;
//		float enemyY = 2;
		
		
		for(int i = 0; i < 100; i++){
			if(!detect.emptyLeft(bombman, indestructibles[i]) && xVel <= 0){
				bombermanXtemp = 0;
			}
			if(!detect.emptyRight(bombman, indestructibles[i]) && xVel >= 0){
				bombermanXtemp = 0;
			}
			if(!detect.emptyAbove(bombman, indestructibles[i]) && yVel <= 0){
				bombermanYtemp = 0;
			}
			if(!detect.emptyBelow(bombman, indestructibles[i]) && yVel >= 0){
				bombermanYtemp = 0;
			}
			/*for(int j=0;j<enemies.length -1;j++){
				if(tiles[0].emptyLeft(enemies[j], indestructibles[i]) && xVel <= 0){
					enemyX = -enemyX;
				}
				if(tiles[0].emptyRight(enemies[j], indestructibles[i]) && xVel >= 0){
					enemyX = -enemyX;
				}
				if(tiles[0].emptyAbove(enemies[j], indestructibles[i]) && yVel <= 0){
					enemyY = -enemyY;
				}
				if(tiles[0].emptyBelow(enemies[j], indestructibles[i]) && yVel >= 0){
					enemyY = -enemyY;
				}
			}*/
		}
		bombman.incrementXval(bombermanXtemp);
		bombman.incrementYval(bombermanYtemp);
		
		/*for(int j=0;j<enemies.length -1;j++){
			enemies[j].incrementXval(2);
			enemies[j].incrementYval(2);
		}*/
		
		for (int i=0; i<enemies.length -1; i++){
			if(detect.collisionDetection(bombman, enemies[i])){
				if(detect.collisionDetection(bombman, enemies[i])){
					bombman.incrementXval(-xVel);
					bombman.incrementYval(-yVel);
				}
			}
		}
	
		
	}

	public void setVelX(float xVel) {
		this.xVel = xVel;
	}

	public void setVelY(float yVel) {
		this.yVel = yVel;
	}
	
	public static Tile getTile(int i){
		return tiles[i];
	}
	
	public static int getWidth(){
		return width;
	}
	public static int getHeight(){
		return height;
	}
	
	public static Indestructible getIndestructible(int i){
		return indestructibles[i];
	}
	
	public static Destructible getDestructible(int i){
		if(bricks[i].getExists()){
			return bricks[i];
		} else {
			return null;
		}
	}
	
	public static Enemy getEnemy(int i){
		return enemies[i];
	}
	
	public static Bomberman getBomberman(){
		return bombman;
	}
	public static Bomb getBomb(){
		return bomb;
	}
	
}
