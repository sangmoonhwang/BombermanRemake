package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This class is responsible for showing the game play display
 *
 */
public class DrawMap implements Serializable{

	//variables
	/**
	 * running indicator
	 */
	private boolean running;

	//objects
	/**
	 * main frame
	 */
	private JFrame gameFrame;
	/**
	 * the main panel for the game play
	 */
	private DrawGameObject gamePanel;
	/**
	 * status bar
	 */
	private JLabel status;

	//singleton
	/**
	 * singletone object
	 */
	private static DrawMap instance = new DrawMap();

	/**
	 * constructor
	 */
	public DrawMap(){

		//variables
		running = false;

		//objects
		gamePanel = new DrawGameObject();
		gameFrame = new JFrame();
		status = new JLabel();
	}

	//singleton
	/**
	 * getter for the singletone object
	 * @return singletone object
	 */
	public static DrawMap getInstance(){
		return instance;
	}


	/**
	 * activate the game play display
	 */
	public void run(){
		//only make frame one time
		if(!running){
			makeFrame();
		}
		gameFrame.setVisible(true);
	}

	/**
	 * set up the gameplay display
	 */
	public void makeFrame(){
		running = true;

		gameFrame.setSize(800,666);
		gameFrame.setUndecorated(true);
		gameFrame.setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		gameFrame.setLocation(dim.width/2-gameFrame.getSize().width/2, dim.height/2-gameFrame.getSize().height/2);

		gameFrame.add(status,BorderLayout.NORTH);
		gameFrame.add(gamePanel);
		gameFrame.setVisible(true);

	}

	//update panel
	/**
	 * update the game panel
	 */
	public void draw(){
		gamePanel.repaint();
	}

	//getters
	/**
	 * getter for the main frame
	 * @return main frame
	 */
	public JFrame getFrame(){
		return gameFrame;
	}
	/**
	 * getter for the status bar
	 * @return status bar
	 */
	public JLabel getStatusBar(){
		return status;
	}

}