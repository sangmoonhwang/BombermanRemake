package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import View.DrawMap;
import Controller.GamePlay;
import Controller.Map;

/**
 * Bomb model
 *
 */
public class Bomb implements Serializable, Runnable, ActionListener {
	private int xval, yval;
	private int height, width;
	private boolean active;
	/**
	 * indicates whether the user got out of the bomb after laying it
	 */
	private boolean escaped;
	private Explosion[] personalExplosions;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
	private boolean used;
	private boolean paused = false;
	private long pausedAt = 0;
	private int state;
	private int counter;

	/**
	 * constructor
	 * @param active true if bomb is activated false otherwise
	 */
	public Bomb(boolean active) {
		xval = yval = 0;
		height = width = 50;
		state = 0;
		counter = 0;
		this.active = active;
		escaped = false;
		personalExplosions = new Explosion[5];
		for(int i = 0; i < 5; i++){
			personalExplosions[i] = new Explosion();
		}
		used = false;
	}

	//explosion
	/**
	 * explodes the bomb
	 */
	public void explode(){
		used = true;
		personalExplosions[0].setXval(this.xval);
		personalExplosions[0].setYval(this.yval);
		personalExplosions[1].setXval(xval+50);
		personalExplosions[1].setYval(yval);
		personalExplosions[1].setWidth(Bomberman.flames*50);
		personalExplosions[2].setXval(xval-50*Bomberman.flames); 
		personalExplosions[2].setYval(yval);
		personalExplosions[2].setWidth(Bomberman.flames*50);
		personalExplosions[3].setXval(xval);
		personalExplosions[3].setYval(yval+50);
		personalExplosions[3].setHeight(Bomberman.flames*50);
		personalExplosions[4].setXval(xval);
		personalExplosions[4].setYval(yval-50*Bomberman.flames); 
		personalExplosions[4].setHeight(50*Bomberman.flames);
		for(int i = 0; i < 5; i++){
			personalExplosions[i].setExploding(true);
		}
	}


	public void run() {
		
		if(state == 0 && counter > 3) {
			explode();
			state = 1;
			counter++;
		}else if(state == 1 && counter > 4) {
			GamePlay.getTimer().getLast().stop();
			Map.getBomberman().getBombs().addFirst(new Bomb(false));
			Map.getActiveBombs().removeLast();
			GamePlay.getTimer().removeLast();
			counter = 0;
		} else {
			counter++;
		}
	}


	//setters
	public void setXval(int i){
		xval = i;
	}
	public void setYval(int i){
		yval = i;
	}
	public void setActive(boolean b){
		active = b;
	}

	public void setEscaped(boolean b){
		escaped = b;
	}

	//getters
	public int getXval(){
		return xval;
	}
	public int getYval(){
		return yval;
	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public boolean getActive(){
		return active;
	}

	public boolean getEscaped(){
		return escaped;
	}

	public Explosion[] getPersonalExplosions(){
		return personalExplosions;
	}

	public boolean getUsed() {
		return used;
	}

	public ScheduledExecutorService getSchedule(){
		return scheduler;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!Map.isPaused()) {
			run();
		}
	}
}
