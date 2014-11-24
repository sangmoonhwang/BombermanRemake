// next step http://stackoverflow.com/questions/7537570/eliminating-initial-keypress-delay


package Controller;

import java.awt.event.*;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JFrame;

import Model.Bomb;
import Model.Bomberman;
import Model.Destructible;
import Model.Door;
import Model.Explosion;
import Model.Indestructible;
import static java.util.concurrent.TimeUnit.*;
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
	private int xVel;
	private int yVel;
	private final ScheduledExecutorService scheduler;
	private static int width;
	private static int height;
	private Timer gameTimer;
	static boolean running = false;
	private CollissionDetection detect;
	private SpawnGameObjects spawn;
	private static int bombermanState;
	private boolean leftFree = true;
	private boolean rightFree = true;
	private boolean aboveFree = true;
	private boolean belowFree = true;
	private boolean leftFreeBrick = true;
	private boolean rightFreeBrick = true;
	private boolean aboveFreeBrick = true;
	private boolean belowFreeBrick = true;

	public Map(int level){

		//attributes
		width = 50;
		height = 50;
		xVel = 0;
		yVel = 0;

		//new objects
		detect = new CollissionDetection();
		bombman = new Bomberman();
		bombs = new ArrayList<Bomb>();
		activeBombs = new ArrayList<Bomb>();
		bombs.add(new Bomb());
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


		scheduler = Executors.newScheduledThreadPool(10);

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

	//public Map(JFrame mainFrame) {
	//	scheduler = Executors.newScheduledThreadPool(10);
	//	main = mainFrame;
	//	d = DrawMap.getInstance();
	//	running = true;
	//	this.run();
	//}

	public void run(){
		running = true;
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
			setVelY(bombman.getSpeed());//2
		}
		else if(value != KeyEvent.VK_DOWN && value ==KeyEvent.VK_UP){
			setVelY(-bombman.getSpeed());//-2
		}
		else if(value == KeyEvent.VK_LEFT && value !=KeyEvent.VK_RIGHT){
			bombermanState = 2;
			setVelX(-bombman.getSpeed());//-2
		}
		else if(value == KeyEvent.VK_RIGHT && value !=KeyEvent.VK_LEFT){
			bombermanState = 1;
			setVelX(bombman.getSpeed());//2
		}
		if(value == KeyEvent.VK_ESCAPE){
			running = false;
			d.getFrame().dispose();
			DrawMenu.getInstance().viewFrame(true);
		}
		if(value == KeyEvent.VK_V && Bomberman.detonate == true && activeBombs.size() >= 1){
			for(int i =0; i< activeBombs.size(); i++){
				if(!activeBombs.get(i).getUsed()){
					final Runnable unExplode = new Runnable() {

						@Override
						public void run() {
							for(int i = 0; i < 4; i++){
								activeBombs.get(0).getPersonalExplosions()[i].setExploding(false);
							}
							bombs.add(new Bomb());
							activeBombs.remove(0);
						}
					};
					activeBombs.get(i).explode();
					activeBombs.get(i).getSchedule().schedule(unExplode, 500, MILLISECONDS);

					break;
				}
			}
			//activeBombs.get(0).explode();
			//explosions = activeBombs.get(activeBombs.size()-1).getPersonalExplosions();
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
				activeBombs.get(activeBombs.size()-1).activate();

			}
		}
	}

	//stop moving when key is released
	public void keyReleased(KeyEvent e) {
		int value = e.getKeyCode();
		if (value == KeyEvent.VK_DOWN){
			if(yVel == bombman.getSpeed())//2
				setVelY(0);
		}
		else if(value ==KeyEvent.VK_UP){
			if(yVel == -bombman.getSpeed())//-2
				setVelY(0);
		}
		else if(value == KeyEvent.VK_LEFT){
			if(xVel == -bombman.getSpeed())//-2
				setVelX(0);
		}
		else if(value == KeyEvent.VK_RIGHT){
			if(xVel == bombman.getSpeed()) //2
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


		//create test object at max length, check collision, if true set flame length to that, but repeat process at max length-1 and if true, set length to that, iterate
		//until the explosion is at min. length
		//explosion check  
		if(activeBombs.size() != 0 && activeBombs.get(0).getPersonalExplosions()[0].isExploding()){
			//for(int i = 0; i< activeBombs.size(); i++){
			for(int i = 0; i < 5; i++){

				switch(i) {

				case 1:
					boolean rightAdjust = false;
					Explosion testR = new Explosion();
					testR = activeBombs.get(0).getPersonalExplosions()[1];
					while(detect.collisionDetection(bombman, testR)){
						testR.adjustWidth(-50);
						rightAdjust = true;
					}
					for(int j = 0; j < enemies.size(); j++){
						while(detect.collisionDetection(enemies.get(j), testR)){
							testR.adjustWidth(-50);
							rightAdjust = true;
						}
					}
					for(int k = 0; k < bricks.size();k++){
						//if(detect.collisionDetection_new(explosions[i], bricks.get(k),i,max)){
						while(detect.collisionDetection(testR, bricks.get(k))){
							testR.adjustWidth(-50);
							rightAdjust = true;
						}
					}
					for(int k = 0; k < indestructibles.size();k++){
						//if(detect.collisionDetection_new(explosions[i], bricks.get(k),i,max)){
						while(detect.collisionDetection(testR, indestructibles.get(k))){
							testR.adjustWidth(-50);
							rightAdjust = true;
						}
					}
					if(rightAdjust){
						activeBombs.get(0).getPersonalExplosions()[1].setWidth(testR.getWidth()+50);
					}

				case 2:
					boolean leftAdjust = false;
					Explosion testL = new Explosion();
					testL = activeBombs.get(0).getPersonalExplosions()[2];
					while(detect.collisionDetection(bombman, testL)){
						testL.adjustXval(50);
						leftAdjust = true;
					}
					for(int j = 0; j < enemies.size(); j++){
						while(detect.collisionDetection(enemies.get(j), testL)){
							testL.adjustXval(50);
							leftAdjust = true;
						}
					}
					for(int k = 0; k < bricks.size();k++){
						//if(detect.collisionDetection_new(explosions[i], bricks.get(k),i,max)){
						while(detect.collisionDetection(testL, bricks.get(k))){
							testL.adjustXval(50);
							leftAdjust = true;
						}
					}
					for(int k = 0; k < indestructibles.size();k++){
						//if(detect.collisionDetection_new(explosions[i], bricks.get(k),i,max)){
						while(detect.collisionDetection(testL, indestructibles.get(k))){
							testL.adjustXval(50);
							leftAdjust = true;
						}
					}
					if(leftAdjust){
						activeBombs.get(0).getPersonalExplosions()[2].setXval(testL.getXval()-50);
					}

				case 3:
					boolean topAdjust = false;
					Explosion testT = new Explosion();
					testT = activeBombs.get(0).getPersonalExplosions()[3];
					while(detect.collisionDetection(bombman, testT)){
						testT.adjustHeight(-50);
						topAdjust = true;
					}
					for(int j = 0; j < enemies.size(); j++){
						while(detect.collisionDetection(enemies.get(j), testT)){
							testT.adjustHeight(-50);
							topAdjust = true;
						}
					}
					for(int k = 0; k < bricks.size();k++){
						//if(detect.collisionDetection_new(explosions[i], bricks.get(k),i,max)){
						while(detect.collisionDetection(testT, bricks.get(k))){
							testT.adjustHeight(-50);
							topAdjust = true;
						}
					}
					for(int k = 0; k < indestructibles.size();k++){
						//if(detect.collisionDetection_new(explosions[i], bricks.get(k),i,max)){
						while(detect.collisionDetection(testT, indestructibles.get(k))){
							testT.adjustHeight(-50);
							topAdjust = true;
						}
					}
					if(topAdjust){
						activeBombs.get(0).getPersonalExplosions()[3].setHeight(testT.getHeight()+50);
					}

				case 4:
					boolean botAdjust = false;
					Explosion testB = new Explosion();
					testB = activeBombs.get(0).getPersonalExplosions()[4];
					while(detect.collisionDetection(bombman, testB)){
						testB.adjustYval(50);
						botAdjust = true;
					}
					for(int j = 0; j < enemies.size(); j++){
						while(detect.collisionDetection(enemies.get(j), testB)){
							testB.adjustYval(50);
							botAdjust = true;
						}
					}
					for(int k = 0; k < bricks.size();k++){
						//if(detect.collisionDetection_new(explosions[i], bricks.get(k),i,max)){
						while(detect.collisionDetection(testB, bricks.get(k))){
							testB.adjustYval(50);
							botAdjust = true;
						}
					}
					for(int k = 0; k < indestructibles.size();k++){
						//if(detect.collisionDetection_new(explosions[i], bricks.get(k),i,max)){
						while(detect.collisionDetection(testB, indestructibles.get(k))){
							testB.adjustYval(50);
							botAdjust = true;
						}
					}
					if(botAdjust){
						activeBombs.get(0).getPersonalExplosions()[4].setYval(testB.getYval()-50);
					}


				}

				if(detect.collisionDetection(bombman, activeBombs.get(0).getPersonalExplosions()[i])){
					if(!bombman.flamePass && !bombman.isMystery())
						System.out.println("You died.");
				}
				for(int j = 0; j < enemies.size(); j++){
					if(detect.collisionDetection(enemies.get(j), activeBombs.get(0).getPersonalExplosions()[i])){
						User.updateScore(enemies.get(j).getPoints());
						System.out.println(User.getTotalScore());
						enemies.remove(j);
					}
				}
				for(int k = 0; k < bricks.size();k++){
					//if(detect.collisionDetection_new(explosions[i], bricks.get(k),i,max)){
					if(detect.collisionDetection(activeBombs.get(0).getPersonalExplosions()[i], bricks.get(k))){
						bricks.remove(k);
					}
				}
			}
		}

		if(detect.collisionDetection(bombman, door) && enemies.size() == 0) {
			System.out.println("Level Complete!");
		}



		//Powerup obtaining
		System.out.println(power.getXval()+", "+power.getYval());
		if(detect.collisionDetection(bombman, power)){
			power.setXval(0);
			power.setYval(0);
			power.activate();
		}
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

			//if intelligence is either 2 or 3 it will check if the bomberman is within a range and will try to chase the bomberman
			if(enemy.getIntelligence() > 1) {
				int tileBombman = whichTileIsOn(bombman.getXval(), bombman.getYval());
				int tileEnemy = whichTileIsOn(enemy.getXval(), enemy.getYval());
				int bombermanDirection = chaseDirection(tileBombman, tileEnemy);
				if(enemy.getIntelligence() == 2) {
					if(isBombermanWithinOneSquare(tileBombman, tileEnemy)) {
						if(isIntersection(enemy.getXval(), enemy.getYval())) {
							enemy.setState(bombermanDirection);
							moveEnemy(enemy);
						} else {
							changeDirectionToChaseBomberman(enemy, bombermanDirection, enemy.getState());
							moveEnemy(enemy);
						}
					} else {
						moveEnemy(enemy);
						changeDirectionAtIntersection(enemy);
					}
				} else if(enemy.getIntelligence() == 3) {
					if(isBombermanWithinTwoSquare(tileBombman, tileEnemy) || isBombermanWithinOneSquare(tileBombman, tileEnemy)) {
						if(isIntersection(enemy.getXval(), enemy.getYval())) {
							enemy.setState(bombermanDirection);
							moveEnemy(enemy);
						} else {
							changeDirectionToChaseBomberman(enemy, bombermanDirection, enemy.getState());
							moveEnemy(enemy);
						}
					} else {
						moveEnemy(enemy);
						changeDirectionAtIntersection(enemy);
					}
				}
			} else {
				moveEnemy(enemy);
			}
		}
	}

	/**
	 * move the enemy to current moving direction or change the moving direction to opposite if path is not free
	 * @param Enemy instance
	 * @return None
	 */
	public void moveEnemy(Enemy enemy) {
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
	}

	/**
	 * if intelligence is 2 or 3 and also at the intersection it can change direction with probability of 0.1(intelligence=2) or 0.5(intelligence=3)
	 * @param Enemy instance
	 * @return None
	 */
	public void changeDirectionAtIntersection(Enemy enemy) {
		if(isIntersection(enemy.getXval(), enemy.getYval())){
			int state = enemy.getState();
			if((state == 0 && aboveFree && aboveFreeBrick) || (state == 0 && belowFree && belowFreeBrick) 
					|| (state == 1 && aboveFree && aboveFreeBrick) || (state == 1 && belowFree && belowFreeBrick)) {
				enemy.intersectionDirectionChange(aboveFree);
			} else if((state == 2 && leftFree && leftFreeBrick) || (state == 2 && rightFree && rightFreeBrick) 
					|| (state == 3 && leftFree && leftFreeBrick) || (state == 3 && rightFree && rightFreeBrick)) {
				enemy.intersectionDirectionChange(leftFree);
			}
		} 
	}

	/**
	 * Whether the enemy is at the intersection
	 * @param xPos and yPos of enemy
	 * @return True if enemy is at intersection, otherwise false
	 */
	public boolean isIntersection(int x, int y) {
		int xMOD = x % 100;
		int yMOD = y % 100;
		if(xMOD == 50 && yMOD == 50)
			return true;

		return false;
	}

	/**
	 * returns the correct direction to chase the Bomberman 
	 * @param Tile number of Bomberman and Enemy
	 * @return Direction state 0-right 1-left 2-Below 3-Above
	 */
	public int chaseDirection(int tileBombman, int tileEnemy) {
		if(tileBombman == (tileEnemy + 1) || tileBombman == (tileEnemy + 2)) {
			return 0;
		} else if(tileBombman == (tileEnemy - 1) || tileBombman == (tileEnemy - 2)) {
			return 1;
		} else if(tileBombman == (tileEnemy + 31) || tileBombman == (tileEnemy + 62)) {
			return 2;
		} else if(tileBombman == (tileEnemy - 31) || tileBombman == (tileEnemy - 62)) {
			return 3;
		} else if(tileBombman == (tileEnemy + 32) && belowFree && belowFreeBrick || tileBombman == (tileEnemy + 30) && belowFree && belowFreeBrick) {
			return 2;
		} else if(tileBombman == (tileEnemy - 32) && aboveFree && aboveFreeBrick || tileBombman == (tileEnemy - 30) && aboveFree && aboveFreeBrick) {
			return 3;
		} else if(tileBombman == (tileEnemy + 32) && rightFree && rightFreeBrick || tileBombman == (tileEnemy - 30) && rightFree && rightFreeBrick) {
			return 0;
		} else if(tileBombman == (tileEnemy - 32) && leftFree && leftFreeBrick || tileBombman == (tileEnemy + 30) && leftFree && leftFreeBrick) {
			return 1;
		}

		return tileEnemy;
	}

	/**
	 * if the bomberman is at the opposite direction of the enemy movement then change the movement state to opposite
	 * @param Enemy instance, Direction of the bomberman is located respect to the enemy position and enemy direction state
	 * @return None
	 */
	public void changeDirectionToChaseBomberman(Enemy enemy, int bombermanDirection, int enemyDirection) {
		if((bombermanDirection == 3 && enemyDirection == 2) && (bombermanDirection == 2 && enemyDirection == 3)
				&& (bombermanDirection == 1 && enemyDirection == 0) && (bombermanDirection == 0 && enemyDirection == 1)) {
			enemy.changeDirection();
		}
	}

	/**
	 * Checks if the bomberman is within one square of the enemy
	 * @param Tile number of Bomberman and Enemy
	 * @return True if bomberman is within one square, otherwise false
	 */
	public boolean isBombermanWithinOneSquare(int tileBombman, int tileEnemy) {
		if(tileBombman == (tileEnemy + 1)) {
			return true;
		} else if(tileBombman == (tileEnemy - 1)) {
			return true;
		} else if(tileBombman == (tileEnemy + 31)) {
			return true;
		} else if(tileBombman == (tileEnemy - 31)) {
			return true;
		}

		return false;
	}

	/**
	 * Checks if the bomberman is within two square of the enemy
	 * @param Tile number of Bomberman and Enemy
	 * @return True if bomberman is within two square, otherwise false
	 */
	public boolean isBombermanWithinTwoSquare(int tileBombman, int tileEnemy) {
		if(tileBombman == (tileEnemy + 2)) {
			return true;
		} else if(tileBombman == (tileEnemy - 2)) {
			return true;
		} else if(tileBombman == (tileEnemy + 62)) {
			return true;
		} else if(tileBombman == (tileEnemy - 62)) {
			return true;
		} else if(tileBombman == (tileEnemy + 32)) {
			return true;
		} else if(tileBombman == (tileEnemy + 30)) {
			return true;
		} else if(tileBombman == (tileEnemy - 32)) {
			return true;
		} else if(tileBombman == (tileEnemy - 30)) {
			return true;
		}

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
