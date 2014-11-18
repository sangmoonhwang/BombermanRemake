package Model.Enemies;

public class Enemy {
	private String identity;
	private float xval, yval;
	private int intelligence;
	private int speed;
  	private boolean wallPass;
  	private int points;
  
  	
  	public Enemy(){
  		float xval, yval;
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
	 public void setXval(float i){
	   xval = i;
	 }
	 //setter
	 public void setYval(float i){
	   yval = i;
	 }
	 public void patrol(){
		 
	 }
	 public void incrementXval(float i){
			xval += i;
	 }
	 public void incrementYval(float i){
			yval += i;
	 }
}
