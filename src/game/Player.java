package game;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends AbstractGameObject {

	public Player(int pX, int pY, ObjType aType) {
		super(pX, pY, aType);
		
		velX = 1;
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
}
