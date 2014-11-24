package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Controller.Map;


public class DrawPauseMenu{
	
	//frame
	private JFrame pauseFrame;
	
	//buttons
	private JButton resumeButton;
	private JButton quitButton;
	private JButton saveButton;
	
	//variables
	private boolean running;
	
	//singleton
	private static DrawPauseMenu instance = new DrawPauseMenu();
	
	private DrawPauseMenu() {
		
		//frame
		pauseFrame = new JFrame();
		
		//buttons
		resumeButton = new JButton("Resume Game");
		quitButton = new JButton("Exit to Menu");
		saveButton = new JButton("Save Game");
		
		//variables
		running = false;
	}
	
	//singleton
	public static DrawPauseMenu getInstance() {
		return instance;
	}
	
	public void run(){
		//only make once
		if(!running){
			makeFrame();
		}
		viewFrame(true);
	}
	
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
		pauseFrame.setVisible(true);
		
		addButtons();
	}
	
	public void addButtons(){
		//resume button
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
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//temporary variables
				DrawMap game = DrawMap.getInstance();
				DrawMenu menu = DrawMenu.getInstance();
				
				menu.viewFrame(true);
				Map.setRunning(false);
				game.getFrame().dispose();
				viewFrame(false);
			}
		});
		
		//save button
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("save stuff");
			}
		});
	}
	
	//setters
	public void viewFrame(boolean b){
		pauseFrame.setVisible(b);
	}
	
	//getters
	public boolean isRunning(){
		return running;
	}
}