package Model.Enemies;

public class Doll {
	private int intelligence;
	private float speed;
	private int points;
	private boolean wallPass;
	private int scoreValue;

	public Doll() {
		speed = (float) 2;
		intelligence = 1;
		points = 400;
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

	public int getScore(){
		return scoreValue;
	}

	public boolean getWallPass() {
		return wallPass;
	}
}
