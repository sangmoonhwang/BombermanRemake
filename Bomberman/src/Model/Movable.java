package Model;

public class Movable {
	
	//physical attributes
	private float xval, yval;
	private int height, width;
	private int speed;
	
	//setters
	public void setXval(float i) {
		xval = i;
	}
	public void setYval(float i) {
		yval = i;
	}
	public void setHeight(int i) {
		height = i;
	}
	public void setWidth(int i) {
		width = i;
	}
	public void setSpeed(int i) {
		speed = i;
	}
	
	//increment
	public void incrementXval(float i) {
		xval += i;
	}
	public void incrementYval(float i) {
		yval += i;
	}

	//getters
	public int getXval() {
		return (int)xval;
	}
	public int getYval( ) {
		return (int)yval;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public int getSpeed() {
		return speed;
	}
}
