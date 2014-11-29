package Controller;

import java.io.Serializable;

import Model.Block;
import Model.Bomb;
import Model.Bomberman;
import Model.Explosion;
import Model.Movable;

public class CollisionDetection implements Serializable{

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

	public boolean collisionDetection(Movable test, Explosion test1, int i, int max){
		boolean collision = false;
		switch(i){
		case 0:
			if ((test.getXval() + test.getWidth() > test1.getXval() &&
					test.getYval() < test1.getYval() + test1.getHeight() &&
					test.getXval() < test1.getXval() + test1.getWidth() && 
					test.getYval() + test.getHeight() > test1.getYval())){
				collision = true;
			}
			break;
		case 1:	
			if ((test.getXval() + test.getWidth() > test1.getXval() &&
					test.getYval() < test1.getYval() + test1.getHeight() &&
					test.getXval() < test1.getXval() + test1.getWidth()*max && 
					test.getYval() + test.getHeight() > test1.getYval())){
				collision = true;
			}
			break;
		case 2:	
			if ((test.getXval() + test.getWidth() > test1.getXval() - max * test1.getWidth() &&
					test.getYval() < test1.getYval() + test1.getHeight() &&
					test.getXval() < test1.getXval() + test1.getWidth() && 
					test.getYval() + test.getHeight() > test1.getYval())){
				collision = true;
			}
			break;
		case 3:	
			if ((test.getXval() + test.getWidth() > test1.getXval() &&
					test.getYval() < test1.getYval() + test1.getHeight() * max &&
					test.getXval() < test1.getXval() + test1.getWidth() && 
					test.getYval() + test.getHeight() > test1.getYval())){
				collision = true;
			}
			break;
		case 4:	
			if ((test.getXval() + test.getWidth() > test1.getXval() &&
					test.getYval() < test1.getYval() + test1.getHeight() &&
					test.getXval() < test1.getXval() + test1.getWidth() && 
					test.getYval() + test.getHeight() > test1.getYval()- max * test1.getWidth())){
				collision = true;
			}
			break;
		}
		return collision;
	}


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
