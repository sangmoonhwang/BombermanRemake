package Controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Database;
import View.DrawLogin;

public class Login extends Database implements KeyListener, FocusListener {
	private static DrawLogin d;
	private static boolean running = false;

	CreateAccount newUser;
	ModifyAccount modifyUser;
	
	//to keep the structure of the components static
	static String blank = "                                         ";
	
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
	public static void loginUser(String username, String password) {
		boolean user = false;
		boolean pass = false;
		d.name_typed.setText (blank);//("                                         ");
		d.password_typed.setText (blank);//("                                         ");
		if(username.equals("")){
			d.name_typed.setText("Enter your username!");
		}
		if(password.equals("")){
			d.password_typed.setText("Enter your password!");
		}
		if(username.equals("Alex")){ // @Todo: "Alex" to be replaced by the database username
			user = true;
		}
		if(password.equals("Makri")){// @Todo: "Makri" to be replaced by the database password
			pass = true;
		}
		if(user && pass){
			System.out.println("Great Success");
			d.mainFrame.removeAll();

			UserInput testP = new UserInput(d.mainFrame);
			d.mainFrame.revalidate();
			d.mainFrame.repaint();
		}
		else {
			System.out.println("Try Again");
		}
		/*try {
			
			User user = readUserCSVEntry(username);
			
			//if password match
			if(user.getPassword() == password) {
				
			} else {
				
			}
			
		} catch(IOException ex) {
	        System.out.println (ex.toString());
		}*/
	}
	
	//call create account class
//	public void createUser() {
//		newUser = new CreateAccount();
//	
//	}
	
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
