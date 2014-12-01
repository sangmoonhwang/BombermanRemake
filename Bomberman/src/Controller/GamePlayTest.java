package Controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class GamePlayTest {

	@Test
	public void testGamePlay() {
		Map m = new Map(5);
		GamePlay g = new GamePlay(5,m);
		assertNotNull(g.getDrawMap());
	}

	@Test
	public void testRun() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetPause() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetShutdown() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testKeyPressed() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testKeyReleased() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testKeyTyped() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testFocusGained() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testFocusLost() {
		fail("Not yet implemented"); // TODO
	}

}
