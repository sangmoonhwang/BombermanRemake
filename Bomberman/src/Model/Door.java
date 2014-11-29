package Model;

import java.io.Serializable;

/**
 * Door model
 *
 */
public class Door extends Block implements Serializable{
	
	private int xval, yval;
	private int height, width;

	/**
	 * constructor
	 */
	public Door() {
		xval = yval = 50;
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
