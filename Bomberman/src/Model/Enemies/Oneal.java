package Model.Enemies;

public class Oneal {
	private int intelligence;
	private float speed;
	private int points;
	private boolean wallPass;

	public Oneal(){
		speed = (float) 2;
		intelligence = 2;
		points = 200;
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
