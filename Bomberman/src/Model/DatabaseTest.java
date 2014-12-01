package Model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	Database data;
	User user, user2;
	String username = "david252";
	String password = "david252!";
	String realName = "Young";

	@Before
	public void setup() throws IOException {
		data = new Database();
	}
	
	@Test
	public void test() throws IOException {
		user = new User(username,password,realName);
		data.writeUserCSVEntry(user);
		//write the new user and compare with the data written with original data
		user2 = Database.readUserCSVEntry(username);
		assertTrue("user name", username.equals(user2.getUsername()));
		assertTrue("password", password.equals(user2.getPassword()));
		assertTrue("real name", realName.equals(user2.getRealName()));
		assertTrue("num play", 0 == user2.getNumOfPlay());
		assertTrue("total score", 0 == user2.getTotalScore());
		assertTrue("level completed", 0 == user.getLevelCompleted());
	}
	@Test
	public void test2() throws IOException {
		//reading wrong user info
		user = new User("poketmon","pocket","pika");
		data.writeUserCSVEntry(user);
		user2 = Database.readUserCSVEntry(username);
		assertFalse("Wrong user name", user2.getUsername().equals("david"));
		assertFalse("Wrong password", user2.getPassword().equals("pass"));
		assertFalse("Wrong real name", user2.getRealName().equals("K"));
		assertFalse("Wrong num play", 2 == user2.getNumOfPlay());
		assertFalse("Wrong total score", 10000 == user2.getTotalScore());
		assertFalse("Wrong level completed", 22 == user2.getLevelCompleted());
	}
	
	@Test(expected=NullPointerException.class)
	public void test3() throws IOException {
		//reading non-existent user
		user2 = Database.readUserCSVEntry("david1205");
		throw new NullPointerException();
	}
	
	@Test
	public void tes4() throws IOException {
		//modify user info
		user = new User("david",password,realName);
		data.writeUserCSVEntry(user);
		Database.modifyUserCSVEntry("david", "pass", "K", 2, 10000, 22);
		user2 = Database.readUserCSVEntry("david");
		assertTrue("user name", user2.getUsername().equals("david"));
		assertTrue("password", user2.getPassword().equals("pass"));
		assertTrue("real name", user2.getRealName().equals("K"));
		assertTrue("num play", 2 == user2.getNumOfPlay());
		assertTrue("total score", 10000 == user2.getTotalScore());
		assertTrue("level completed", 22 == user2.getLevelCompleted());
	}

	@Test
	public void test5() throws IOException {
		//Does user exist
		assertTrue("user exist", data.isUserExist(username));
		assertFalse("user does not exist", data.isUserExist("Bench"));
	}
	
	@Test
	public void test6() throws IOException {
		//counting total number of user in user database
		ArrayList<User> user = new ArrayList<User>();
		user = data.returnAllUsers();
		assertTrue("total user in database", user.size() == 16);
	}
}