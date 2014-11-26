package Model.Enemies;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

import Controller.Map;
import Model.Bomb;
import Model.Box;
import Model.Destructible;
import Model.Indestructible;
import Model.Movable;
import Utils.GNode;

public class Enemy extends Movable implements Serializable{

	//physical attributes
	private float xval,yval;
	private int height, width;

	//enemy values
	private String identity;
	private int intelligence;
	private float speed;
	private int points;
	private boolean wallPass;
	private int state;
	private Image image;
	private Balloom balloom;
	private Oneal oneal;
	private Doll doll;
	private Minvo minvo;
	private Kondoria kondoria;
	private Ovapi ovapi;
	private Pass pass;
	private Pontan pontan;
	private boolean leftFree = true;
	private boolean rightFree = true;
	private boolean aboveFree = true;
	private boolean belowFree = true;
	private boolean aboveLeftFree = true;
	private boolean aboveRightFree = true;
	private boolean belowLeftFree = true;
	private boolean belowRightFree = true;
	private boolean leftFreeBrick = true;
	private boolean rightFreeBrick = true;
	private boolean aboveFreeBrick = true;
	private boolean belowFreeBrick = true;
	private boolean aboveLeftFreeBrick = true;
	private boolean aboveRightFreeBrick = true;
	private boolean belowLeftFreeBrick = true;
	private boolean belowRightFreeBrick = true;
	private boolean twoLeftFree = true;
	private boolean twoRightFree = true;
	private boolean twoAboveFree = true;
	private boolean twoBelowFree = true;
	private boolean twoLeftFreeBrick = true;
	private boolean twoRightFreeBrick = true;
	private boolean twoAboveFreeBrick = true;
	private boolean twoBelowFreeBrick = true;

	private int rows = 31;
	private int cols = 13;
	ArrayList<Box> path = new ArrayList<Box>();

	public Enemy(String enemy){
		xval = (float)0;
		yval = (float)0;
		height = 50;
		width = 50;
		identity = enemy;

		if(identity.equals("Balloom")){
			balloom = new Balloom();
			intelligence = balloom.getIntelligence();
			speed = balloom.getSpeed();
			points = balloom.getPoints();
			wallPass = balloom.getWallPass();
			image = Toolkit.getDefaultToolkit().getImage("Balloom.png");
			state = (int) (Math.random()*3) + 1;
		} else if(identity.equals("Oneal")) {
			oneal = new Oneal();
			intelligence = oneal.getIntelligence();
			speed = oneal.getSpeed();
			points = oneal.getPoints();
			wallPass = oneal.getWallPass();
			image = Toolkit.getDefaultToolkit().getImage("Oneal.png");
			state = (int) (Math.random()*3) + 1;
		} else if(identity.equals("Doll")) {
			doll = new Doll();
			intelligence = doll.getIntelligence();
			speed = doll.getSpeed();
			points = doll.getPoints();
			wallPass = doll.getWallPass();
			image = Toolkit.getDefaultToolkit().getImage("Doll.png");
			state = (int) (Math.random()*3) + 1;
		} else if(identity.equals("Minvo")) {
			minvo = new Minvo();
			intelligence = minvo.getIntelligence();
			speed = minvo.getSpeed();
			points = minvo.getPoints();
			wallPass = minvo.getWallPass();
			image = Toolkit.getDefaultToolkit().getImage("Minvo.png");
			state = (int) (Math.random()*3) + 1;
		} else if(identity.equals("Kondoria")) {
			kondoria = new Kondoria();
			intelligence = kondoria.getIntelligence();
			speed = kondoria.getSpeed();
			points = kondoria.getPoints();
			wallPass = kondoria.getWallPass();
			image = Toolkit.getDefaultToolkit().getImage("Kondoria.png");
			state = (int) (Math.random()*3) + 1;
		} else if(identity.equals("Ovapi")) {
			ovapi = new Ovapi();
			intelligence = ovapi.getIntelligence();
			speed = ovapi.getSpeed();
			points = ovapi.getPoints();
			wallPass = ovapi.getWallPass();
			image = Toolkit.getDefaultToolkit().getImage("Ovapi.png");
			state = (int) (Math.random()*3) + 1;
		} else if(identity.equals("Pass")) {
			pass = new Pass();
			intelligence = pass.getIntelligence();
			speed = pass.getSpeed();
			points = pass.getPoints();
			wallPass = pass.getWallPass();
			image = Toolkit.getDefaultToolkit().getImage("Pass.png");
			state = (int) (Math.random()*3) + 1;
		} else if(identity.equals("Pontan")) {
			pontan = new Pontan();
			intelligence = pontan.getIntelligence();
			speed = pontan.getSpeed();
			points = pontan.getPoints();
			wallPass = pontan.getWallPass();
			image = Toolkit.getDefaultToolkit().getImage("Pontan.png");
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
			incrementXval(speed);	  
		} else if(getState() == 1) {
			incrementXval(-speed);	 
		} else if(getState() == 2) {
			incrementYval(speed);	 
		} else {
			incrementYval(-speed);	 
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
	 * changes the enemy to the free intersection with probability of 0.1 for intelligence 1 and 0.5 for intelligence 3
	 * @param Two boolean that has either left and Above free
	 * @return None
	 */
	public void intersectionDirectionChange(Boolean free1) {
		double prob = Math.random();

		if((intelligence == 2 && prob < 0.1) || (intelligence == 3 && prob < 0.5)) {
			if(getState() == 0 || getState() == 1){
				if(free1) {
					setState(3);
				} else {
					setState(2);
				}
			} else if(getState() == 2 || getState() == 3) {
				if(free1) {
					setState(1);
				} else {
					setState(0);
				}
			}
		}
	}

	/**
	 * Resets all the variables to TRUE that is used for enemy collision detection 
	 * @param None
	 * @return None
	 */
	public void statusReset() {
		leftFree = true;
		rightFree = true;
		aboveFree = true;
		belowFree = true;
		leftFreeBrick = true;
		rightFreeBrick = true;
		aboveFreeBrick = true;
		belowFreeBrick = true;
		aboveLeftFree = true;
		aboveRightFree = true;
		belowLeftFree = true;
		belowRightFree = true;
		aboveLeftFreeBrick = true;
		aboveRightFreeBrick = true;
		belowLeftFreeBrick = true;
		belowRightFreeBrick = true;
	}

	/**
	 * returns the tile number
	 * @param xPos and yPos
	 * @return The tile number it is on
	 */
	public int whichTileIsOn(float x, float y) {
		int tmp = (int) (y/50);
		return (int) ((x/50) + tmp*31);
	}

	/**
	 * Returns boolean whether current tile is free or not
	 * @param X and Y position
	 * @return True if not free, otherwise false
	 */
	public boolean isNotFree(int x, int y) {
		int tileNum = whichTileIsOn(x,y);
		ArrayList<Indestructible> indestructibles = Map.getIndestructible();
		ArrayList<Destructible> bricks = Map.getDestructible();

		for(int i = 0; i < indestructibles.size(); i++) {
			int indestructiblesTileNum = whichTileIsOn(indestructibles.get(i).getXval(), indestructibles.get(i).getYval());
			if(indestructiblesTileNum == tileNum)
				return true;
		}
		for(int i=0; i<bricks.size(); i++) {
			int bricksTileNum = whichTileIsOn(bricks.get(i).getXval(), bricks.get(i).getYval());
			if(bricksTileNum == tileNum)
				return true;
		}
		return false;	
	}

	/**
	 * search the Free path
	 * @param ArrayList<Indestructible>
	 * @param ArrayList<Destructible>
	 * @param LinkedList<Bomb>
	 * @return None
	 */
	public void searchFreePath(ArrayList<Indestructible> indestructibles, ArrayList<Destructible> bricks, LinkedList<Bomb> activeBombs) {
		statusReset();
		int tileNum = whichTileIsOn(getXval(), getYval());
		int enemyState = getState();
		for(int i = 0; i < indestructibles.size(); i++) {
			int indestructiblesTileNum = whichTileIsOn(indestructibles.get(i).getXval(), indestructibles.get(i).getYval());
			if(indestructiblesTileNum == (tileNum) && (enemyState == 1)) {
				leftFree = false;
			} else if(indestructiblesTileNum == (tileNum + 1) && (enemyState == 0)) {
				rightFree = false;
			} else if(indestructiblesTileNum == (tileNum) && (enemyState == 3)) {
				aboveFree = false;
			} else if(indestructiblesTileNum == (tileNum + 31) && (enemyState == 2)) {
				belowFree = false;
			} else if(indestructiblesTileNum == (tileNum - 32)) {
				aboveLeftFree = false;
			} else if(indestructiblesTileNum == (tileNum - 30)) {
				aboveRightFree = false;
			} else if(indestructiblesTileNum == (tileNum + 30)) {
				belowLeftFree = false;
			} else if(indestructiblesTileNum == (tileNum + 32)) {
				belowRightFree = false;
			} else if(indestructiblesTileNum == (tileNum - 2)) {
				twoLeftFree = false;
			} else if(indestructiblesTileNum == (tileNum + 2)) {
				twoRightFree = false;
			} else if(indestructiblesTileNum == (tileNum - 62)) {
				twoAboveFree = false;
			} else if(indestructiblesTileNum == (tileNum + 62)) {
				twoBelowFree = false;
			} 

		}
		if(!wallPass) {
			for(int i=0; i<bricks.size(); i++) {
				int bricksTileNum = whichTileIsOn(bricks.get(i).getXval(), bricks.get(i).getYval());
				if(bricksTileNum == (tileNum) && (enemyState == 1)) {
					leftFreeBrick = false;
				} else if(bricksTileNum == (tileNum+1) && (enemyState == 0)) {
					rightFreeBrick = false;
				} else if(bricksTileNum == (tileNum) && (enemyState == 3)) {
					aboveFreeBrick = false;
				} else if(bricksTileNum == (tileNum + 31) && (enemyState == 2)) {
					belowFreeBrick = false;
				} else if(bricksTileNum == (tileNum - 32)) {
					aboveLeftFreeBrick = false;
				} else if(bricksTileNum == (tileNum - 30)) {
					aboveRightFreeBrick = false;
				} else if(bricksTileNum == (tileNum + 30)) {
					belowLeftFreeBrick = false;
				} else if(bricksTileNum == (tileNum + 32)) {
					belowRightFreeBrick = false;
				} else if(bricksTileNum == (tileNum - 2)) {
					twoLeftFreeBrick = false;
				} else if(bricksTileNum == (tileNum + 2)) {
					twoRightFreeBrick = false;
				} else if(bricksTileNum == (tileNum - 62)) {
					twoAboveFreeBrick = false;
				} else if(bricksTileNum == (tileNum + 62)) {
					twoBelowFreeBrick = false;
				} 
			}
		}

		if(!activeBombs.isEmpty()) {
			for(int i=0; i<activeBombs.size(); i++) {
				
				if(activeBombs.get(i).getActive()) {
					int bombsTileNum = whichTileIsOn(activeBombs.get(i).getXval(), activeBombs.get(i).getYval());
					if(bombsTileNum == (tileNum) && (enemyState == 1)) {
						leftFree = false;
					} else if(bombsTileNum == (tileNum + 1) && (enemyState == 0)) {
						rightFree = false;
					} else if(bombsTileNum == (tileNum) && (enemyState == 3)) {
						aboveFree = false;
					} else if(bombsTileNum == (tileNum + 31) && (enemyState == 2)) {
						belowFree = false;
					} else if(bombsTileNum == (tileNum - 32)) {
						aboveLeftFree = false;
					} else if(bombsTileNum == (tileNum - 30)) {
						aboveRightFree = false;
					} else if(bombsTileNum == (tileNum + 30)) {
						belowLeftFree = false;
					} else if(bombsTileNum == (tileNum + 32)) {
						belowRightFree = false;
					} else if(bombsTileNum == (tileNum - 2)) {
						twoLeftFree = false;
					} else if(bombsTileNum == (tileNum + 2)) {
						twoRightFree = false;
					} else if(bombsTileNum == (tileNum - 62)) {
						twoAboveFree = false;
					} else if(bombsTileNum == (tileNum + 62)) {
						twoBelowFree = false;
					} 
				}
			}
		}
	}

	/**
	 * Calculates the Euclidian distance
	 * @param current position and end point
	 * @return None
	 */
	public float EuclidianDistance(Box b1, Box b2) {
		return (float) Math.sqrt(Math.pow((b1.x - b2.x),2) + Math.pow((b1.y - b2.y),2));
	}

	/**
	 * search for the path to chase bomberman
	 * @param 
	 * @return None
	 */
	public void searchForPath(int xBomberman, int yBomberman) {

		GNode goalNode = AStar(xBomberman, yBomberman);

		if(goalNode == null)
			return;

		while(goalNode.eParent != null) {
			path.add(goalNode.eCurrent);
			goalNode = goalNode.eParent;
		}

	}

	/**
	 * Applies the Astar method to find the path from the enemy to Bomberman
	 * @param 
	 * @return None
	 */
	public GNode AStar(int xBomberman, int yBomberman) {
		//list of all nodes we are going to search ie. the frontier
		TreeMap<Float, GNode> openList = new TreeMap<Float, GNode>();
		//list of all nodes we've already searched
		TreeMap<Integer, GNode> closedList = new TreeMap<Integer, GNode>();
		Box bStart = new Box((int)getXval(), (int)getYval());
		Box bEnd = new Box(xBomberman, yBomberman);

		//init the openlist
		GNode startNode = new GNode(bStart, null, 0, EuclidianDistance(bStart, bEnd));
		openList.put(startNode.fCost, startNode);

		while(!openList.isEmpty()){
			//first node in open list
			Float key = openList.firstKey();
			GNode current = openList.get(key);
			openList.remove(key);
			//add it to closed list
			closedList.put(whichTileIsOn(current.eCurrent.x, current.eCurrent.y), current);

			//check if its the goal
			if(whichTileIsOn(current.eCurrent.x,current.eCurrent.y) == whichTileIsOn(xBomberman, yBomberman)){
				return current;
			}

			int xIndex = current.eCurrent.x/50;
			int yIndex = current.eCurrent.y/50;

			//for all 8 adjacent nodes
			for(int i=xIndex-1;i<=xIndex+1;i++){
				for(int j=yIndex-1;j<=yIndex+1;j++){

					//skip if out of bounds
					if(i<0 || i> rows || j<0 || j>cols) {
						continue;
					}

					//skip if the block is not free
					if(isNotFree(i*50,j*50)) {
						continue;
					}

					//skip if already searched before
					if(closedList.containsKey(whichTileIsOn(i*50,j*50))) {
						continue;
					}

					//Create next node
					GNode next = new GNode(new Box(i*50,j*50),current,current.gCost+50,EuclidianDistance(new Box(i*50,j*50),bEnd));

					//add node to open list
					if(!openList.containsKey(next.fCost)) {
						openList.put(next.fCost, next);
					}
				}
			}
		}
		return null;
	}

	//setters
	public void setXval(float i) {
		xval = i;
	}
	public void setYval(float i) {
		yval = i;
	}
	public void setState(int state) {
		this.state = state;
	}
	//increment
	public void incrementXval(float i) {
		xval += i;
	}
	public void incrementYval(float i) {
		yval += i;
	}

	//getters
	public String getIdentity() {
		return identity;
	}
	public int getXval() {
		return (int)xval;
	}
	public int getYval() {
		return (int)yval;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public int getPoints() {
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
	public ArrayList<Box> getPath() {
		return path;
	}
	public Image getImage() {
		return image;
	}
	public boolean getLeftFree() {
		return leftFree;
	}
	public boolean getRightFree() {
		return rightFree;
	}
	public boolean getAboveFree() {
		return aboveFree;
	}
	public boolean getBelowFree() {
		return belowFree;
	}
	public boolean getAboveLeftFree() {
		return aboveLeftFree;
	}
	public boolean getAboveRightFree() {
		return aboveRightFree;
	}
	public boolean getBelowLeftFree() {
		return belowLeftFree;
	}
	public boolean getBelowRightFree() {
		return belowRightFree;
	}
	public boolean getLeftFreeBrick() {
		return leftFreeBrick;
	}
	public boolean getRightFreeBrick() {
		return rightFreeBrick;
	}
	public boolean getAboveFreeBrick() {
		return aboveFreeBrick;
	}
	public boolean getBelowFreeBrick() {
		return belowFreeBrick;
	}
	public boolean getAboveLeftFreeBrick() {
		return aboveLeftFreeBrick;
	}
	public boolean getAboveRightFreeBrick() {
		return aboveRightFreeBrick;
	}
	public boolean getBelowLeftFreeBrick() {
		return belowLeftFreeBrick;
	}
	public boolean getBelowRightFreeBrick() {
		return belowRightFreeBrick;
	}
	public boolean get2LeftFree() {
		return twoLeftFree;
	}
	public boolean get2RightFree() {
		return twoRightFree;
	}
	public boolean get2BelowFree() {
		return twoBelowFree;
	}
	public boolean get2AboveFree() {
		return twoAboveFree;
	}
	public boolean get2LeftFreeBrick() {
		return twoLeftFreeBrick;
	}
	public boolean get2RightFreeBrick() {
		return twoRightFreeBrick;
	}
	public boolean get2BelowFreeBrick() {
		return twoBelowFreeBrick;
	}
	public boolean get2AboveFreeBrick() {
		return twoAboveFreeBrick;
	}
}
