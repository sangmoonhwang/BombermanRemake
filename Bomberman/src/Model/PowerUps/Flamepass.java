package Model.PowerUps;

import Model.Bomberman;

public class Flamepass extends Powerup{

	public Flamepass(){
		super();
	}
	@Override
	public void activate() {
		Bomberman.flamePass = true;
	}

}
