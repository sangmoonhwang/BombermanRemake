package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void testUserStringStringString() {
		User u = new User("Amak1020", "Abcdef1!", "AlexMakri");
		assertEquals("Amak1020",u.getUsername());
		assertEquals("Abcdef1!",u.getPassword());
		assertEquals("AlexMakri",u.getRealName());
		assertEquals(0,u.getNumOfPlay());
		assertEquals(0,u.getTotalScore());
		assertEquals(0,u.getLevelCompleted());
	}

	@Test
	public void testUserStringArray() {
		String[] data = new String[6];
		data[0] = "Amak1020";
		data[1] = "Abcdef1!";
		data[2] = "AlexMakri";
		data[3] = "400";
		data[4] = "300";
		data[5] = "40";
		User u = new User(data);
		assertEquals("Amak1020",u.getUsername());
		assertEquals("Abcdef1!",u.getPassword());
		assertEquals("AlexMakri",u.getRealName());
		assertEquals(400,u.getNumOfPlay());
		assertEquals(300,u.getTotalScore());
		assertEquals(40,u.getLevelCompleted());
	}


	@Test
	public void testUpdateScore() {
		User u = new User("Amak1020", "Abcdef1!", "AlexMakri");
		u.setTotalScore(4000);
		u.updateScore(400);
		assertEquals(4400,u.getTotalScore());
	}

/*	//NEEDS TO BE DONE WHEN SAVING/LOADING WORKS AND PROBABLY BY SOMEONE WHO MADE THEM AND KNOWS HOW THEY WORK
	@Test
	public void testGetSavedGame() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetSavedGame() {
		fail("Not yet implemented"); // TODO
	}
	*/

}
