// next step http://stackoverflow.com/questions/7537570/eliminating-initial-keypress-delay


package Controller;

import java.awt.event.*;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import Model.Bomb;
import Model.Bomberman;
import Model.Destructible;
import Model.Door;
import Model.Explosion;
import Model.Indestructible;
import Model.User;
import Model.Enemies.Enemy;
import Model.PowerUps.Powerup;
import Model.PowerUps.UpBombs;
import View.DrawMap;
import View.DrawMenu;

public class Map implements KeyListener, FocusListener{
	public JFrame main;
	private static DrawMap d;
	private static Bomberman bombman;
	private static ArrayList<Indestructible> indestructibles;
	private static ArrayList<Destructible> bricks;
	private static ArrayList<Enemy> enemies;
	private static ArrayList<Bomb> bombs;
	private static ArrayList<Bomb> activeBombs;
	private static Explosion[] explosions;
	private static Powerup power;
	private static Door door;
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
	//	private static UpBombs upbombs;
	boolean leftFree = true;
	boolean rightFree = true;
	boolean aboveFree = true;
	boolean belowFree = true;
	boolean leftFreeBrick = true;
	boolean rightFreeBrick = true;
	boolean aboveFreeBrick = true;
	boolean belowFreeBrick = true;

	public Map(int level){

		//attributes
		width = 50;
		height = 50;

		//new objects
		detect = new CollissionDetection();
		bombman = new Bomberman();
		bombs = new ArrayList<Bomb>();
		activeBombs = new ArrayList<Bomb>();
		bombs.add(new Bomb());
		bombs.add(new Bomb());
		spawn = new SpawnGameObjects(level);
		explosions = new Explosion[9];
		for(int i = 0; i<8; i++){
			explosions[i] = new Explosion();
		}

		//spawn gameObjects
		indestructibles = spawn.spawnIndestructibles();
		bricks = spawn.spawnBricks();
		enemies = spawn.spawnEnemies();
		power = spawn.spawnPowerup();
		door = spawn.spawnDoor();



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
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;

		while(running) {
			long now = System.nanoTime();
			if((now - start)/ns >= 1) {
				tick();
				tick2();
				start = now;
				d.draw();
			}
		}
	}

	//react to keyPress by moving Bomberman
	public void keyPressed ( KeyEvent e ){
		int value = e.getKeyCode();
		if (value == KeyEvent.VK_DOWN && value !=KeyEvent.VK_UP){
			setVelY(bombman.speed);//2
		}
		else if(value != KeyEvent.VK_DOWN && value ==KeyEvent.VK_UP){
			setVelY(-bombman.speed);//-2
		}
		else if(value == KeyEvent.VK_LEFT && value !=KeyEvent.VK_RIGHT){
			bombermanState = 2;
			setVelX(-bombman.speed);//-2
		}
		else if(value == KeyEvent.VK_RIGHT && value !=KeyEvent.VK_LEFT){
			bombermanState = 1;
			setVelX(bombman.speed);//2
		}
		if(value == KeyEvent.VK_ESCAPE){
			d.getFrame().dispose();
			DrawMenu.getInstance().viewFrame(true);
		}
		if(value == KeyEvent.VK_SPACE){
			//if(bombman.getavailableBombs() != 0){
			if(bombs.size() != 1){
				System.out.println(bombs.size());
				activeBombs.add(new Bomb());
				bombs.remove(bombs.size()-1);
				System.out.println(bombs.size());
				int tilex = (int)bombman.getXval() + (int)(0.5*bombman.getWidth());
				int tiley = (int)bombman.getYval() + (int)(0.5*bombman.getHeight());
				tilex = (tilex/50) * 50;
				tiley = (tiley/50) * 50;

				activeBombs.get(activeBombs.size()-1).setXval(tilex);
				activeBombs.get(activeBombs.size()-1).setYval(tiley);
				explosions[0].setXval(tilex);
				explosions[0].setYval(tiley);
				explosions[1].setXval(tilex+50);
				explosions[1].setYval(tiley);
				explosions[2].setXval(tilex); // -50
				explosions[2].setYval(tiley);
				explosions[3].setXval(tilex);
				explosions[3].setYval(tiley+50);
				explosions[4].setXval(tilex);
				explosions[4].setYval(tiley); // -50
				activeBombs.get(activeBombs.size()-1).setActive(true);
				int delay = 2000;
				explodeTimer = new Timer();
				explodeTimer.schedule(new TimerTask(){
					public void run(){
						activeBombs.get(activeBombs.size()-1).setActive(false);
						for(int i = 0; i<4;i++){
							explosions[i].setExploding(true);
						}
						unexplodeTimer = new Timer();
						unexplodeTimer.schedule(new TimerTask(){
							public void run(){
								for(int i = 0; i < 4; i++){
									explosions[i].setExploding(false);
								}
								bombs.add(new Bomb());
								activeBombs.remove(activeBombs.size()-1);
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
			if(yVel == bombman.speed)//2
				setVelY(0);
		}
		else if(value ==KeyEvent.VK_UP){
			if(yVel == -bombman.speed)//-2
				setVelY(0);
		}
		else if(value == KeyEvent.VK_LEFT){
			if(xVel == -bombman.speed)//-2
				setVelX(0);
		}
		else if(value == KeyEvent.VK_RIGHT){
			if(xVel == bombman.speed) //2
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
		if(bombman.wallPass == false){
			for(int j=0; j<bricks.size(); j++){
				if(!detect.emptyLeft(bombman, bricks.get(j)) && xVel <= 0){
					bombermanXtemp = 0;
				}
				if(!detect.emptyRight(bombman, bricks.get(j)) && xVel >= 0){
					bombermanXtemp = 0;
				}
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
		if(bombman.wallPass == false){
			for(int i=0; i<bricks.size(); i++){
				if(!detect.emptyAbove(bombman, bricks.get(i)) && yVel <= 0){
					bombermanYtemp = 0;
				}
				if(!detect.emptyBelow(bombman, bricks.get(i)) && yVel >= 0){
					bombermanYtemp = 0;
				}
			}
		}
		bombman.incrementYval(bombermanYtemp);

		//Bomberman and Enemy Collisions
		for (int i=0; i<enemies.size(); i++){
			if(detect.collisionDetection(bombman, enemies.get(i))){
				bombman.incrementXval(-xVel);
				bombman.incrementYval(-yVel);
				if(!bombman.isMystery()){
					System.out.println("You died!!");
				}
			}
		}

		//Bomberman and Bombs Collisions
		if(!Bomberman.bombPass){
			for (int i=0; i<activeBombs.size(); i++){
				if(detect.collisionDetection(bombman, activeBombs.get(i))){
					if(activeBombs.get(i).getEscaped()){
						bombman.incrementXval(-xVel);
						bombman.incrementYval(-yVel);
					}
				}
				else{
					activeBombs.get(i).setEscaped(true);
				}
			}
		}


		
		//explosion check
		if(explosions[0].isExploding()){
			for(int i = 0; i< 5; i++){
				int max = get_MaxFlame(i);
				
				if(detect.collisionDetection(bombman, explosions[i],i,max)){
					if(!bombman.flamePass && !bombman.isMystery())
						System.out.println("You died.");
				}
				for(int j = 0; j < enemies.size(); j++){
					if(detect.collisionDetection(enemies.get(j), explosions[i],i,max)){
						User.updateScore(enemies.get(j).getPoints());
						System.out.println(User.getTotalScore());
						enemies.remove(j);
					}
				}
				for(int k = 0; k < bricks.size();k++){
					if(detect.collisionDetection_new(explosions[i], bricks.get(k),i,max)){
						bricks.remove(k);
					}
				}
				switch(i){
				case 1:
					explosions[i].setWidth(max * 50);
					break;
				case 2:
					explosions[i].setWidth(-max * 50);
					break;
				case 3:
					explosions[i].setHeight(max * 50);
					break;
				case 4:
					explosions[i].setHeight(-max * 50);
					break;
				}
			}
		}

		if(detect.collisionDetection(bombman, door) && enemies.size() == 0) {
			System.out.println("Level Complete!");
		}
		/*
		if(detect.collisionDetection(bombman,upbombs)){
			bombs.add(new Bomb());
			upbombs.setXval(0);
			upbombs.setYval(0);
			bombman.mystery_From = System.nanoTime(); //for testing
			bombman.speed += 2; //for testing
		}
		 */
	}

	public void tick2() {
		//collision check for enemy with indestructibles and bricks

		for(int k=0;k<enemies.size();k++) {
			statusReset();
			Enemy enemy = enemies.get(k);
			int tileNum = whichTileIsOn(enemy.getXval(), enemy.getYval());

			for(int i = 0; i < indestructibles.size(); i++) {
				if((whichTileIsOn(indestructibles.get(i).getXval(), indestructibles.get(i).getYval())) == (tileNum) && (enemy.getState() == 1)) {
					leftFree = false;
				} else if((whichTileIsOn(indestructibles.get(i).getXval(), indestructibles.get(i).getYval())) == (tileNum+1) && (enemy.getState() == 0)) {
					rightFree = false;
				} else if((whichTileIsOn(indestructibles.get(i).getXval(), indestructibles.get(i).getYval())) == (tileNum) && (enemy.getState() == 3)) {
					aboveFree = false;
				} else if((whichTileIsOn(indestructibles.get(i).getXval(), indestructibles.get(i).getYval())) == (tileNum+31) && (enemy.getState() == 2)) {
					belowFree = false;
				}
			}

			for(int i=0; i<bricks.size(); i++) {
				if((whichTileIsOn(bricks.get(i).getXval(), bricks.get(i).getYval())) == (tileNum) && (enemy.getState() == 1)) {
					leftFreeBrick = false;
				} else if((whichTileIsOn(bricks.get(i).getXval(), bricks.get(i).getYval())) == (tileNum+1) && (enemy.getState() == 0)) {
					rightFreeBrick = false;
				} else if((whichTileIsOn(bricks.get(i).getXval(), bricks.get(i).getYval())) == (tileNum) && (enemy.getState() == 3)) {
					aboveFreeBrick = false;
				} else if((whichTileIsOn(bricks.get(i).getXval(), bricks.get(i).getYval())) == (tileNum+31) && (enemy.getState() == 2)) {
					belowFreeBrick = false;
				}
			}

			if(enemy.getState() == 0) {
				if(rightFree && rightFreeBrick) {
					enemy.move();
				} else {
					enemy.changeDirection();
				}
			} else if(enemy.getState() == 1) {
				if(leftFree && leftFreeBrick ) {
					enemy.move();
				}  else {
					enemy.changeDirection();
				}
			} else if(enemy.getState() == 2) {
				if(belowFree && belowFreeBrick) {
					enemy.move();
				}  else {
					enemy.changeDirection();
				}
			} else {
				if(aboveFree && aboveFreeBrick) {
					enemy.move();
				}  else {
					enemy.changeDirection();
				}
			}
			
			if(enemy.getIntelligence() == 2){
				
			} else if(enemy.getIntelligence() == 3) {
				
			}
		}
	}
	
	/**
	 * returns if the enemy is at the intersection
	 * @param xPos and yPos
	 * @return True if enemy is at intersection, otherwise false
	 */
	public boolean isIntersection(int x, int y) {
		
		return false;
	}

	/**
	 * returns the tile number
	 * @param xPos and yPos
	 * @return The tile number it is on
	 */
	public int whichTileIsOn(int x, int y) {
		int tmp = y/50;
		return ((x/50) + tmp*31);
	}

	/**
	 * Resets all the variables to TRUE that is used for enemy collision detection 
	 * @param None
	 * @return None
	 */
	public void statusReset() {
		leftFree = true;
		rightFree = true;
		aboveFree = true;
		belowFree = true;
		leftFreeBrick = true;
		rightFreeBrick = true;
		aboveFreeBrick = true;
		belowFreeBrick = true;
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
	public static ArrayList<Bomb> getBombs(){
		return bombs;
	}
	public static Door getDoor(){
		return door;
	}
	public static Explosion getExplosion(int i){
		return explosions[i];
	}
	public static int getBombermanState() {
		return bombermanState;
	}
	public static Powerup getPowerup() {
		return power;
	}

	//detects an obstacle within the range of flame
	public int get_MaxFlame(int i){
		if(Bomberman.flames == 1){
			return 1;
		}
		else{
			int max = 0;
			int tile = whichTileIsOn(explosions[i].getXval(), explosions[i].getYval());
			for(int j = 0; j <= Bomberman.flames; j++){
				if((whichTileIsOn(indestructibles.get(j).getXval(), indestructibles.get(j).getYval())) == tile) {
					break;
				}
				if((whichTileIsOn(bricks.get(j).getXval(), bricks.get(j).getYval())) == tile){
					max++;
					break;
				}
				max++;
				switch(i){
				case 1:
					tile = whichTileIsOn(explosions[i].getXval()+50*j, explosions[i].getYval());
					break;
				case 2:
					tile = whichTileIsOn(explosions[i].getXval()-50*j, explosions[i].getYval());
					break;
				case 3:
					tile = whichTileIsOn(explosions[i].getXval(), explosions[i].getYval()+50*j);
					break;
				case 4:
					tile = whichTileIsOn(explosions[i].getXval(), explosions[i].getYval()-50*j);
					break;
				}
			}
			return max;
		}
	}
	
	//empty methods
	public void keyTyped(KeyEvent e) {
	}
	public void focusGained(FocusEvent e) {
	}
	public void focusLost(FocusEvent e) {
	}

	public static ArrayList<Bomb> getActiveBombs() {
		return activeBombs;
	}

}
