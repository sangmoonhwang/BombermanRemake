package Controller;

import java.util.Random;

import Model.Destructible;
import Model.Indestructible;
import Model.Tile;
import Model.Enemies.Enemy;

public class SpawnGameObjects {
	
	private static Indestructible[] indestructibles;
	private static Tile[] tiles;
	private static Destructible[] bricks;
	private static Enemy[] enemies;
	
	public Indestructible[] spawnIndestructibles(){
		indestructibles = new Indestructible[101];
		for (int i = 0; i < 100; i++){
			indestructibles[i] = new Indestructible();
		}

		int a = 0;
		for(int x=0; x<15; x++){
			for(int y=0; y<13; y++){
				if( (x == 0 || y == 0 || y == 12 || x == 14) || (x%2 == 0 && y%2 == 0)){
					indestructibles[a].setYval(y);
					indestructibles[a].setXval(x);
					a++;
				}
			}
		}
		return indestructibles;
	}

	public Destructible[] spawnBricks(){
		bricks = new Destructible[201];
		for(int i =0; i < 200; i++){
			bricks[i] = new Destructible();
			Random r = new Random();
			int ans = r.nextInt(100) + 1;
			if(ans < 32){
				bricks[i].setExists(true);
			} else {
				bricks[i].setExists(false);
			}
		}


		int a = 0;
		for(int x = 0; x<15; x++){
			for(int y = 0; y<13; y++){
				if(bricks[a] != null){
					bricks[a].setYval(y);
					bricks[a].setXval(x);
				}
				a++;
			}
		}
		return bricks;
	}

	public Enemy[] spawnEnemies(){
		enemies = new Enemy[8];
		for(int i = 0; i < 7; i++){
			int x = 0;
			int y = 0;
			enemies[i] = new Enemy();
			Random r = new Random();
			x = 50 * (r.nextInt(11)+1);
			y = 50 * (r.nextInt(11)+1);
			enemies[i].setXval(x);
			enemies[i].setYval(y);
		}
		return enemies;
	}

	public Tile[] spawnTiles(){
		tiles = new Tile[401];
		for (int i = 0; i < 400; i++){
			tiles[i] = new Tile();
		}

		int a = 0;
		for(int x = 0; x<15; x++){
			for(int y = 0; y<13; y++){
				tiles[a].setYval(y);
				tiles[a].setXval(x);
				a++;
			}
		}
		return tiles;
	}
}
