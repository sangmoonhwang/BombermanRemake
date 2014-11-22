package Model.Enemies;

import Model.Block;
import Model.Movable;

public class Balloom {
  private int intelligence;
  private int speed;
  private int points;
  private boolean wallPass;
  private int scoreValue;

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
    scoreValue = 1000;

  }
  
  public void die() {
    
  }
  
  public void patrol() {
    
  }
  
  public void move(Enemy enemy) {
    //collision detection on the moving direction
	//if no collision then move to moving direction
	//otherwise move to opposite direction 0->1, 1->0, 2->3, 3->2
	
	if(enemy.getState() == 0){
		enemy.incrementXval(1);	  
	} else if(enemy.getState() == 1) {
		enemy.incrementXval(-1);	 
	} else if(enemy.getState() == 2) {
		enemy.incrementYval(1);	 
	} else {
		enemy.incrementYval(-1);	 
	}
  }
  
  public void changeDirection(Enemy enemy) {
	  if(enemy.getState() == 0){
		  enemy.setState(1);
	  } else if(enemy.getState() == 1) {
		  enemy.setState(0);
	  } else if(enemy.getState() == 2) {
		  enemy.setState(3);
	  } else {
		  enemy.setState(2);
	  }
  }
  

  public int getIntelligence() {
	  return intelligence;
  }

  public int getSpeed() {
	  return speed;
  }
  
  public int getPoints() {
	  return points;
  }
  
  public int getScore(){
	  return scoreValue;
  }
  
  public boolean getWallPass() {
	  return wallPass;
  }
}
