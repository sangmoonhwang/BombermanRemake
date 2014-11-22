package Model.Enemies;

public class Pass {
	private int intelligence;
	private int speed;
	private int points;
	private boolean wallPass;

	public Pass( ){
		speed = 4;
		intelligence = 3;
		points = 4000;
		wallPass = false;

	}

	public void die() {

	}

	public void patrol() {

	}

	public void chase() {

	}

	public void move() {
		//check if it is at intersection(probability of 50% of changing direction)
		//check bomberman position if it is within 2 block then call chase method(if obstacle exist then use Astar search)
	}

	public void Astar() {

	}

	public int getIntelligence() {
		return intelligence;
	}

	public int getSpeed() {
		return speed;
	}

	public int getPoints() {
		return points;
	}
	
	public boolean getWallPass() {
		return wallPass;
	}

}
