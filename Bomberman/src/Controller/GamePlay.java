package Controller;

public class GamePlay {

	public static void run() {
		boolean shutdown = false;
		Map.setLife(2);
		Map.setPaused(false);
		new Map(1);

		while(!shutdown) {
			if(Map.getGameOver())
				shutdown = true;
		}
	}
}
