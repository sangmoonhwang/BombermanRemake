package Model.Enemies;

/**
 * Oneal enemy
 *
 */
public class Oneal {
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
	 * constructor
	 */
	public Oneal(){
		speed = (float) 2;
		intelligence = 2;
		points = 200;
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
	 * getter for the wallpass ability
	 * @return wallpass ability
	 */
	public boolean getWallPass() {
		return wallPass;
	}
}
