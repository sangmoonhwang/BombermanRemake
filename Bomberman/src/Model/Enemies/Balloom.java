package Model.Enemies;

import Model.Block;
import Model.Movable;

public class Balloom {
  private int intelligence;
  private int speed;
  private int points;
  private boolean wallPass;
  private int state;

  /**
   * Sets the attribute for the Ballom. state indicated the movement direction(0-right, 1-left, 2-down, 3-up)
   * @param None
   * @return None
   */
  
  public Balloom() {
    speed = 2;
    intelligence = 1;
    points = 100;
    wallPass = false;
    state = (int) (Math.random()*3);
  }
  
  public void die() {
    
  }
  
  public void patrol() {
    
  }
  
  public void move(Enemy enemy) {
    //collision detection on the moving direction
	//if no collision then move to moving direction
	//otherwise move to opposite direction 0->1, 1->0, 2->3, 3->2
	
	if(state == 0){
		enemy.incrementXval(0);	  
	} else if(state == 1) {
		enemy.incrementXval(0);	 
	} else if(state == 2) {
		enemy.incrementYval(0);	 
	} else {
		enemy.incrementYval(0);	 
	}
  }
  
  public void changeDirection() {
	  if(state == 0){
		  state = 1;
	  } else if(state == 1) {
		  state = 0;
	  } else if(state == 2) {
		  state = 3;
	  } else {
		  state = 2;
	  }
  }
  
  public int getState() {
	  return state;
  }

}
