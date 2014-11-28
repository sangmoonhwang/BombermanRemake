package Model.PowerUps;

import Model.Bomberman;

/**
 * This class gives the mystery state to the bomberman
 *
 */
public class Mystery extends Powerup{

	public Mystery(String identity) {
		super(identity);
	}

	@Override
	public void activate() {
		Bomberman.mystery_From = System.nanoTime();
	}

}
