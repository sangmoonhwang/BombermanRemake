package Model.PowerUps;

import Model.Bomberman;

public class Detonator extends Powerup{

	public Detonator(){
		super();
	}
	
	@Override
	public void activate() {
		Bomberman.detonate = true;
	}


}
