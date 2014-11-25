package Model;

import java.io.Serializable;

public class Indestructible extends Block implements Serializable{
	private int xval, yval;
	private int height, width;

	public Indestructible() {
		xval = yval = 0;
		height = width = 50;
	}
	public Indestructible(int x, int y) {
		xval = x;
		yval = y;
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
