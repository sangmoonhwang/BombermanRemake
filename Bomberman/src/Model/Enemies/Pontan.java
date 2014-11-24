package Model.Enemies;

public class Pontan {
	private int intelligence;
	private float speed;
	private int points;
	private boolean wallPass;
	
	public Pontan(){
		speed = (float) 3.125;
		intelligence = 3;
		points = 8000;
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
