package Controller;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

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
	

	@Test
	//not working no idea why
	public void testSort() throws IOException {
		User u = new User("Amak1020","Abcdef1!","AlexMakri");
		Login ln = new Login();
		ln.setUser(u);
		Leaderboard ld = new Leaderboard();
		ld.sort();
		for(int i =0; i< 9; i++){
			assertTrue(ld.getTopTen()[i].getTotalScore() > ld.getTopTen()[i+1].getTotalScore()); 
		}
	}

}
