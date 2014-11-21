package Model.PowerUps;

import Model.Bomberman;

public class Wallpass extends Powerup{

	public Wallpass(){
		super();
	}
	
	@Override
	public void activate() {
		Bomberman.wallPass = true;
	}

}
