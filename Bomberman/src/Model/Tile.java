package Model;

/**
 * Tile model
 *
 */
public class Tile {
	private int xval, yval;
	
	/**
	 * constructor
	 */
	public Tile() {
		xval = yval = 0;
	}
	/**
	 * constructor
	 * @param x x position
	 * @param y y position
	 */
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
