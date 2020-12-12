package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class BasicEnemy extends AbstractGameObject{

	public BasicEnemy(int pX, int pY, ObjType pType) {
		super(pX, pY, pType);
		
		this.velX = 5;
		this.velY = 5;
	}

	@Override
	public void tick() {
		
		x += velX;
		y += velY;
		
		if (y <= 0 || y>=Game.HEIGHT - 32) velY *= -1;
		if (x <= 0 || x>=Game.WIDTH - 32) velX *= -1;
		
	}

	@Override
	public void render(Graphics pGraphics) {
		pGraphics.setColor(Color.red);
		pGraphics.clearRect(this.x, this.y, 20, 20);
		
	}

	
	/**
	 * Nothing should be done for any keyPressEvent or keyReleaseEvent
	 */
	@Override
	public void keyPressEvent(KeyEvent pEvent) { }
	@Override
	public void keyReleaseEvent(KeyEvent pEvent) { }

}
