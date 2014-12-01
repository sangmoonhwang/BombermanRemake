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

/**
 * This class is responsible for user interaction in the main gameplay
 *
 */
public class GamePlay implements Runnable, FocusListener, KeyListener {
	/**
	 * switches for key holding
	 */
	private static boolean shutdown,l_Pressed, r_Pressed, u_Pressed, d_Pressed;
	private static Map play;
	private static DrawMap d;
	private Bomberman bombman;
	/**
	 * constructor
	 * @param level level
	 * @param game game controller to use
	 */
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
			d_Pressed = true;
			play.setVelY(bombman.getSpeed());//2
			if(!l_Pressed && !r_Pressed)
				bombman.setDirection(2);
			else if(l_Pressed)
				bombman.setDirection(5);
			else if(r_Pressed)
				bombman.setDirection(6);
		}
		else if(value != KeyEvent.VK_DOWN && value ==KeyEvent.VK_UP){
			u_Pressed = true;
			play.setVelY(-bombman.getSpeed());//-2
			if(!l_Pressed && !r_Pressed)
				bombman.setDirection(0);
			else if(l_Pressed)
				bombman.setDirection(4);
			else if(r_Pressed)
				bombman.setDirection(7);
		}
		else if(value == KeyEvent.VK_LEFT && value !=KeyEvent.VK_RIGHT){
			l_Pressed = true;
			play.setBombermanState(2);
			play.setVelX(-bombman.getSpeed());//-2
			if(!u_Pressed && !d_Pressed)
				bombman.setDirection(1);
			else if(u_Pressed)
				bombman.setDirection(4);
			else if(d_Pressed)
				bombman.setDirection(5);
		}
		else if(value == KeyEvent.VK_RIGHT && value !=KeyEvent.VK_LEFT){
			r_Pressed = true;
			play.setBombermanState(1);
			play.setVelX(bombman.getSpeed());//2
			if(!u_Pressed && !d_Pressed)
				bombman.setDirection(3);
			else if(u_Pressed)
				bombman.setDirection(7);
			else if(d_Pressed)
				bombman.setDirection(6);
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
		if(value == KeyEvent.VK_UP && value == KeyEvent.VK_DOWN && value == KeyEvent.VK_LEFT && value == KeyEvent.VK_RIGHT)
			bombman.setMoving(false);
		if(value == KeyEvent.VK_DOWN) {
			d_Pressed = false;
			if(play.getyVel() == bombman.getSpeed())//2
				play.setVelY(0);
			if(bombman.getDirection() == 2)
				bombman.setMoving(false);
			else if(bombman.getDirection() == 5)
				bombman.setDirection(1);
			else if(bombman.getDirection() == 6)
				bombman.setDirection(3);

		} else if(value ==KeyEvent.VK_UP) {
			u_Pressed = false;
			if(play.getyVel() == -bombman.getSpeed())//-2
				play.setVelY(0);
			if(bombman.getDirection() == 0)
				bombman.setMoving(false);
			else if(bombman.getDirection() == 4)
				bombman.setDirection(1);
			else if(bombman.getDirection() == 7)
				bombman.setDirection(3);
		} else if(value == KeyEvent.VK_LEFT) {
			l_Pressed = false;
			if(play.getxVel() == -bombman.getSpeed())//-2
				play.setVelX(0);
			if(bombman.getDirection() == 1)
				bombman.setMoving(false);
			else if(bombman.getDirection() == 4)
				bombman.setDirection(0);
			else if(bombman.getDirection() == 5)
				bombman.setDirection(2);
		} else if(value == KeyEvent.VK_RIGHT) {
			r_Pressed = false;
			if(play.getxVel() == bombman.getSpeed()) //2
				play.setVelX(0);
			if(bombman.getDirection() == 3)
				bombman.setMoving(false);
			else if(bombman.getDirection() == 6)
				bombman.setDirection(2);
			else if(bombman.getDirection() == 7)
				bombman.setDirection(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		int value = e.getKeyCode();
		bombman.setMoving(true);
		if (value == KeyEvent.VK_DOWN && value !=KeyEvent.VK_UP){
			play.setVelY(bombman.getSpeed());//2
			if(value != KeyEvent.VK_LEFT && value !=KeyEvent.VK_RIGHT)
				bombman.setDirection(2);
			else if(value == KeyEvent.VK_LEFT)
				bombman.setDirection(5);
			else 
				bombman.setDirection(6);
		}
		else if(value != KeyEvent.VK_DOWN && value ==KeyEvent.VK_UP){
			play.setVelY(-bombman.getSpeed());//-2
			if(value != KeyEvent.VK_LEFT && value !=KeyEvent.VK_RIGHT)
				bombman.setDirection(0);
			else if(value == KeyEvent.VK_LEFT)
				bombman.setDirection(4);
			else
				bombman.setDirection(7);
		}
		else if(value == KeyEvent.VK_LEFT && value !=KeyEvent.VK_RIGHT){
			play.setBombermanState(2);
			play.setVelX(-bombman.getSpeed());//-2
			if(value != KeyEvent.VK_DOWN && value !=KeyEvent.VK_UP)
				bombman.setDirection(1);
			else if(value == KeyEvent.VK_UP)
				bombman.setDirection(4);
			else
				bombman.setDirection(5);
		}
		else if(value == KeyEvent.VK_RIGHT && value !=KeyEvent.VK_LEFT){
			play.setBombermanState(1);
			play.setVelX(bombman.getSpeed());//2
			if(value != KeyEvent.VK_DOWN && value !=KeyEvent.VK_UP)
				bombman.setDirection(3);
			else if(value == KeyEvent.VK_UP)
				bombman.setDirection(7);
			else
				bombman.setDirection(6);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}    
	
	public DrawMap getDrawMap(){
		return d;
	}
	
	public boolean getShutDown(){
		return shutdown;
	}


}
