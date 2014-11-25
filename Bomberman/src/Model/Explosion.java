package Model;

import java.io.Serializable;

public class Explosion extends Movable implements Serializable {
	private float xval, yval;
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
		return (int)xval;
	}
	public int getYval(){
		return (int)yval;
	}
	
	//for powerup
	public void setWidth(int i){
		width = i;
	}
	public void setHeight(int i){
		height = i;
	}
	public void adjustWidth(int i){
		width += i;
	}
	public void adjustHeight(int i){
		height += i;
	}
	public void adjustXval(int i){
		xval += i;
	}
	public void adjustYval(int i){
		yval += i;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}

}
