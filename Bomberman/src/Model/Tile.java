package Model;

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
