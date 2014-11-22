package Model.PowerUps;

import Model.Bomberman;

public class Detonator extends Powerup{

	public Detonator(String identity){
		super(identity);
	}
	
	@Override
	public void activate() {
		Bomberman.detonate = true;
	}


}
