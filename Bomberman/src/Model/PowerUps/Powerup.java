package Model.PowerUps;

import Model.Block;

public abstract class Powerup extends Block {
	int xval;
		//physical attributes
		private int yval;
		int height;
		private int width;
		
		public Powerup() {
			xval = yval = 150;
			height = width = 50;
		}
		
		//setters
		public void setXval(int i){
			xval = i;
		}
		public void setYval(int i){
			yval = i;
		}
		public void setHeight(int i){
			height = i;
		}
		public void setWidth(int i){
			width = i;
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
		
		abstract public void activate();
}
