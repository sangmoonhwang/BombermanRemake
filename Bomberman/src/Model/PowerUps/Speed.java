package Model.PowerUps;

import Model.Bomberman;

public class Speed extends Powerup{

	public Speed(String identity){
		super(identity);
	}
	
	@Override
	public void activate() {
		Bomberman.speed++;
	}

}
