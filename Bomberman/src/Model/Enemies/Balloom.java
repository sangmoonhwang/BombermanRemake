package Model.Enemies;

import Model.Block;
import Model.Movable;

public class Balloom {
	private int intelligence;
	private int speed;
	private int points;
	private boolean wallPass;
	private int scoreValue;

	/**
	 * Sets the attribute for the Balloom. state indicating the movement direction(0-right, 1-left, 2-down, 3-up)
	 * @param None
	 * @return None
	 */

	public Balloom() {
		speed = 2;
		intelligence = 1;
		points = 100;
		wallPass = false;
		scoreValue = 100;

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

	public int getScore(){
		return scoreValue;
	}

	public boolean getWallPass() {
		return wallPass;
	}
}
