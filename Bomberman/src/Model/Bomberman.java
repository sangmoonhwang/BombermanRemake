package Model;

public class Bomberman extends Movable{

	//physical attributes
	private int xval, yval;
	private int height, width;

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
	public void setXval(int i){
		xval = i;
	}
	public void setYval(int i){
		yval = i;
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

}