package Model;

public class Bomb {
	private int xval, yval;
	private int height, width;
	private boolean active;
	private boolean escaped;
	private Explosion[] personalExplosions;

	public Bomb() {
		xval = yval = 0;
		height = width = 50;
		active = false;
		escaped = false;
		personalExplosions = new Explosion[5];
	}
	
	//explosion
	public void explode(){
		active = false;
		for(int i = 0; i < 5; i++){
			personalExplosions[i] = new Explosion();
		}
		personalExplosions[0].setXval(this.xval);
		personalExplosions[0].setYval(this.yval);
		personalExplosions[1].setXval(xval+50);
		personalExplosions[1].setYval(yval);
		personalExplosions[1].setWidth(Bomberman.flames*50);
		personalExplosions[2].setXval(xval-50*Bomberman.flames); 
		personalExplosions[2].setYval(yval);
		personalExplosions[2].setWidth(Bomberman.flames*50);
		personalExplosions[3].setXval(xval);
		personalExplosions[3].setYval(yval+50);
		personalExplosions[3].setHeight(Bomberman.flames*50);
		personalExplosions[4].setXval(xval);
		personalExplosions[4].setYval(yval-50*Bomberman.flames); 
		personalExplosions[4].setHeight(50*Bomberman.flames);
		for(int i = 0; i < 5; i++){
			personalExplosions[i].setExploding(true);
		}
	}
	
	//setters
	public void setXval(int i){
		xval = i;
	}
	public void setYval(int i){
		yval = i;
	}
	public void setActive(boolean b){
		active = b;
	}
	
	public void setEscaped(boolean b){
		escaped = b;
	}
	
	//getters
	public int getXval(){
		return xval;
	}
	public int getYval(){
		return yval;
	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public boolean getActive(){
		return active;
	}
	
	public boolean getEscaped(){
		return escaped;
	}
	
	public Explosion[] getPersonalExplosions(){
		return personalExplosions;
	}

}
