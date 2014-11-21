// next step http://stackoverflow.com/questions/7537570/eliminating-initial-keypress-delay


package Controller;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import Model.Bomb;
import Model.Bomberman;
import Model.Destructible;
import Model.Explosion;
import Model.Indestructible;
import Model.Enemies.Balloom;
import Model.Enemies.Enemy;
import View.DrawMap;
import View.DrawMenu;

public class Map implements KeyListener, FocusListener{
	public JFrame main;
	private static DrawMap d;
	private static Bomberman bombman;
	private static ArrayList<Indestructible> indestructibles;
	private static ArrayList<Destructible> bricks;
	private static ArrayList<Enemy> enemies;
	private static Bomb bomb;
	private static Explosion[] explosions;
	private int xVel = 0;
	private int yVel = 0;
	private static int width;
	private static int height;
	private Timer explodeTimer;
	private Timer unexplodeTimer;
	private Timer gameTimer;
	static boolean running = false;
	private CollissionDetection detect;
	private SpawnGameObjects spawn;
	private static int bombermanState;

	public Map(){

		//attributes
		width = 50;
		height = 50;

		//new objects
		detect = new CollissionDetection();
		bombman = new Bomberman();
		bomb = new Bomb();
		spawn = new SpawnGameObjects();
		explosions = new Explosion[9];
		for(int i = 0; i<8; i++){
			explosions[i] = new Explosion();
		}

		//spawn gameObjects
		indestructibles = spawn.spawnIndestructibles();
		bricks = spawn.spawnBricks();
		enemies = spawn.spawnEnemies();


		d = DrawMap.getInstance();
		running = true;
		gameTimer = new Timer();
		gameTimer.schedule(new TimerTask(){
			public void run(){
				//change
				System.out.println("Times up!");
			};
		},200000);
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
		d.getFrame().addFocusListener(this);
		d.getFrame().addKeyListener(this);
		d.getFrame().requestFocus();

		long start = System.nanoTime();
		long start2 = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;

		while(running) {
			long now = System.nanoTime();
			long now2 = System.nanoTime();
			if((now - start)/ns >= 1) {
				tick();
				start = now;
				d.draw();
			}
			
			if((now2 - start2)/ns >=10){
				tick2();
				start2 = now2;
				d.draw();
			}

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
			bombermanState = 2;
			setVelX(-2);
		}
		else if(value == KeyEvent.VK_RIGHT && value !=KeyEvent.VK_LEFT){
			bombermanState = 1;
			setVelX(2);
		}
		if(value == KeyEvent.VK_ESCAPE){
			d.getFrame().dispose();
			DrawMenu.getInstance().viewFrame(true);
		}
		if(value == KeyEvent.VK_SPACE){
			if(bombman.getavailableBombs() != 0){
				bombman.giveBombs(-1);
				int tilex = (int)bombman.getXval() + (int)(0.5*bombman.getWidth());
				int tiley = (int)bombman.getYval() + (int)(0.5*bombman.getHeight());
				tilex = (tilex/50) * 50;
				tiley = (tiley/50) * 50;

				bomb.setXval(tilex);
				bomb.setYval(tiley);
				explosions[0].setXval(tilex);
				explosions[0].setYval(tiley);
				explosions[1].setXval(tilex+50);
				explosions[1].setYval(tiley);
				explosions[2].setXval(tilex-50);
				explosions[2].setYval(tiley);
				explosions[3].setXval(tilex);
				explosions[3].setYval(tiley+50);
				explosions[4].setXval(tilex);
				explosions[4].setYval(tiley-50);
				bomb.setActive(true);
				int delay = 2000;
				explodeTimer = new Timer();
				explodeTimer.schedule(new TimerTask(){
					public void run(){
						bomb.setActive(false);
						for(int i = 0; i<4;i++){
							explosions[i].setExploding(true);
						}
						unexplodeTimer = new Timer();
						unexplodeTimer.schedule(new TimerTask(){
							public void run(){
								for(int i = 0; i < 4; i++){
									explosions[i].setExploding(false);
								}
								bombman.giveBombs(1);
							}
						},500);
					};
				},delay);
			}
		}
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


	public void tick() {
		int bombermanXtemp = xVel;
		int bombermanYtemp = yVel;

		//bomberman and indestructible collision
		for(int i = 0; i < indestructibles.size(); i++){
			if(!detect.emptyLeft(bombman, indestructibles.get(i)) && xVel <= 0){
				bombermanXtemp = 0;
			}
			if(!detect.emptyRight(bombman, indestructibles.get(i)) && xVel >= 0){
				bombermanXtemp = 0;
			}
		}

		bombman.incrementXval(bombermanXtemp);

		for(int i = 0; i < indestructibles.size(); i++){
			if(!detect.emptyAbove(bombman, indestructibles.get(i)) && yVel <= 0){
				bombermanYtemp = 0;
			}
			if(!detect.emptyBelow(bombman, indestructibles.get(i)) && yVel >= 0){
				bombermanYtemp = 0;
			}
		}
		
		//bomberman collision detection with bricks
		for(int i=0; i<bricks.size(); i++){
			if(!detect.emptyLeft(bombman, bricks.get(i)) && xVel <= 0){
				bombermanXtemp = 0;
			}
			if(!detect.emptyRight(bombman, bricks.get(i)) && xVel >= 0){
				bombermanXtemp = 0;
			}
			if(!detect.emptyAbove(bombman, bricks.get(i)) && yVel <= 0){
				bombermanYtemp = 0;
			}
			if(!detect.emptyBelow(bombman, bricks.get(i)) && yVel >= 0){
				bombermanYtemp = 0;
			}
		}
		bombman.incrementYval(bombermanYtemp);
		
		//Bomberman and Enemy Collisions
		for (int i=0; i<enemies.size(); i++){
			if(detect.collisionDetection(bombman, enemies.get(i))){
				if(detect.collisionDetection(bombman, enemies.get(i))){
					bombman.incrementXval(-xVel);
					bombman.incrementYval(-yVel);
					System.out.println("You died!!");
				}
			}
		}


		if(explosions[0].isExploding()){
			for(int i = 0; i< 5; i++){
				if(detect.collisionDetection(bombman, explosions[i])){
					System.out.println("You died.");
				}
				for(int j = 0; j < enemies.size(); j++){
					if(detect.collisionDetection(enemies.get(j), explosions[i])){
						enemies.remove(j);
					}
				}
				for(int k = 0; k < bricks.size();k++){
					if(detect.collisionDetection(explosions[i], bricks.get(k))){
						bricks.remove(k);
					}
				}
			}
		}
	}
	
	public void tick2() {
		//collision check for enemy with indestructibles and bricks
		for(int k=0;k<enemies.size();k++) {
			for(int i = 0; i < indestructibles.size(); i++) {
				for(int j=0; j<bricks.size(); j++) {

					Balloom enemy = enemies.get(k).getBalloomInstance();

					switch(enemy.getState()) {

					case 0:
						if(detect.emptyRight(enemies.get(k), bricks.get(j)) && detect.emptyRight(enemies.get(k), indestructibles.get(i))){
							enemy.move(enemies.get(k));
						} else {
							enemy.changeDirection();
							if(detect.emptyLeft(enemies.get(k), bricks.get(j)) && detect.emptyLeft(enemies.get(k), indestructibles.get(i))) {
								enemy.move(enemies.get(k));
							} else {
								enemies.get(k).incrementXval(0);
							}
						}
						break;

					case 1:
						if(detect.emptyLeft(enemies.get(k), bricks.get(j)) && detect.emptyLeft(enemies.get(k), indestructibles.get(i))){
							enemy.move(enemies.get(k));
						} else {
							enemy.changeDirection();
							if(detect.emptyRight(enemies.get(k), bricks.get(j)) && detect.emptyRight(enemies.get(k), indestructibles.get(i))) {
								enemy.move(enemies.get(k));
							} else {
								enemies.get(k).incrementXval(0);
							}
						}
						break;

					case 2:
						if(detect.emptyBelow(enemies.get(k), bricks.get(j)) && detect.emptyBelow(enemies.get(k), indestructibles.get(i))){
							enemy.move(enemies.get(k));
						} else {
							enemy.changeDirection();
							if(detect.emptyAbove(enemies.get(k), bricks.get(j)) && detect.emptyAbove(enemies.get(k), indestructibles.get(i))) {
								enemy.move(enemies.get(k));
							} else {
								enemies.get(k).incrementYval(0);
							}
						}
						break;

					case 3:
						if(detect.emptyAbove(enemies.get(k), bricks.get(j)) && detect.emptyAbove(enemies.get(k), indestructibles.get(i))){
							enemy.move(enemies.get(k));
						} else {
							enemy.changeDirection();
							if(detect.emptyBelow(enemies.get(k), bricks.get(j)) && detect.emptyBelow(enemies.get(k), indestructibles.get(i))) {
								enemy.move(enemies.get(k));
							} else {
								enemies.get(k).incrementYval(0);
							}
						}
						break;		
					}
				}
			}
		}

	}

	//setters
	public void setVelX(int xVel) {
		this.xVel = xVel;
	}
	public void setVelY(int yVel) {
		this.yVel = yVel;
	}

	//getters
	public static int getWidth(){
		return width;
	}
	public static int getHeight(){
		return height;
	}
	public static ArrayList<Indestructible> getIndestructible(){
		return indestructibles;
	}
	public static ArrayList<Destructible> getDestructible(){
		return bricks;
	}
	public static ArrayList<Enemy> getEnemy(){
		return enemies;
	}
	public static Bomberman getBomberman(){
		return bombman;
	}
	public static Bomb getBomb(){
		return bomb;
	}
	public static Explosion getExplosion(int i){
		return explosions[i];
	}
	public static int getBombermanState() {
		return bombermanState;
	}

	//empty methods
	public void keyTyped(KeyEvent e) {
	}
	public void focusGained(FocusEvent e) {
	}
	public void focusLost(FocusEvent e) {
	}

}
