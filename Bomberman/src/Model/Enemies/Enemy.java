package Model.Enemies;
import Model.Movable;

public class Enemy extends Movable{

	//physical attributes
	private int xval,yval;
	private int height, width;

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
