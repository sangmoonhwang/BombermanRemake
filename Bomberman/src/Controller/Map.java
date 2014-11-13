// next step http://stackoverflow.com/questions/7537570/eliminating-initial-keypress-delay


package Controller;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JFrame;

import Model.Bomberman;
import Model.Indestructible;
import Model.Tile;
import View.DrawMap;

public class Map implements KeyListener, FocusListener{
	public JFrame main;
	private static DrawMap d;
	private static Bomberman bombman;
	private static Indestructible[] indestructibles;
	private static Tile[] tiles;
	private float xVel = 0;
	private float yVel = 0;
	private static int width;
	private static int height;
	static boolean running = false;
	
	public Map(){
		width = 50;
		height = 50;
		bombman = new Bomberman();
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
		bombman.setXval(xVel);
		bombman.setYval(yVel);
		//hard-coded bomberman/indestructibles collision detection for demo purposes
		for(int i = 0; i < 100; i++){
			if(tiles[0].collisionDetection(bombman, indestructibles[i])){
				bombman.setXval(-xVel);
				bombman.setYval(-yVel);
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
	
	public static Bomberman getBomberman(){
		return bombman;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
