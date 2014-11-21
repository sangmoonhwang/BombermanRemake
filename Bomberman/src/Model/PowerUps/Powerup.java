package Model.PowerUps;

public abstract class Powerup {
	//physical attributes
		private float xval, yval;
		private float height, width;
		
		public Powerup() {
			xval = yval = 0;
			height = width = 50;
		}
		
		//setters
		public void setXval(float i){
			xval = i;
		}
		public void setYval(float i){
			yval = i;
		}
		public void setHeight(float i){
			height = i;
		}
		public void setWidth(float i){
			width = i;
		}

		//getters
		public float getXval(){
			return xval;
		}
		public float getYval(){
			return yval;
		}
		public float getHeight(){
			return height;
		}
		public float getWidth(){
			return width;
		}
		
		abstract public void activate();
}
