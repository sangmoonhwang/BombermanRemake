

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Bomberman;

public class BombermanTest {

	@Test
	public void testSetSpeed() {
		Bomberman b = new Bomberman();
		b.setSpeed(0);
		assertEquals(0,b.getSpeed());
	}

	@Test
	public void testGetXval() {
		Bomberman b = new Bomberman();
		assertEquals(50,b.getXval());
	}

	@Test
	public void testGetYval() {
		Bomberman b = new Bomberman();
		assertEquals(50,b.getYval());
	}

	@Test
	public void testGetHeight() {
		Bomberman b = new Bomberman();
		assertEquals(42,b.getHeight());
	}

	@Test
	public void testGetWidth() {
		Bomberman b = new Bomberman();
		assertEquals(30,b.getWidth());
	}

	@Test
	public void testGetSpeed() {
		Bomberman b = new Bomberman();
		assertEquals(2,b.getSpeed());
	}

	@Test
	public void testBomberman() {
		Bomberman b = new Bomberman();
		assertEquals(2,b.getSpeed());
		assertEquals(50,b.getXval());
		assertEquals(50,b.getYval());
		assertEquals(30,b.getWidth());
		assertEquals(42,b.getHeight());
		assertEquals(1,b.getavailableBombs());
		assertEquals(1,b.getFlames());
		assertEquals(-1000000000,b.mystery_From);
		assertFalse(b.wallPass);
		assertFalse(b.flamePass);
		assertFalse(b.bombPass);
		assertFalse(b.detonate);
	}

	@Test
	public void testSetXvalInt() {
		Bomberman b = new Bomberman();
		b.setXval(100);
		assertEquals(100,b.getXval());
	}

	@Test
	public void testSetYvalInt() {
		Bomberman b = new Bomberman();
		b.setYval(100);
		assertEquals(100,b.getYval());
	}

	@Test
	public void testSetScore() {
		Bomberman b = new Bomberman();
		b.setScore(330);
		assertEquals(330,b.getScore());
	}

	@Test
	public void testSetDirection() {
		Bomberman b = new Bomberman();
		b.setDirection(20);
		assertEquals(20,b.getDirection());
	}

	@Test
	public void testSetMoving() {
		Bomberman b = new Bomberman();
		b.setMoving(true);
		assertTrue(b.isMoving());
	}

	@Test
	public void testIncrementXvalInt() {
		Bomberman b = new Bomberman();
		b.setXval(100);
		b.incrementXval(5);
		assertEquals(105,b.getXval());
	}

	@Test
	public void testIncrementYvalInt() {
		Bomberman b = new Bomberman();
		b.setYval(100);
		b.incrementYval(5);
		assertEquals(105,b.getYval());
	}

	@SuppressWarnings("static-access")
	@Test
	public void testIncrementBombs() {
		Bomberman b = new Bomberman();
		b.incrementBombs();
		assertEquals(2,b.getavailableBombs());
	}

	@SuppressWarnings("static-access")
	@Test
	public void testDecrementBombs() {
		Bomberman b = new Bomberman();
		b.decrementBombs();
		assertEquals(0,b.getavailableBombs());
	}

	@Test
	public void testGetDirection() {
		Bomberman b = new Bomberman();
		b.setDirection(30);
		assertEquals(30,b.getDirection());
	}

	@Test
	public void testGetavailableBombs() {
		Bomberman b = new Bomberman();
		assertEquals(1,b.getavailableBombs());
	}

	@Test
	public void testIsMystery() {
		Bomberman b = new Bomberman();
		assertFalse(b.isMystery());
	}

	@Test
	public void testGetBombs() {
		Bomberman b = new Bomberman();
		assertEquals(1,b.getBombs().size());
	}

	@Test
	public void testGetScore() {
		Bomberman b = new Bomberman();
		assertEquals(0,b.getScore());
	}

	@Test
	public void testIsMoving() {
		Bomberman b = new Bomberman();
		assertFalse(b.isMoving());
	}


}
