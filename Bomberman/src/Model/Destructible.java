package Model;

public class Destructible extends Block{
	private int xval, yval;
	private int height, width;
	private boolean exists;

	public Destructible() {
		xval = yval = 0;
		height = width = 50;
		exists = false;
	}

	//setters
	public void setXval(int i){
		xval = i;
	}
	public void setYval(int i){
		yval = i;
	}
	public void setExists(boolean b){
		exists = b;
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
	public boolean getExists(){
		return exists;
	}
}
