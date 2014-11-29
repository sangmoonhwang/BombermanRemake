package Model.PowerUps;

import Model.Bomberman;

/**
 * This class extends the length of the bomb explosion
 *
 */
public class Flames extends Powerup{
	String identity = "Flames";

	public Flames(String identity){
		super(identity);
	}

	@Override
	public void activate() {
		Bomberman.flames++;
	}

}
