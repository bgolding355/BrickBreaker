package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

abstract class AbstractGameObject {
	protected int x, y, velX, velY;
	protected ObjType aType;
	
	public enum ObjType {
		PLAYER,
		ENEMY
	}
	
	/**
	 * @pre pX > 0 && pY > 0 && aType != null
	 * @param pX x co-ordinate
	 * @param pY y co-ordinate
	 * @param aType type of this
	 */
	public AbstractGameObject(int pX, int pY, ObjType pType) {
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
	 * @pre pGraphics != null
	 * Renders all observed items
	 */
	public abstract void render(Graphics pGraphics);
	
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
}