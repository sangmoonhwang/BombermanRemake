package Model;

/**
 * Block model
 *
 */
public class Block{

	//physical attributes
	private int xval, yval;
	private int height, width;


	/**
	 * constructor
	 */
	public Block() {
		//physical attributes
		xval = yval = 0;
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
