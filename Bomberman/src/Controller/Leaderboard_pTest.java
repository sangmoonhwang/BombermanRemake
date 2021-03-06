package Controller;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Model.User;

public class Leaderboard_pTest {

	@Test
	public void testLeaderboard_p() throws IOException {
		User u = new User("Amak1020","Abcdef1!","AlexMakri");
		Login ln = new Login();
		ln.setUser(u);
		Leaderboard_p ld = new Leaderboard_p();
		assertNotNull(ld.getUsers());
	}
	
	@Test
	//not working, no idea why not
	public void testSort() throws IOException {
		User u = new User("Amak1020","Abcdef1!","AlexMakri");
		Login ln = new Login();
		ln.setUser(u);
		Leaderboard_p ld = new Leaderboard_p();
		for(int i =0; i< 9; i++){
			assertTrue(ld.getTopTen()[i].getTotalScore() > ld.getTopTen()[i+1].getTotalScore()); 
		}
	}

}
