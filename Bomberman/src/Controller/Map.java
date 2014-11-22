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
import Model.Door;
import Model.Explosion;
import Model.Indestructible;
import Model.User;
import Model.Enemies.Balloom;
import Model.Enemies.Enemy;
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
	private static UpBombs upbombs;

	public Map(){

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
		upbombs = new UpBombs();
		spawn = new SpawnGameObjects();
		explosions = new Explosion[9];
		for(int i = 0; i<8; i++){
			explosions[i] = new Explosion();
		}

		//spawn gameObjects
		indestructibles = spawn.spawnIndestructibles();
		bricks = spawn.spawnBricks();
		enemies = spawn.spawnEnemies();
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
				explosions[2].setXval(tilex-50);
				explosions[2].setYval(tiley);
				explosions[3].setXval(tilex);
				explosions[3].setYval(tiley+50);
				explosions[4].setXval(tilex);
				explosions[4].setYval(tiley-50);
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

		if(explosions[0].isExploding()){
			for(int i = 0; i< 5; i++){
				if(detect.collisionDetection(bombman, explosions[i])){
					if(!bombman.flamePass && !bombman.isMystery())
						System.out.println("You died.");
				}
				for(int j = 0; j < enemies.size(); j++){
					if(detect.collisionDetection(enemies.get(j), explosions[i])){
						User.updateScore(enemies.get(j).getScore());
						System.out.println(User.getTotalScore());
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

		if(detect.collisionDetection(bombman, door) && enemies.size() == 0){
			System.out.println("Level Complete!");
		}

		if(detect.collisionDetection(bombman,upbombs)){
			bombs.add(new Bomb());
			upbombs.setXval(0);
			upbombs.setYval(0);
			bombman.mystery_From = System.nanoTime(); //for testing
			bombman.speed += 2; //for testing
		}
	}

	public void tick2() {
		//collision check for enemy with indestructibles and bricks
		boolean leftFree = true;
		boolean rightFree = true;
		boolean aboveFree = true;
		boolean belowFree = true;
		boolean leftFreeBrick = true;
		boolean rightFreeBrick = true;
		boolean aboveFreeBrick = true;
		boolean belowFreeBrick = true;

		for(int k=0;k<enemies.size();k++) {
			int tileNum = whichTileIsOn(enemies.get(k).getXval(),enemies.get(k).getYval());

			for(int i = 0; i < indestructibles.size(); i++) {
				if((whichTileIsOn(indestructibles.get(i).getXval(), indestructibles.get(i).getYval())) == (tileNum-1)) {
					leftFree = false;
				} else if((whichTileIsOn(indestructibles.get(i).getXval(), indestructibles.get(i).getYval())) == (tileNum+1)) {
					rightFree = false;
				} else if((whichTileIsOn(indestructibles.get(i).getXval(), indestructibles.get(i).getYval())) == (tileNum-31)) {
					aboveFree = false;
				} else if((whichTileIsOn(indestructibles.get(i).getXval(), indestructibles.get(i).getYval())) == (tileNum+31)) {
					belowFree = false;
				}
			}

			for(int i=0; i<bricks.size(); i++) {
				if((whichTileIsOn(bricks.get(i).getXval(), bricks.get(i).getYval())) == (tileNum-1)) {
					leftFreeBrick = false;
				} else if((whichTileIsOn(bricks.get(i).getXval(), bricks.get(i).getYval())) == (tileNum+1)) {
					rightFreeBrick = false;
				} else if((whichTileIsOn(bricks.get(i).getXval(), bricks.get(i).getYval())) == (tileNum-31)) {
					aboveFreeBrick = false;
				} else if((whichTileIsOn(bricks.get(i).getXval(), bricks.get(i).getYval())) == (tileNum+31)) {
					belowFreeBrick = false;
				}
			}
		}
		

//			Balloom enemy = enemies.get(k).getBalloomInstance();
			//System.out.println("Balloom "+ k + " state " +enemy.getState());
//			if(enemy.getState() == 0) {
//				if(rightFree && rightFreeBrick) {
//					enemy.move(enemies.get(k));
//				} else {
//					enemy.changeDirection();
//				}
//			} else if(enemy.getState() == 1) {
//				if(leftFree && leftFreeBrick) {
//					enemy.move(enemies.get(k));
//				}  else {
//					enemy.changeDirection();
//				}
//			} else if(enemy.getState() == 2) {
//				if(belowFree && belowFreeBrick) {
//					enemy.move(enemies.get(k));
//				}  else {
//					enemy.changeDirection();
//				}
//			} else {
//				if(aboveFree && aboveFreeBrick) {
//					enemy.move(enemies.get(k));
//				}  else {
//					enemy.changeDirection();
//				}
//			}
//		}
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

	/**
	 * returns the tile number
	 * @param xPos and yPos
	 * @return The tile number it is on
	 */
	public int whichTileIsOn(int x, int y) {
		return ((x/50)*31 + y/50);
	}

	//empty methods
	public void keyTyped(KeyEvent e) {
	}
	public void focusGained(FocusEvent e) {
	}
	public void focusLost(FocusEvent e) {
	}

	public static UpBombs getUpBombs() {
		return upbombs;
	}

	public static ArrayList<Bomb> getActiveBombs() {
		return activeBombs;
	}

}
