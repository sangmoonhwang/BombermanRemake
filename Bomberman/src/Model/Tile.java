package Model;

import java.util.ArrayList;

import Controller.SpawnGameObjects;

public class Tile {
	
	private static ArrayList<SpawnGameObjects> tile;
	
	public Tile() {
		tile = new ArrayList<SpawnGameObjects>(402);
	}
	
	public SpawnGameObjects getWhatsOnIt(int i) {
		return tile.get(i);
	}
	
	public static void addObject(SpawnGameObjects obj) {
		tile.add(obj);
	}
	
	
}
