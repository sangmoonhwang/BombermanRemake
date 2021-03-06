

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Controller.Leaderboard_p;
import Controller.Login;
import Model.User;

public class Leaderboard_pTest {

	@Test
	public void testLeaderboard_p() throws IOException {
		User u = new User("Amak1020","Abcdef1!","AlexMakri");
		Login ln = new Login();
		ln.setUser(u);
		Leaderboard_p ld = new Leaderboard_p();
		assertNotNull(ld.getUsers());
		assertNotNull(ld.getTopTen());
	}

}
