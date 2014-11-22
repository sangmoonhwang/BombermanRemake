package Model.PowerUps;

import Model.Bomberman;

public class Mystery extends Powerup{

	public Mystery(String identity) {
		super(identity);
	}

	@Override
	public void activate() {
		Bomberman.mystery_From = System.nanoTime();
	}

}
