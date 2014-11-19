package Model.Enemies;
import Model.Movable;

public class Enemy extends Movable{
	private String identity;
	private float xval;
	private float yval;
	private int intelligence;
	private int speed;
  	private boolean wallPass;
  	private int points;
  
  	
  	public Enemy(){
  		xval= 0;
  		yval= 0;
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
	 public void patrol(){
		 
	 }
	 public void incrementXval(int i){
			xval += i;
	 }
	 public void incrementYval(int i){
			yval += i;
	 }

}
