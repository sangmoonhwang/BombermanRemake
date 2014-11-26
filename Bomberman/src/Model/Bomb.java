package Model;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import Controller.Map;

public class Bomb implements Serializable, Runnable{
	private int xval, yval;
	private int height, width;
	private boolean active;
	private boolean escaped;
	private Explosion[] personalExplosions;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
	private boolean used;
	private int numOfBomb;

	public Bomb(boolean active) {
		xval = yval = 0;
		height = width = 50;
		this.active = active;
		escaped = false;
		personalExplosions = new Explosion[5];
		for(int i = 0; i < 5; i++){
			personalExplosions[i] = new Explosion();
		}
		used = false;
	}

	//explosion
	public void explode(){
		used = true;
		//active = false;
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
		long start = System.nanoTime();
		boolean shutdown = false;

		System.out.println("threadID" + Thread.currentThread().getId());
		while(!shutdown) {
			long now = System.nanoTime();
			if((now - start) >= 2000000000) {
				explode();
				System.out.println("threadID" + Thread.currentThread().getId() + "exploding");
				start = now;

				while(true) {
					now = System.nanoTime();
					if((now - start) >= 500000000) {
						for(int i = 0; i < 5; i++){
							personalExplosions[i].setExploding(false);
						}
						Map.getBomberman().getBombs().add(new Bomb(false));
						Map.getActiveBombs().remove(Map.getActiveBombs().size()-1);
						shutdown = true;
						break;
					}
				}
			}
		}
	}

	public void activate() {
/*
		final Runnable unExplode = new Runnable() {

			@Override
			public void run() {
				for(int i = 0; i < 4; i++){
					personalExplosions[i].setExploding(false);
				}
				Map.getBomberman().getBombs().add(new Bomb(false));
				Map.getActiveBombs().remove(Map.getActiveBombs().size()-1);
			}
		};

		final Runnable fuse = new Runnable() {

			@Override
			public void run() {
				if(!used){
					explode();
					scheduler.schedule(unExplode, 500, MILLISECONDS);
				}
			}

		};
		final ScheduledFuture<?> fuseHandle = scheduler.schedule(fuse, 2, SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() { fuseHandle.cancel(true); }
		}, 2, SECONDS);
		  */
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

}
