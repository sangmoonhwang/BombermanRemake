package Model.PowerUps;

import Model.Bomberman;

/**
 * This class enables the bomberman to go through bombs
 *
 */
public class Bombpass extends Powerup{
	String identity = "BombPass";
	
	public Bombpass(String identity){
		super(identity);
	}
	@Override
	public void activate() {
		Bomberman.bombPass = true;
	}

}
