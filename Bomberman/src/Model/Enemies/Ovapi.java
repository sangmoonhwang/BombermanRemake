package Model.Enemies;

public class Ovapi {
	private int intelligence;
	private float speed;
	private int points;
	private boolean wallPass;

	public Ovapi() {
		speed = (float) 1;
		intelligence = 2;
		points = 2000;
		wallPass = true;

	}

	public int getIntelligence() {
		return intelligence;
	}

	public float getSpeed() {
		return speed;
	}

	public int getPoints() {
		return points;
	}

	public boolean getWallPass() {
		return wallPass;
	}

}
