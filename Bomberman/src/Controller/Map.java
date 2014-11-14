// next step http://stackoverflow.com/questions/7537570/eliminating-initial-keypress-delay


package Controller;

import java.awt.Color;
import java.awt.event.*;
import java.util.Random;

import javax.swing.JFrame;

import Model.Bomberman;
import Model.Destructible;
import Model.Indestructible;
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
	private float xVel = 0;
	private float yVel = 0;
	private static int width;
	private static int height;
	static boolean running = false;
	
	public Map(){
		width = 50;
		height = 50;
		bombman = new Bomberman();
		
		enemies = new Enemy[8];
		for(int i = 0; i < 7; i++){

			Random r = new Random();
			int x = r.nextInt(13)+1;
			int y = r.nextInt(13)+1;
			enemies[i] = new Enemy();
			enemies[i].setXval(x);
			enemies[i].setYval(y);
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
	@Override
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
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	//stop moving when key is released
	@Override
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
			if(xVel ==2)
				setVelX(0);
		}
		else{
			setVelX(0);
			setVelY(0);
		}
	}    

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
	}


	public void tick() {
		bombman.incrementXval(xVel);
		bombman.incrementYval(yVel);
		//hard-coded bomberman/indestructibles collision detection for demo purposes
		for(int i = 0; i < 100; i++){
			if(tiles[0].collisionDetection(bombman, indestructibles[i])){
				if( (tiles[0].emptyLeft(bombman, indestructibles[i], -yVel) && xVel <= 0)
						||(tiles[0].emptyRight(bombman, indestructibles[i], -yVel) && xVel >= 0) ){
					bombman.incrementYval(-yVel);
				}
				else if( (tiles[0].emptyAbove(bombman, indestructibles[i], -xVel) && yVel <= 0)
						|| (tiles[0].emptyBelow(bombman, indestructibles[i], -xVel) && yVel >= 0) ){
					bombman.incrementXval(-xVel);
				}
				else{
					bombman.incrementXval(-xVel);
					bombman.incrementYval(-yVel);
				}
			}
		}
		/*for(int i = 0; i < 400; i++){
			if(bricks[i] != null){
				if(tiles[0].collisionDetection(bombman, bricks[i])){
					bombman.setXval(-xVel);
					bombman.setYval(-yVel);
				}
			}
		}*/
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
	
}
