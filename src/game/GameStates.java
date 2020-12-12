package game;

import java.awt.Graphics;

public enum GameStates {
	INITIAL_STATE,
	PLAYING,
	BALL_HIT_GROUND,
	NO_BRICKS;
	
	/**
	 * Changes the state of the game
	 * @param pGraphics graphics to effect
	 */
	abstract void state(Graphics pGraphics);

}
