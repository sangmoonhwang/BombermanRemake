package Model;

import Model.Enemies.Enemy;

public class Tile implements GameObject {
	private int xval, yval;

	
	public Tile() {
		int xval, yval;
	}
	
	public boolean collisionDetection(Bomberman b, Indestructible i) {
		boolean collision = false;
		if ((b.getXval()+40 > i.getXval()*50 &&
				b.getYval() < i.getYval()*50+45 &&
			     b.getXval() < i.getXval()*50+40 && 
			     b.getYval()+45 > i.getYval()*50)){
			collision = true;
		}
		return collision;
	}
	
	public boolean collisionDetection(Enemy e, Indestructible i) {
		boolean collision = false;
		if ((e.getXval()+40 > i.getXval()*50 &&
				e.getYval() < i.getYval()*50+45 &&
			     e.getXval() < i.getXval()*50+40 && 
			     e.getYval()+45 > i.getYval()*50)){
			collision = true;
		}
		return collision;
		
	}
	
	public boolean emptyLeft(Enemy e, Indestructible i, float y) {
		Enemy test = new Enemy();
		test.setYval(e.getYval()+y);
		test.setXval(e.getXval()-1);
		
		if(collisionDetection(test,i)){
			return false;
		}
		return true;
	}

	public boolean emptyRight(Enemy e, Indestructible i, float y) {
		Enemy test = new Enemy();
		test.setYval(e.getYval()+y);
		test.setXval(e.getXval()+1);
		
		if(collisionDetection(test,i)){
			return false;
		}
		return true;
	}

	public boolean emptyBelow(Enemy e, Indestructible i, float x) {
		Enemy test = new Enemy();
		test.setYval(e.getYval()+1);
		test.setXval(e.getXval()+x);
		
		if(collisionDetection(test,i)){
			return false;
		}
		return true;
	}

	public boolean emptyAbove(Enemy e, Indestructible i, float x) {
		Enemy test = new Enemy();
		test.setYval(e.getYval()-1);
		test.setXval(e.getXval()+x);
		
		if(collisionDetection(test,i)){
			return false;
		}
		return true;
	}
	
	public boolean emptyAbove(Bomberman b, Indestructible i, float x) {
		Bomberman test = new Bomberman();
		test.setYval(b.getYval()-1);
		test.setXval(b.getXval()+x);
		
		if(collisionDetection(test,i)){
			return false;
		}
		return true;
	}
	
	public boolean emptyBelow(Bomberman b, Indestructible i, float x) {
		Bomberman test = new Bomberman();
		test.setYval(b.getYval()+1);
		test.setXval(b.getXval()+x);
		
		if(collisionDetection(test,i)){
			return false;
		}
		return true;
	}
	
	public boolean emptyLeft(Bomberman b, Indestructible i, float y) {
		Bomberman test = new Bomberman();
		test.setYval(b.getYval()+y);
		test.setXval(b.getXval()-1);
		
		if(collisionDetection(test,i)){
			return false;
		}
		return true;
	}
	
	public boolean emptyRight(Bomberman b, Indestructible i, float y) {
		Bomberman test = new Bomberman();
		test.setYval(b.getYval()+y);
		test.setXval(b.getXval()+1);
		
		if(collisionDetection(test,i)){
			return false;
		}
		return true;
	}
	
	public boolean collisionDetection(Movable b, Destructible i) {
		boolean collision = false;
		if ((b.getXval()+40 > i.getXval()*50 &&
				b.getYval() < i.getYval()*50+45 &&
			     b.getXval() < i.getXval()*50+40 && 
			     b.getYval()+45 > i.getYval()*50)){
			collision = true;
		}
		return collision;
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
