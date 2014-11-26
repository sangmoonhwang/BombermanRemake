package Controller;

public class GamePlay {

	public static void run(int level) {
		boolean shutdown = false;
		Map.setLife(2);
		Map.setPaused(false);
		new Map(level);

		while(!shutdown) {
			if(Map.getGameOver())
				shutdown = true;
		}
	}
}
