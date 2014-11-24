package Model;

/**
 * 
 * @author 
 *
 *	This class defines each cell in the grid
 */
public class Box {
	public int x;
	public int y;
	public static int size = 50;
	
	public Box(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	
	public boolean equals(Box b){
		if(this.x == b.x && this.y == b.y)
			return true;
		return false;
	}
}
