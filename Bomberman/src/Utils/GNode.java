package Utils;

import Model.Box;

public class GNode {

	public Box eCurrent;
	public float fCost;
	public float hCost;
	public float gCost;
	
	public GNode eParent;
	
	public GNode(Box current, GNode Parent, float HCost, float GCost){
		eCurrent = current;
		eParent = Parent;
		hCost = HCost;
		gCost = GCost;
		fCost = hCost + gCost;
	}
}
