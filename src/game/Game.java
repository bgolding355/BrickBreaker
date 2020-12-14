package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 6307620746112193723L;
	protected static final int WIDTH = 640;
	protected static final int HEIGHT = 820;
	protected static final int BRICK_ROWS = 8;
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
		Observer.add(new Player(WIDTH / 2, HEIGHT - 60, GameObject.ObjType.PLAYER));
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
			Graphics g = bs.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);

			Observer.render(g);
			GameState.getCurrentState().graphicsAction(g);
			HUD.displayHUD(g);
			g.dispose();
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
				// System.out.println(String.format("FPS: %d", frames)); //Uncomment to get FPS
				frames = 0;
			}
		}
		stop();
	}

	/**
	 * Method for playing sound
	 * 
	 * @param soundFile sound to be played
	 */
	public static void playSound(String soundFile) {
		new Thread(() -> {
			try {
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(
						new BufferedInputStream(Game.class.getResourceAsStream("/" + soundFile))
					);
				
				Clip clip = AudioSystem.getClip();
				clip.open(inputStream);
				clip.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
}
