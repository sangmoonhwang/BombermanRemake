package Controller;

import java.io.Serializable;

import Model.Block;
import Model.Bomb;
import Model.Bomberman;
import Model.Explosion;
import Model.Movable;

/**
 * Collision detector
 *
 */
public class CollisionDetection implements Serializable{

	/**
	 * collision detector between block and movable
	 * @param test movable
	 * @param i block
	 * @return true if collided false otherwise
	 */
	public boolean collisionDetection(Movable test, Block i) {
		boolean collision = false;
		if ((test.getXval() + test.getWidth() > i.getXval() &&
				test.getYval() < i.getYval() + i.getHeight() &&
				test.getXval() < i.getXval() + i.getWidth() && 
				test.getYval() + test.getHeight() > i.getYval())){
			collision = true;
		}
		return collision;
	}

	/**
	 * collision detector between movable and bomb
	 * @param test movable
	 * @param i bomb
	 * @return true if collided false otherwise
	 */
	public boolean collisionDetection(Movable test, Bomb i) {
		boolean collision = false;
		if(i.getActive()){

			if ((test.getXval() + test.getWidth() > i.getXval() &&
					test.getYval() < i.getYval() + i.getHeight() &&
					test.getXval() < i.getXval() + i.getWidth() && 
					(test.getYval() + test.getHeight()) > i.getYval())){
				collision = true;
			}
		}
		return collision;
	}

	/**
	 * collision detector between two movables
	 * @param test movable
	 * @param test1 movable
	 * @return true if collided false otherwise
	 */
	public boolean collisionDetection(Movable test, Movable test1){
		boolean collision = false;
		if ((test.getXval() + test.getWidth() > test1.getXval() &&
				test.getYval() < test1.getYval() + test1.getHeight() &&
				test.getXval() < test1.getXval() + test1.getWidth() && 
				test.getYval() + test.getHeight() > test1.getYval())){
			collision = true;
		}
		return collision;
	}



	/**
	 * check emptiness above movable
	 * @param b movable
	 * @param i block
	 * @return true if empty false otherwise
	 */
	public boolean emptyAbove(Movable b, Block i) {
		Movable copy = new Movable();
		copy.setYval(b.getYval() - Bomberman.speed);
		copy.setXval(b.getXval());
		copy.setHeight(b.getHeight());
		copy.setWidth(b.getWidth());

		if(collisionDetection(copy,i)){
			return false;
		}
		return true;
	}

	/**
	 * check emptiness below movable
	 * @param b movable
	 * @param i block
	 * @return true if empty false otherwise
	 */
	public boolean emptyBelow(Movable b, Block i) {
		Movable copy = new Movable();
		copy.setYval(b.getYval() + Bomberman.speed);
		copy.setXval(b.getXval());
		copy.setHeight(b.getHeight());
		copy.setWidth(b.getWidth());

		if(collisionDetection(copy,i)){
			return false;
		}
		return true;
	}

	/**
	 * check emptiness left of the movable
	 * @param b movable
	 * @param i block
	 * @return true if empty false otherwise
	 */
	public boolean emptyLeft(Movable b, Block i) {
		Movable copy = new Movable();
		copy.setYval(b.getYval());
		copy.setXval(b.getXval() - Bomberman.speed);
		copy.setHeight(b.getHeight());
		copy.setWidth(b.getWidth());

		if(collisionDetection(copy,i)){
			return false;
		}
		return true;
	}

	/**
	 * check emptiness right of the movable
	 * @param b movable
	 * @param i block
	 * @return true if empty false otherwise
	 */
	public boolean emptyRight(Movable b, Block i) {
		Movable copy = new Movable();
		copy.setYval(b.getYval());
		copy.setXval(b.getXval() + Bomberman.speed);
		copy.setHeight(b.getHeight());
		copy.setWidth(b.getWidth());

		if(collisionDetection(copy,i)){
			return false;
		}
		return true;
	}
}
