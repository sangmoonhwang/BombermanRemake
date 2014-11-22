package Model.PowerUps;

import Model.Bomberman;

public class UpBombs extends Powerup{

	public UpBombs(String identity){
		super(identity);
	}
	
	@Override
	public void activate() {
		if(Bomberman.availableBombs <= 10)
			Bomberman.availableBombs++;
	}

}
