package game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

abstract class GameObject {
	protected int x, y, velX, velY;
	protected ObjType aType;
	protected int aWidth, aHeight;
	
	public enum ObjType {
		PLAYER,
		BALL,
		BRICK
	}
	
	/**
	 * @pre pX > 0 && pY > 0 && aType != null
	 * @param pX x co-ordinate
	 * @param pY y co-ordinate
	 * @param aType type of this
	 */
	public GameObject(int pX, int pY, ObjType pType) {
		assert pX > 0 && pY > 0 && aType != null;
		this.x = pX;
		this.y = pY;
		this.aType = pType;
	}
	
	/**
	 * Ticks all observed items
	 */
	public abstract void tick();
	
	/**
	 * @param pGraphics graphics to be rendered
	 * Renders all observed items
	 */
	public abstract void render();
	
	/**
	 * Performs pEvent on this
	 * 
	 * @pre pEvent != null
	 * @param pEvent event which occurred
	 */
	public abstract void keyPressEvent(KeyEvent pEvent);

	/**
	 * @pre pEvent != null
	 * @param pEvent event which occurred
	 */
	public abstract void keyReleaseEvent(KeyEvent pEvent);
	
	/**
	 * Method which returns the bounds of this. To be used for collision detection.
	 * @return rectangle around this
	 */
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.aWidth, this.aHeight);
	}
}