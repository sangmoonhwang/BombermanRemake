package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import Controller.Leaderboard;
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
		playButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					leaderButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT ){
					saveButton.requestFocus();
				}
				else if(value ==KeyEvent.VK_UP){
					leaderButton.requestFocus();
				}
				else if( value == KeyEvent.VK_RIGHT){
					modifyButton.requestFocus();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					leaderButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT ){
					saveButton.requestFocus();
				}
				else if(value ==KeyEvent.VK_UP){
					leaderButton.requestFocus();
				}
				else if( value == KeyEvent.VK_RIGHT){
					modifyButton.requestFocus();
				}
			}
		});
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread(){
					public void run(){
						Map.setLife(5);
						Map play = new Map(2);//TODO should take user input of levels or next level when current level clears
						Map.setPaused(false);
						//play.run();
					}
				};
				viewFrame(false);
				thread.start();
			}
		});
		
		//logout button
		logoutButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					saveButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT ){
					modifyButton.requestFocus();
				}
				else if(value ==KeyEvent.VK_UP){
					saveButton.requestFocus();
				}
				else if( value == KeyEvent.VK_RIGHT){
					leaderButton.requestFocus();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					saveButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT ){
					modifyButton.requestFocus();
				}
				else if(value ==KeyEvent.VK_UP){
					saveButton.requestFocus();
				}
				else if( value == KeyEvent.VK_RIGHT){
					leaderButton.requestFocus();
				}
			}
		});
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DrawLogin loginMenu = DrawLogin.getInstance();
				loginMenu.viewFrame(true);
				viewFrame(false);
			}
		});
		
		//load button
		loadButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					modifyButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT ){
					leaderButton.requestFocus();
				}
				else if(value ==KeyEvent.VK_UP){
					modifyButton.requestFocus();
				}
				else if( value == KeyEvent.VK_RIGHT){
					saveButton.requestFocus();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					modifyButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT ){
					leaderButton.requestFocus();
				}
				else if(value ==KeyEvent.VK_UP){
					modifyButton.requestFocus();
				}
				else if( value == KeyEvent.VK_RIGHT){
					saveButton.requestFocus();
				}
			}
		});
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Show user's loaded games
				System.out.println("Load Game");
			}
		});
		
		//save button
		saveButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					logoutButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT ){
					loadButton.requestFocus();
				}
				else if(value ==KeyEvent.VK_UP){
					logoutButton.requestFocus();
				}
				else if( value == KeyEvent.VK_RIGHT){
					playButton.requestFocus();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					logoutButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT ){
					loadButton.requestFocus();
				}
				else if(value ==KeyEvent.VK_UP){
					logoutButton.requestFocus();
				}
				else if( value == KeyEvent.VK_RIGHT){
					playButton.requestFocus();
				}
			}
		});
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Save the user's current game, shouldn't be clickable if user is not in game
				System.out.println("Save Game");
			}
		});
		
		//modify button
		modifyButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					loadButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT ){
					playButton.requestFocus();
				}
				else if(value ==KeyEvent.VK_UP){
					loadButton.requestFocus();
				}
				else if( value == KeyEvent.VK_RIGHT){
					logoutButton.requestFocus();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					loadButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT ){
					playButton.requestFocus();
				}
				else if(value ==KeyEvent.VK_UP){
					loadButton.requestFocus();
				}
				else if( value == KeyEvent.VK_RIGHT){
					logoutButton.requestFocus();
				}
			}
		});
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
		leaderButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					playButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT ){
					logoutButton.requestFocus();
				}
				else if(value ==KeyEvent.VK_UP){
					playButton.requestFocus();
				}
				else if( value == KeyEvent.VK_RIGHT){
					loadButton.requestFocus();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					playButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT ){
					logoutButton.requestFocus();
				}
				else if(value ==KeyEvent.VK_UP){
					playButton.requestFocus();
				}
				else if( value == KeyEvent.VK_RIGHT){
					loadButton.requestFocus();
				}
			}
		});
		leaderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Display leaderboard
				try {
					Leaderboard lb = new Leaderboard();
				} catch (IOException e1) {
				}
				viewFrame(false);
				System.out.println("View Leaderboards");
			}
		});
	}
	
	//setters
	public void viewFrame(boolean b){
		menuFrame.setVisible(b);
	}
}