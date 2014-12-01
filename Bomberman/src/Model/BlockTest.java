package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BlockTest {

	@Test
	public void testBlock() {
		Block b = new Block();
		assertEquals(0,b.getXval());
		assertEquals(0,b.getYval());
		assertEquals(50,b.getHeight());
		assertEquals(50,b.getWidth());
	}

}
