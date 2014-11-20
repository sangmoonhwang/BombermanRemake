package Model.Enemies;

public class Doll {
  private int intelligence;
  private int speed;
  private int points;
  private boolean wallPass;
  private final int left = -1;
  private final int right = 1;
  private final int Up = -1;
  private final int down = 1;
  
  
  public Doll() {
    speed = 3;
    intelligence = 1;
    points = 400;
    wallPass = false;
    
  }
  
  public void die() {
    
  }
  
  public void patrol() {
    
  }
  
  public void move() {
	    //collision detection on the moving direction
		//if no collision then move to moving direction
		//otherwise move to opposite direction 0->1, 1->0, 2->3, 3->2
  }
}
