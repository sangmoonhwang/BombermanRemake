package Model;

public class Bomberman extends Movable{

	//physical attributes
	private int xval, yval;
	private int height, width;

	//bomberman values
	private int score;
	private int life;
	
	public static int speed;
	public static int availableBombs;
	public static int flames;
	public static long mystery_From;
	
	public static boolean detonate;
	public static boolean wallPass;
	public static boolean bombPass;
	public static boolean flamePass;


	public Bomberman(){
		xval = 50;
		yval = 50;//starting pos
		height = 42;
		width = 30;
		availableBombs = 1;
		flames = 2;
		mystery_From = -1000000000;
		wallPass = false;
		flamePass = false;
		bombPass = true;
		detonate = true;
		speed = 4;
	}
	//setters
	public void setXval(int i){
		xval = i;
	}
	public void setYval(int i){
		yval = i;
	}
	public void giveBombs(int i){
		availableBombs += i;
	}

	//increment
	public void incrementXval(int i){
		xval += i;
	}
	public void incrementYval(int i){
		yval += i;
	}

	//getters
	public int getXval(){
		return xval;
	}
	public int getYval(){
		return yval;
	}
	
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public int getavailableBombs() {
		return availableBombs;
	}
	public boolean isMystery(){
		System.out.println((System.nanoTime() - mystery_From)/1000000000);
		return (System.nanoTime() - mystery_From)/1000000000 <= 10; //10seconds?
	}

}