package Model;

import java.io.Serializable;

/**
 *	This class defines each tile in the grid
 */
public class Box implements Serializable{
	/**
	 * x position of the tile
	 */
	public int x;
	/**
	 * y position of the tile
	 */
	public int y;
	/**
	 * width and height of the tile
	 */
	public static int size = 50;
	
	/**
	 * constructor
	 * @param x x position
	 * @param y y position
	 */
	public Box(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * determines if the equality of the tiles
	 * @param b tile to compare
	 * @return true if equal false otherwise
	 */
	public boolean equals(Box b){
		if(this.x == b.x && this.y == b.y)
			return true;
		return false;
	}
}
