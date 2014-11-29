package Model.PowerUps;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;

import Model.Block;
import Model.Bomb;
import Model.Bomberman;

/**
 * This class implements all powerups and their effects
 *
 */
public class Powerup extends Block implements Serializable{

	//physical attributes
	/**
	 * X position of the powerup 
	 */
	private int xval;
	/**
	 * Y position of the powerup
	 */
	private int yval;
	/**
	 * Height of the powerup
	 */
	private int height;
	/**
	 * Width of the powerup
	 */
	private int width;
	/**
	 * Identifier of the powerup
	 */
	private String identity;
	/**
	 * Power up image
	 */
	private Image image;

	/**
	 * constructor
	 * @param identity The identity of the powerup
	 */
	public Powerup(String identity) {
		this.identity = identity;
		setImage();
		height = 50;
		width = 50;
	}

	/**
	 * sets the powerup image according to its identity
	 * @param None
	 * @return None
	 */
	public void setImage() {
		if(identity.equals("Bombpass")){
			image = Toolkit.getDefaultToolkit().getImage("res/image/Bombpass.png");
		} else if(identity.equals("Detonator")) {
			image = Toolkit.getDefaultToolkit().getImage("res/image/Detonator.png");
		} else if(identity.equals("Flamepass")) {
			image = Toolkit.getDefaultToolkit().getImage("res/image/Flamepass.png");
		} else if(identity.equals("Flames")) {
			image = Toolkit.getDefaultToolkit().getImage("res/image/Flames.png");
		} else if(identity.equals("Mystery")) {
			image = Toolkit.getDefaultToolkit().getImage("res/image/Mystery.png");
		} else if(identity.equals("Speed")) {
			image = Toolkit.getDefaultToolkit().getImage("res/image/Speed.png");
		} else if(identity.equals("UpBombs")) {
			image = Toolkit.getDefaultToolkit().getImage("res/image/UpBombs.png");
		} else if(identity.equals("Wallpass")) {
			image = Toolkit.getDefaultToolkit().getImage("res/image/Wallpass.png");
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
	/**
	 * setter for identity of the powerup
	 * @return Identity of the powerup
	 */
	public String getIdentity() {
		return identity;
	}
	/**
	 * setter for image of the powerup
	 * @return Image of the powerup
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * This method activate the powerup so that it becomes effective
	 */
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
			Bomberman.bombs.add(new Bomb(false));
		} else if(identity.equals("Wallpass")) {
			Bomberman.wallPass = true;
		}
	}
}
