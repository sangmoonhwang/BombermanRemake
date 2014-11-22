package Model.PowerUps;

import Model.Bomberman;

public class Flamepass extends Powerup{

	public Flamepass(String identity){
		super(identity);
	}
	@Override
	public void activate() {
		Bomberman.flamePass = true;
	}

}
