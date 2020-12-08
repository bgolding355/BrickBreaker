package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Arrays;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 6307620746112193723L;
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final String GAME_HEADER = "Some Game";

	private Thread aThread; // Single threaded game
	private MainWindow aWindow;
	private boolean isRunning = false;

	public Game() {
		aThread = new Thread();
		aWindow = new MainWindow(WIDTH, HEIGHT, GAME_HEADER, this);
		this.start();
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

	/**
	 * Helper method for run
	 */
	private void tick() {
		
	}
	
	/** 
	 * Helper method for run
	 */
	private void render() {	
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
		} else {
			Graphics graph = bs.getDrawGraphics();
			graph.setColor(Color.black);
			graph.fillRect(0, 0, WIDTH, HEIGHT);
			graph.dispose();
			bs.show();
		}
	} 
	
	/**
	 * Contains game loop. This loop is taken from https://gamedev.stackexchange.com/a/154986 and has been 
	 * modified only slightly
	 */
	@Override
	public void run() {
		final double amountOfTicks = 60.0;
		final double ns = 1000000000 / amountOfTicks;
		
		long lastTime = System.nanoTime();
		this.requestFocus();
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
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
				System.out.println(String.format("FPS: %d", frames));
				timer += 1000;
				frames = 0;
			}
		}
		stop();

	}
}
