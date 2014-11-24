package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DrawMap{
	
	//variables
	private boolean running;
	
	//objects
	private JFrame gameFrame;
	private DrawGameObject gamePanel;
	private JLabel status;
	
	//singleton
	private static DrawMap instance = new DrawMap();
		
	public DrawMap(){
		
		//variables
		running = false;
		
		//objects
		gamePanel = new DrawGameObject();
		gameFrame = new JFrame();
		status = new JLabel();
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
	public void draw(){
		gamePanel.repaint();
	}
	
	//getters
	public JFrame getFrame(){
		return gameFrame;
	}
	public JLabel getStatusBar(){
		return status;
	}

}