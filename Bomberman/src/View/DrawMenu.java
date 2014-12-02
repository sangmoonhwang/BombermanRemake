package View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.xml.ws.Response;

import Controller.GamePlay;
import Controller.Leaderboard;
import Controller.LevelSelect;
import Controller.Login;
import Controller.Map;
import Controller.ModifyAccount;


/**
 * This class is responsible for displaying the main menu
 *
 */
public class DrawMenu{
	//variables
	/**
	 * running indicator
	 */
	private boolean running;
	/**
	 * Game controller
	 */
	Map game;
	/**
	 * Thread used to initiate the game
	 */
	Thread thread;
	/**
	 * main frame of the main menu
	 */
	private JFrame menuFrame;
	/**
	 * button to play a new game
	 */
	private JButton playButton;
	/**
	 * button to logout
	 */
	private JButton logoutButton;
	/**
	 * button to modify account
	 */
	private JButton modifyButton;
	/**
	 * button to show leaderboard
	 */
	private JButton leaderButton;
	/**
	 * button to load a saved game
	 */
	private JButton loadButton;
	/**
	 * button to select a game from a selected level
	 */
	private JButton selectButton;
	
	private Timer timer;

	//singleton
	/**
	 * singleton object
	 */
	private static DrawMenu instance = new DrawMenu();

	/**
	 * constructor
	 */
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
		selectButton = new JButton("Level Select");
	}

	//singleton
	/**
	 * getter for the singletone object
	 * @return singleton object
	 */
	public static DrawMenu getInstance() {
		return instance;
	}

	/**
	 * activate the main menu
	 */
	public void run() {
		//only makeFrame once
		if(!running){
			makeFrame();
		}
		menuFrame.setVisible(true);
	}

	/**
	 * setup the frame
	 */
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
		menuFrame.add(selectButton);

		addButtons();
	}

	/**
	 * append all buttons to the main menu display
	 */
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
					selectButton.requestFocus();
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
					selectButton.requestFocus();
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
				GamePlay play = new GamePlay(1,null);
				timer = new Timer(1000/60, play);
				timer.start();
				viewFrame(false);
			}
		});

		//logout button
		logoutButton.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					selectButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT ){
					modifyButton.requestFocus();
				}
				else if(value ==KeyEvent.VK_UP){
					selectButton.requestFocus();
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
					selectButton.requestFocus();
				}
				else if(value == KeyEvent.VK_LEFT ){
					modifyButton.requestFocus();
				}
				else if(value ==KeyEvent.VK_UP){
					selectButton.requestFocus();
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
					selectButton.requestFocus();
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
					selectButton.requestFocus();
				}
			}
		});
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = null;
				try
				{
					File[] files;
					//Show user's loaded games
					List<String> results = new ArrayList<String>();
					if((new File("save/" + Login.getUser().getUsername()).exists())){
						files = new File("save/" + Login.getUser().getUsername()).listFiles();

						//If this pathname does not denote a directory, then listFiles() returns null. 

						for (File file : files) {
							if (file.isFile()) {
								results.add(file.getName());
							}
						}
					}
					else{
						results.add(" ");
					}
					if (EventQueue.isDispatchThread()) {
						JPanel panel = new JPanel();
						panel.add(new JLabel("Please make a selection:"));
						DefaultComboBoxModel model = new DefaultComboBoxModel();
						for (String temp : results) {
							model.addElement(temp);
						}
						JComboBox comboBox = new JComboBox(model);
						panel.add(comboBox);

						int iResult = JOptionPane.showConfirmDialog(null, panel, "Flavor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						switch (iResult) {
						case JOptionPane.OK_OPTION:
							result = (String) comboBox.getSelectedItem();
							break;
						}
					}
					if((new File("save/" + Login.getUser().getUsername()).exists())){
						FileInputStream fileIn = new FileInputStream("save/" + Login.getUser().getUsername() + "/" + result);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						game = (Map) in.readObject();
						in.close();
						fileIn.close();
					}
				}catch(IOException i)
				{
					i.printStackTrace();
					return;
				}catch(ClassNotFoundException c)
				{
					System.out.println("map class not found");
					c.printStackTrace();
					return;
				}

				System.out.println("Load Game");
				viewFrame(false);
				GamePlay play = new GamePlay(0, game);
				timer = new Timer(1000/60, play);
				timer.start();
				viewFrame(false);
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

		selectButton.addKeyListener(new KeyListener() {

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
		selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewFrame(false);
				try {
					LevelSelect ls = new LevelSelect();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * getter for the running indicator
	 * @return running indicator
	 */
	public boolean getRunning() {
		return running;
	}
	/**
	 * getter for timer
	 * @return timer
	 */
	public Timer getTimer() {
		return timer;
	}

	//setters
	/**
	 * visibility control method
	 * @param b true to show false to hide
	 */
	public void viewFrame(boolean b){
		menuFrame.setVisible(b);
	}
	/**
	 * running indicator control method
	 * @param bool running status true if running false otherwise
	 */
	public void setRunnning(boolean bool) {
		running = bool;
	}
}