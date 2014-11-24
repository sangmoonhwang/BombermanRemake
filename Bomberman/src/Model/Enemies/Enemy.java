package Model.Enemies;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;

import Controller.Map;
import Model.Destructible;
import Model.Indestructible;
import Model.Movable;
import Utils.GNode;

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

	ArrayList<Integer> path = new ArrayList<Integer>();

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
	public int whichTileIsOn(int x, int y) {
		int tmp = y/50;
		return ((x/50) + tmp*31);
	}

	/**
	 * 
	 * @param 
	 * @return None
	 */
	public void searchFreePath(ArrayList<Indestructible> indestructibles, ArrayList<Destructible> bricks) {
		statusReset();
		int tileNum = whichTileIsOn(getXval(), getYval());
		int enemyState = getState();
		for(int i = 0; i < indestructibles.size(); i++) {
			int indestructiblesTileNum = whichTileIsOn(indestructibles.get(i).getXval(), indestructibles.get(i).getYval());
			if(indestructiblesTileNum == (tileNum) && (enemyState == 1)) {
				leftFree = false;
			} else if(indestructiblesTileNum == (tileNum+1) && (enemyState == 0)) {
				rightFree = false;
			} else if(indestructiblesTileNum == (tileNum) && (enemyState == 3)) {
				aboveFree = false;
			} else if(indestructiblesTileNum == (tileNum+31) && (enemyState == 2)) {
				belowFree = false;
			} else if(indestructiblesTileNum == (tileNum-32)) {
				aboveLeftFree = false;
			} else if(indestructiblesTileNum == (tileNum-30)) {
				aboveRightFree = false;
			} else if(indestructiblesTileNum == (tileNum+30)) {
				belowLeftFree = false;
			} else if(indestructiblesTileNum == (tileNum+32)) {
				belowRightFree = false;
			}
		}

		for(int i=0; i<bricks.size(); i++) {
			int bricksTileNum = whichTileIsOn(bricks.get(i).getXval(), bricks.get(i).getYval());
			if(bricksTileNum == (tileNum) && (enemyState == 1)) {
				leftFreeBrick = false;
			} else if(bricksTileNum == (tileNum+1) && (enemyState == 0)) {
				rightFreeBrick = false;
			} else if(bricksTileNum == (tileNum) && (enemyState == 3)) {
				aboveFreeBrick = false;
			} else if(bricksTileNum == (tileNum+31) && (enemyState == 2)) {
				belowFreeBrick = false;
			} else if(bricksTileNum == (tileNum-32)) {
				aboveLeftFreeBrick = false;
			} else if(bricksTileNum == (tileNum-30)) {
				aboveRightFreeBrick = false;
			} else if(bricksTileNum == (tileNum+30)) {
				belowLeftFreeBrick = false;
			} else if(bricksTileNum == (tileNum+32)) {
				belowRightFreeBrick = false;
			}
		}
	}
	
	/**
	 * calculates the Euclidian Distance
	 * @param current position and end point
	 * @return None
	 */
	public float EuclidianDistance(int current, int end) {
		return (float) Math.sqrt(Math.pow(current,2) + Math.pow(end,2));
	}

	/**
	 * search for the path to chase bomberman
	 * @param Bobmerman Tile number and Enemy Tile number
	 * @return None
	 */
	public void searchForPath(int bombermanTile, int enemyTile) {

		GNode goalNode = AStar(bombermanTile, enemyTile);

		if(goalNode == null)
			return;

		while(goalNode.eParent != null){
			path.add(goalNode.eCurrent);
			goalNode = goalNode.eParent;
		}

	}

	/**
	 * Applies the Astar method to find the path from the enemy to Bomberman
	 * @param Bobmerman Tile number and Enemy Tile number
	 * @return None
	 */
	public GNode AStar(int bombermanTile, int enemyTile) {
		int cur = 1;
		//list of all nodes we are going to search ie. the frontier
		LinkedList<GNode> openList = new LinkedList<GNode>();
		
		//list of all nodes we've already searched
		LinkedList<Integer> closedList = new LinkedList<Integer>();

		//init the openlist
		GNode startNode = new GNode(enemyTile, null, 0, EuclidianDistance(enemyTile, bombermanTile));
		//openList.add(startNode);
		openList.add(startNode);
		   
		while(!openList.isEmpty()) {
			cur++;
			//first node in open list
			GNode current = openList.removeFirst();

			//add it to closed  list
			closedList.add(current.eCurrent);

			//check if its the goal
			if(current.eCurrent == bombermanTile) {
				return current;
			}

			int index = current.eCurrent;

			//for 3 Top adjacent nodes
			for(int i = index - 32; i < (index - 29); i++) {

				//skip if out of bounds and notFree
				if(i<0 || (!aboveFree && i == index - 31)|| (!aboveFreeBrick && i == index - 31) 
						|| (!aboveLeftFree && i == index - 32) || (!aboveLeftFreeBrick && i == index - 32)
						|| (!aboveRightFree && i == index - 30) || (!aboveRightFreeBrick && i == index - 30)) {
					continue;
				}

				//skip if already searched before
				if(closedList.contains(i)) {
					continue;
				}

				//Create next node
				GNode next = new GNode(i,current,current.gCost+10,EuclidianDistance(i,bombermanTile));

				//add node to open list
				openList.add(next);

				//sort open list
				for(int k = 0;k<openList.size()-1;k++){
					if(openList.get(k).fCost>openList.get(k+1).fCost){
						//swap
						GNode temp = openList.get(k);
						openList.set(k, openList.get(k+1));
						openList.set(k+1, temp);							
					}
				}

			}

			//for 2 adjacent nodes
			for(int i = index - 1; i < (index + 2); i++) {

				//skip if out of bounds and current enemy location and notFree
				if(i<0 || i == index || (!leftFree && i == index - 1)|| (!leftFreeBrick && i == index - 1) 
						|| (!rightFree && i == index + 1) || (!rightFreeBrick && i == index + 1)) {
					continue;
				}

				//skip if already searched before
				if(closedList.contains(i)) {
					continue;
				}

				//Create next node
				GNode next = new GNode(i,current,current.gCost+10,EuclidianDistance(i,bombermanTile));

				//add node to open list
				openList.add(next);

				//sort open list
				for(int k = 0;k<openList.size()-1;k++){
					if(openList.get(k).fCost>openList.get(k+1).fCost){
						//swap
						GNode temp = openList.get(k);
						openList.set(k, openList.get(k+1));
						openList.set(k+1, temp);							
					}
				}
			}
			//for 3 bottom adjacent nodes
			for(int i = enemyTile + 30; i < (enemyTile - 33); i++) {

				//skip if out of bounds or notFree
				if(i<0 || (!belowFree && i == index + 31)|| (!belowFreeBrick && i == index + 31) 
						|| (!belowLeftFree && i == index + 30) || (!belowLeftFreeBrick && i == index + 30)
						|| (!belowRightFree && i == index + 32) || (!belowRightFreeBrick && i == index + 32)){
					continue;
				}

				//skip if already searched before
				if(closedList.contains(i)) {
					continue;
				}

				//Create next node
				GNode next = new GNode(i,current,current.gCost+10,EuclidianDistance(i,bombermanTile));

				//add node to open list
				openList.add(next);

				//sort open list
				for(int k = 0;k<openList.size()-1;k++){
					if(openList.get(k).fCost>openList.get(k+1).fCost){
						//swap
						GNode temp = openList.get(k);
						openList.set(k, openList.get(k+1));
						openList.set(k+1, temp);							
					}
				}
			}
		}

		return null;
	}

	//setters
	public void setXval(int i) {
		xval = i;
	}
	public void setYval(int i) {
		yval = i;
	}
	public void setState(int state) {
		this.state = state;
	}
	//increment
	public void incrementXval(int i) {
		xval += i;
	}
	public void incrementYval(int i) {
		yval += i;
	}

	//getters
	public int getXval() {
		return xval;
	}
	public int getYval() {
		return yval;
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
	public ArrayList<Integer> getPath() {
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
}
