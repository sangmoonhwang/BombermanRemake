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

import Controller.Login;
import Controller.Map;
import Controller.ModifyAccount;


public class DrawMenu{
	private JFrame menuFrame;
	
	private JButton playButton;
	private JButton logoutButton;
	private JButton modifyButton;
	private JButton leaderButton;
	private JButton loadButton;
	private JButton saveButton;
	private static DrawMenu instance = new DrawMenu();
	
	private DrawMenu() {
		playButton = new JButton("New Game");
		loadButton = new JButton("Load saved game");
		leaderButton = new JButton("View Leaderboards");
		modifyButton = new JButton("Modify Account");
		logoutButton = new JButton("Logout");
		saveButton = new JButton("Save Game");
		menuFrame = new JFrame();
	}
	
	public static DrawMenu getInstance() {
		return instance;
	}
	
	public void run() {
		makeFrame();
	}
	
	public void makeFrame() {
		menuFrame.setSize(800,500);
		//menuFrame.setUndecorated(true);
		menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		menuFrame.setLocation(dim.width/2-menuFrame.getSize().width/2, dim.height/2-menuFrame.getSize().height/2);
		menuFrame.setLayout(new GridLayout(2,1));
		menuFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		menuFrame.add(playButton);
		menuFrame.add(modifyButton);
		menuFrame.add(logoutButton);
		menuFrame.add(leaderButton);
		menuFrame.add(loadButton);
		menuFrame.add(saveButton);
		menuFrame.setVisible(true);
	
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread(){
					public void run(){
						Map play = new Map(50);                   //should take user input of levels or next level when current level clears
					}
				};
				viewFrame(false);
				thread.start();
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
		
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Show user's loaded games
				System.out.println("Load Game");
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Save the user's current game, shouldn't be clickable if user is not in game
				System.out.println("Save Game");
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
		
		leaderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Display leaderboard
				System.out.println("View Leaderboards");
			}
		});
	}
	
	
	public void viewFrame(boolean b){
		menuFrame.setVisible(b);
	}
}