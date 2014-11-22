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

public class SpawnGameObjects {

	private static Explosion[] explosions;
	private static ArrayList<Indestructible> indestructibles;
	private static ArrayList<Destructible> bricks;
	private static ArrayList<Enemy> enemies;
	private static Door door;

	public SpawnGameObjects(){
		indestructibles = new ArrayList<Indestructible>();
		bricks = new ArrayList<Destructible>();
		enemies = new ArrayList<Enemy>();
		door = new Door();
	}
	public ArrayList<Indestructible> spawnIndestructibles(){
		for(int x=0; x<31; x++){
			for(int y=0; y<13; y++){
				if( (x == 0 || y == 0 || x == 30 || y == 12) || (x%2 == 0 && y%2 == 0)){
					indestructibles.add(new Indestructible(50*x, 50*y));
				}
			}
		}
		return indestructibles;
	}

	public ArrayList<Destructible> spawnBricks(){
		for(int x = 1; x<30; x++){
			for(int y = 1; y<12; y++){
				if((x!=1 && y!=1) || (x!=1 && y!=2) || (x!=2 && y!=1) ){
					//do{
						double r = Math.random();
						if(r<0.1){
							bricks.add(new Destructible(50*x,50*y));
						}
					//}while(bricks.get(bricks.size() - 1));
				}
			}
		}
		return bricks;
	}

	public ArrayList<Enemy> spawnEnemies(){
		for(int i = 0; i < 7; i++){
			enemies.add(new Enemy("Balloom"));              //just spawning Balloom for now
			int x, y, tile;
			
			do {
				x = (int)(Math.random()*29 +1);
				y = (int)(Math.random()*11 +1);
				tile = whichTileIsOn(x,y);
			}while(validSpawn(tile));
			enemies.get(i).setXval(50*x);
			enemies.get(i).setYval(50*y);
		}

		return enemies;
	}
	
	public Door spawnDoor(){
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



}
