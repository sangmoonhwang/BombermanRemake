package Model.Enemies;

public class Ovapi {
	private int intelligence;
	private int speed;
	private int points;
	private boolean wallPass;

	public Ovapi() {
		speed = 2;
		intelligence = 2;
		points = 2000;
		wallPass = true;

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
