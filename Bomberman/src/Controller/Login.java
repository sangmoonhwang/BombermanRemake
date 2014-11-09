package Controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import Model.User;
import Model.Database;
import View.DrawLogin;

public class Login extends Database implements KeyListener, FocusListener {
	private DrawLogin d;
	private static boolean running = false;

	CreateAccount newUser;
	ModifyAccount modifyUser;
	
	//draw login with button listener
	public Login() {
		d = new DrawLogin();
	}
	
	public static void main(String[] args){
		Login test = new Login();
		running = true;
		test.run();
	}
	 public void run(){
		  d.run();
	 }
	
	//if user exists then login to menu else display user does not exist
	public void loginUser(String username, String password) {
		try {
			
			User user = readUserCSVEntry(username);
			
			//if password match
			if(user.getPassword() == password) {
				
			} else {
				
			}
			
		} catch(IOException ex) {
	        System.out.println (ex.toString());
		}
	}
	
	//call create account class
	public void createUser() {
		newUser = new CreateAccount();
	
	}
	
	//call modifyAccount Class
	public void modifyUser() {
		modifyUser = new ModifyAccount();
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
