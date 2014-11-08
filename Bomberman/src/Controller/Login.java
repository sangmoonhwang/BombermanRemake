package Controller;

import java.io.IOException;

import Model.User;
import Model.Database;

public class Login extends Database {

	CreateAccount newUser;
	ModifyAccount modifyUser;
	
	//draw login with button listener
	public Login() {
		
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
}
