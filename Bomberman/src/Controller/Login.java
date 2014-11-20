package Controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import Model.Database;
import Model.User;
import View.DrawLogin;
import View.DrawMenu;

public class Login extends Database implements KeyListener, FocusListener {
	private static boolean running = false;
	private static User u;
	private static DrawLogin loginFrame;

	CreateAccount newUser;
	ModifyAccount modifyUser;
	
	//to keep the structure of the components static
	static String blank = "                                         ";
	
	//draw login with button listener
	public Login() {
		running = true;
		loginFrame = DrawLogin.getInstance();
	}
	
	public void run(){
		loginFrame.run();
	}
	
	//if user exists then login to menu else display user does not exist
	public static void loginUser(String username, String password) {
		boolean user = false;
		boolean pass = false;
		loginFrame.name_typed.setText (blank);//("                                         ");
		loginFrame.password_typed.setText (blank);//("                                         ");
		if(username.equals("")){
			loginFrame.name_typed.setText("Enter your username!");
		}
		if(password.equals("")){
			loginFrame.password_typed.setText("Enter your password!");
		}
		try {
			u = readUserCSVEntry(username);
		} catch (IOException e) {
			
		}
		
		if(u != null) {
			try {
			  if(username.equals(u.getUsername())){ 
				  user = true;
			  }
			  if(password.equals(u.getPassword())){
				  pass = true;
			  }
			} catch (NullPointerException e){
				
			}
			if(user && pass){
				DrawLogin.setStatus("Login Successful!");
				Thread thread = new Thread() {
			        public void run() {
			        	DrawMenu menuFrame = DrawMenu.getInstance();
			        	menuFrame.run();
			        	loginFrame.viewFrame(false);
			        }
			    };
			    thread.start();
				
			}
		}
		if((!user || !pass) && u != null){
			DrawLogin.setStatus("Wrong username or password, please try again");
		} else if(u == null) {
			DrawLogin.setStatus("User account does not exist");
		} else {
			DrawLogin.setStatus("Login unsuccessful, please try again");
		}
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
