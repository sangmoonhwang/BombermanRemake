package Model;

public class Bomberman {
	private int xval, yval;
	
	public Bomberman(){
		xval = yval = 0;
	}
	
	public int getXval(){
		return xval;
	}
	
	public void setXval(int i){
		xval = i;
	}
	
	public int getYval(){
		return yval;
	}
	
	public void setYval(int i){
		yval = i;
	}
	
	public void incrementXval(int i){
		xval += i;
	}
	
	public void incrementYval(int i){
		yval += i;
	}
}
