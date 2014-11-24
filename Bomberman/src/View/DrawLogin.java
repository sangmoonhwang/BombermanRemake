//help from http://www.tutorialspoint.com/swing/swing_jtextfield.htm

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.CreateAccount;
import Controller.Login;


public class DrawLogin{

	public static JFrame mainFrame;
	
	static String blank = "                                         ";
	
	public static String n_typed = "                                         ";
	public static String p_typed = "                                         ";
	public static JLabel name_typed = new JLabel(n_typed, SwingConstants.CENTER);
	public static JLabel password_typed = new JLabel(p_typed, SwingConstants.CENTER);
	
	private final JTextField userText;
	private final JPasswordField passwordText;
	private static JLabel headerLabel;
	private static JLabel statusLabel;
	private static JPanel controlPanel;
	private static String username;
	private static String password;
	private static DrawLogin instance = new DrawLogin();
	
	private static boolean running;

	private DrawLogin(){
		//variables
		running = false;
		
		//objects
		mainFrame = new JFrame("Login");
		userText = new JTextField(13);
		passwordText = new JPasswordField(13);
		controlPanel = new JPanel();
		headerLabel = new JLabel("",SwingConstants.CENTER);
		statusLabel = new JLabel("",SwingConstants.CENTER);
	}
	
	//singleton
	public static DrawLogin getInstance() {
		return instance;
	}
	
	public void run(){
		//only make once
		if(!running){
			makeFrame();
		}
		mainFrame.setVisible(true);
	}

	public void makeFrame() {
		running = true;
		
		//setup frame
		mainFrame.setSize(800, 500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2);
		mainFrame.setLayout(new GridLayout(3,1));
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		//add labels and buttons
		statusLabel.setSize(350,100);
		controlPanel.setLayout (new GridBagLayout());//(new FlowLayout());
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		showLogin();

	}

	public void showLogin(){
		headerLabel.setText("Login to play BomberMan!");
		headerLabel.setFont(new Font("Serif", Font.BOLD, 55));

		JLabel namelabel = new JLabel("Username: ", SwingConstants.RIGHT);
		JLabel passwordLabel = new JLabel("Password: ", SwingConstants.CENTER);
		namelabel.setFont(new Font("Serif", Font.BOLD, 20));
		passwordLabel.setFont(new Font("Serif", Font.BOLD, 20));

		name_typed.setForeground(Color.RED);
		password_typed.setForeground(Color.RED);

		JButton verifyButton = new JButton("Login");
		JButton createButton = new JButton("Create New Account");

		verifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username = userText.getText();
				password = new String(passwordText.getPassword());
				Login.loginUser(username,password);
				String data = "Username: " + userText.getText();
				data += ", Password: " + new String(passwordText.getPassword());
			}
		});

		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateAccount ca = new CreateAccount(headerLabel, controlPanel, statusLabel, mainFrame);
			}
		});

		userText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username = userText.getText();
				password = new String(passwordText.getPassword());
				Login.loginUser(username,password);
				String data = "Username: " + userText.getText();
				data += ", Password: " + new String(passwordText.getPassword());
				//statusLabel.setText(data);
			}
		});

		passwordText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username = userText.getText();
				password = new String(passwordText.getPassword());
				Login.loginUser(username,password);
				String data = "Username: " + userText.getText();
				data += ", Password: " + new String(passwordText.getPassword());
			}
		});

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 0, 1, 0);
		controlPanel.add(namelabel,c);
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 1, 0);
		controlPanel.add(userText,c);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(1, 0, 0, 0);
		controlPanel.add(passwordLabel,c);
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(1, 0, 0, 0);
		controlPanel.add(passwordText,c);
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 1, 0, 1);
		c.fill = GridBagConstraints.HORIZONTAL;
		controlPanel.add(verifyButton,c);
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 1, 0, 1);
		controlPanel.add(createButton,c);
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(1, 1, 0, 0);
		controlPanel.add(name_typed,c);
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(1, 1, 0, 0);
		controlPanel.add(password_typed,c);
		mainFrame.setVisible(true);
		mainFrame.revalidate();
		mainFrame.repaint();
		userText.requestFocus();
	}

	//setters
		public static void setStatus(String s){
			statusLabel.setText(s);
		}
		public void viewFrame(boolean b){
			clearText();
			mainFrame.setVisible(b);
		}
		public void clearText(){
			userText.setText("");
			userText.requestFocus();
			passwordText.setText("");
		}
	
	//getters
	public static String getUsername(){
		return username;
	}
	public static String getPassword(){
		return password;
	}
}