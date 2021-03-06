package Controller;

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
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.Database;
import Model.User;
import View.DrawLogin;
import View.DrawMenu;

/**
 * controller for modifying an account
 *
 */
public class ModifyAccount extends CreateAccount{
	/**
	 * new username
	 */
	public String newUsername;
	/**
	 * old password
	 */
	public String oldPassword;
	/**
	 * new password
	 */
	public String newPassword;
	public String verifynewPassword;
	private JFrame main;
	private JPanel controlPanel;	
	private JLabel status;
	private JLabel header_login;
	private final JTextField userText_newName;
	private final JPasswordField userText_oldPass;
	private final JPasswordField userText_newPass;
	private final JPasswordField userText_verifynewPass;

	//draw modifyAccount view
	/**
	 * constructor
	 */
	public ModifyAccount(){
		main = new JFrame("Modify Account");
		main.setSize(800, 500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		main.setLocation(dim.width/2-main.getSize().width/2, dim.height/2-main.getSize().height/2);
		main.setUndecorated(true);
		main.setLayout(new GridLayout(3,1));
		main.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		header_login = new JLabel("",SwingConstants.CENTER);
		status = new JLabel("",SwingConstants.CENTER);
		status.setSize(350,100);
		controlPanel = new JPanel();
		controlPanel.setLayout (new GridBagLayout());//(new FlowLayout());
		main.add(header_login);
		main.add(controlPanel);
		main.add(status);
		userText_newName = new JTextField(13);
		userText_oldPass = new JPasswordField(13);
		userText_newPass = new JPasswordField(13);
		userText_verifynewPass = new JPasswordField(13);
		main.setVisible(true);
		drawpanel();
	}

	/**
	 * set up the GUI
	 */
	private void drawpanel() {
		header_login.setText("Modify your account information");

		JLabel newNameLabel = new JLabel("New Real Name: ", SwingConstants.RIGHT);
		JLabel oldPassLabel = new JLabel("Old Password:  ", SwingConstants.RIGHT);
		JLabel newPassLabel = new JLabel("New Password: ", SwingConstants.CENTER);
		JLabel newverifyPassLabel = new JLabel("Re-enter Password: ", SwingConstants.CENTER);
		newNameLabel.setFont(new Font("Serif", Font.BOLD, 20));
		oldPassLabel.setFont(new Font("Serif", Font.BOLD, 20));
		newPassLabel.setFont(new Font("Serif", Font.BOLD, 20));
		newverifyPassLabel.setFont(new Font("Serif", Font.BOLD, 20));

		final JTextField userText_newName = new JTextField(13);
		final JPasswordField userText_oldPass = new JPasswordField(13);
		final JPasswordField userText_newPass = new JPasswordField(13);
		final JPasswordField userText_verifynewPass = new JPasswordField(13);

		JButton backButton = new JButton("Back");
		JButton modifyButton = new JButton("Apply Changes");

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.setVisible(false);
				DrawMenu.getInstance().viewFrame(true);
			}
		});

		
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newUsername = userText_newName.getText();
				newPassword = new String(userText_newPass.getPassword());
				verifynewPassword = new String(userText_verifynewPass.getPassword());
				oldPassword = new String(userText_oldPass.getPassword());

				if(newPassword.equals(verifynewPassword)) {
					if(passwordValidate(newPassword)) {
						try {
							Database.modifyUserCSVEntry(Login.getUser().getUsername(), newPassword, newUsername, 0, 0, 0);
							main.setVisible(false);
							DrawMenu.getInstance().viewFrame(true);

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						status.setText("Password must be 8-20 characters and contain at least one capital "
								+ "letter, one lowercase letter, one number and one special character.");
					}
					
				} else {
					status.setText("Password does not match");
				}

			}
		});

		userText_newName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newUsername = userText_newName.getText();
				newPassword = new String(userText_newPass.getPassword());
				verifynewPassword = new String(userText_verifynewPass.getPassword());
				oldPassword = new String(userText_oldPass.getPassword());
			}
		});

		userText_oldPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newUsername = userText_newName.getText();
				newPassword = new String(userText_newPass.getPassword());
				verifynewPassword = new String(userText_verifynewPass.getPassword());
				oldPassword = new String(userText_oldPass.getPassword());
			}
		});

		userText_newPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newUsername = userText_newName.getText();
				newPassword = new String(userText_newPass.getPassword());
				verifynewPassword = new String(userText_verifynewPass.getPassword());
				oldPassword = new String(userText_oldPass.getPassword());
			}
		});

		userText_newPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newUsername = userText_newName.getText();
				newPassword = new String(userText_newPass.getPassword());
				verifynewPassword = new String(userText_verifynewPass.getPassword());
				oldPassword = new String(userText_oldPass.getPassword());
			}
		});
		GridBagConstraints c = new GridBagConstraints();

		c.gridwidth = 1;
		c.gridheight = 1;

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 1, 0);
		controlPanel.add(newNameLabel,c);

		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 1, 0);
		controlPanel.add(userText_newName,c);

		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(1, 0, 0, 0);
		controlPanel.add(oldPassLabel,c);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(1, 0, 0, 0);
		controlPanel.add(userText_oldPass,c);

		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(1, 0, 0, 0);
		controlPanel.add(newPassLabel,c);

		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(1, 0, 0, 0);
		controlPanel.add(userText_newPass,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(1, 0, 0, 0);
		controlPanel.add(newverifyPassLabel,c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(1, 0, 0, 0);
		controlPanel.add(userText_verifynewPass,c);

		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(10, 1, 0, 1);
		controlPanel.add(backButton,c);
		
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(10, 1, 0, 1);
		controlPanel.add(modifyButton,c);
		main.revalidate();
		main.repaint();
		userText_newName.requestFocus();

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
