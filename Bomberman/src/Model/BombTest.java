package Model;

import static org.junit.Assert.*;

import org.junit.Test;

import Controller.GamePlay;
import Controller.Map;

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

	@Test
	public void testRun() {
		fail("Not yet implemented"); // TODO
	}

	/*public void run() {

		long start = System.nanoTime();
		boolean shutdown = false;

		System.out.println("threadID" + Thread.currentThread().getId());
		while(!shutdown) {
			long now = System.nanoTime();
			while(GamePlay.getPause()) {
				if(!paused) {
					pausedAt += now - start;
					paused  = true;
				}
				System.out.println("paused");
				now = System.nanoTime();
				start = now;
			}
			paused = false;
			if((now - start) + pausedAt >= 2000000000) {
				pausedAt = 0;

				explode();
				System.out.println("threadID" + Thread.currentThread().getId() + "exploding");
				start = now;

				while(true) {
					now = System.nanoTime();
					while(GamePlay.getPause()) {
						if(!paused) {
							pausedAt += now - start;
							paused  = true;
						}
						System.out.println("paused after explode");
						now = System.nanoTime();
						start = now;
					}
					paused = false;
					if((now - start) + pausedAt >= 800000000 || !Map.getActiveBombs().getLast().getUsed()) {
						for(int i = 0; i < 5; i++){
							personalExplosions[i].setExploding(false);
						}
						Map.getBomberman().getBombs().addFirst(new Bomb(false));
						Map.getActiveBombs().removeLast();
						shutdown = true;
						break;
					}
				}
				pausedAt = 0;
			}
		}
		System.out.println("Bomb thread terminated");
	}*/


}
