// next step http://stackoverflow.com/questions/7537570/eliminating-initial-keypress-delay


package Controller;

import java.awt.event.*;

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
	
	public void keyPressed ( KeyEvent e ){
        int value = e.getKeyCode();
        
        switch(value){
            case KeyEvent.VK_DOWN:setVelY(1);
            break;
            case KeyEvent.VK_UP:setVelY(-1);
            break;
            case KeyEvent.VK_RIGHT:setVelX(1);
            break;
            case KeyEvent.VK_LEFT:setVelX(-1);
            break;
        }
   }
	
	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		
        int value = e.getKeyCode();
        
        switch(value){
            case KeyEvent.VK_DOWN:setVelY(0);
            break;
            case KeyEvent.VK_UP:setVelY(0);
            break;
            case KeyEvent.VK_RIGHT:setVelX(0);
            break;
            case KeyEvent.VK_LEFT:setVelX(0);
            break;
        }
	}    

	public void focusGained(FocusEvent e) {
	}

	public void focusLost(FocusEvent e) {
	}
	
	public void tick() {
		d.getBomberman().setXval(xVel);
		d.getBomberman().setYval(yVel);
	}
	
	public void setVelX(float xVel) {
		this.xVel = xVel;
	}
	
	public void setVelY(float yVel) {
		this.yVel = yVel;
	}
	
}
