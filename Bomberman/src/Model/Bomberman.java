package Model;

public class Bomberman extends Movable{

	//physical attributes
	private float xval, yval;
	private float height, width;

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
		height = 45;
		width = 40;
	}
	//setters
	public void setXval(float i){
		xval = i;
	}
	public void setYval(float i){
		yval = i;
	}

	//increment
	public void incrementXval(float i){
		xval += i;
	}
	public void incrementYval(float i){
		yval += i;
	}

	//getters
	public float getXval(){
		return xval;
	}
	public float getYval(){
		return yval;
	}
	public float getHeight(){
		return height;
	}
	public float getWidth(){
		return width;
	}

}