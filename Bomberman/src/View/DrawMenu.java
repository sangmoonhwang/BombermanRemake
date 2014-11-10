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
import Controller.UserInput;


public class DrawMenu extends JFrame{
	private JFrame menuFrame;
	
	private JButton playButton;
	private JButton logoutButton;
	
	public DrawMenu(){
		playButton = new JButton("Play");
		logoutButton = new JButton("Logout");
		menuFrame = new JFrame();
	}
	
	public void run(){
		makeFrame();
	}
	
	public void makeFrame(){
		menuFrame.setSize(800,500);
		menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		menuFrame.setLocation(dim.width/2-menuFrame.getSize().width/2, dim.height/2-menuFrame.getSize().height/2);
		menuFrame.setLayout(new GridLayout(2,1));
		menuFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		menuFrame.add(playButton);
		menuFrame.add(logoutButton);
		menuFrame.setVisible(true);
	
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread() {
			        public void run() {
			        	UserInput play = new UserInput();
			        }
			    };
			    thread.start();
			}
		});
		
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread() {
			        public void run(){
			        	Login logout = new Login();
			        	logout.run();
			        }
			    };
			    thread.start();
			}
		});
	}
}