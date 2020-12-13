package game;

/**
 * Handles all collisions not resulting from KeyPress/KeyRelease's
 */
public enum Collisions {
	BALL_BRICK {
		@Override
		void action() {
			for (GameObject ball : Observer.getByObjType(GameObject.ObjType.BALL)) {
				for (GameObject brick : Observer.getByObjType(GameObject.ObjType.BRICK)) {
					if (ball.getBounds().intersects(brick.getBounds())) {
						ball.velY *= -1;
						Observer.remove(brick);
					}
				}
			}
		}
	},

	BALL_PLAYER {
		@Override
		void action() {			
			for (GameObject ball : Observer.getByObjType(GameObject.ObjType.BALL)) {
				for (GameObject player : Observer.getByObjType(GameObject.ObjType.PLAYER)) {
					if (ball.getBounds().intersects(player.getBounds())) {
						ball.velY *= -1;
					}
				}
			}
		}
	},
	BALL_FLOOR {
		@Override
		void action() {
			if (Observer.getByObjType(GameObject.ObjType.BALL).stream().anyMatch(ball -> ball.y >= Game.HEIGHT - 32)) {
				GameState.setState(GameState.BALL_HIT_GROUND);
			}
		}
	},
	BALL_SIDES_OR_ROOF {
		@Override
		void action() {
			Observer.getByObjType(GameObject.ObjType.BALL).forEach(ball -> {
				if (ball.y <= 0 || ball.y >= Game.HEIGHT - 32)
					ball.velY *= -1;
				if (ball.x <= 0 || ball.x >= Game.WIDTH - 32)
					ball.velX *= -1;
			});
		}
	};

	/**
	 * Checks if collision occurred, then performs some action if it did
	 */
	abstract void action();

	/**
	 * Performs all action()'s
	 */
	public static void checkAllCollisions() {
		for (Collisions c : Collisions.values())
			c.action();

	}
}