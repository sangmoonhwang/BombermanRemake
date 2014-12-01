package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoxTest {

	@Test
	public void testBox() {
		Box b = new Box(50,60);
		assertEquals(50,b.x);
		assertEquals(60,b.y);
	}

	@Test
	public void testEqualsBox() {
		Box b1 = new Box(40,40);
		Box b2 = new Box(40,40);
		assertTrue(b1.equals(b2));
	}

}
