package Model.PowerUps;

import Model.Block;

public abstract class Powerup extends Block {
	
		//physical attributes
		private int xval;
		private int yval;
		private int height;
		private int width;
		private String identity;
		
		public Powerup() {
			//xval = yval = 150;
			//height = width = 50;
		}
		
		public Powerup(String identity) {
			//xval = yval = 150;
			//height = width = 50;
			this.identity = identity;
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
