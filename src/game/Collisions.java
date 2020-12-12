package game;

import java.util.LinkedList;

/**
 * Handles all collisions not resulting from KeyPress/KeyRelease's
 */
public enum Collisions {
	PLAYER_BALL {
		@Override
		void action() {

			LinkedList<AbstractGameObject> balls = GameObjectObserver.getByObjType(AbstractGameObject.ObjType.BALL);
			LinkedList<AbstractGameObject> players = GameObjectObserver.getByObjType(AbstractGameObject.ObjType.PLAYER);

			doesCollide(players, balls).forEach(ball -> {
				ball.velY *= -1;
			});
		}
	},
	BALL_FLOOR {
		@Override
		void action() {
			if (GameObjectObserver.getByObjType(AbstractGameObject.ObjType.BALL).stream()
					.anyMatch(ball -> ball.y >= Game.HEIGHT - 32)) {
				System.out.println("Ball Collided with Floor");
			}
		}
	},
	BALL_SIDES_OR_ROOF {
		@Override
		void action() {
			GameObjectObserver.getByObjType(AbstractGameObject.ObjType.BALL).forEach(ball -> {
				if (ball.y <= 0 || ball.y>=Game.HEIGHT - 32) ball.velY *= -1;
				if (ball.x <= 0 || ball.x>=Game.WIDTH - 32) ball.velX *= -1;
			});
		}
	};

	/**
	 * Checks if collision occurred, then performs some action if it did
	 */
	abstract void action();

	/**
	 * Detecting if two LinkedList's collide
	 * 
	 * @param list1 the 1rst LinkedList
	 * @param list2 the 2nd LinkedList
	 * @return LinkedList<AbstractGameObject> of objects in list2 which collided
	 */
	private static LinkedList<AbstractGameObject> doesCollide(LinkedList<AbstractGameObject> list1,
			LinkedList<AbstractGameObject> list2) {
		LinkedList<AbstractGameObject> toReturn = new LinkedList<AbstractGameObject>();
		for (AbstractGameObject i : list1) {
			for (AbstractGameObject j : list2) {
				if (i.getBounds().intersects(j.getBounds())) {
					toReturn.add(j);
				}
			}
		}
		return toReturn;
	}

	/**
	 * Performs all action()'s
	 */
	public static void checkAllCollisions() {
		for (Collisions c : Collisions.values())
			c.action();

	}
}