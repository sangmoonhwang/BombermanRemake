package Model.PowerUps;

import Model.Bomberman;

public class Bombpass extends Powerup{

	public Bombpass(){
		super();
	}
	@Override
	public void activate() {
		Bomberman.bombPass = true;
	}

}
