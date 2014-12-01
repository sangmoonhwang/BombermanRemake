package Model.Enemies;

import static org.junit.Assert.*;

import org.junit.Test;

import Controller.Map;

public class EnemyTest {


	
	@Test
	public void testEnemy() {
		Enemy e = new Enemy("Balloom");
		assertEquals("Balloom",e.getIdentity());
		assertEquals(1,e.getIntelligence());
		assertEquals(100,e.getPoints());
		assertFalse(e.isWallPass());
		
		e = new Enemy("Doll");
		assertEquals("Doll",e.getIdentity());
		assertEquals(1,e.getIntelligence());
		assertEquals(400,e.getPoints());
		assertFalse(e.isWallPass());
		
		e = new Enemy("Kondoria");
		assertEquals("Kondoria",e.getIdentity());
		assertEquals(3,e.getIntelligence());
		assertEquals(1000,e.getPoints());
		assertTrue(e.isWallPass());
		
		e = new Enemy("Minvo");
		assertEquals("Minvo",e.getIdentity());
		assertEquals(2,e.getIntelligence());
		assertEquals(800,e.getPoints());
		assertFalse(e.isWallPass());
		
		e = new Enemy("Oneal");
		assertEquals("Oneal",e.getIdentity());
		assertEquals(2,e.getIntelligence());
		assertEquals(200,e.getPoints());
		assertFalse(e.isWallPass());
		
		e = new Enemy("Ovapi");
		assertEquals("Ovapi",e.getIdentity());
		assertEquals(2,e.getIntelligence());
		assertEquals(2000,e.getPoints());
		assertTrue(e.isWallPass());
		
		e = new Enemy("Pass");
		assertEquals("Pass",e.getIdentity());
		assertEquals(3,e.getIntelligence());
		assertEquals(4000,e.getPoints());
		assertFalse(e.isWallPass());
		
		e = new Enemy("Pontan");
		assertEquals("Pontan",e.getIdentity());
		assertEquals(3,e.getIntelligence());
		assertEquals(8000,e.getPoints());
		assertTrue(e.isWallPass());
	}
	
	@Test
	public void testMove() {
		Enemy e = new Enemy("Balloom");
		e.setState(0);
		e.setXval(50);
		e.setYval(50);
		e.move();
		assertEquals("Enemy is 1 unit farther in X direction",51,e.getXval());
		
		e.setState(1);
		e.setXval(50);
		e.setYval(50);
		e.move();
		assertEquals("Enemy is -1 unit farther in X direction",49,e.getXval());
		
		e.setState(2);
		e.setXval(50);
		e.setYval(50);
		e.move();
		assertEquals("Enemy is 1 unit farther in Y direction",51,e.getYval());
		
		e.setState(3);
		e.setXval(50);
		e.setYval(51);
		e.move();
		assertEquals("Enemy is -1 unit farther in X direction",50,e.getXval());
	}

	@Test
	public void testChangeDirection() {
		Enemy e = new Enemy("Balloom");
		e.setState(0);
		e.changeDirection();
		assertEquals("Enemy was moving right, now moving left",1,e.getState());
		e.changeDirection();
		assertEquals("Enemy was moving left, now moving right",0,e.getState());
		e.setState(2);
		e.changeDirection();
		assertEquals("Enemy was moving up, now moving down",3,e.getState());
		e.changeDirection();
		assertEquals("Enemy was moving down, now moving up",2,e.getState());
	}



	@Test
	public void testWhichTileIsOn() {
		Enemy e = new Enemy("Balloom");
		int tile = e.whichTileIsOn(50,50); //tile 32
		assertEquals("Enemy is on tile",32,tile);
	}

	@Test
	public void testIsNotFree() {
		Enemy e = new Enemy("Balloom");
		Map m = new Map(5);
		e.setXval(150);
		e.setYval(150);
		boolean test = e.isNotFree(e.getXval(), e.getYval());
		assertFalse(test);
		e.setXval(200);
		e.setYval(200);
		test = e.isNotFree(e.getXval(), e.getYval());
		assertTrue(test);
	}


	@Test
	public void testAStar() {
		//fail("Not yet implemented"); // TODO
	}


}
