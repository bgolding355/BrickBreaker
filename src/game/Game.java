package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 6307620746112193723L;
	protected static final int WIDTH = 640;
	protected static final int HEIGHT = 480;
	protected static final int BRICK_ROWS = 4;
	private static Graphics aGraphics;
	private static final String GAME_HEADER = "BrickBreaker";

	private Thread aThread; // Single threaded game
	private boolean isRunning = false;

	private Game() {
		aThread = new Thread();
		this.addKeyListener(new KeyInput());
		Window.setup(WIDTH, HEIGHT, GAME_HEADER, this);
		gameSetup();
		this.start();
	}

	/**
	 * Helper method which sets up game
	 */
	private void gameSetup() {
		Observer.add(new Player(WIDTH / 2, HEIGHT - 42, GameObject.ObjType.PLAYER));
		Observer.add(new Ball(WIDTH/2, BRICK_ROWS*(Brick.BRICK_HEIGHT 
				+ Brick.BRICK_OFFSET), GameObject.ObjType.BALL));
		Brick.populateWithBricks(BRICK_ROWS);
	}

	public synchronized void start() {
		aThread.start();
		isRunning = true;
	}

	public synchronized void stop() {
		try {
			aThread.join();
			isRunning = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void tick() {
		GameState.getCurrentState().gameAction();
		Collisions.checkAllCollisions();
		Observer.tick();
	}

	/**
	 * returns pValue bounded by [min,max]
	 * 
	 * @param pValue value to be bounded
	 * @param min    minimum bound
	 * @param max    maximum bound
	 * @return value in [min, max]
	 */
	protected static int bound(int pValue, int min, int max) {
		if (pValue > max) {
			return max;
		} else if (pValue < min) {
			return min;
		} else {
			return pValue;
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
		} else {
			aGraphics = bs.getDrawGraphics();
			aGraphics.setColor(Color.black);
			aGraphics.fillRect(0, 0, WIDTH, HEIGHT);

			Observer.render();
			GameState.getCurrentState().graphicsAction();
			aGraphics.dispose();
			bs.show();
		}
	}

	/**
	 * Contains game loop. This loop is taken from
	 * https://gamedev.stackexchange.com/a/154986 and has been modified only
	 * slightly
	 */
	@Override
	public void run() {
		final double amountOfTicks = 60.0;
		final double ns = 1000000000 / amountOfTicks;

		long lastTime = System.nanoTime();
		this.requestFocus();
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0; // frames = current FPS
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}

	public static Graphics getGraphic() {
		return aGraphics;
	}
}
