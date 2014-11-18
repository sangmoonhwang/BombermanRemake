package Model;

public class Bomb {
	private int xval, yval;
	private boolean active;

	public Bomb() {
		int xval, yval;
		active = false;
	}
	//getter
	 public int getXval(){
	   return xval;
	 }
	 public void setActive(boolean b){
		 active = b;
	 }
	 public boolean isActive(){
		 return active;
	 }
	 public void explode(){
		 //explosion
		 active = false;
	 }
	 //getter
	 public int getYval(){
	   return yval;
	 }
	 //setter
	 public void setXval(int i){
	   xval = i;
	 }
	 //setter
	 public void setYval(int i){
	   yval = i;
	 }
	
}
