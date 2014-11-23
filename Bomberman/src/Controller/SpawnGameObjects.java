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

	/**
	 * Spawns the concrete on the map
	 * @param None
	 * @return ArrayList<Indestructible>
	 */
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

	/**
	 * Spawns the bricks on the tile where it is valid
	 * @param None
	 * @return ArrayList<Destructible>
	 */
	public ArrayList<Destructible> spawnBricks() {
		for(int x = 1; x<30; x++){
			for(int y = 1; y<12; y++){
				if((x!=1 && y!=1) && (x!=1 && y!=2) && (x!=2 && y!=1) ){
					double random = Math.random();
					if(random<=0.1){
						int xVal = x*50;
						int yVal = y*50;
						int tile = whichTileIsOn(xVal,yVal);
						if(validBrickSpawn(tile))
							bricks.add(new Destructible(xVal,yVal));
					}
				}
			}
		}
		return bricks;
	}

	/**
	 * Spawns the enemies depending on the level requirements
	 * @param None
	 * @return ArrayList<Enemy>
	 */
	public ArrayList<Enemy> spawnEnemies() {
		for(int i = 0; i < enemy.size(); i++){
			enemies.add(new Enemy(enemy.get(i)));
			int x, y, tile;

			do {
				x = (int)(Math.random()*29 +1)*50;
				y = (int)(Math.random()*11 +1)*50;
				tile = whichTileIsOn(x,y);
			}while(!validEnemySpawn(tile));
			enemies.get(i).setXval(x);
			enemies.get(i).setYval(y);
			isStuck(enemies.get(i), x, y);
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
		int random = (int)(Math.random()*bricks.size());   
		int xVal = bricks.get(random).getXval();				
		int yVal = bricks.get(random).getYval();
		power.setXval(xVal);
		power.setYval(yVal);

		return power;
	}

	/**
	 * Initiates the door and place behind the random brick that does not have the powerup already
	 * @param None
	 * @return Door
	 */
	public Door spawnDoor() {							
		int random, xVal, yVal, tile;
		do {
			random = (int)(Math.random()*bricks.size());
			xVal = bricks.get(random).getXval();				
			yVal = bricks.get(random).getYval();
			tile = whichTileIsOn(xVal,yVal);
		}while(!validDoorSpawn(tile));
		door.setXval(xVal);
		door.setYval(yVal);

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
	 * returns if the brick is spawned on the concrete
	 * @param brick position on the tile
	 * @return the current brick position is valid for true, otherwise false
	 */
	public boolean validBrickSpawn(int tile) {
		for(int i=0; i<indestructibles.size(); i++) {
			Indestructible temp = indestructibles.get(i);
			if(whichTileIsOn(temp.getXval(), 
					temp.getYval()) == tile)
				return false;
		}
		return true;
	}

	/**
	 * returns the if the enemy position spwaned is free from indestructible and brick 
	 * Also looks at if the enemy is spawned in same location
	 * @param enemy tile position
	 * @return the current enemy position is valid for true, otherwise false
	 */
	public boolean validEnemySpawn(int tile) {
		for(int i = 0; i < indestructibles.size(); i++) {
			if(whichTileIsOn(indestructibles.get(i).getXval(), indestructibles.get(i).getYval()) == tile)
				return false;
		}

		for(int i=0; i<bricks.size(); i++) {
			if(whichTileIsOn(bricks.get(i).getXval(), bricks.get(i).getYval()) == tile)
				return false;
		}

		for(int i=0; i<enemies.size(); i++) {
			if(whichTileIsOn(enemies.get(i).getXval(), enemies.get(i).getYval()) == tile)
				return false;
		}

		return true;
	}
	
	/**
	 * check if the enemy will get stuck in the concrete when they are spwaned, if it is then change the moving direction
	 * @param Enemy object
	 * @return None
	 */
	public void isStuck(Enemy enemy, int xVal, int yVal) {
		if(enemy.getState() == 0 || enemy.getState() == 1) {
			int xMOD = xVal % 50;
			int yMOD = yVal % 100;
			if(xMOD == 0 && yMOD == 0) 
				enemy.setState(2);
		} else {
			int xMOD = xVal % 100;
			int yMOD = yVal % 50;
			if(xMOD == 0 && yMOD == 0)
				enemy.setState(0);;
		}
	}

	/**
	 * returns if the door is spanwed on the same block as the powerup
	 * @param door position on the tile
	 * @return the current door position is valid for true, otherwise false
	 */
	public boolean validDoorSpawn(int tile) {
		if(whichTileIsOn(power.getXval(), power.getYval()) == tile) {
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
			powerUp.add("UpBombs");
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
			powerUp.add("UpBombs");
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
			powerUp.add("UpBombs");
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
			powerUp.add("UpBombs");
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
			powerUp.add("UpBombs");
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
