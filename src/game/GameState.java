package game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import game.GameObject.ObjType;

public enum GameState {
	INITIAL_STATE {
		@Override
		void gameAction() {
			// TODO Auto-generated method stub

		}

		@Override
		void graphicsAction() {
			// TODO Auto-generated method stub

		}

		@Override
		void keyPressEvent(KeyEvent pEvent) {
			// TODO Auto-generated method stub
			
		}
	},
	PLAYING {
		
		@Override
		void gameAction() {
			if (stateChange) {
				stateChange = false;
				
				//Adding ball
				Observer.add(new Ball(Game.WIDTH/2, Game.BRICK_ROWS*(Brick.BRICK_HEIGHT 
						+ Brick.BRICK_OFFSET), GameObject.ObjType.BALL));
				
				//Removing old bricks
				Observer.getByObjType(GameObject.ObjType.BRICK).forEach(brick -> {
					Observer.remove(brick);
				});
				
				//Adding new bricks
				Brick.populateWithBricks(Game.BRICK_ROWS);
				
			}
		}

		@Override
		void graphicsAction() {
			// TODO Auto-generated method stub

		}

		@Override
		void keyPressEvent(KeyEvent pEvent) {
			// TODO Auto-generated method stub
			
		}
	},
	BALL_HIT_GROUND {
		@Override
		void gameAction() {
			Game.getGraphic().setColor(Color.white);
			Game.getGraphic().drawString("You Lose, Press 'Space' to try again", Game.WIDTH / 2, Game.HEIGHT / 2);
			Observer.getByObjType(ObjType.BALL).forEach(ball -> {
				Observer.remove(ball);
			});
		}

		@Override
		void graphicsAction() {
			Game.getGraphic().setColor(Color.white);
			Game.getGraphic().drawString("You Lose, Press 'Space' to try again", Game.WIDTH/2-110, Game.HEIGHT / 2);
		}

		@Override
		void keyPressEvent(KeyEvent pEvent) {
			if (pEvent.getKeyCode() == KeyEvent.VK_SPACE) {
				stateChange = true;
				GameState.setState(GameState.PLAYING);
			}		
		}
	},
	NO_BRICKS {
		@Override
		void gameAction() {
			//Removes Everything from the screen
			for (GameObject.ObjType t : GameObject.ObjType.values()) {
				Observer.getByObjType(t).forEach(item -> Observer.remove(item));
			}
		}

		@Override
		void graphicsAction() {
			Game.getGraphic().setColor(Color.white);
			Game.getGraphic().drawString("You Win, Press 'Space' to play again", Game.WIDTH/2-110, Game.HEIGHT / 2);
		}

		@Override
		void keyPressEvent(KeyEvent pEvent) {
			if (pEvent.getKeyCode() == KeyEvent.VK_SPACE) {
				stateChange = true;
				GameState.setState(GameState.PLAYING);
			}
		}
	};

	private static GameState currentState = INITIAL_STATE;
	private static boolean stateChange = false;

	public static void setState(GameState g) {
		currentState = g;
	}
	
	public static GameState getCurrentState() {
		return currentState;
	}
	
	abstract void gameAction();
	abstract void graphicsAction();
	abstract void keyPressEvent(KeyEvent pEvent);



}
