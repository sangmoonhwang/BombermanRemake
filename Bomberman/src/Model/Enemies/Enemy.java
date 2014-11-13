package Model.Enemies;

public class Enemy {
	private String identity;
	private float xval, yval;
	private int intelligence;
	private int speed;
  	private boolean wallPass;
  	private int points;
  
  	
  	public Enemy(){
  		int xval, yval;
  	}
  	
	 //getter
	 public float getXval(){
	   return xval;
	 }
	 //getter
	 public float getYval(){
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
