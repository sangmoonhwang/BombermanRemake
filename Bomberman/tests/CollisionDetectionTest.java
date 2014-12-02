

import static org.junit.Assert.*;

import org.junit.Test;

import Controller.CollisionDetection;
import Model.Bomb;
import Model.Bomberman;
import Model.Indestructible;
import Model.Enemies.Enemy;

public class CollisionDetectionTest {

	@Test
	public void testCollisionDetectionMovableBlock() {
		CollisionDetection detect = new CollisionDetection();
		Bomberman b = new Bomberman();
		Indestructible i = new Indestructible();
		b.setXval(50);
		b.setYval(50);
		i.setXval(50);
		i.setYval(50);
		assertTrue(detect.collisionDetection(b,i));
		b.setXval(50+50);
		assertFalse(detect.collisionDetection(b,i));
	}

	@Test
	public void testCollisionDetectionMovableBomb() {
		CollisionDetection detect = new CollisionDetection();
		Bomberman b = new Bomberman();
		Bomb i = new Bomb(false);
		b.setXval(50);
		b.setYval(50);
		i.setXval(50);
		i.setYval(50);
		assertFalse(detect.collisionDetection(b,i));
		i.setActive(true);
		assertTrue(detect.collisionDetection(b,i));
		b.setXval(50+50);
		i.setActive(false);
		assertFalse(detect.collisionDetection(b,i));
		i.setActive(true);
		assertFalse(detect.collisionDetection(b,i));
	}

	@Test
	public void testCollisionDetectionMovableMovable() {
		CollisionDetection detect = new CollisionDetection();
		Bomberman b = new Bomberman();
		Enemy i = new Enemy("Balloom");
		b.setXval(50);
		b.setYval(50);
		i.setXval(50);
		i.setYval(50);
		assertTrue(detect.collisionDetection(b,i));
		b.setXval(50+50);
		assertFalse(detect.collisionDetection(b,i));
	}

	@Test
	public void testEmptyAbove() {
		CollisionDetection detect = new CollisionDetection();
		Bomberman b = new Bomberman();
		Indestructible i = new Indestructible();
		b.setXval(50);
		b.setYval(50);
		i.setXval(50);
		i.setYval(0);	//block is above bman wrt human's point of view, coordinate map is reversed compared to normal x-y
		assertFalse(detect.emptyAbove(b, i));
		i.setYval(100); //block is below bman
		assertTrue(detect.emptyAbove(b, i));
	}

	@Test
	public void testEmptyBelow() {
		CollisionDetection detect = new CollisionDetection();
		Bomberman b = new Bomberman();
		Indestructible i = new Indestructible();
		b.setXval(50);
		b.setYval(50);
		i.setXval(50);
		i.setYval(75); //below him
		assertFalse(detect.emptyBelow(b, i));
		i.setYval(0);  //above him
		assertTrue(detect.emptyBelow(b, i));
	}

	@Test
	public void testEmptyLeft() {
		CollisionDetection detect = new CollisionDetection();
		Bomberman b = new Bomberman();
		Indestructible i = new Indestructible();
		b.setXval(50);
		b.setYval(50);
		i.setXval(0);
		i.setYval(50);
		assertFalse(detect.emptyLeft(b, i));
		i.setXval(100);
		assertTrue(detect.emptyLeft(b, i));
	}

	@Test
	public void testEmptyRight() {
		CollisionDetection detect = new CollisionDetection();
		Bomberman b = new Bomberman();
		Indestructible i = new Indestructible();
		b.setXval(50);
		b.setYval(50);
		i.setXval(75);
		i.setYval(50);
		assertFalse(detect.emptyRight(b, i));
		i.setXval(0);
		assertTrue(detect.emptyRight(b, i));
	}

}
