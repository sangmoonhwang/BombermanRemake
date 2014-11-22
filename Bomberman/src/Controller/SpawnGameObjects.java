package Controller;

import java.util.ArrayList;
import java.util.Random;

import Model.Destructible;
import Model.Door;
import Model.Explosion;
import Model.Indestructible;
import Model.Tile;
import Model.Enemies.Balloom;
import Model.Enemies.Enemy;
import Model.PowerUps.Powerup;

public class SpawnGameObjects {

	private static Explosion[] explosions;
	private static ArrayList<Indestructible> indestructibles;
	private static ArrayList<Destructible> bricks;
	private static ArrayList<Enemy> enemies;
	private static ArrayList<String> enemy;
	private static ArrayList<String> powerUp;
	private static Powerup power;
	private static Door door;

	public SpawnGameObjects(int level) {
		indestructibles = new ArrayList<Indestructible>();
		bricks = new ArrayList<Destructible>();
		enemies = new ArrayList<Enemy>();
		enemy = new ArrayList<String>(10);
		powerUp = new ArrayList<String>(1);
		door = new Door();
		levels(level);
	}
	public ArrayList<Indestructible> spawnIndestructibles() {
		for(int x=0; x<31; x++){
			for(int y=0; y<13; y++){
				if( (x == 0 || y == 0 || x == 30 || y == 12) || (x%2 == 0 && y%2 == 0)){
					indestructibles.add(new Indestructible(50*x, 50*y));
				}
			}
		}
		return indestructibles;
	}

	public ArrayList<Destructible> spawnBricks() {
		for(int x = 1; x<30; x++){
			for(int y = 1; y<12; y++){
				if((x!=1 && y!=1) && (x!=1 && y!=2) && (x!=2 && y!=1) ){
					double r = Math.random();
					if(r<=0.1){
						bricks.add(new Destructible(50*x,50*y));
					}
				}
			}
		}
		return bricks;
	}

	public ArrayList<Enemy> spawnEnemies() {
		for(int i = 0; i < enemy.size(); i++){
			enemies.add(new Enemy(enemy.get(i)));
			int x, y, tile;

			do {
				x = (int)(Math.random()*29 +1)*50;
				y = (int)(Math.random()*11 +1)*50;
				tile = whichTileIsOn(x,y);
			}while(!validSpawn(tile));
			enemies.get(i).setXval(x);
			enemies.get(i).setYval(y);
		}

		return enemies;
	}
	
	/**
	 * Initiates the powerup and place behind the random brick
	 * @param None
	 * @return Powerup
	 */
	public Powerup spawnPowerup() {
		power = new Powerup(powerUp.get(0));
		int x = (int)(Math.random()*bricks.size());   
		int xVal = bricks.get(x).getXval();				
		int yVal = bricks.get(x).getYval();
		power.setXval(xVal);
		power.setYval(yVal);
		
		return power;
	}
	
	public Door spawnDoor() {							//DO NOT DELETE THIS IS THE CODE WE WANT AT THE END
//		int x = (int)(Math.random()*bricks.size());   //code for hiding the door with brick 
//		int xVal = bricks.get(x).getXval();				// currently door does not appear even though all the bricks are detonated
//		int yVal = bricks.get(x).getYval();
//		door.setXval(xVal);
//		door.setYval(yVal);								//should check for the condition where powerUp is already placed in the given brick
		
		
		int x = (int)(Math.random()*29 + 1);
		int y = (int)(Math.random()*11 + 1);
		door.setXval(50*x);
		door.setYval(50*y);
		return door;
	}

	/**
	 * returns the tile number
	 * @param xPos and yPos
	 * @return The tile number it is on
	 */
	public int whichTileIsOn(int x, int y) {
		int tmp = y/50;
		return ((x/50) + tmp*31);
	}

	/**
	 * returns the if the enemy position spwaned is free from indestructible and brick
	 * @param enemy tile position
	 * @return whether the current enemy position is valid for true, otherwise false
	 */
	public boolean validSpawn(int tile) {
		for(int i = 0; i < indestructibles.size(); i++) {
			if(whichTileIsOn(indestructibles.get(i).getXval(), indestructibles.get(i).getYval()) == tile)
				return false;
		}

		for(int i=0; i<bricks.size(); i++) {
			if(whichTileIsOn(bricks.get(i).getXval(), bricks.get(i).getYval()) == tile)
				return false;
		}

		return true;
	}

	/**
	 * sets the correct #of enemy, types and powerups for the given level
	 * @param level
	 * @return None
	 */
	public void levels(int level) {

		switch(level) {

		case 1:
			for(int i = 0; i < 6; i++) {
				enemy.add("Balloom");
			}
			powerUp.add("Flames");
			break;
		case 2:
			for(int i = 0; i < 3; i++) {
				enemy.add("Balloom");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Oneal");
			}
			powerUp.add("UpBombs");
			break;
		case 3:
			for(int i = 0; i < 2; i++) {
				enemy.add("Balloom");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Oneal");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Doll");
			}
			powerUp.add("Detonator");
			break;
		case 4:
			enemy.add("Balloom");
			enemy.add("Oneal");
			for(int i = 0; i < 2; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Minvo");
			}
			powerUp.add("Speed");
			break;
		case 5:
			for(int i = 0; i < 4; i++) {
				enemy.add("Oneal");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Doll");
			}
			powerUp.add("UpBombs");
			break;
		case 6:
			for(int i = 0; i < 2; i++) {
				enemy.add("Oneal");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Minvo");
			}
			powerUp.add("UpBombs");
			break;
		case 7:
			for(int i = 0; i < 2; i++) {
				enemy.add("Oneal");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			powerUp.add("Flames");
			break;
		case 8:
			enemy.add("Oneal");
			for(int i = 0; i < 2; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 4; i++) {
				enemy.add("Minvo");
			}
			powerUp.add("Detonator");
			break;
		case 9:
			enemy.add("Oneal");
			enemy.add("Doll");
			for(int i = 0; i < 4; i++) {
				enemy.add("Minvo");
			}
			enemy.add("Kondoria");
			powerUp.add("Bombpass");
			break;
		case 10:
			enemy.add("Oneal");
			enemy.add("Doll");
			enemy.add("Minvo");
			for(int i = 0; i < 3; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Ovapi");
			powerUp.add("Wallpass");
			break;
		case 11:
			enemy.add("Oneal");
			for(int i = 0; i < 2; i++) {
				enemy.add("doll");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Minvo");
			}
			enemy.add("Kondoria");
			enemy.add("Ovapi");
			powerUp.add("UpBombs");
			break;
		case 12:
			enemy.add("Oneal");
			enemy.add("Doll");
			enemy.add("Minvo");
			for(int i = 0; i < 4; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Ovapi");
			powerUp.add("UpBombs");
			break;
		case 13:
			for(int i = 0; i < 3; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Minvo");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Kondoria");
			}
			powerUp.add("Detonator");
			break;
		case 14:
			for(int i = 0; i < 7; i++) {
				enemy.add("Ovapi");
			}
			enemy.add("Pass");
			powerUp.add("Bombpass");
			break;
		case 15:
			enemy.add("Doll");
			for(int i = 0; i < 3; i++) {
				enemy.add("Minvo");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Pass");
			powerUp.add("Flames");
			break;
		case 16:
			for(int i = 0; i < 3; i++) {
				enemy.add("Minvo");
			}
			for(int i = 0; i < 4; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Pass");
			powerUp.add("Wallpass");
			break;
		case 17:
			for(int i = 0; i < 5; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Pass");
			powerUp.add("Bombs");
			break;
		case 18:
			for(int i = 0; i < 3; i++) {
				enemy.add("Balloom");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Oneal");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Bombpass");
			break;
		case 19:
			enemy.add("Oneal");
			enemy.add("Doll");
			for(int i = 0; i < 3; i++) {
				enemy.add("Minvo");
			}
			enemy.add("Ovapi");
			for(int i = 0; i < 2; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Bombs");
			break;
		case 20:
			enemy.add("Oneal");
			enemy.add("Doll");
			enemy.add("Minvo");
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Ovapi");
			for(int i = 0; i < 2; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Detonator");
			break;
		case 21:
			for(int i = 0; i < 4; i++) {
				enemy.add("Kondoria");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Ovapi");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Bombpass");
			break;
		case 22:
			for(int i = 0; i < 4; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Minvo");
			}
			enemy.add("Kondoria");
			enemy.add("Pass");
			powerUp.add("Detonator");
			break;
		case 23:
			for(int i = 0; i < 2; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Minvo");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Ovapi");
			}
			enemy.add("Pass");
			powerUp.add("Bombs");
			break;
		case 24:
			enemy.add("Doll");
			enemy.add("Minvo");
			for(int i = 0; i < 4; i++) {
				enemy.add("Kondoria");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Ovapi");
			}
			enemy.add("Pass");
			powerUp.add("Detonator");
			break;
		case 25:
			for(int i = 0; i < 2; i++) {
				enemy.add("Oneal");
			}
			enemy.add("Doll");
			enemy.add("Minvo");
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Ovapi");
			}
			enemy.add("Pass");
			powerUp.add("Bombpass");
			break;
		case 26:
			enemy.add("Balloom");
			enemy.add("Oneal");
			enemy.add("Doll");
			enemy.add("Minvo");
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Ovapi");
			enemy.add("Pass");
			powerUp.add("Mystery");
			break;
		case 27:
			enemy.add("Balloom");
			enemy.add("Oneal");
			for(int i = 0; i < 5; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Ovapi");
			enemy.add("Pass");
			powerUp.add("Flames");
			break;
		case 28:
			enemy.add("Oneal");
			for(int i = 0; i < 3; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Minvo");
			}
			enemy.add("Kondoria");
			enemy.add("Pass");
			powerUp.add("Bombs");
			break;
		case 29:
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			for(int i = 0; i < 5; i++) {
				enemy.add("Ovapi");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Detonator");
			break;
		case 30:
			for(int i = 0; i < 3; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Minvo");
			}
			enemy.add("Kondoria");
			for(int i = 0; i < 2; i++) {
				enemy.add("Ovapi");
			}
			enemy.add("Pass");
			powerUp.add("Flamepass");
			break;
		case 31:
			for(int i = 0; i < 2; i++) {
				enemy.add("Oneal");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Minvo");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Ovapi");
			}
			powerUp.add("Wallpass");
			break;
		case 32:
			enemy.add("Oneal");
			enemy.add("Doll");
			for(int i = 0; i < 3; i++) {
				enemy.add("Minvo");
			}
			for(int i = 0; i < 4; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Pass");
			powerUp.add("Bombs");
			break;
		case 33:
			for(int i = 0; i < 2; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Minvo");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Ovapi");
			for(int i = 0; i < 2; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Detonator");
			break;
		case 34:
			for(int i = 0; i < 2; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Minvo");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Kondoria");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Mystery");
			break;
		case 35:
			for(int i = 0; i < 2; i++) {
				enemy.add("Doll");
			}
			enemy.add("Minvo");
			for(int i = 0; i < 3; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Ovapi");
			for(int i = 0; i < 2; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Bombpass");
			break;
		case 36:
			for(int i = 0; i < 2; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Minvo");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Kondoria");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Flamepass");
			break;
		case 37:
			for(int i = 0; i < 2; i++) {
				enemy.add("Doll");
			}
			enemy.add("Minvo");
			for(int i = 0; i < 3; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Ovapi");
			for(int i = 0; i < 3; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Detonator");
			break;
		case 38:
			for(int i = 0; i < 2; i++) {
				enemy.add("Doll");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Minvo");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Kondoria");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Flames");
			break;
		case 39:
			enemy.add("Doll");
			enemy.add("Minvo");
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Ovapi");
			}
			for(int i = 0; i < 4; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Wallpass");
			break;
		case 40:
			enemy.add("Doll");
			for(int i = 0; i < 2; i++) {
				enemy.add("Minvo");
			}
			for(int i = 0; i < 3; i++) {
				enemy.add("Kondoria");
			}
			for(int i = 0; i < 4; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Mystery");
			break;
		case 41:
			enemy.add("Doll");
			enemy.add("Minvo");
			for(int i = 0; i < 3; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Ovapi");
			for(int i = 0; i < 4; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Detonator");
			break;
		case 42:
			enemy.add("Minvo");
			for(int i = 0; i < 3; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Ovapi");
			for(int i = 0; i < 5; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Wallpass");
			break;
		case 43:
			enemy.add("Minvo");
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Ovapi");
			for(int i = 0; i < 5; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Bombpass");
			break;
		case 44:
			enemy.add("Minvo");
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Ovapi");
			for(int i = 0; i < 5; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Detonator");
			break;
		case 45:
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Ovapi");
			}
			for(int i = 0; i < 6; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Mystery");
			break;
		case 46:
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Ovapi");
			}
			for(int i = 0; i < 6; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Wallpass");
			break;
		case 47:
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Ovapi");
			}
			for(int i = 0; i < 6; i++) {
				enemy.add("Pass");
			}
			powerUp.add("Bombpass");
			break;
		case 48:
			for(int i = 0; i < 2; i++) {
				enemy.add("Kondoria");
			}
			enemy.add("Ovapi");
			for(int i = 0; i < 6; i++) {
				enemy.add("Pass");
			}
			enemy.add("Pontan");
			powerUp.add("Detonator");
			break;
		case 49:
			enemy.add("Kondoria");
			for(int i = 0; i < 2; i++) {
				enemy.add("Ovapi");
			}		
			for(int i = 0; i < 6; i++) {
				enemy.add("Pass");
			}
			enemy.add("Pontan");
			powerUp.add("Flamepass");
			break;
		case 50:
			enemy.add("Kondoria");
			for(int i = 0; i < 2; i++) {
				enemy.add("Ovapi");
			}		
			for(int i = 0; i < 5; i++) {
				enemy.add("Pass");
			}
			for(int i = 0; i < 2; i++) {
				enemy.add("Pontan");
			}
			powerUp.add("Mystery");
			break;
		}
	}
}
