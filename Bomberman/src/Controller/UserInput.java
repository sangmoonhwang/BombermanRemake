// next step http://stackoverflow.com/questions/7537570/eliminating-initial-keypress-delay


package Controller;

import java.awt.event.*;

import View.DrawMap;

public class UserInput implements KeyListener, FocusListener{
    private DrawMap d;
	
	public UserInput(){
		d = new DrawMap();
	}
	
	public static void main(String[] args){
        UserInput test = new UserInput();
        test.run();
    }
	
	public void run(){
		d.run();
		d.getCanvas().addFocusListener(this);
        d.getCanvas().addKeyListener(this);
        d.getCanvas().requestFocus();
	}
	
	public void keyPressed ( KeyEvent e ){
        int value = e.getKeyCode();
        switch(value){
            case KeyEvent.VK_DOWN:d.getBomberman().incrementYval(10);
            break;
            case KeyEvent.VK_UP:d.getBomberman().incrementYval(-10);
            break;
            case KeyEvent.VK_RIGHT:d.getBomberman().incrementXval(10);
            break;
            case KeyEvent.VK_LEFT:d.getBomberman().incrementXval(-10);
            break;
        }
        d.update();
    }
	
	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void focusGained(FocusEvent e) {
	}

	public void focusLost(FocusEvent e) {
	}
}
