package Model;




public class Bomberman implements Movable {
	private int score;
	private int life;
	private int speed;
	private int availableBombs;
	private int flames;
	private boolean detonate;
	private boolean wallPAss;
	private boolean bombPass;
	private boolean flamePass;
	
	public Bomberman() {
		
		score = 0;
		life = 3;
		speed = 1;
		availableBombs = 1;
		flames = 1;
		detonate = false;
		wallPAss = false;
		bombPass = false;
		flamePass = false;
	}
	
	public void move() {
		
	}
}
