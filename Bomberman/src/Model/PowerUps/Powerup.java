package Model.PowerUps;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;

import Model.Block;
import Model.Bomb;
import Model.Bomberman;

public class Powerup extends Block implements Serializable{

	//physical attributes
	private int xval;
	private int yval;
	private int height;
	private int width;
	private String identity;
	private Image image;

	public Powerup(String identity) {
		this.identity = identity;
		setImage();
		height = 50;
		width = 50;
	}

	/**
	 * move the enemy depending on the current moving direction
	 * @param None
	 * @return None
	 */
	public void setImage() {
		if(identity.equals("Bombpass")){
			image = Toolkit.getDefaultToolkit().getImage("Bombpass.png");
		} else if(identity.equals("Detonator")) {
			image = Toolkit.getDefaultToolkit().getImage("Detonator.png");
		} else if(identity.equals("Flamepass")) {
			image = Toolkit.getDefaultToolkit().getImage("Flamepass.png");
		} else if(identity.equals("Flames")) {
			image = Toolkit.getDefaultToolkit().getImage("Flames.png");
		} else if(identity.equals("Mystery")) {
			image = Toolkit.getDefaultToolkit().getImage("Mystery.png");
		} else if(identity.equals("Speed")) {
			image = Toolkit.getDefaultToolkit().getImage("Speed.png");
		} else if(identity.equals("UpBombs")) {
			image = Toolkit.getDefaultToolkit().getImage("UpBombs.png");
		} else if(identity.equals("Wallpass")) {
			image = Toolkit.getDefaultToolkit().getImage("Wallpass.png");
		}
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
	public Image getImage() {
		return image;
	}

	public void activate() {
		if(identity.equals("Bombpass")){
			Bomberman.bombPass = true;
		} else if(identity.equals("Detonator")) {
			Bomberman.detonate = true;
		} else if(identity.equals("Flamepass")) {
			Bomberman.flamePass = true;
		} else if(identity.equals("Flames")) {
			Bomberman.flames++;
		} else if(identity.equals("Mystery")) {
			Bomberman.mystery_From = System.nanoTime();
		} else if(identity.equals("Speed")) {
			Bomberman.speed++;
		} else if(identity.equals("UpBombs")) {
			if(Bomberman.availableBombs <= 10)
				Bomberman.availableBombs++;
			Bomberman.bombs.add(new Bomb());
		} else if(identity.equals("Wallpass")) {
			Bomberman.wallPass = true;
		}
	}
}
