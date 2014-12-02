

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Controller.CreateAccount;
import Model.Database;
import Model.User;

public class CreateAccountTest {
	Database data;
	CreateAccount account;
	User user, user2;
	String username = "david252";
	String password = "daviD252!";
	String realName = "Young";
	String verify = "daviD252!";
	
	@Before
	public void setup() throws IOException {
		data = new Database();
		account = new CreateAccount();
	}
	@Test
	public void test() throws IOException {
		user = new User(username,password,realName);
		data.writeUserCSVEntry(user);

		boolean create = account.accountCreate(username,password,realName,verify);

		//user2 = data.readUserCSVEntry(username);
		System.out.println(user2.getUsername());
		assertTrue("user name", create);


	}
}
