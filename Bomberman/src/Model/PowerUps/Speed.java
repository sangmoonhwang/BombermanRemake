package Model.PowerUps;

import Model.Bomberman;

/**
 * This class increases the speed of the bomberman
 *
 */
public class Speed extends Powerup{

	public Speed(String identity){
		super(identity);
	}
	
	@Override
	public void activate() {
		Bomberman.speed++;
	}

}
