package Model;

public class Bomberman implements Movable{
 private float xval, yval;
 private int score;
 private int life;
 private int speed;
 private int availableBombs;
 private int flames;
 private boolean detonate;
 private boolean wallPass;
 private boolean bombPass;
 private boolean flamePass;
 
 public Bomberman(){
   xval = yval = 50;             //starting pos
 }
 
 //getter
 public float getXval(){
  return xval;
 }
 //setter
 public void setXval(float i){
  xval += i;
 }
 //getter
 public float getYval(){
  return yval;
 }
 //setter
 public void setYval(float i){
  yval += i;
 }

 @Override
public void move() {
 }
}