//help from http://www.tutorialspoint.com/swing/swing_jtextfield.htm

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;
import javax.swing.JTextField;






public class DrawLogin extends JFrame{
 private JFrame mainFrame;
 private JLabel headerLabel;
 private JLabel statusLabel;
 private JPanel controlPanel;

 public DrawLogin(){
  //prepareGui();
  mainFrame = new JFrame("Login");
  
  
  //created arrays of tiles/indestructibles/bomberman
  
 }

 public void prepareGui() {
	mainFrame = new JFrame("Login");
	mainFrame.setSize(1000, 1000);
	mainFrame.setLayout(new GridLayout(3,1));
	mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent windowEvent){
			System.exit(0);
		}
	});
	headerLabel = new JLabel("",JLabel.CENTER);
	statusLabel = new JLabel("",JLabel.CENTER);
	
	statusLabel.setSize(350,100);
	
	controlPanel = new JPanel();
	controlPanel.setLayout(new FlowLayout());
	
	mainFrame.add(headerLabel);
	mainFrame.add(controlPanel);
	mainFrame.add(statusLabel);
	mainFrame.setVisible(true);
	
}
 


 public void run(){
	 prepareGui();
	 showLogin();
	 //mainFrame.setVisible(true);
 	}
 private void showLogin(){
	 headerLabel.setText("Enter your Username and Password");
	 
	 JLabel namelabel = new JLabel("Username: ", JLabel.RIGHT);
	 JLabel passwordLabel = new JLabel("Password: ", JLabel.CENTER);
	 final JTextField userText = new JTextField(6);
	 final JPasswordField passwordText = new JPasswordField(6);
	 
	 JButton verifyButton = new JButton("Login");
	 verifyButton.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
			 String data = "Username " + userText.getText();
			 data += ", Password: " + new String(passwordText.getPassword());
			 statusLabel.setText(data);
		 }
	 });
	 
	 controlPanel.add(namelabel);
	 controlPanel.add(userText);
	 controlPanel.add(passwordLabel);
	 controlPanel.add(passwordText);
	 controlPanel.add(verifyButton);
	 mainFrame.setVisible(true);
 }








}