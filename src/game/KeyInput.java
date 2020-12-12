package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter  {
	
	@Override
	public void keyPressed(KeyEvent pEvent) {
		Observer.keyPressEvent(pEvent);
	}
	
	@Override
	public void keyReleased(KeyEvent pEvent) {
		Observer.keyReleaseEvent(pEvent); 
	}
 }
