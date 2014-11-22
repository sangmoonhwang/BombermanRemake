package Model;

public class Bomb {
	private int xval, yval;
	private int height, width;
	private boolean active;
	private boolean escaped;

	public Bomb() {
		xval = yval = 0;
		height = width = 50;
		active = false;
		escaped = false;
	}
	
	//explosion
	public void explode(){
		active = false;
	}
	
	//setters
	public void setXval(int i){
		xval = i;
	}
	public void setYval(int i){
		yval = i;
	}
	public void setActive(boolean b){
		active = b;
	}
	
	public void setEscaped(boolean b){
		escaped = b;
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
	public boolean getActive(){
		return active;
	}
	
	public boolean getEscaped(){
		return escaped;
	}

}
