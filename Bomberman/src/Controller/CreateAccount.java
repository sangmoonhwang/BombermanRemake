package Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.User;
import Model.Database;
import View.DrawLogin;

public class CreateAccount extends Database {

	public String username;
	public String password;
	public String realName;
	private String error = "Username must consist of 6-20 alphanumeric characters.";
	public static String n_typed = "Enter your username!";
	public static String p_typed = "Enter your password!";
	private JFrame main;
	private JPanel controlPanel;	
	private JLabel status;
	private JLabel header_login;
	public boolean success = false;

	//draw create account and button listener
	public CreateAccount(JLabel label_Header, JPanel panel_Login, JLabel label_Status, JFrame main) {
		this.main = main;
		controlPanel = panel_Login;
		header_login = label_Header;
		status = label_Status;
		drawpanel();
	}
	
	//for test purpose 
	public CreateAccount() {
		
	}

	private void drawpanel() {
		header_login.setText("Create An account to play!");
		controlPanel.removeAll();

		JLabel namelabel = new JLabel("Username: ", SwingConstants.RIGHT);
		JLabel realNamelabel = new JLabel("Realname: ", SwingConstants.RIGHT);
		JLabel passwordLabel = new JLabel("Password: ", SwingConstants.CENTER);
		namelabel.setFont(new Font("Serif", Font.BOLD, 20));
		realNamelabel.setFont(new Font("Serif", Font.BOLD, 20));
		passwordLabel.setFont(new Font("Serif", Font.BOLD, 20));

		final JTextField userText = new JTextField(13);
		final JTextField realNameText = new JTextField(13);
		final JPasswordField passwordText = new JPasswordField(13);

		JButton createButton = new JButton("Create");

		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username = userText.getText();
				realName = realNameText.getText();
				password = new String(passwordText.getPassword());
				accountCreate(username, password, realName);
			}
		});

		userText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username = userText.getText();
				realName = realNameText.getText();
				password = new String(passwordText.getPassword());
				accountCreate(username, password, realName);
			}
		});

		realNameText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username = userText.getText();
				realName = realNameText.getText();
				password = new String(passwordText.getPassword());
				accountCreate(username, password, realName);
			}
		});

		passwordText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username = userText.getText();
				realName = realNameText.getText();
				password = new String(passwordText.getPassword());
				accountCreate(username, password, realName);
			}
		});
		GridBagConstraints c = new GridBagConstraints();

		c.gridwidth = 1;
		c.gridheight = 1;

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 1, 0);
		controlPanel.add(namelabel,c);

		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 1, 0);
		controlPanel.add(userText,c);

		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(1, 0, 0, 0);
		controlPanel.add(realNamelabel,c);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(1, 0, 0, 0);
		controlPanel.add(realNameText,c);

		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(1, 0, 0, 0);
		controlPanel.add(passwordLabel,c);

		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(1, 0, 0, 0);
		controlPanel.add(passwordText,c);

		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 1, 0, 1);
		controlPanel.add(createButton,c);
		main.revalidate();
		main.repaint();
		userText.requestFocus();

	}

	//creates the account after create button being pressed
	public boolean accountCreate(String username, String password, String realName) {
		User newUser = new User(username, password, realName);

		if(!usernameValidate(username)){
			status.setText("Username should contain only numbers and alphabets");
			return false;
		}
		
		try {
			if(isUserExist(username)) {
				status.setText("An account with this username already exists.");
				return false;
			}
		} catch (IOException e1) {
			System.out.println(e1.toString());
		}
		
		if(!passwordValidate(password)){
			status.setText("Password must be 8-20 characters and contain at least one capital "
					+ "letter, one lowercase letter, one number and one special character.");
			return false;
		}
		
		//after successful login it should proceed to menu not the login screen
		//TODO
		try {
			writeUserCSVEntry(newUser);
			header_login.setText("Login to play BomberMan!");
			status.setText("Creation successful");
			controlPanel.removeAll();
			DrawLogin loginFrame = DrawLogin.getInstance();
			loginFrame.showLogin();
			return true;
		} catch(IOException ex) {
			System.out.println (ex.toString());
		}

		return false;
	}

	/**
	 * Validate username 
	 * @param username to validate
	 * @return true if it passes the requirements, otherwise false
	 */
	public boolean usernameValidate(String username) {
		return username.matches("((?=.+\\w).{6,20})");
	}	
	
	/**
	 * Validate password 
	 * @param password to validate
	 * @return true if it passes the requirements, otherwise false
	 */
	public boolean passwordValidate(String password) {
		return password.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%]).{8,20})");
	}

}
