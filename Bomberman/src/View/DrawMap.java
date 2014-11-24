package View;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class DrawMap{
	
	//variables
	private boolean running;
	
	//objects
	private JFrame gameFrame;
	private DrawGameObject gamePanel;
	
	//singleton
	private static DrawMap instance = new DrawMap();
		
	public DrawMap(){
		
		//variables
		running = false;
		
		//objects
		gamePanel = new DrawGameObject();
		gameFrame = new JFrame();

	}

	//singleton
	public static DrawMap getInstance(){
		return instance;
	}


	public void run(){
		//only make frame one time
		if(!running){
			makeFrame();
		}
		gameFrame.setVisible(true);
	}

	public void makeFrame(){
		running = true;
		
		gameFrame.setSize(800,648);
		gameFrame.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		gameFrame.setLocation(dim.width/2-gameFrame.getSize().width/2, dim.height/2-gameFrame.getSize().height/2);

		gameFrame.add(gamePanel);
		gameFrame.setVisible(true);

	}

	//update panel
	public void draw(){
		gamePanel.repaint();
	}
	
	//getters
	public JFrame getFrame(){
		return gameFrame;
	}

}