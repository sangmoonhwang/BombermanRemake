package Model.Enemies;

public class Pontan {
	private int intelligence;
	private int speed;
	private int points;
	private boolean wallPass;
	
	public Pontan(){
		speed = 4;
		intelligence = 3;
		points = 8000;
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
