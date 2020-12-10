package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	/**
	 * @pre pType != null
	 * @param pType type to search for
	 * @return all AbstractGameObject's with aType = pType
	 */
	public static LinkedList<AbstractGameObject> getByObjType(AbstractGameObject.ObjType pType) {
		assert pType != null;
		
		LinkedList<AbstractGameObject> toReturn = new LinkedList<AbstractGameObject>();
		
		aObserved.forEach(item -> {
			if (item.aType == pType) toReturn.add(item);
		});
		
		return toReturn;
	}
	
	/**
	 * Performs pEvent on this
	 * 
	 * @pre pEvent != null
	 * @param pEvent event which occurred
	 */
	public static void keyPressEvent(KeyEvent pEvent) {
		assert pEvent != null;
		aObserved.forEach(item -> item.keyPressEvent(pEvent));
	}

	/**
	 * @pre pEvent != null
	 * @param pEvent event which occurred
	 */
	public static void keyReleaseEvent(KeyEvent pEvent) {
		assert pEvent != null;
		aObserved.forEach(item -> item.keyReleaseEvent(pEvent));
	}
}
