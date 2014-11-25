package Model;

public class Tile {
	private int xval, yval;
	
	public Tile() {
		xval = yval = 0;
	}
	public Tile(int x, int y) {
		xval = x;
		yval = y;
	}
	
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
}
