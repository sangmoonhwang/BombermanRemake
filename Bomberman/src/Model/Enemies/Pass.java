package Model.Enemies;

public class Pass {
	private int intelligence;
	private float speed;
	private int points;
	private boolean wallPass;

	public Pass( ){
		speed = (float) 3.125;
		intelligence = 3;
		points = 4000;
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
