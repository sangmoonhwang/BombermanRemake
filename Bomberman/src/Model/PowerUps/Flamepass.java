package Model.PowerUps;

import Model.Bomberman;

/**
 * This class gives an immunity to the bomberman
 *
 */
public class Flamepass extends Powerup{

	public Flamepass(String identity){
		super(identity);
	}
	@Override
	public void activate() {
		Bomberman.flamePass = true;
	}

}
