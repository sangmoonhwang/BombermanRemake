package Model;

public class Destructible extends Block{
	private int xval, yval;
	private int height, width;

	public Destructible() {
		xval = yval = 0;
		height = width = 50;
	}
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
