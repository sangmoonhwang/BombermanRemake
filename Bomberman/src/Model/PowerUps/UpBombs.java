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
		Bomberman bombman = Map.getBomberman();
		bombman.getBombs().add(new Bomb(false));
		bombman.giveBomb();
	}
}
