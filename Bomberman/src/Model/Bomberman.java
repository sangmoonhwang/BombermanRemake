package Model;

public class Bomberman extends Movable{

	//physical attributes
	private float xval, yval;
	private float height, width;

	//bomberman values
	private int score;
	private int life;
	private int speed;
	private int availableBombs;
	private int flames;

	private boolean detonate;
	private boolean wallPass;
	private boolean bombPass;
	private boolean flamePass;

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