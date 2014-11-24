package Model.Enemies;

public class Minvo {
	private int intelligence;
	private float speed;
	private int points;
	private boolean wallPass;

	public Minvo() {
		speed = (float) 3.125;
		intelligence = 2;
		points = 800;
		wallPass = false;

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
