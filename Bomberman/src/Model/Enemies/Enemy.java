package Model.Enemies;
import Model.Movable;

public class Enemy extends Movable{

	//physical attributes
	private int xval,yval;
	private int height, width;

	//enemy values
	private String identity;
	private int intelligence;
	private int speed;
	private int points;
	private boolean wallPass;
	private int state;
	private int scoreValue;
	private Balloom balloom;
	
	public Enemy(){
		xval = 0;
		yval = 0;
		height = 50;
		width = 50;
		scoreValue = 0;
	}
	
	public Enemy(String enemy){
		xval = 0;
		yval = 0;
		height = 50;
		width = 50;
		identity = enemy;
		
		if(identity.equals("Balloom")){
			balloom = new Balloom();
			intelligence = balloom.getIntelligence();
			speed = balloom.getSpeed();
			points = balloom.getPoints();
			wallPass = balloom.getWallPass();
			scoreValue = balloom.getScore();
			state = (int) (Math.random()*3) + 1;
		}
	}
	
	public Balloom getBalloomInstance() {
		return balloom;
	}


	//setters
	public void setXval(int i){
		xval = i;
	}
	public void setYval(int i){
		yval = i;
	}
	public void setState(int state) {
		this.state = state;
	}
	//increment
	public void incrementXval(int i){
		xval += i;
	}
	public void incrementYval(int i){
		yval += i;
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
	public int getScore(){
		return scoreValue;
	}
	public int getState() {
		return state;
	}
	 

}
