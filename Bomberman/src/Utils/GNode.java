package Utils;

public class GNode {

	public int eCurrent;
	public float fCost;
	public float hCost;
	public float gCost;
	
	public GNode eParent;
	
	public GNode(int current, GNode Parent, float HCost, float GCost){
		eCurrent = current;
		eParent = Parent;
		hCost = HCost;
		gCost = GCost;
		fCost = hCost + gCost;
		
	}
}
