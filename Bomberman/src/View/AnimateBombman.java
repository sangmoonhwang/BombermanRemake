package View;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Bomberman;

public class AnimateBombman {
	Bomberman bm = null;
	int x;
	int y;
	int width;
	int height;

	public AnimateBombman(Bomberman bm){
		this.bm = bm;
		this.width = 17;
		this.height = 22;
	}

    
	
	public Image animateBm(){
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("Bomberman-BombermanTournament.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		switch(bm.getDirection()){
		//north
		case 0:
			if(bm.isMoving() && System.currentTimeMillis()/100 % 2 == 0){
				x = 210;
				y = 3;
			}
			else{
				x = 194;
				y = 2;
			}
			break;
		//west	
		case 1:
			if(bm.isMoving()){
				if(System.currentTimeMillis()/100 % 2 == 0){
					x = 107;
					y = 3;
				}
				else{
					x = 122;
					y = 3;
				}
			}
			else{
				x = 92;
				y = 3;
			}
			break;
		//south
		case 2:
			if(bm.isMoving() && System.currentTimeMillis()/100 % 2 == 0){
				x = 23;
				y = 2;
			}
			else{
				x = 7;
				y = 2;
			}
			break;
		//east
		case 3:
			if(bm.isMoving()){
				if(System.currentTimeMillis()/100 % 2 == 0){
					x = 161;
					y = 112;
				}
				else{
					x = 177;
					y = 113;
				}
			}
			else{
				x = 192;
				y = 113;
			}
			break;
		case 4: //nw
			if(bm.isMoving()){
				if(System.currentTimeMillis()/100 % 2 == 0){
					x = 157;
					y = 2;
				}
				else{
					x = 174;
					y = 2;
				}
			}
			else{
				x = 141;
				y = 2;
			}
			break;
		case 5: //sw
			if(bm.isMoving()){
				if(System.currentTimeMillis()/100 % 2 == 0){
					x = 74;
					y = 2;
				}
				else{
					x = 57;
					y = 2;
				}
			}
			else{
				x = 41;
				y = 2;
			}
			break;
		case 6: //se
			if(bm.isMoving()){
				if(System.currentTimeMillis()/100 % 2 == 0){
					x = 140;
					y = 29;
				}
				else{
					x = 157;
					y = 29;
				}
			}
			else{
				x = 174;
				y = 28;
			}
			break;
		case 7: //ne
			if(bm.isMoving()){
				if(System.currentTimeMillis()/100 % 2 == 0){
					x = 40;
					y = 29;
				}
				else{
					x = 57;
					y = 29;
				}
			}
			else{
				x = 74;
				y = 29;
			}
			break;
		}
		img = img.getSubimage(x, y, width, height);
		return img;
	}
}
