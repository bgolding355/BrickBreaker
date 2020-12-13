package game;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Player extends GameObject {
	private static final int PLAYER_WIDTH = 64;
	private static final int PLAYER_HEIGHT = 12;
	private static final int MAX_VELOCITY = 10;
	private static final int VELOCITY_MODIFIER = 12;

	public Player(int pX, int pY, ObjType aType) {
		super(pX, pY, aType);
		this.aWidth = PLAYER_WIDTH;
		this.aHeight = PLAYER_HEIGHT;
	}

	@Override
	public void tick() {
		x += Game.bound(velX, -1*MAX_VELOCITY, MAX_VELOCITY);
		this.x = Game.bound(x, 0, Game.WIDTH - 60);
	}

	@Override
	public void render() {
		Game.getGraphic().setColor(Color.white);
		Game.getGraphic().fillRect(x, y, this.aWidth, this.aHeight);
	}

	@Override
	public void keyPressEvent(KeyEvent pEvent) {
		int keyCode = pEvent.getKeyCode();
		if (keyCode == KeyEvent.VK_RIGHT) {
			this.velX = VELOCITY_MODIFIER;
		} else if (keyCode == KeyEvent.VK_LEFT) {
			this.velX = -1 * VELOCITY_MODIFIER;
		}
	}

	@Override
	public void keyReleaseEvent(KeyEvent pEvent) {
		int keyCode = pEvent.getKeyCode();
		if (keyCode == KeyEvent.VK_RIGHT) {
			this.velX = Game.bound(this.velX - VELOCITY_MODIFIER, -VELOCITY_MODIFIER, VELOCITY_MODIFIER);
		} else if (keyCode == KeyEvent.VK_LEFT) {
			this.velX = Game.bound(this.velX + VELOCITY_MODIFIER, -VELOCITY_MODIFIER, VELOCITY_MODIFIER); 
		}
	}
}
