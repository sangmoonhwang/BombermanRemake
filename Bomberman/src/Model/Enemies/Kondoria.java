package Model.Enemies;

public class Kondoria {
	private int intelligence;
	private float speed;
	private int points;
	private boolean wallPass;

	public Kondoria() {
		speed = (float) 0.625;
		intelligence = 3;
		points = 1000;
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
