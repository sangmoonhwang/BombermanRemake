package Controller;

import java.io.IOException;

import Model.User;
import Model.Database;

public class CreateAccount extends Database {
	
	public String username;
	public String password;
	public String realName;
	
	//draw create account and button listener
	public CreateAccount() {
		
		
	}
	
	//creates the account after create button being pressed
	public void accountCreate(String username, String password, String realName) {
		User newUser = new User(username, password, realName);
		
		try {
			writeUserCSVEntry(newUser);
			
		} catch(IOException ex) {
			System.out.println (ex.toString());
		}
		
		
	}

}
