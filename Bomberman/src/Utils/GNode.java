package Utils;

import Model.Box;

/**
 * This class is a helper class for A* algorithm
 *
 */
public class GNode {

	/**
	 * current tile
	 */
	public Box eCurrent;
	/**
	 * fCost of this tile
	 */
	public float fCost;
	/**
	 * hCost of this tile
	 */
	public float hCost;
	/**
	 * gCost of this tile
	 */
	public float gCost;
	
	/**
	 * the parent tile
	 */
	public GNode eParent;
	
	/**
	 * constructor
	 * @param current current tile
	 * @param Parent parent tile
	 * @param HCost Hcost associated with the current tile
	 * @param GCost Gcost associated with the current tile
	 */
	public GNode(Box current, GNode Parent, float HCost, float GCost){
		eCurrent = current;
		eParent = Parent;
		hCost = HCost;
		gCost = GCost;
		fCost = hCost + gCost;
	}
}
