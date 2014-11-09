// next step http://stackoverflow.com/questions/7537570/eliminating-initial-keypress-delay


package Controller;

import java.awt.event.*;

import Model.Indestructible;
import View.DrawMap;

public class UserInput implements KeyListener, FocusListener{
 private DrawMap d;
 private float xVel = 0;
 private float yVel = 0;
 private static boolean running = false;
 public UserInput(){
  d = new DrawMap();
 }

 public static void main(String[] args){
  UserInput test = new UserInput();
  running = true;
  test.run();
 }

 public void run(){
  d.run();
  d.getCanvas().addFocusListener(this);
  d.getCanvas().addKeyListener(this);
  d.getCanvas().requestFocus();

  long start = System.nanoTime();
  final double amountOfTicks = 60.0;
  double ns = 1000000000 / amountOfTicks;
  double delta = 0;

  while(running) {
   long now = System.nanoTime();
   delta += (now - start) / ns;
   start = now;

   if(delta >= 1) {
    tick();
    delta--;
   }
   d.update();

  }
 }

 //react to keyPress by moving Bomberman
 public void keyPressed ( KeyEvent e ){
  int value = e.getKeyCode();
  if (value == KeyEvent.VK_DOWN && value !=KeyEvent.VK_UP){
   setVelY(2);
  }
  else if(value != KeyEvent.VK_DOWN && value ==KeyEvent.VK_UP){
   setVelY(-2);
  }
  else if(value == KeyEvent.VK_LEFT && value !=KeyEvent.VK_RIGHT){
   setVelX(-2);
  }
  else if(value == KeyEvent.VK_RIGHT && value !=KeyEvent.VK_LEFT){
   setVelX(2);
  }
 }

 public void keyTyped(KeyEvent e) {
 }

 //stop moving when key is released
 public void keyReleased(KeyEvent e) {
  int value = e.getKeyCode();
  if (value == KeyEvent.VK_DOWN){
   if(yVel == 2)
    setVelY(0);
  }
  else if(value ==KeyEvent.VK_UP){
   if(yVel == -2)
    setVelY(0);
  }
  else if(value == KeyEvent.VK_LEFT){
   if(xVel == -2)
    setVelX(0);
  }
  else if(value == KeyEvent.VK_RIGHT){
   if(xVel ==2)
    setVelX(0);
  }
  else{
   setVelX(0);
   setVelY(0);
  }
 }    

 public void focusGained(FocusEvent e) {
 }

 public void focusLost(FocusEvent e) {
 }

 
 public void tick() {
  d.getBomberman().setXval(xVel);
  d.getBomberman().setYval(yVel);
  //hard-coded bomberman/indestructibles collision detection for demo purposes
  for(int i = 0; i < 100; i++){
	  if(d.getTile().collisionDetection(d.getBomberman(), d.getIndestructible(i))){
		  d.getBomberman().setXval(-xVel);
		  d.getBomberman().setYval(-yVel);
	  }
  }
  /*for (int i = 0; i < 100; i++){
   if(d.getBomberman().getXval()+42 > d.indestructibles[i].getXval()*50 && d.getBomberman().getYval() < d.indestructibles[i].getYval()*50+50 &&
     d.getBomberman().getXval() < d.indestructibles[i].getXval()*50+50 && d.getBomberman().getYval()+42 > d.indestructibles[i].getYval()*50){
    d.getBomberman().setXval(-xVel);
    d.getBomberman().setYval(-yVel);
   }
  }*/
 }

 public void setVelX(float xVel) {
  this.xVel = xVel;
 }

 public void setVelY(float yVel) {
  this.yVel = yVel;
 }

}
