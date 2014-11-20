package Model;

public class Movable {
	
	//physical attributes
	private float xval, yval;
	private float height, width;
	
	//setters
	public void setXval(float i){
		xval = i;
	}
	public void setYval(float i){
		yval = i;
	}
	public void setHeight(float i){
		height = i;
	}
	public void setWidth(float i){
		width = i;
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
