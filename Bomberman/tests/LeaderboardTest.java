

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Controller.Leaderboard;
import Controller.Login;
import Model.Database;
import Model.User;

public class LeaderboardTest {

	@Test
	public void testLeaderboard() throws IOException {
		User u = new User("Amak1020","Abcdef1!","AlexMakri");
		Login ln = new Login();
		ln.setUser(u);
		Leaderboard ld = new Leaderboard();
		assertNotNull(ld.getUsers());
		assertNotNull(ld.getTopTen());
	}


}
