package Model.PowerUps;

import Model.Bomberman;

/**
 * This class gives an ability to detonate bombs to the bomberman
 *
 */
public class Detonator extends Powerup{

	public Detonator(String identity){
		super(identity);
	}
	
	@Override
	public void activate() {
		Bomberman.detonate = true;
	}


}
