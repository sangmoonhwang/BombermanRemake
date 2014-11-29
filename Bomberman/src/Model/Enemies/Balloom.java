package Model.Enemies;

import Model.Block;
import Model.Movable;

/**
 * Balloom enemy
 *
 */
public class Balloom {
	/**
	 * intelligence: higher, more intelligent
	 */
	private int intelligence;
	/**
	 * speed of the enemy
	 */
	private float speed;
	/**
	 * points that is returned when killed
	 */
	private int points;
	/**
	 * wallpass ability
	 */
	private boolean wallPass;
	/**
	 * score that is returned when killed
	 */
	private int scoreValue;

	//Sets the attribute for the Balloom. state indicating the movement direction(0-right, 1-left, 2-down, 3-up)

	/**
	 * constructor
	 */
	public Balloom() {
		speed = (float) 1;
		intelligence = 1;
		points = 100;
		wallPass = false;
		scoreValue = 100;

	}

	/**
	 * getter for the intelligence
	 * @return intelligence
	 */
	public int getIntelligence() {
		return intelligence;
	}

	/**
	 * getter for the speed
	 * @return speed
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * getter for the points
	 * @return points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * getter for the score
	 * @return score
	 */
	public int getScore(){
		return scoreValue;
	}

	/**
	 * getter for the wallpass ability
	 * @return wallpass ability
	 */
	public boolean getWallPass() {
		return wallPass;
	}
}
