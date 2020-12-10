package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter  {
	
	@Override
	public void keyPressed(KeyEvent pEvent) {
		GameObjectObserver.keyPressEvent(pEvent);
	}
	
	@Override
	public void keyReleased(KeyEvent pEvent) {
		GameObjectObserver.keyReleaseEvent(pEvent); 
	}
 }
