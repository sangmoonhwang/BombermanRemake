package Controller;

import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.*;

import javax.swing.JFrame;

import Controller.CollisionDetection;
import Model.Bomb;
import Model.Bomberman;
import Model.Box;
import Model.Destructible;
import Model.Door;
import Model.Explosion;
import Model.Indestructible;
import Model.Database;
import Model.User;
import Model.Enemies.Enemy;
import Model.PowerUps.Powerup;
import View.DrawMap;
import View.DrawMenu;
import View.DrawPauseMenu;


public class Map implements KeyListener, FocusListener, Serializable{
	public JFrame main;
	private User user;
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
	private static int life = 5;
	private int level;
//	private final ScheduledExecutorService scheduler;
	private static int width;
	private static int height;
	private long startTime = System.nanoTime()/1000000000;
	private long gameTime;
//	private Timer gameTimer;
	static boolean running = false;
	private CollisionDetection detect;
	private SpawnGameObjects spawn;
	private static int bombermanState;
	private long pausedAt = 0;
	private long duration = 200;
	private static boolean paused;
	ArrayList<Box> path;


	public Map(int level){
		this.level = level;
		//attributes
		width = 50;
		height = 50;
		xVel = 0;
		yVel = 0;

		//new objects
		user = Login.getUser();
		detect = new CollisionDetection();
		bombman = new Bomberman();
		bombs = bombman.getBombs();
		activeBombs = new ArrayList<Bomb>();
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
		

//		scheduler = Executors.newScheduledThreadPool(10);

		d = DrawMap.getInstance();
		running = true;
		paused = false;
//		gameTimer = new Timer();
//		gameTimer.schedule(new TimerTask(){
//			public void run(){
//				//change
//				System.out.println("Times up!");
//				d.getStatusBar().setText("Times Up!");
//			};
//		},200000);
		this.run();
	}

	public void run(){
		d.run();
		d.getFrame().addFocusListener(this);
		d.getFrame().addKeyListener(this);
		d.getFrame().requestFocus();
		running = true;
		paused = false;

		long start = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		
		while(running) {
			if(!paused){
				long now = System.nanoTime();
				if((now - start)/ns >= 1) {
					if (pausedAt == 0)
						gameTime = duration  + startTime - System.nanoTime()/1000000000;
					else{
						startTime += (System.nanoTime()/1000000000 - pausedAt);
						pausedAt = 0;
					}
					tick();
					tick2();
					start = now;
					d.draw();
					d.getStatusBar().setText("Level: "+ level +" Time: " + gameTime + " Life: " + life + " Score: " + user.getTotalScore());
					if(gameTime < 0){
						System.out.println("Times up!");
						d.getStatusBar().setText("Times Up!");
						dieBombman();
					}
				}
			}
			else {
				if(pausedAt == 0)
					pausedAt = System.nanoTime()/1000000000;
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
		} else if(value == KeyEvent.VK_ESCAPE || value == KeyEvent.VK_SPACE){
			paused = true;
			setVelY(0);
			setVelX(0);
			d.getFrame().setVisible(false);
			if(!DrawPauseMenu.getInstance().isRunning()){
				DrawPauseMenu.getInstance().makeFrame();
			}
			DrawPauseMenu.getInstance().setMap(this);
			DrawPauseMenu.getInstance().viewFrame(true);
			DrawPauseMenu.getInstance().run();
		} else if(value == KeyEvent.VK_X && Bomberman.detonate == true && activeBombs.size() >= 1){
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
		} else if(value == KeyEvent.VK_Z && !bombs.isEmpty()){
			//if(bombman.getavailableBombs() != 0){
//			if(bombs.size() >= 1){
				System.out.println("bombs Size " + bombs.size());
				activeBombs.add(new Bomb());
				bombs.remove(bombs.size()-1);
				System.out.println("After removing bombs" + bombs.size());
				int tilex = (int)bombman.getXval() + (int)(0.5*bombman.getWidth());
				int tiley = (int)bombman.getYval() + (int)(0.5*bombman.getHeight());
				tilex = (tilex/50) * 50;
				tiley = (tiley/50) * 50;

				activeBombs.get(activeBombs.size()-1).setXval(tilex);
				activeBombs.get(activeBombs.size()-1).setYval(tiley);
				activeBombs.get(activeBombs.size()-1).activate();
//			}
		}
	}

	//stop moving when key is released
	public void keyReleased(KeyEvent e) {
		int value = e.getKeyCode();
		if(value == KeyEvent.VK_DOWN) {
			if(yVel == bombman.getSpeed())//2
				setVelY(0);
		} else if(value ==KeyEvent.VK_UP) {
			if(yVel == -bombman.getSpeed())//-2
				setVelY(0);
		} else if(value == KeyEvent.VK_LEFT) {
			if(xVel == -bombman.getSpeed())//-2
				setVelX(0);
		} else if(value == KeyEvent.VK_RIGHT) {
			if(xVel == bombman.getSpeed()) //2
				setVelX(0);
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
					dieBombman();
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
		if(activeBombs.size() != 0 && activeBombs.get(0).getPersonalExplosions()[0].isExploding()){
			for(int i = 0; i < 5; i++){
				
				
				/* This massive ugly switch creates a test explosion to find the right
				 * width/height/xval/yval depending on the explosion, then sets the real
				 * explosion's values to the closest collision before making the important
				 * collision detection that has an outcome in the game
				 */

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
						while(detect.collisionDetection(testR, bricks.get(k))){
							testR.adjustWidth(-50);
							rightAdjust = true;
						}
					}
					for(int k = 0; k < indestructibles.size();k++){
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
						while(detect.collisionDetection(testL, bricks.get(k))){
							testL.adjustXval(50);
							leftAdjust = true;
						}
					}
					for(int k = 0; k < indestructibles.size();k++){
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
						while(detect.collisionDetection(testT, bricks.get(k))){
							testT.adjustHeight(-50);
							topAdjust = true;
						}
					}
					for(int k = 0; k < indestructibles.size();k++){
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
						while(detect.collisionDetection(testB, bricks.get(k))){
							testB.adjustYval(50);
							botAdjust = true;
						}
					}
					for(int k = 0; k < indestructibles.size();k++){
						while(detect.collisionDetection(testB, indestructibles.get(k))){
							testB.adjustYval(50);
							botAdjust = true;
						}
					}
					if(botAdjust){
						activeBombs.get(0).getPersonalExplosions()[4].setYval(testB.getYval()-50);
					}


				}


				//Collision Detection
				if(detect.collisionDetection(bombman, activeBombs.get(0).getPersonalExplosions()[i])){
					if(!bombman.flamePass && !bombman.isMystery()){
						dieBombman();
					}
					
				}
				
				TreeMap<Integer, String> killedEnemies = new TreeMap<Integer, String>();
				for(int j = 0; j < enemies.size(); j++){
					if(detect.collisionDetection(enemies.get(j), activeBombs.get(0).getPersonalExplosions()[i])){
						killedEnemies.put(enemies.get(j).getPoints(), enemies.get(j).getIdentity());
						enemies.remove(j);
					}
				}
				
				//point Calculation
				if(!killedEnemies.isEmpty())
					pointCalculation(killedEnemies);
				
				for(int k = 0; k < bricks.size();k++){
					if(detect.collisionDetection(activeBombs.get(0).getPersonalExplosions()[i], bricks.get(k))){
						bricks.remove(k);
					}
				}
			}
		}

		if(detect.collisionDetection(bombman, door) && enemies.size() == 0) {
			new Map(level+1);
			System.out.println("Level Complete!");
			user.setLevelCompleted(level);
		}



		//Powerup obtaining
//		System.out.println(whichTileIsOn(power.getXval(), power.getYval()));
		if(detect.collisionDetection(bombman, power)){
			power.setXval(0);
			power.setYval(0);
			power.activate();
		}
	}

	public void tick2() {
		//collision check for enemy with indestructibles, bricks and active bombs

		for(int k=0;k<enemies.size();k++) {
			Enemy enemy = enemies.get(k);
			enemy.searchFreePath(indestructibles, bricks, bombs);

			//if intelligence is either 2 or 3 it will check if the bomberman is within a range and will try to chase the bomberman
			if(enemy.getIntelligence() > 1) {
				int tileBombman = whichTileIsOn(bombman.getXval(), bombman.getYval());
				int tileEnemy = whichTileIsOn(enemy.getXval(), enemy.getYval());
				int bombermanDirection = chaseDirection(enemy, tileBombman, tileEnemy);
				if(enemy.getIntelligence() == 2) {
					if(isBombermanWithinOneSquare(tileBombman, tileEnemy)) {
						moveEnemyWhenBombermanWithInRange(enemy, bombermanDirection);
					} else {
						moveEnemy(enemy);
						changeDirectionAtIntersection(enemy);
					}
				} else if(enemy.getIntelligence() == 3) {
					if(isBombermanWithinTwoSquare(tileBombman, tileEnemy) || isBombermanWithinOneSquare(tileBombman, tileEnemy)) {
						if(!isChaseBombermanPathFree(enemy, tileBombman, tileEnemy)) {
							findPathToBomberman(enemy, tileBombman, tileEnemy);
							moveEnemy(enemy);
						} else {
							moveEnemyWhenBombermanWithInRange(enemy, bombermanDirection);
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
			if(enemy.getRightFree() && enemy.getRightFreeBrick()) {
				enemy.move();
			} else {
				enemy.changeDirection();
			}
		} else if(enemy.getState() == 1) {
			if(enemy.getLeftFree() && enemy.getLeftFreeBrick()) {
				enemy.move();
			}  else {
				enemy.changeDirection();
			}
		} else if(enemy.getState() == 2) {
			if(enemy.getBelowFree() && enemy.getBelowFreeBrick()) {
				enemy.move();
			}  else {
				enemy.changeDirection();
			}
		} else {
			if(enemy.getAboveFree() && enemy.getAboveFreeBrick()) {
				enemy.move();
			}  else {
				enemy.changeDirection();
			}
		}
	}

	/**
	 * move the enemy when bomberman is within range.
	 * @param Enemy instance and bomberman direction of chase from enemy perspective
	 * @return None
	 */
	public void moveEnemyWhenBombermanWithInRange(Enemy enemy, int bombermanDirection) {
		if(isIntersection(enemy.getXval(), enemy.getYval())) {
			enemy.setState(bombermanDirection);
			moveEnemy(enemy);
		} else {
			changeDirectionToChaseBomberman(enemy, bombermanDirection, enemy.getState());
			moveEnemy(enemy);
		}
	}

	/**
	 * returns boolean whether the chase bomberman path is free(for intelligence = 3 only)
	 * @param Enemy enemy
	 * @param Int tileBomberman
	 * @param Int tileEnemy
	 * @return True if bombermanPath is Free otherwise false
	 */
	public boolean isChaseBombermanPathFree(Enemy enemy, int tileBombman, int tileEnemy) {
		if(tileBombman == tileEnemy) {
			return true;
		} else if(tileBombman == (tileEnemy + 1) && enemy.getRightFree() && enemy.getRightFreeBrick()) {
			return true;
		} else if(tileBombman == (tileEnemy - 1) && enemy.getLeftFree() && enemy.getLeftFreeBrick()) {
			return true;
		} else if(tileBombman == (tileEnemy + 31) && enemy.getBelowFree() && enemy.getBelowFreeBrick()) {
			return true;
		} else if(tileBombman == (tileEnemy - 31) && enemy.getAboveFree() && enemy.getAboveFreeBrick()) {
			return true;
		} else if(tileBombman == (tileEnemy - 2) && enemy.get2LeftFree() && enemy.get2LeftFreeBrick() && enemy.getLeftFree() && enemy.getLeftFreeBrick()) {
			return true;
		} else if(tileBombman == (tileEnemy + 2) && enemy.get2RightFree() && enemy.get2RightFreeBrick() && enemy.getRightFree() && enemy.getRightFreeBrick()) {
			return true;
		} else if(tileBombman == (tileEnemy + 62) && enemy.get2BelowFree() && enemy.get2BelowFreeBrick() && enemy.getBelowFree() && enemy.getBelowFreeBrick()) {
			return true;
		} else if(tileBombman == (tileEnemy - 62) && enemy.get2AboveFree() && enemy.get2AboveFreeBrick() && enemy.getAboveFree() && enemy.getAboveFreeBrick()) {
			return true;
		} 
		return false;
	}

	/**
	 * Finds the correct path to bomberman when obstacles exist(for intelligence = 3 only)
	 * @param Enemy instance, Tile number of bomberman and enemy
	 * @return
	 */
	public void findPathToBomberman(Enemy enemy, int tileBombman, int tileEnemy) {
		enemy.searchForPath(bombman.getXval(), bombman.getYval());
		path = enemy.getPath();

		int moveToTile = whichTileIsOn(path.get(path.size()-1).x, path.get(path.size()-1).y);
		if(moveToTile == (tileEnemy + 1) && isIntersection(enemy.getXval(), enemy.getYval())) {
			enemy.setState(0);
		} else if(moveToTile == (tileEnemy - 1) && isIntersection(enemy.getXval(), enemy.getYval())) {
			enemy.setState(1);
		} else if(moveToTile == (tileEnemy + 31) && isIntersection(enemy.getXval(), enemy.getYval())) {
			enemy.setState(2);
		}else if(moveToTile == (tileEnemy - 31) && isIntersection(enemy.getXval(), enemy.getYval())) {
			enemy.setState(3);
		} else {
			changeDirectionToChaseBomberman(enemy, chaseDirection(enemy, tileBombman, tileEnemy), enemy.getState());
		}
		path.clear();
	}

	/**
	 * if intelligence is 2 or 3 and also at the intersection it can change direction with probability of 0.1(intelligence=2) or 0.5(intelligence=3)
	 * @param Enemy instance
	 * @return None
	 */
	public void changeDirectionAtIntersection(Enemy enemy) {
		if(isIntersection(enemy.getXval(), enemy.getYval())){
			int state = enemy.getState();
			if((state == 0 && enemy.getAboveFree() && enemy.getAboveFreeBrick()) || (state == 0 && enemy.getBelowFree() && enemy.getBelowFreeBrick()) 
					|| (state == 1 && enemy.getAboveFree() && enemy.getAboveFreeBrick()) || (state == 1 && enemy.getBelowFree() && enemy.getBelowFreeBrick())) {
				enemy.intersectionDirectionChange(enemy.getAboveFree());
			} else if((state == 2 && enemy.getLeftFree() && enemy.getLeftFreeBrick()) || (state == 2 && enemy.getRightFree() && enemy.getRightFreeBrick()) 
					|| (state == 3 && enemy.getLeftFree() && enemy.getLeftFreeBrick()) || (state == 3 && enemy.getRightFree() && enemy.getRightFreeBrick())) {
				enemy.intersectionDirectionChange(enemy.getLeftFree());
			}
		} 
	}

	/**
	 * Whether the enemy is at the intersection
	 * @param xPos and yPos of enemy
	 * @return True if enemy is at intersection, otherwise false
	 */
	public boolean isIntersection(int x, int y) {
		int xMOD =  (x % 100);
		int yMOD = (y % 100);
		if(xMOD == 50 && yMOD == 50)
			return true;

		return false;
	}

	/**
	 * returns the correct direction to chase the Bomberman 
	 * @param Enemy object and Tile number of Bomberman and Enemy
	 * @return Direction state 0-right 1-left 2-Below 3-Above
	 */
	public int chaseDirection(Enemy enemy, int tileBombman, int tileEnemy) {
		if(tileBombman == (tileEnemy + 1) || tileBombman == (tileEnemy + 2)) {
			return 0;
		} else if(tileBombman == (tileEnemy - 1) || tileBombman == (tileEnemy - 2)) {
			return 1;
		} else if(tileBombman == (tileEnemy + 31) || tileBombman == (tileEnemy + 62)) {
			return 2;
		} else if(tileBombman == (tileEnemy - 31) || tileBombman == (tileEnemy - 62)) {
			return 3;
		} else if(tileBombman == (tileEnemy + 32) && enemy.getBelowFree() && enemy.getBelowFreeBrick() 
				|| tileBombman == (tileEnemy + 30) && enemy.getBelowFree() && enemy.getBelowFreeBrick()) {
			return 2;
		} else if(tileBombman == (tileEnemy - 32) && enemy.getAboveFree() && enemy.getAboveFreeBrick() 
				|| tileBombman == (tileEnemy - 30) && enemy.getAboveFree() && enemy.getAboveFreeBrick()) {
			return 3;
		} else if(tileBombman == (tileEnemy + 32) && enemy.getRightFree() && enemy.getRightFreeBrick()
				|| tileBombman == (tileEnemy - 30) && enemy.getRightFree() && enemy.getRightFreeBrick()) {
			return 0;
		} else if(tileBombman == (tileEnemy - 32) && enemy.getLeftFree() && enemy.getLeftFreeBrick()
				|| tileBombman == (tileEnemy + 30) && enemy.getLeftFree() && enemy.getLeftFreeBrick()) {
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
	 * @param int x
	 * @param int y
	 * @return The tile number it is on
	 */
	public int whichTileIsOn(int x, int y) {
		int tmp = (y/50);
		return  ((x/50) + tmp*31);
	}
	
	/**
	 * Calculates the killed enemy points and updates the User total points
	 * @param TreeMap<Integer, String>
	 * @return None
	 */
	public void pointCalculation(TreeMap<Integer, String> killedEnemies) {
		int totalPoints = 0, key, numberOfKills = 2;
		
		key = killedEnemies.lastKey();
		killedEnemies.remove(key);
		totalPoints = key;
		
		while(!killedEnemies.isEmpty()){
			key = killedEnemies.lastKey();
			killedEnemies.remove(key);
			
			totalPoints += key * numberOfKills;
			numberOfKills *= 2;
		}
		user.updateScore(totalPoints);
	}

	public void dieBombman(){
		if(life>0){
			life --;
			softResetBombman();
			new Map(level);//should take user input of levels or next level when current level clears
			Map.setPaused(false);
		}
		else{
			System.out.println("Game over");
			//saveScore_to_Leaderboard();
			DrawMap game = DrawMap.getInstance();
			DrawMenu menu = DrawMenu.getInstance();
			Database data = new Database();
			try {
				Database.modifyUserCSVEntry(user.getUsername(), null, null, user.getNumOfPlay() + 1, user.getTotalScore(), level);
			} catch (IOException e) {
				System.out.println("error saving data");
				e.printStackTrace();
			}
			menu.viewFrame(true);
			Map.setRunning(false);
			game.getFrame().dispose();
		}
	}
	
	/**
	 * soft reset bombman in case of death: only preserves speed, bombs, flames
	 */
	public void softResetBombman(){
		Bomberman.bombPass = false;
		Bomberman.flamePass = false;
		Bomberman.detonate = false;
		Bomberman.wallPass = false;
		Bomberman.mystery_From = -1000000000;
	}
	
	/**
	 * hard reset bombman
	 */
	public static void hardResetBombman(){
		Bomberman.bombPass = false;
		Bomberman.flamePass = false;
		Bomberman.detonate = false;
		Bomberman.wallPass = false;
		Bomberman.mystery_From = -1000000000;
		Bomberman.speed = 2;
		Bomberman.flames = 1;
		Bomberman.availableBombs = 1;
		bombman.getBombs().clear();
		bombman.getBombs().add(new Bomb());
		bombman.getBombs().add(new Bomb());
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
	public static void setLife(int a){
		life = a;
	}
	public static ArrayList<Bomb> getActiveBombs() {
		return activeBombs;
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

	public static void setRunning(boolean b){
		running = b;
	}
	public static void setPaused(boolean b){
		paused = b;
	}
	public Map getMap(){
		return this;
	}
	//empty methods
	public void keyTyped(KeyEvent e) {
	}
	public void focusGained(FocusEvent e) {
	}
	public void focusLost(FocusEvent e) {
	}
}
