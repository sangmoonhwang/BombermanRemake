package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import Controller.Map;
import Controller.ModifyAccount;


public class DrawMenu{
	//variables
	private boolean running;
	
	//objects
	private JFrame menuFrame;
	private JButton playButton;
	private JButton logoutButton;
	private JButton modifyButton;
	private JButton leaderButton;
	private JButton loadButton;
	private JButton saveButton;
	
	//singleton
	private static DrawMenu instance = new DrawMenu();
	
	private DrawMenu() {
		//variables
		running = false;
		
		//objects
		menuFrame = new JFrame();
		playButton = new JButton("New Game");
		loadButton = new JButton("Load saved game");
		leaderButton = new JButton("View Leaderboards");
		modifyButton = new JButton("Modify Account");
		logoutButton = new JButton("Logout");
		saveButton = new JButton("Save Game");
	}
	
	//singleton
	public static DrawMenu getInstance() {
		return instance;
	}
	
	public void run() {
		//only makeFrame once
		if(!running){
			makeFrame();
		}
		menuFrame.setVisible(true);
	}
	
	public void makeFrame() {
		running = true;
		
		//setup frame
		menuFrame.setSize(800,500);
		menuFrame.setUndecorated(true);
		menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		menuFrame.setLocation(dim.width/2-menuFrame.getSize().width/2, dim.height/2-menuFrame.getSize().height/2);
		menuFrame.setLayout(new GridLayout(2,3));
		
		//add buttons
		menuFrame.add(playButton);
		menuFrame.add(modifyButton);
		menuFrame.add(logoutButton);
		menuFrame.add(leaderButton);
		menuFrame.add(loadButton);
		menuFrame.add(saveButton);
		
		addButtons();
	}
	
	public void addButtons(){
		//play button
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread(){
					public void run(){
						Map play = new Map(30);//should take user input of levels or next level when current level clears
						Map.set_IsPaused(false);
						//play.run();
					}
				};
				viewFrame(false);
				thread.start();
			}
		});
		
		//logout button
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DrawLogin loginMenu = DrawLogin.getInstance();
				loginMenu.viewFrame(true);
				viewFrame(false);
			}
		});
		
		//load button
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Show user's loaded games
				System.out.println("Load Game");
			}
		});
		
		//save button
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Save the user's current game, shouldn't be clickable if user is not in game
				System.out.println("Save Game");
			}
		});
		
		//modify button
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModifyAccount md = new ModifyAccount();
				viewFrame(false);
				//Show modifyAccount GUI
				System.out.println("Modify Account");
			}
		});
		
		//leaderboard button
		leaderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Display leaderboard
				System.out.println("View Leaderboards");
			}
		});
	}
	
	//setters
	public void viewFrame(boolean b){
		menuFrame.setVisible(b);
	}
}