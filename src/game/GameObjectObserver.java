package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class GameObjectObserver {
	private static LinkedList<AbstractGameObject> aObserved = new LinkedList<AbstractGameObject>();
	
	/**
	 * Ticks all observed items
	 */
	public static void tick() {
		aObserved.forEach(item -> item.tick());
	}
	
	/**
	 * @param pGraphics graphics to be rendered
	 * @pre pGraphics != null
	 * Renders all observed items
	 */
	public static void render(Graphics pGraphics) {
		assert pGraphics != null;
		aObserved.forEach(item -> item.render(pGraphics));
	}
	
	/**
	 * @pre aObject != null
	 * @param aObject AbstractGameObject to be added
	 */
	public static void add(AbstractGameObject aObject) {
		assert aObject != null;
		aObserved.add(aObject);
	}
	
	/**
	 * @param aObject AbstractGameObject to be added
	 * @return false if aObject was being observed
	 */
	public static boolean remove(AbstractGameObject aObject) {
		assert aObject != null;
		return aObserved.remove(aObject);
	}
}
