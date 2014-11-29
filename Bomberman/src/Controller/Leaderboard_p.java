package Controller;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Database;
import Model.User;
import View.DrawMenu;
import View.DrawPauseMenu;

/**
 * This class is responsible for leaderboard control when the game is paused
 *
 */
public class Leaderboard_p extends Database {
	public String newUsername;
	public String oldPassword;
	public String newPassword;
	private JFrame main;
	private JPanel controlPanel;	
	private JLabel header_login;
	private ArrayList<User> users;
	private User[] topTen;
	
	//draw modifyAccount view
	/**
	 * constructor
	 * @throws IOException
	 */
	public Leaderboard_p() throws IOException{
		users = returnAllUsers();
		topTen = new User[10];
		sort();
		main = new JFrame("Leaderboards");
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
		controlPanel = new JPanel();
		controlPanel.setLayout (new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));//(new FlowLayout());
		main.add(header_login);
		main.add(controlPanel);
		main.setVisible(true);
		drawpanel();
	}
	
	/**
	 * set up the GUI
	 */
	private void drawpanel() {
		header_login.setText("Leaderboards");
		header_login.setFont(new Font("Serif", Font.BOLD, 40));
		
		JLabel first = new JLabel("1. " + topTen[0].getUsername() + ", " + topTen[0].getTotalScore() + " Number of Plays " + topTen[0].getNumOfPlay());
		JLabel second = new JLabel("2. " + topTen[1].getUsername() + ", " + topTen[1].getTotalScore() + " Number of Plays " + topTen[1].getNumOfPlay());
		JLabel third = new JLabel("3. " + topTen[2].getUsername() + ", " + topTen[2].getTotalScore() + " Number of Plays " + topTen[2].getNumOfPlay());
		JLabel fourth = new JLabel("4. " + topTen[3].getUsername() + ", " + topTen[3].getTotalScore() + " Number of Plays " + topTen[3].getNumOfPlay());
		JLabel fifth = new JLabel("5. " + topTen[4].getUsername() + ", " + topTen[4].getTotalScore() + " Number of Plays " + topTen[4].getNumOfPlay());
		JLabel sixth = new JLabel("6. " + topTen[5].getUsername() + ", " + topTen[5].getTotalScore() + " Number of Plays " + topTen[5].getNumOfPlay());
		JLabel seventh = new JLabel("7. " + topTen[6].getUsername() + ", " + topTen[6].getTotalScore() + " Number of Plays " + topTen[6].getNumOfPlay());
		JLabel eighth = new JLabel("8. " + topTen[7].getUsername() + ", " + topTen[7].getTotalScore() + " Number of Plays " + topTen[7].getNumOfPlay());
		JLabel ninth = new JLabel("9. " + topTen[8].getUsername() + ", " + topTen[8].getTotalScore() + " Number of Plays " + topTen[8].getNumOfPlay());
		JLabel tenth = new JLabel("10. " + topTen[9].getUsername() + ", " + topTen[9].getTotalScore() + " Number of Plays " + topTen[9].getNumOfPlay());
		JLabel viewer = new JLabel("You: " + Login.getUser().getUsername() + ", " + Login.getUser().getTotalScore() + " Number of Plays " + Login.getUser().getNumOfPlay());
		first.setFont(new Font("Serif", Font.BOLD, 20));
		second.setFont(new Font("Serif", Font.BOLD, 20));
		third.setFont(new Font("Serif", Font.BOLD, 20));
		fourth.setFont(new Font("Serif", Font.BOLD, 20));
		fifth.setFont(new Font("Serif", Font.BOLD, 20));
		sixth.setFont(new Font("Serif", Font.BOLD, 20));
		seventh.setFont(new Font("Serif", Font.BOLD, 20));
		eighth.setFont(new Font("Serif", Font.BOLD, 20));
		ninth.setFont(new Font("Serif", Font.BOLD, 20));
		tenth.setFont(new Font("Serif", Font.BOLD, 20));
		viewer.setFont(new Font("Serif", Font.BOLD, 20));
		controlPanel.add(first);
		controlPanel.add(second);
		controlPanel.add(third);
		controlPanel.add(fourth);
		controlPanel.add(fifth);
		controlPanel.add(sixth);
		controlPanel.add(seventh);
		controlPanel.add(eighth);
		controlPanel.add(ninth);
		controlPanel.add(tenth);
		controlPanel.add(viewer);
		


		JButton back = new JButton("Main Menu");
		controlPanel.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//back to menu
				DrawMenu menu = DrawMenu.getInstance();
				
				menu.viewFrame(true);
				main.setVisible(false);
			}
		});
		
		JButton back2 = new JButton("Pause Menu");
		controlPanel.add(back2);
		back2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//back to menu
				DrawPauseMenu pauseMenu = DrawPauseMenu.getInstance();
				
				pauseMenu.viewFrame(true);
				main.setVisible(false);
			}
		});


		
		main.revalidate();
		main.repaint();

	}

	/**
	 * sorts the User data with total Points
	 * @param None
	 * @return None
	 */
	private void sort(){
		for(int i = 0; i < 10; i++){
			int highest = 0;
			int winner = 0;
			for(int j = 0; j < users.size(); j++){
				if(users.get(j).getTotalScore() > highest){
					highest = users.get(j).getTotalScore();
					topTen[i] = users.get(j);
					winner = j;
				}
			}
			if(users.size() != 0){
				users.remove(winner);
			}
		}
	}
}