package Model;

import java.io.Serializable;

/**
 * Destructible block model
 *
 */
public class Destructible extends Block implements Serializable{
	private int xval, yval;
	private int height, width;

	/**
	 * constructor
	 */
	public Destructible() {
		xval = yval = 0;
		height = width = 50;
	}
	/**
	 * constructor
	 * @param x x position
	 * @param y y position
	 */
	public Destructible(int x, int y) {
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
