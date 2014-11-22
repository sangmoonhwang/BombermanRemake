package Model.Enemies;

import java.util.LinkedList;


public class Kondoria {
	private int intelligence;
	private int speed;
	private int points;
	private boolean wallPass;

	public Kondoria() {
		speed = 1;
		intelligence = 3;
		points = 1000;
		wallPass = true;

	}

	public void die() {

	}

	public void patrol() {

	}

	public void chase() {

	}

	public void move() {
		//check if it is at intersection(probability of 50% of changing direction)
		//check bomberman position if it is within 2 block then call chase method(if obstacle exist then use Astar search)
	}

	public void Astar() {

		/*
	  public void SearchForPath(){

			GNode goalNode = AStar();

			if(goalNode == null)
				return;

			while(goalNode.bParent != null){
				path.add(goalNode.bCurrent);
				goalNode = goalNode.bParent;
			}

			this.repaint();
		}

		public GNode AStar(){

			//list of all nodes we are going to search ie. the frontier
			LinkedList<GNode> openList = new LinkedList<GNode>();
			//list of all nodes we've already searched
			LinkedList<Box> closedList = new LinkedList<Box>();

			//init the  openlist
			GNode startNode = new GNode(bStart, null, 0, EuclidianDistance(bStart, bEnd));
			openList.add(startNode);

			while(!openList.isEmpty()){
				//first node in open list
				GNode current = openList.removeFirst();
				//add it to closed  list
				closedList.add(current.bCurrent);

				//check if its the goal
				if(current.bCurrent.equals(bEnd)){
					return current;
				}

				int xIndex = current.bCurrent.x/10;
				int yIndex = current.bCurrent.y/10;

				//for all 8 adjacent nodes
				for(int i=xIndex-1;i<=xIndex+1;i++){
					for(int j=yIndex-1;j<=yIndex+1;j++){

						//skip if out of bounds
						if(i<0 || i> rows || j<0 || j>cols){
							continue;
						}

						//skip if filled box
						if(gridModel[i][j].isFilled()){
							continue;
						}

						//skip if already searched before
						if(closedList.contains(gridModel[i][j])){
							continue;
						}

						//Create next node
						GNode next = new GNode(gridModel[i][j],current,current.gCost+10,EuclidianDistance(gridModel[i][j],bEnd));

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
			}

			return null;
		}

		 */
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

	public boolean getWallPass() {
		return wallPass;
	}

}
