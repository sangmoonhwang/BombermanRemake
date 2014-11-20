package Model.Enemies;
import Model.Movable;

public class Enemy extends Movable{

	//physical attributes
	private float xval,yval;
	private float height, width;

	//enemy values
	private String identity;
	private int intelligence;
	private int speed;
	private int points;
	private boolean wallPass;

	public Enemy(){
		xval = 0;
		yval = 0;
		height = 50;
		width = 50;
	}


	public void patrol(){

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
