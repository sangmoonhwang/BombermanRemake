package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Bomberman extends Movable implements Serializable{

	//physical attributes
	private static float xval, yval;
	private static int height, width;
	private int direction = 2; //0n 1w 2s 3e
	private boolean isMoving;

	//bomberman values
	private static int score;
	private static int life;
	
	public static int speed;
	public static int availableBombs;
	public static int flames;
	public static long mystery_From;
	
	public static boolean detonate;
	public static boolean wallPass;
	public static boolean bombPass;
	public static boolean flamePass;
	
	public static LinkedList<Bomb> bombs;


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
		detonate = true;
		speed = 2;
		bombs = new LinkedList<Bomb>();
		bombs.addFirst(new Bomb(false));
		bombs.addFirst(new Bomb(false));
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
	public static void setScore(int score) {
		Bomberman.score = score;
	}
	public void setDirection (int i) {
		direction = i;
	}
	public void setMoving(boolean b) {
		isMoving = b;
	}

	//increment
	public void incrementXval(int i) {
		xval += i;
	}
	public void incrementYval(int i) {
		yval += i;
	}
	public static int incrementBombs(){
		return availableBombs++;
	}
	public static int decrementBombs(){
		return availableBombs--;
	}

	//getters
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
	public static int getavailableBombs() {
		return availableBombs;
	}
	public boolean isMystery(){
		//System.out.println((System.nanoTime() - mystery_From)/1000000000);
		return (System.nanoTime() - mystery_From)/1000000000 <= 10; //10seconds?
	}
	public LinkedList<Bomb> getBombs() {
		return bombs;
	}
	public static int getScore() {
		return score;
	}

	public boolean isMoving() {
		return isMoving;
	}
}