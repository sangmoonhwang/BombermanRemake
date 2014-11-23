package Model.Enemies;

public class Minvo {
	private int intelligence;
	private int speed;
	private int points;
	private boolean wallPass;

	public Minvo() {
		speed = 4;
		intelligence = 2;
		points = 800;
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
