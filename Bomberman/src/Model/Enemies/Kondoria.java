package Model.Enemies;

import java.util.LinkedList;


public class Kondoria {
	private int intelligence;
	private int speed;
	private int points;
	private boolean wallPass;

	public Kondoria() {
		speed = 1;
		intelligence = 3;
		points = 1000;
		wallPass = true;

	}

	public int getIntelligence() {
		return intelligence;
	}

	public int getSpeed() {
		return speed;
	}

	public int getPoints() {
		return points;
	}

	public boolean getWallPass() {
		return wallPass;
	}

}
