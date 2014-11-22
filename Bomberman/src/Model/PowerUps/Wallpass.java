package Model.PowerUps;

import Model.Bomberman;

public class Wallpass extends Powerup{

	public Wallpass(String identity){
		super(identity);
	}
	
	@Override
	public void activate() {
		Bomberman.wallPass = true;
	}

}
