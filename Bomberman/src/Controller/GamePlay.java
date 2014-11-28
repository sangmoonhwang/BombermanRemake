package Controller;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Bomb;
import Model.Bomberman;
import View.DrawMap;
import View.DrawMenu;
import View.DrawPauseMenu;

public class GamePlay implements Runnable, FocusListener, KeyListener {
	private static boolean shutdown;
	private static Map play;
	private static DrawMap d;
	private Bomberman bombman;
	public GamePlay(int level, Map game) {
		shutdown = false;
		if(game != null) {
			play = game;
			Map.setPaused(false);
		} else {
			play = new Map(level);
			Map.setLife(2);
		}
		bombman = Map.getBomberman();
		d = DrawMap.getInstance();
		d.run();
		d.getFrame().addFocusListener(this);
		d.getFrame().addKeyListener(this);
		d.getFrame().requestFocus();
	}

	public void run() {
		System.out.println("threadID" + Thread.currentThread().getId() + "running");
		while(!shutdown) {
			play.run();
			d.draw();
			d.getStatusBar().setText("Level: "+ play.getLevel() +"        Time: " + play.getGameTime() + "        Life: " + play.getLife() + "       Score: " + play.getUser().getTotalScore());
			if(play.getGameTime() < 0){
				System.out.println("Times up!");
				d.getStatusBar().setText("Times Up!");
				play.dieBombman();
				shutdown = true;
				break;
			}
			
			if(play.getGameOver()) {
				shutdown = true;
				System.out.println("Gameover");
			}
			while(play.getPause()) {
				System.out.println("Paused");
				play.run();
			}
		}
		System.out.println("Thread terminate");
	}

	public static boolean getPause() {
		return play.getPause();
	}
	public static void setShutdown(boolean bool) {
		shutdown = bool;
	}

	//react to keyPress by moving Bomberman
	public void keyPressed ( KeyEvent e ){
		int value = e.getKeyCode();
		bombman.setMoving(true);
		if (value == KeyEvent.VK_DOWN && value !=KeyEvent.VK_UP){
			play.setVelY(bombman.getSpeed());//2
		if(value != KeyEvent.VK_LEFT && value !=KeyEvent.VK_RIGHT)
				bombman.setDirection(2);
		}
		else if(value != KeyEvent.VK_DOWN && value ==KeyEvent.VK_UP){
			play.setVelY(-bombman.getSpeed());//-2
			if(value != KeyEvent.VK_LEFT && value !=KeyEvent.VK_RIGHT)
				bombman.setDirection(0);
		}
		else if(value == KeyEvent.VK_LEFT && value !=KeyEvent.VK_RIGHT){
			play.setBombermanState(2);
			play.setVelX(-bombman.getSpeed());//-2
			if(value != KeyEvent.VK_DOWN && value !=KeyEvent.VK_UP)
				bombman.setDirection(1);
		}
		else if(value == KeyEvent.VK_RIGHT && value !=KeyEvent.VK_LEFT){
			play.setBombermanState(1);
			play.setVelX(bombman.getSpeed());//2
			if(value != KeyEvent.VK_DOWN && value !=KeyEvent.VK_UP)
				bombman.setDirection(3);
		} else if(value == KeyEvent.VK_ESCAPE || value == KeyEvent.VK_SPACE){
			play.setVelY(0);
			play.setVelX(0);
			if(!play.getPause()){
				Map.setPaused(true);
				d.getFrame().setVisible(false);
				DrawPauseMenu.getInstance().run();
				DrawPauseMenu.getInstance().setMap(play);
			}
		} else if(value == KeyEvent.VK_X && Bomberman.detonate == true && !Map.getActiveBombs().isEmpty()){
			for(int i =0; i< Map.getActiveBombs().size(); i++){
				if(!Map.getActiveBombs().get(i).getUsed()){
					final Runnable unExplode = new Runnable() {

						@Override
						public void run() {
						}
					};
					Map.getActiveBombs().get(i).explode();
					Map.getActiveBombs().get(i).getSchedule().schedule(unExplode, 500, MILLISECONDS);

					break;
				}
			}
			//activeBombs.get(0).explode();
			//explosions = activeBombs.get(activeBombs.size()-1).getPersonalExplosions();
		} else if(value == KeyEvent.VK_Z && !bombman.getBombs().isEmpty()){
			Map.getActiveBombs().addFirst(new Bomb(true));
			bombman.getBombs().removeLast();
			int tilex = (int)bombman.getXval() + (int)(0.5*bombman.getWidth());
			int tiley = (int)bombman.getYval() + (int)(0.5*bombman.getHeight());
			tilex = (tilex/50) * 50;
			tiley = (tiley/50) * 50;

			Map.getActiveBombs().getFirst().setXval(tilex);
			Map.getActiveBombs().getFirst().setYval(tiley);
			Thread thread = new Thread(Map.getActiveBombs().getFirst());
			thread.start();
		}
	}

	//stop moving when key is released
	public void keyReleased(KeyEvent e) {
		int value = e.getKeyCode();
		bombman.setMoving(false);
		if(value == KeyEvent.VK_DOWN) {
			if(play.getyVel() == bombman.getSpeed())//2
				play.setVelY(0);
		} else if(value ==KeyEvent.VK_UP) {
			if(play.getyVel() == -bombman.getSpeed())//-2
				play.setVelY(0);
		} else if(value == KeyEvent.VK_LEFT) {
			if(play.getxVel() == -bombman.getSpeed())//-2
				play.setVelX(0);
		} else if(value == KeyEvent.VK_RIGHT) {
			if(play.getxVel() == bombman.getSpeed()) //2
				play.setVelX(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}    


}
