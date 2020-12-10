package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Player extends AbstractGameObject {
	public Player(int pX, int pY, ObjType aType) {
		super(pX, pY, aType);
		
//		velX = (int) (Math.random()*100%10);
//		velY = (int) (Math.random()*100%10);
	}

	@Override
	public void tick() {
		this.x += velX;
		this.y += velY;
	}

	@Override
	public void render(Graphics pGraphics) {
		pGraphics.setColor(Color.white);
		pGraphics.fillRect(x, y, 32, 32);
	}

	@Override
	public void keyPressEvent(KeyEvent pEvent) {
		int keyCode = pEvent.getKeyCode();
		if (keyCode == KeyEvent.VK_UP) {
			this.velY--;
		} else if (keyCode == KeyEvent.VK_DOWN) {
			this.velY++;
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			this.velX++;
		} else if (keyCode == KeyEvent.VK_LEFT) {
			this.velX--;
		}
	}

	@Override
	public void keyReleaseEvent(KeyEvent pEvent) {
		int keyCode = pEvent.getKeyCode();
		if (keyCode == KeyEvent.VK_UP) {
			this.velY = 0;
		} else if (keyCode == KeyEvent.VK_DOWN) {
			this.velY = 0;
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			this.velX = 0;
		} else if (keyCode == KeyEvent.VK_LEFT) {
			this.velX = 0;
		}
		
	}
}
