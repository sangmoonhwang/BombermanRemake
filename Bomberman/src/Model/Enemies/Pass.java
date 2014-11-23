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
