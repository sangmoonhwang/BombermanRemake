package Model;

public class Movable {
	
	//physical attributes
	private int xval, yval;
	private int height, width;
	
	//setters
	public void setXval(int i){
		xval = i;
	}
	public void setYval(int i){
		yval = i;
	}
	public void setHeight(int i){
		height = i;
	}
	public void setWidth(int i){
		width = i;
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
