package Model.PowerUps;

import Model.Bomberman;

public class Mystery extends Powerup{

	@Override
	public void activate() {
		Bomberman.mystery_From = System.nanoTime();
	}

}
