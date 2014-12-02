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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Model.Database;
import Model.User;
import View.DrawMenu;
import View.DrawPauseMenu;

/**
 * This class is responsible for leaderboard control
 *
 */
public class Leaderboard extends Database {
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
	public Leaderboard() throws IOException{
		setUsers(returnAllUsers());
		setTopTen(new User[10]);
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
	public void drawpanel() {
		header_login.setText("Leaderboards");
		header_login.setFont(new Font("Serif", Font.BOLD, 40));
		JTable table = new JTable();
		controlPanel.add(table);
		
		String columnNames[] = {"Column 1", "Column 2", "Column 3", "Column 4", "Column 5"};
		
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		for(int i=0; i<10; i++){
			Object rowData[] = { i+1 + ".", topTen[i].getUsername(),  topTen[i].getTotalScore(), " Number of Plays ",  topTen[i].getNumOfPlay()};
			model.addRow(rowData);
		}
		User user = Login.getUser();
		Object rowData[] = { "You", user.getUsername(), user.getTotalScore(), " Number of Plays ", user.getNumOfPlay()};
		model.addRow(rowData);
		table.setModel(model);

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



		main.revalidate();
		main.repaint();

	}

	/**
	 * sorts the User data with total Points
	 * @param None
	 * @return None
	 */
	public void sort(){
		for(int i = 0; i < 10; i++){
			int highest = 0;
			int winner = 0;
			for(int j = 0; j < getUsers().size(); j++){
				if(getUsers().get(j).getTotalScore() > highest){
					highest = getUsers().get(j).getTotalScore();
					getTopTen()[i] = getUsers().get(j);
					winner = j;
				}
			}
			if(getUsers().size() != 0){
				getUsers().remove(winner);
			}
		}
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public User[] getTopTen() {
		return topTen;
	}

	public void setTopTen(User[] topTen) {
		this.topTen = topTen;
	}
}