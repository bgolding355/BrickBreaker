package game;

import java.awt.Graphics;

abstract class AbstractGameObject {
	public static enum ObjType {
		PLAYER, ENEMY
	}
	
	protected int x, y, velX, velY;
	protected ObjType aType;
	
	/**
	 * @pre pX > 0 && pY > 0 && aType != null
	 * @param pX x co-ordinate
	 * @param pY y co-ordinate
	 * @param aType type of this
	 */
	public AbstractGameObject(int pX, int pY, ObjType aType) {
		assert pX > 0 && pY > 0 && aType != null;
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
	
}