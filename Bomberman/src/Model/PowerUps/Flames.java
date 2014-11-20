package Model.PowerUps;

import Model.Bomberman;

public class Flames extends Powerup{

	public Flames(){
		super();
	}
	
	@Override
	public void activate() {
		Bomberman.flames++;
	}

}
