package View;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;



public class DrawMap{
	private DrawGameObject gamePanel;
	private JFrame gameFrame;
	private static DrawMap instance = new DrawMap();
	
	private DrawMap(){
		gamePanel = new DrawGameObject();
		gameFrame = new JFrame();

	}

	public static DrawMap getInstance(){
		return instance;
	}


	public void run(){
		makeFrame();
		gameFrame.setVisible(true);
	}

	public void makeFrame(){
		gameFrame.setSize(800,648);
		gameFrame.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		gameFrame.setLocation(dim.width/2-gameFrame.getSize().width/2, dim.height/2-gameFrame.getSize().height/2);

		gameFrame.setVisible(true);

		gameFrame.add(gamePanel);
	}

	public void draw(){
		gamePanel.repaint();
	}
	
	public JFrame getFrame(){
		return gameFrame;
	}

}