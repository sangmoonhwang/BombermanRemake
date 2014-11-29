package Model.Enemies;

/**
 * Pontan enemy
 *
 */
public class Pontan {
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
	public Pontan(){
		speed = (float) 3.125;
		intelligence = 3;
		points = 8000;
		wallPass = true;

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
