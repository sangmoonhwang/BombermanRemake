package Model;

public class Bomberman implements Movable{
 private int xval, yval;
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
  xval = yval = 50;
 }
 
 public int getXval(){
  return xval;
 }
 
 public void setXval(int i){
  xval = i;
 }
 
 public int getYval(){
  return yval;
 }
 
 public void setYval(int i){
  yval = i;
 }
 
 public void incrementXval(int i){
  xval += i;
 }
 
 public void incrementYval(int i){
  yval += i;
 }

 public void move() {
 }
}