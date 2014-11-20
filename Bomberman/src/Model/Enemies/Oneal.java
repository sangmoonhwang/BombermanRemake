package Model.Enemies;

public class Oneal {
  private int intelligence;
  private int speed;
  private int points;
  private boolean wallPass;
  private final int left = -1;
  private final int right = 1;
  private final int Up = -1;
  private final int down = 1;
  
  
  public Oneal(){
    speed = 3;
    intelligence = 2;
    points = 200;
    wallPass = false;
    
  }
  
  public void die() {
    
  }
  
  public void patrol() {
    
  }
  
  public void chase() {
    
  }
  
  public void move() {
    //check if it is at intersection(probability of 10% of changing direction)
	//check bomberman position if it is within 1 block then call chase
  }

}
