package Model.PowerUps;

import Model.Bomberman;

public class UpBombs extends Powerup{

	public UpBombs(){
		super();
	}
	
	@Override
	public void activate() {
		if(Bomberman.availableBombs <= 10)
			Bomberman.availableBombs++;
	}

}
