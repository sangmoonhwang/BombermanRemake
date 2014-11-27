package Controller;

public class GamePlay {

	public static void run(int level, Map game) {
		boolean shutdown = false;
		Map play;
		if(game != null) {
			play = game;
			play.run();
		} else {
			Map.setLife(2);
			Map.setPaused(false);
			play = new Map(level);
		}

		while(!shutdown) {
			if(Map.getGameOver())
				shutdown = true;
		}
	}
	
	public static boolean setShutdown() {
		return true;
	}
}
