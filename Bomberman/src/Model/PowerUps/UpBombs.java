package Model.PowerUps;

import Controller.Map;
import Model.Bomb;
import Model.Bomberman;

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
