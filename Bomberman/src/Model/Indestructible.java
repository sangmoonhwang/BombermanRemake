package Model;

import java.io.Serializable;

/**
 * Indestructible model
 *
 */
public class Indestructible extends Block implements Serializable{
	private int xval, yval;
	private int height, width;

	/**
	 * constructor
	 */
	public Indestructible() {
		xval = yval = 0;
		height = width = 50;
	}
	/**
	 * constructor
	 * @param x x position
	 * @param y y position
	 */
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
