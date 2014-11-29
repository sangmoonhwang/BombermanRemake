package Model.Enemies;

/**
 * Doll enemy
 *
 */
public class Doll {
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

	/**
	 * constructor
	 */
	public Doll() {
		speed = (float) 2;
		intelligence = 1;
		points = 400;
		wallPass = false;

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
