 package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Ball extends GameObject{
	
	protected static final int BALL_DIAMETER = 20;
	protected static final int DEFAULT_BALL_SPEED = 5;
	protected static final int MIN_BALL_SPEED = 3;
	protected static final int MAX_BALL_SPEED = 20;
	
	public Ball(int pX, int pY, ObjType pType) {
		super(pX, pY, pType);
		
		this.velX = DEFAULT_BALL_SPEED;
		this.velY = DEFAULT_BALL_SPEED;
		this.aHeight = BALL_DIAMETER;
		this.aWidth = BALL_DIAMETER;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
	}

	@Override
	public void render(Graphics pGraphics) {
		pGraphics.setColor(Color.red);
		pGraphics.fillOval(this.x, this.y, BALL_DIAMETER, BALL_DIAMETER);
	}

	/**
	 * Nothing should be done for any keyPressEvent or keyReleaseEvent
	 */
	@Override public void keyPressEvent(KeyEvent pEvent) { }
	@Override public void keyReleaseEvent(KeyEvent pEvent) { }
}
