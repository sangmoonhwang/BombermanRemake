package Model.Enemies;

public class Oneal {
	private int intelligence;
	private int speed;
	private int points;
	private boolean wallPass;

	public Oneal(){
		speed = 3;
		intelligence = 2;
		points = 200;
		wallPass = false;

	}

	public void chase() {

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
