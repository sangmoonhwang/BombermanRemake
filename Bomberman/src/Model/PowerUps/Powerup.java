package Model.PowerUps;

import Model.Block;

public class Powerup extends Block {
	
		//physical attributes
		private int xval;
		private int yval;
		private int height;
		private int width;
		private String identity;

		public Powerup(String identity) {
			this.identity = identity;
		}
		
		//setters
		public void setXval(int i) {
			xval = i;
		}
		public void setYval(int i) {
			yval = i;
		}
		public void setHeight(int i) {
			height = i;
		}
		public void setWidth(int i) {
			width = i;
		}

		//getters
		public int getXval() {
			return xval;
		}
		public int getYval() {
			return yval;
		}
		public int getHeight() {
			return height;
		}
		public int getWidth() {
			return width;
		}
		public String getIdentity() {
			return identity;
		}
		
		//abstract public void activate();
}
