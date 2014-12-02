

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.Test;

import Controller.GamePlay;
import Controller.Map;

public class GamePlayTest {

	@Test
	public void testGamePlay() {
		Map m = new Map(5);
		GamePlay g = new GamePlay(5,m);
		assertFalse(g.getShutDown());
		assertNotNull(g.getDrawMap());
	}
}