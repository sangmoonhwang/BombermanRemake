package Model;

public class Explosion extends Movable {
	private int xval, yval;
	private int height, width;
	private boolean exploding;
	
	
	public Explosion(){
		xval = 0;
		yval = 0;
		height = 50;
		width = 50;
		exploding = false;
		
	}
	
	public void setExploding(boolean b){
		exploding = b;
	}
	public boolean isExploding(){
		return exploding;
	}
	
	public void setXval(int i){
		xval = i;
	}
	public void setYval(int i){
		yval = i;
	}
	public int getXval(){
		return xval;
	}
	public int getYval(){
		return yval;
	}
	
	//for powerup
	public void setWidth(int i){
		width = i;
	}
	public void setHeight(int i){
		height = i;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}

}
