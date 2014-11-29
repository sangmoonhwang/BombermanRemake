package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controller.GamePlay;
import Controller.Leaderboard;
import Controller.Leaderboard_p;
import Controller.Login;
import Controller.Map;


/**
 * This class is responsible for drawing the paused menu
 *
 */
public class DrawPauseMenu{
	
	//frame
	/**
	 * Main frame for the paused menu
	 */
	private JFrame pauseFrame;
	
	//buttons
	/**
	 * Button to go back to the game
	 */
	private JButton resumeButton;
	/**
	 * Button to return to the main menu
	 */
	private JButton quitButton;
	/**
	 * Button to save the current game
	 */
	private JButton saveButton;
	/**
	 * Button to show leaderboard
	 */
	private JButton leaderboardButton;
	
	//variables
	/**
	 * running indicator
	 */
	private boolean running;
	/**
	 * Game controller
	 */
	private Map game;
	
	//singleton
	/**
	 * singletone object
	 */
	private static DrawPauseMenu instance = new DrawPauseMenu();
	
	/**
	 * constructor
	 */
	private DrawPauseMenu() {
		
		//frame
		pauseFrame = new JFrame();
		
		//buttons
		resumeButton = new JButton("Resume Game");
		quitButton = new JButton("Exit to Menu");
		saveButton = new JButton("Save Game");
		leaderboardButton = new JButton("View Leaderboards");
		
		//variables
		running = false;
	}
	
	//singleton
	/**
	 * getter for the singletone object
	 * @return singletone object
	 */
	public static DrawPauseMenu getInstance() {
		return instance;
	}
	
	/**
	 * activate the paused menu
	 */
	public void run(){
		//only make once
		if(!running){
			makeFrame();
		}
		viewFrame(true);
	}
	
	/**
	 * set up the paused menu
	 */
	public void makeFrame() {
		running = true;
		
		//set layout
		pauseFrame.setSize(800,500);
		pauseFrame.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		pauseFrame.setLocation(dim.width/2-pauseFrame.getSize().width/2, dim.height/2-pauseFrame.getSize().height/2);
		pauseFrame.setLayout(new GridLayout(1,3));
		
		//add buttons
		pauseFrame.add(resumeButton);
		pauseFrame.add(quitButton);
		pauseFrame.add(saveButton);
		pauseFrame.add(leaderboardButton);
		pauseFrame.setVisible(true);
		
		addButtons();
	}
	
	/**
	 * append the buttons to the display
	 */
	public void addButtons(){
		//resume button
		resumeButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN || value ==KeyEvent.VK_RIGHT){
					quitButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT || value ==KeyEvent.VK_UP){
					leaderboardButton.requestFocus();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN || value ==KeyEvent.VK_RIGHT){
					quitButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT || value ==KeyEvent.VK_UP){
					leaderboardButton.requestFocus();
				}
			}
		});
		resumeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//temporary variables
				DrawMap game = DrawMap.getInstance();
				Map.setPaused(false);
				game.getFrame().setVisible(true);
				viewFrame(false);
			}
		});		
		
		//quit button
		quitButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN || value ==KeyEvent.VK_RIGHT){
					saveButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT || value ==KeyEvent.VK_UP){
					resumeButton.requestFocus();
				}
			}
			
			
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN || value ==KeyEvent.VK_RIGHT){
					saveButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT || value ==KeyEvent.VK_UP){
					resumeButton.requestFocus();
				}
			}
		});
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//temporary variables
				DrawMap game = DrawMap.getInstance();
				DrawMenu menu = DrawMenu.getInstance();
			
				Map.setGameOver();
				Map.setPaused(false);
				game.getFrame().dispose();
				viewFrame(false);
				pauseFrame.dispose();
				
				menu.setRunnning(true);
				menu.viewFrame(true);
			}
		});
		
		leaderboardButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN || value ==KeyEvent.VK_RIGHT){
					resumeButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT || value ==KeyEvent.VK_UP){
					saveButton.requestFocus();
				}
			}
			
			
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN || value ==KeyEvent.VK_RIGHT){
					resumeButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT || value ==KeyEvent.VK_UP){
					saveButton.requestFocus();
				}
			}
		});
		
		leaderboardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//temporary variables
				try {
					Leaderboard_p ld = new Leaderboard_p();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//Map.setRunning(false);
				viewFrame(false);
			}
		});
		
		//save button
		saveButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN || value == KeyEvent.VK_RIGHT){
					leaderboardButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT || value ==KeyEvent.VK_UP){
					quitButton.requestFocus();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN || value == KeyEvent.VK_RIGHT){
					leaderboardButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT || value ==KeyEvent.VK_UP){
					quitButton.requestFocus();
				}
			}
		});
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("save stuff");
				try
				{	
					if(!(new File("save/" + Login.getUser().getUsername()).exists())){
						new File("save/" + Login.getUser().getUsername()).mkdir();
					}
					Map file = game;
					FileOutputStream fileOut;
					String name = JOptionPane.showInputDialog(pauseFrame, "Name of the save file? ", null);
					if(name.equalsIgnoreCase(null) || name.equals("")){
						Date date = new Date();
						fileOut = new FileOutputStream("save/" + Login.getUser().getUsername() + "/" + date.getYear() + 
								date.getMonth() + date.getDate() + date.getHours() + date.getMinutes() + ".ser");
					}
					else{
						fileOut = new FileOutputStream("save/" + Login.getUser().getUsername() + "/" + name + ".ser");
					}
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(file);
					out.close();
					fileOut.close();
					System.out.println("Serialized data is saved");
				}catch(IOException i)
				{
					i.printStackTrace();
				}
			}
		});
	}
	
	//setters
	/**
	 * visibility control method
	 * @param b true to show false to hide
	 */
	public void viewFrame(boolean b){
		pauseFrame.setVisible(b);
	}
	
	/**
	 * setter for the game controller
	 * @param g game controller to use
	 */
	public void setMap(Map g){
		this.game = g;
	}
	
	//getters
	/**
	 * getter for the running status
	 * @return running indicator
	 */
	public boolean isRunning(){
		return running;
	}
}