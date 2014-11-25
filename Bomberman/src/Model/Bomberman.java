package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Bomberman extends Movable implements Serializable{

	//physical attributes
	private static float xval, yval;
	private static int height, width;

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
	
	public static ArrayList<Bomb> bombs;


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
		bombs = new ArrayList<Bomb>();
		//bombs.add(new Bomb(false));
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
	public int getavailableBombs() {
		return availableBombs;
	}
	public boolean isMystery(){
		//System.out.println((System.nanoTime() - mystery_From)/1000000000);
		return (System.nanoTime() - mystery_From)/1000000000 <= 10; //10seconds?
	}
	public ArrayList<Bomb> getBombs() {
		return bombs;
	}
	public static int getScore() {
		return score;
	}
}