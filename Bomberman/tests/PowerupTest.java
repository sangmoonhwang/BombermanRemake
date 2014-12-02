

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Bomberman;
import Model.PowerUps.Powerup;

public class PowerupTest {


	@Test
	public void testPowerup() {
		Powerup p = new Powerup("Bombpass");
		assertNotNull(p.getIdentity());
		p = new Powerup("Detonator");
		assertNotNull(p.getIdentity());
		p = new Powerup("Flamepass");
		assertNotNull(p.getIdentity());
		p = new Powerup("Flames");
		assertNotNull(p.getIdentity());
		p = new Powerup("Mystery");
		assertNotNull(p.getIdentity());
		p = new Powerup("Speed");
		assertNotNull(p.getIdentity());
		p = new Powerup("UpBombs");
		assertNotNull(p.getIdentity());
		p = new Powerup("Wallpass");
		assertNotNull(p.getIdentity());
		
	}


	@SuppressWarnings("static-access")
	@Test
	public void testActivate() {
		Powerup p = new Powerup("Bombpass");
		Bomberman b = new Bomberman();
		assertFalse(b.bombPass);
		p.activate();
		assertTrue(b.bombPass);
		
		p = new Powerup("Detonator");
		assertFalse(b.detonate);
		p.activate();
		assertTrue(b.detonate);
		
		p = new Powerup("Flamepass");
		assertFalse(b.flamePass);
		p.activate();
		assertTrue(b.flamePass);
		
		p = new Powerup("Wallpass");
		assertFalse(b.wallPass);
		p.activate();
		assertTrue(b.wallPass);
		
		p = new Powerup("Flames");
		assertEquals(1,b.flames);
		p.activate();
		assertEquals(2,b.flames);
		
		p = new Powerup("Mystery");
		assertFalse(b.mystery_From == System.nanoTime());
		p.activate();
		assertTrue(b.mystery_From + 1000 < System.nanoTime());
		
		p = new Powerup("Speed");
		assertEquals(2,b.speed);
		p.activate();
		assertEquals(3,b.speed);
		
		p = new Powerup("UpBombs");
		assertEquals(1,b.getBombs().size());
		p.activate();
		assertEquals(2,b.getBombs().size());
	}

}
