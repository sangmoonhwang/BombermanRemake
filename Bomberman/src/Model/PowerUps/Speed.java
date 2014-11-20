package Model.PowerUps;

import Model.Bomberman;

public class Speed extends Powerup{

	public Speed(){
		super();
	}
	
	@Override
	public void activate() {
		Bomberman.speed++;
	}

}
