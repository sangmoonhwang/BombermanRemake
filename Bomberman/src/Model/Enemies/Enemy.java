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
	private Balloom balloom;
	private Oneal oneal;
	private Doll doll;
	private Minvo minvo;
	private Kondoria kondoria;
	private Ovapi ovapi;
	private Pass pass;
	private Pontan pontan;

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
			state = (int) (Math.random()*3) + 1;
		} else if(identity.equals("Oneal")) {
			oneal = new Oneal();
			intelligence = oneal.getIntelligence();
			speed = oneal.getSpeed();
			points = oneal.getPoints();
			wallPass = oneal.getWallPass();
			state = (int) (Math.random()*3) + 1;
		} else if(identity.equals("Doll")) {
			doll = new Doll();
			intelligence = doll.getIntelligence();
			speed = doll.getSpeed();
			points = doll.getPoints();
			wallPass = doll.getWallPass();
			state = (int) (Math.random()*3) + 1;
		} else if(identity.equals("Minvo")) {
			minvo = new Minvo();
			intelligence = minvo.getIntelligence();
			speed = minvo.getSpeed();
			points = minvo.getPoints();
			wallPass = minvo.getWallPass();
			state = (int) (Math.random()*3) + 1;
		} else if(identity.equals("Kondoria")) {
			kondoria = new Kondoria();
			intelligence = kondoria.getIntelligence();
			speed = kondoria.getSpeed();
			points = kondoria.getPoints();
			wallPass = kondoria.getWallPass();
			state = (int) (Math.random()*3) + 1;
		} else if(identity.equals("Ovapi")) {
			ovapi = new Ovapi();
			intelligence = ovapi.getIntelligence();
			speed = ovapi.getSpeed();
			points = ovapi.getPoints();
			wallPass = ovapi.getWallPass();
			state = (int) (Math.random()*3) + 1;
		} else if(identity.equals("Pass")) {
			pass = new Pass();
			intelligence = pass.getIntelligence();
			speed = pass.getSpeed();
			points = pass.getPoints();
			wallPass = pass.getWallPass();
			state = (int) (Math.random()*3) + 1;
		} else if(identity.equals("Pontan")) {
			pontan = new Pontan();
			intelligence = pontan.getIntelligence();
			speed = pontan.getSpeed();
			points = pontan.getPoints();
			wallPass = pontan.getWallPass();
			state = (int) (Math.random()*3) + 1;
		}
	}

	/**
	 * move the enemy depending on the current moving direction
	 * @param None
	 * @return None
	 */
	public void move() {
		if(getState() == 0){
			incrementXval(1);	  
		} else if(getState() == 1) {
			incrementXval(-1);	 
		} else if(getState() == 2) {
			incrementYval(1);	 
		} else {
			incrementYval(-1);	 
		}
	}
	
	/**
	 * change the direction of the enemy to the opposite of current direction(Enemy has collided with either concrete or brick
	 * @param None
	 * @return None
	 */
	public void changeDirection() {
		if(getState() == 0){
			setState(1);
		} else if(getState() == 1) {
			setState(0);
		} else if(getState() == 2) {
			setState(3);
		} else {
			setState(2);
		}
	}

	/**
	 * changes the enemy to the free intersection
	 * @param None
	 * @return None
	 */
	public void intersectionDirectionChange() {
		
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
	public int getPoints(){
		return points;
	}
	public int getState() {
		return state;
	}
	public int getIntelligence() {
		return intelligence;
	}
	public Balloom getBalloomInstance() {
		return balloom;
	}
	public Oneal getOnealInstance() {
		return oneal;
	}
	public Doll getDollInstance() {
		return doll;
	}
	public Minvo getMinvoInstance() {
		return minvo;
	}
	public Kondoria getKondoriaInstance() {
		return kondoria;
	}
	public Ovapi getOvapiInstance() {
		return ovapi;
	}
	public Pass getPassInstance() {
		return pass;
	}
	public Pontan getPontanInstance() {
		return pontan;
	}


}
