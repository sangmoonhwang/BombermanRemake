package Controller;

import Model.Destructible;
import Model.Indestructible;
import Model.Movable;

public class CollissionDetection {
	
	public boolean collisionDetection(Movable test, Indestructible i) {
		boolean collision = false;
		if ((test.getXval() + test.getWidth() > i.getXval()*50 &&
				test.getYval() < i.getYval()*50 + test.getHeight() &&
			     test.getXval() < i.getXval()*50 + test.getWidth() && 
			     test.getYval() + test.getHeight() > i.getYval()*50)){
			collision = true;
		}
		return collision;
	}

	public boolean collisionDetection(Movable test, Destructible i) {
		boolean collision = false;
		if ((test.getXval() + test.getWidth() > i.getXval()*50 &&
				test.getYval() < i.getYval()*50+45 &&
			     test.getXval() < i.getXval()*50+40 && 
			     test.getYval() + test.getHeight() > i.getYval()*50)){
			collision = true;
		}
		return collision;
	}

	public boolean collisionDetection(Movable test, Movable test1) {
		boolean collision = false;
		if ((test.getXval() + test.getWidth() > test1.getXval() &&
				test.getYval() < test1.getYval() + test.getHeight() &&
			     test.getXval() < test1.getXval() + test.getWidth() && 
			     test.getYval() + test.getHeight() > test1.getYval())){
			collision = true;
	}
		return collision;
	}
	
	
	public boolean emptyAbove(Movable b, Indestructible i) {
		Movable copy = new Movable();
		copy.setYval(b.getYval()-2);
		copy.setXval(b.getXval());
		copy.setHeight(b.getHeight());
		copy.setWidth(b.getWidth());
		
		if(collisionDetection(copy,i)){
			return false;
		}
		return true;
	}
	
	public boolean emptyBelow(Movable b, Indestructible i) {
		Movable copy = new Movable();
		copy.setYval(b.getYval()+2);
		copy.setXval(b.getXval());
		copy.setHeight(b.getHeight());
		copy.setWidth(b.getWidth());
		
		if(collisionDetection(copy,i)){
			return false;
		}
		return true;
	}
	
	public boolean emptyLeft(Movable b, Indestructible i) {
		Movable copy = new Movable();
		copy.setYval(b.getYval());
		copy.setXval(b.getXval()-2);
		copy.setHeight(b.getHeight());
		copy.setWidth(b.getWidth());
		
		if(collisionDetection(copy,i)){
			return false;
		}
		return true;
	}
	
	public boolean emptyRight(Movable b, Indestructible i) {
		Movable copy = new Movable();
		copy.setYval(b.getYval());
		copy.setXval(b.getXval()+2);
		copy.setHeight(b.getHeight());
		copy.setWidth(b.getWidth());
		
		if(collisionDetection(copy,i)){
			return false;
		}
		return true;
	}
}
