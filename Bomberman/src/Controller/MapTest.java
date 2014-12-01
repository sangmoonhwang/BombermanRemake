package Controller;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Bomberman;
import Model.User;

public class MapTest {

	@SuppressWarnings("static-access")
	@Test
	public void testMap() {
		Login ln = new Login();
		User u = new User("Amak1020","Abcdef1!","AlexMakri");
		ln.setUser(u);
		Map m = new Map(5);
		assertEquals(5,m.getLevel());
		assertFalse(m.getLevel() == 4);
		assertNotNull(m.getUser());
		assertNotNull(m.getDetect());
		assertNotNull(m.getBomberman());
		assertNotNull(m.getActiveBombs());
		assertNotNull(m.getSpawn());
		assertNotNull(m.getIndestructible());
		assertNotNull(m.getDestructible());
		assertNotNull(m.getEnemy());
		assertNotNull(m.getPowerup());
		assertNotNull(m.getTiles());
		assertNotNull(m.getDoor());
		assertFalse(m.isPaused());
		
	}

	
	//Not testing things like collision detection as they have already been tested in CollisionDetection class
	//Testing death and level completion
	@SuppressWarnings("static-access")
	@Test
	public void testTick() {
		Login ln = new Login();
		User u = new User("Amak1020","Abcdef1!","AlexMakri");
		ln.setUser(u);
		Map m = new Map(5);
		
		//Soft Death
		m.getBomberman().setXval(150);
		m.getBomberman().setYval(150);
		m.getEnemy().get(0).setXval(150);
		m.getEnemy().get(0).setYval(150);
		assertFalse("Bomberman shouldn't have 1 life before death", m.getLife() == 1);
		m.tick();
		assertEquals("Bomberman dies on enemy collision",1, m.getLife());
		assertEquals("Bomberman gets reset to 50,50 on collision",50,m.getBomberman().getXval());
		assertEquals("Bomberman gets reset to 50,50 on collision",50,m.getBomberman().getYval());
		
		//Powerup
		m.getBomberman().setXval(150);
		m.getBomberman().setYval(150);
		m.getPowerup().setXval(150);
		m.getPowerup().setYval(150);
		assertFalse("bman has 1 bomb before powerup",m.getBomberman().getavailableBombs() == 2);
		m.tick();
		assertEquals("Bman gets a powerup on enemy collision(for lvl 5,upbomb)",2,m.getBomberman().getavailableBombs()); //got an extra bomb after the tick
		
		//Level Completion
		m.getEnemy().clear();
		m.getBomberman().setXval(150);
		m.getBomberman().setYval(150);
		m.getDoor().setXval(150);
		m.getDoor().setYval(150);
		m.tick();
		assertEquals("Level completed when enemies gone/bman enters door",6,m.getLevel());	//initialized at lvl 5 thus now after completion we're at 6
		assertEquals("Bomberman gets reset to 50,50 on level completion",50,m.getBomberman().getXval());
		assertEquals("Bomberman gets reset to 50,50 on level completion",50,m.getBomberman().getYval());
		
		//Hard death
		m.setLife(0);
		m.getBomberman().setXval(150);
		m.getBomberman().setYval(150);
		m.getEnemy().get(0).setXval(150);
		m.getEnemy().get(0).setYval(150);
		m.tick();
		assertTrue(m.getGameOver());
	}

	@Test
	public void testTick2() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testMoveEnemy() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testMoveEnemyWhenBombermanWithInRange() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIsChaseBombermanPathFree() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testFindPathToBomberman() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testChangeDirectionAtIntersection() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIsIntersection() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testChaseDirection() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testChangeDirectionToChaseBomberman() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIsBombermanWithinOneSquare() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIsBombermanWithinTwoSquare() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testWhichTileIsOn() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testPointCalculation() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDieBombman() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSoftResetBombman() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testHardResetBombman() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetVelX() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetVelY() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetLife() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetBombermanState() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testNextLevel() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSameLevel() {
		fail("Not yet implemented"); // TODO
	}


	@Test
	public void testGetBombermanState() {
		fail("Not yet implemented"); // TODO
	}


}
