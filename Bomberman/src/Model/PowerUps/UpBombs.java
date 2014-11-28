package Model.PowerUps;

import Controller.Map;
import Model.Bomb;
import Model.Bomberman;

/**
 * This class increases the amount of the bombs the bomberman possesses
 *
 */
public class UpBombs extends Powerup{

	public UpBombs(String identity){
		super(identity);
	}
	
	@Override
	public void activate() {
		
		Map.getBomberman().getBombs().add(new Bomb(false));
		Bomberman.incrementBombs();
	}
}
