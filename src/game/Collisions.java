package game;

/**
 * Handles all collisions not resulting from KeyPress/KeyRelease's
 */
public enum Collisions {
	BALL_BRICK {
		long lastBallBrickCollision = 0;
		
		@Override
		void action() {
			for (GameObject ball : Observer.getByObjType(GameObject.ObjType.BALL)) {
				for (GameObject brick : Observer.getByObjType(GameObject.ObjType.BRICK)) {
					if (ball.getBounds().intersects(brick.getBounds())) {
						Observer.remove(brick);
						HUD.increment();
						
						/**
						 * Since the ball can collide with multiple bricks at the same time,
						 * velY + sound should only be played once
						 */
						if (System.currentTimeMillis() - lastBallBrickCollision > 200) { 
							ball.velY *= -1;
							Game.playSound("collision.wav");
						}
						
					}
				}
			}	
			/**
			 * At the end if no blocks remain, triggering win condition
			 */
			if (Observer.getByObjType(GameObject.ObjType.BRICK).size() == 0 &&
					GameState.getCurrentState() == GameState.PLAYING) {
				Game.playSound("victory.wav");
				GameState.setState(GameState.VICTORY);
			}	
		}
	},

	BALL_PLAYER {
		long lastBallPlayerCollision = 0;

		@Override
		void action() {
			for (GameObject ball : Observer.getByObjType(GameObject.ObjType.BALL)) {
				for (GameObject player : Observer.getByObjType(GameObject.ObjType.PLAYER)) {
					if (ball.getBounds().intersects(player.getBounds())) {
						Game.playSound("collision.wav");

						/**
						 * Since the player can move to trigger this collision multiple times in a short
						 * time. A timer is added so that the collision can be triggered only once every
						 * second.
						 */
						if (System.currentTimeMillis() - lastBallPlayerCollision > 1000) {

							lastBallPlayerCollision = System.currentTimeMillis();
							ball.velY *= -1;

							int oldRange = 60;
							int newRange = Ball.MAX_BALLX - Ball.MIN_BALLX;
							ball.velX = (((ball.x - player.x) * newRange) / oldRange) 
									+ Ball.MIN_BALLX - Ball.MAX_BALLX/2;
						}
					}
				}
			}
		}
	},
	BALL_FLOOR {
		@Override
		void action() {
			if (Observer.getByObjType(GameObject.ObjType.BALL).stream().anyMatch(ball -> ball.y >= Game.HEIGHT - 32)) {
				Game.playSound("game_over.wav");
				GameState.setState(GameState.BALL_HIT_GROUND);
			}
		}
	},
	BALL_SIDES_OR_ROOF {
		@Override
		void action() {
			Observer.getByObjType(GameObject.ObjType.BALL).forEach(ball -> {
				if (ball.y <= 0) {
					ball.velY *= -1;
					Game.playSound("collision.wav");
				} else if (ball.x <= 0 || ball.x >= Game.WIDTH - 32) {
					ball.velX *= -1;
					Game.playSound("collision.wav");
				}
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