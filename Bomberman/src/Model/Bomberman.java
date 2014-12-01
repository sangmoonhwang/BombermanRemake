package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Bomberman extends Movable implements Serializable{

	//physical attributes
	/**
	 * x and y position of the bomberman
	 */
	private static float xval, yval;
	/**
	 * height and the width of the bomberman
	 */
	private static int height, width;
	/**
	 * 0:north 1:west 2:south 3:east 4:north-west 5:south-west 6:south-east 7:north-east
	 */
	private int direction = 2; 
	/**
	 * indicator if the bomberman is moving
	 */
	private boolean isMoving;

	//bomberman values
	/**
	 * the score obtained
	 */
	private static int score;
	/**
	 * life left
	 */
	private static int life;

	/**
	 * speed of the bomberman
	 */
	public static int speed;
	/**
	 * Number of bombs the bomberman can use
	 */
	public static int availableBombs;
	/**
	 * flame length
	 */
	public static int flames;
	/**
	 * time at which the bomberman obtains the mystery powerup 
	 */
	public static long mystery_From;

	/**
	 * detonate ability indicator
	 */
	public static boolean detonate;
	/**
	 * wallpass ability indicator
	 */
	public static boolean wallPass;
	/**
	 * bombpass ability indicator
	 */
	public static boolean bombPass;
	/**
	 * flamepass ability indicator
	 */
	public static boolean flamePass;

	/**
	 * bombs belonging to the bomberman
	 */
	public static LinkedList<Bomb> bombs;


	/**
	 * constructor
	 */
	public Bomberman() {
		xval = 50;
		yval = 50;//starting pos
		height = 42;
		width = 30;
		availableBombs = 1;
		flames = 1;
		mystery_From = -1000000000;
		wallPass = false;
		flamePass = false;
		bombPass = false;
		detonate = false;
		speed = 2;
		bombs = new LinkedList<Bomb>();
		bombs.addFirst(new Bomb(false));
		//bombs.addFirst(new Bomb(false));
	}

	//setters
	public void setXval(int i) {
		xval = i;
	}
	public void setYval(int i) {
		yval = i;
	}
	public void setSpeed(int i) {
		speed = i;
	}
	/**
	 * sets the score
	 * @param score score
	 */
	public void setScore(int score) {
		Bomberman.score = score;
	}
	/**
	 * sets the direction of the bomberman
	 * @param i direction to be set
	 */
	public void setDirection (int i) {
		direction = i;
	}
	/**
	 * sets the indicator if the bomberman is moving
	 * @param b indicator if the bomberman is moving
	 */
	public void setMoving(boolean b) {
		isMoving = b;
	}

	//increment
	/**
	 * moves horizontally the bomberman by i pixels
	 * @param i speed of the bomberman
	 */
	public void incrementXval(int i) {
		xval += i;
	}
	/**
	 * moves vertically the bomberman by i pixels
	 * @param i speed of the bomberman
	 */
	public void incrementYval(int i) {
		yval += i;
	}
	/**
	 * increases the amount of bombs left the to bomberman
	 * 
	 */
	public static int incrementBombs(){
		return availableBombs++;
	}
	/**
	 * decreases the amount of bombs left to the bomberman
	 * 
	 */
	public static int decrementBombs(){
		return availableBombs--;
	}

	//getters
	/**
	 * getter for direction
	 * @return direction
	 */
	public int getDirection(){
		return direction;
	}
	public int getXval() {
		return (int) xval;
	}
	public int getYval() {
		return (int) yval;
	}

	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public int getSpeed() {
		return speed;
	}
	public int getFlames() {
		return flames;
	}
	/**
	 * getter for the availablebombs
	 * @return available bombs
	 */
	public int getavailableBombs() {
		return availableBombs;
	}
	/**
	 * This indicates whether the bomberman is mystery or not
	 * @return
	 */
	public boolean isMystery(){
		return (System.nanoTime() - mystery_From)/1000000000 <= 10; //10seconds?
	}
	/**
	 * getter for the bombs
	 * @return bombs
	 */
	public LinkedList<Bomb> getBombs() {
		return bombs;
	}
	/**
	 * getter for the score
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * indicating if bomberman is moving
	 * @return moving indicator
	 */
	public boolean isMoving() {
		return isMoving;
	}
}