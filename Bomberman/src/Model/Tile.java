package Model;

import Model.Enemies.Enemy;

public class Tile implements GameObject {
	private int xval, yval;

	
	public Tile() {
		int xval, yval;
	}
	
	public boolean collisionDetection(Movable test, Indestructible i) {
		boolean collision = false;
		if ((test.getXval()+40 > i.getXval()*50 &&
				test.getYval() < i.getYval()*50+45 &&
			     test.getXval() < i.getXval()*50+40 && 
			     test.getYval()+45 > i.getYval()*50)){
			collision = true;
		}
		return collision;
	}

	public boolean collisionDetection(Movable test, Movable test1) {
		boolean collision = false;
		if ((test.getXval()+40 > test1.getXval() &&
				test.getYval() < test1.getYval()+45 &&
			     test.getXval() < test1.getXval()+40 && 
			     test.getYval()+45 > test1.getYval())){
			collision = true;
		}
		return collision;
	}
	
	public boolean collisionDetection(Movable test, Destructible i) {
		boolean collision = false;
		if ((test.getXval()+40 > i.getXval()*50 &&
				test.getYval() < i.getYval()*50+45 &&
			     test.getXval() < i.getXval()*50+40 && 
			     test.getYval()+45 > i.getYval()*50)){
			collision = true;
		}
		return collision;
	}
	
	public boolean emptyAbove(Movable b, Indestructible i, float x) {
		Movable test = new Movable();
		test.setYval(b.getYval()-1);
		test.setXval(b.getXval()+x);
		
		if(collisionDetection(test,i)){
			return false;
		}
		return true;
	}
	
	public boolean emptyBelow(Movable b, Indestructible i, float x) {
		Movable test = new Movable();
		test.setYval(b.getYval()+1);
		test.setXval(b.getXval()+x);
		
		if(collisionDetection(test,i)){
			return false;
		}
		return true;
	}
	
	public boolean emptyLeft(Movable b, Indestructible i, float y) {
		Movable test = new Movable();
		test.setYval(b.getYval()+y);
		test.setXval(b.getXval()-1);
		
		if(collisionDetection(test,i)){
			return false;
		}
		return true;
	}
	
	public boolean emptyRight(Movable b, Indestructible i, float y) {
		Movable test = new Movable();
		test.setYval(b.getYval()+y);
		test.setXval(b.getXval()+1);
		
		if(collisionDetection(test,i)){
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
