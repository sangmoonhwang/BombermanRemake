package Model;

import Model.Enemies.Enemy;

public class Tile implements GameObject {
	private int xval, yval;

	
	public Tile() {
		int xval, yval;
	}
	
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
	
	
	public boolean emptyAbove(Movable b, Indestructible i, float x) {
		Movable copy = new Movable();
		copy.setYval(b.getYval()-2);
		copy.setXval(b.getXval()+x);
		copy.setHeight(b.getHeight());
		copy.setWidth(b.getWidth());
		
		if(collisionDetection(copy,i)){
			return false;
		}
		return true;
	}
	
	public boolean emptyBelow(Movable b, Indestructible i, float x) {
		Movable copy = new Movable();
		copy.setYval(b.getYval()+2);
		copy.setXval(b.getXval()+x);
		copy.setHeight(b.getHeight());
		copy.setWidth(b.getWidth());
		
		if(collisionDetection(copy,i)){
			return false;
		}
		return true;
	}
	
	public boolean emptyLeft(Movable b, Indestructible i, float y) {
		Movable copy = new Movable();
		copy.setYval(b.getYval()+y);
		copy.setXval(b.getXval()-2);
		copy.setHeight(b.getHeight());
		copy.setWidth(b.getWidth());
		
		if(collisionDetection(copy,i)){
			return false;
		}
		return true;
	}
	
	public boolean emptyRight(Movable b, Indestructible i, float y) {
		Movable copy = new Movable();
		copy.setYval(b.getYval()+y);
		copy.setXval(b.getXval()+2);
		copy.setHeight(b.getHeight());
		copy.setWidth(b.getWidth());
		
		if(collisionDetection(copy,i)){
			return false;
		}
		return true;
	}
	
	@Override
	public void spawn() {
		
	}
	//getter
	 public int getXval(){
	   return xval;
	 }
	 //getter
	 public int getYval(){
	   return yval;
	 }
	 //setter
	 public void setXval(int i){
	   xval = i;
	 }
	 //setter
	 public void setYval(int i){
	   yval = i;
	 }

}
