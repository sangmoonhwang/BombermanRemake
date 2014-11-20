package Model;

import Model.Enemies.Enemy;

public class Block{

	//physical attributes
	private int xval, yval;
	private int height, width;


	public Block() {
		xval = yval = 0;
		height = width = 50;
	}

	//setters
	public void setXval(int i){
		xval = i;
	}
	public void setYval(int i){
		yval = i;
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
