package Controller;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Model.Database;
import Model.User;
import View.DrawMenu;
import View.DrawPauseMenu;

/**
 * This class starts a new game at a selected level
 *
 */
public class LevelSelect extends Database {
	public String newUsername;
	public String oldPassword;
	public String newPassword;
	private JFrame main;
	private JPanel controlPanel;	
	private JLabel header_login;
	private ArrayList<User> users;
	private User[] topTen;
	private JLabel levelShow;
	private int level = 1;
	private Timer timer;
	
	//draw modifyAccount view
	/**
	 * constructor
	 * @throws IOException
	 */
	public LevelSelect() throws IOException{
		main = new JFrame("Level Select");
		main.setSize(1000, 800);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		main.setLocation(dim.width/2-main.getSize().width/2, dim.height/2-main.getSize().height/2);

		main.setLayout(new GridLayout(2,1));
		main.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		header_login = new JLabel("",SwingConstants.CENTER);
		levelShow = new JLabel("",SwingConstants.CENTER);
		controlPanel = new JPanel();
		controlPanel.setLayout (new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));//(new FlowLayout());
		main.add(header_login);
		main.add(controlPanel);
		main.setVisible(true);
		drawpanel();
	}
	
	/**
	 * draw GUI
	 */
	public void drawpanel() {
		header_login.setText("Select Level using Up or Down arrow keys.");
		header_login.setFont(new Font("Serif", Font.BOLD, 40));
		levelShow.setText("Level: " + level + " (Max: " + Login.getUser().getLevelCompleted() + ")" + " Press Enter to Play");
		levelShow.setFont(new Font("Serif", Font.BOLD, 30));

		controlPanel.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					if(level > 1)
						level--;
				}
				else if(value ==KeyEvent.VK_UP){
					if(level < Login.getUser().getLevelCompleted())
						level++;
				}
				else if( value == KeyEvent.VK_ENTER){
					GamePlay play = new GamePlay(level, null);
					timer = new Timer(1000/60, play);
					timer.start();
					main.setVisible(false);
				}
				levelShow.revalidate();
				levelShow.repaint();
				main.revalidate();
				main.repaint();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					if(level > 1)
						level--;
				}
				else if(value ==KeyEvent.VK_UP){
					if(level < Login.getUser().getLevelCompleted())
						level++;
				}
				else if(value == KeyEvent.VK_ENTER){
					GamePlay play = new GamePlay(level, null);
					timer = new Timer(1000/60, play);
					timer.start();
					main.setVisible(false);
				}
				levelShow.revalidate();
				levelShow.repaint();
				main.revalidate();
				main.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				levelShow.setText("Level: " + level + " (Max: " + Login.getUser().getLevelCompleted() + ")" + " Press Enter to Play");
				levelShow.setFont(new Font("Serif", Font.BOLD, 30));
				levelShow.revalidate();
				levelShow.repaint();
				main.revalidate();
				main.repaint();
			}
		});

		controlPanel.add(levelShow);

		JButton back = new JButton("Main Menu");
		controlPanel.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DrawMenu menu = DrawMenu.getInstance();

				menu.viewFrame(true);
				main.setVisible(false);
			}
		});

		back.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					if(level > 1)
						level--;
				}

				else if(value ==KeyEvent.VK_UP){
					if(level < Login.getUser().getLevelCompleted())
						level++;
				}
				else if( value == KeyEvent.VK_ENTER ){
					GamePlay play = new GamePlay(level, null);
					timer = new Timer(1000/60, play);
					timer.start();
					main.setVisible(false);
				}
				levelShow.revalidate();
				levelShow.repaint();
				main.revalidate();
				main.repaint();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int value = e.getKeyCode();
				if (value == KeyEvent.VK_DOWN){
					if(level > 1)
						level--;
				}
				else if(value ==KeyEvent.VK_UP){
					if(level < Login.getUser().getLevelCompleted())
						level++;
				}
				else if( value == KeyEvent.VK_ENTER){
					GamePlay play = new GamePlay(level, null);
					timer = new Timer(1000/60, play);
					timer.start();
					main.setVisible(false);
				}
				levelShow.revalidate();
				levelShow.repaint();
				main.revalidate();
				main.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				levelShow.setText("Level: " + level + " (Max: " + Login.getUser().getLevelCompleted() + ")" + " Press Enter to Play");
				levelShow.setFont(new Font("Serif", Font.BOLD, 30));
				levelShow.revalidate();
				levelShow.repaint();
				main.revalidate();
				main.repaint();
			}
		});

		levelShow.revalidate();
		levelShow.repaint();
		main.revalidate();
		main.repaint();

	}
	
	/**
	 * getter for timer
	 * @return timer
	 */
	public Timer getTimer() {
		return timer;
	}


}