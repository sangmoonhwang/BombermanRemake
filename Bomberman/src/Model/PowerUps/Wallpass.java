package Model.PowerUps;

import Model.Bomberman;

/**
 * This class enables the bomberman to go through any brick wall
 *
 */
public class Wallpass extends Powerup{

	public Wallpass(String identity){
		super(identity);
	}

	@Override
	public void activate() {
		Bomberman.wallPass = true;
	}

}
