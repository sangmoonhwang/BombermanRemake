package Model;

public class Destructible {
	private int xval, yval;
	private boolean exists;
	 
	 public Destructible() {
	   int xval, yval;
	   boolean exists;
	 }
	 
	 //getter
	 public int getXval(){
	   return xval;
	 }
	 //getter
	 public int getYval(){
	   return yval;
	 }
	 //getter
	 public boolean getExists(){
		 return exists;
	 }
	 //setter
	 public void setXval(int i){
	   xval = i;
	 }
	 //setter
	 public void setYval(int i){
	   yval = i;
	 }
	 //setter
	 public void setExists(boolean b){
		 exists = b;
	 }
}
