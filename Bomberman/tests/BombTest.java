

import static org.junit.Assert.*;

import org.junit.Test;

import Controller.GamePlay;
import Controller.Map;
import Model.Bomb;
import Model.Bomberman;

public class BombTest {

	
	/*public Bomb(boolean active) {
		xval = yval = 0;
		height = width = 50;
		this.active = active;
		escaped = false;
		personalExplosions = new Explosion[5];
		for(int i = 0; i < 5; i++){
			personalExplosions[i] = new Explosion();
		}
		used = false;
	}*/
	@Test
	public void testBomb() {
		Bomb b = new Bomb(false);
		assertEquals(0,b.getXval());
		assertEquals(0,b.getYval());
		assertFalse(b.getActive());
		assertFalse(b.getEscaped());
		for(int i = 0; i<5; i++){
			assertNotNull(b.getPersonalExplosions()[i]);
		}
		assertFalse(b.getUsed());
	}

	
	@Test
	public void testExplode() {
		Bomberman b2 = new Bomberman();
		Bomb b = b2.getBombs().get(0);
		b.setXval(50);
		b.setYval(50);
		b.explode();
		assertTrue(b.getUsed());
		assertEquals(50,b.getPersonalExplosions()[0].getXval());
		assertEquals(50,b.getPersonalExplosions()[0].getYval());
		assertEquals(100,b.getPersonalExplosions()[1].getXval());
		assertEquals(50,b.getPersonalExplosions()[1].getYval());
		assertEquals(0,b.getPersonalExplosions()[2].getXval());
		assertEquals(50,b.getPersonalExplosions()[2].getYval());
		assertEquals(50,b.getPersonalExplosions()[3].getXval());
		assertEquals(100,b.getPersonalExplosions()[3].getYval());
		assertEquals(50,b.getPersonalExplosions()[4].getXval());
		assertEquals(0,b.getPersonalExplosions()[4].getYval());
		for(int i = 0; i < 5; i++){
			assertTrue(b.getPersonalExplosions()[i].isExploding());
		}
		
	}



}
