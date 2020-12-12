package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Brick extends GameObject {
	
	protected static final int BRICK_HEIGHT = 25;
	protected static final int BRICK_WIDTH = 60;
	private final int[] aRGBColours = new int[3];
	private static final int BRICK_OFFSET = 3; //Space between each brick

	public Brick(int pX, int pY, ObjType pType) {
		super(pX, pY, pType);
		this.aWidth = BRICK_WIDTH;
		this.aHeight = BRICK_HEIGHT;
		
		Random r = new Random();
		//Each of R, G, B in [80,200]
		for (int i = 0; i < 3; i++) aRGBColours[i] = (r.nextInt(120))+80;
	}

	@Override
	public void render(Graphics pGraphics) {
		pGraphics.setColor(new Color(aRGBColours[0], aRGBColours[1], aRGBColours[2]));
		pGraphics.fillRect(this.x, this.y, BRICK_WIDTH, BRICK_HEIGHT);
	}

	/**
	 * Nothing should be done for any tick, keyPressEvent or keyReleaseEvent
	 */
	@Override public void tick() { }
	@Override public void keyPressEvent(KeyEvent pEvent) { }
	@Override public void keyReleaseEvent(KeyEvent pEvent) { }

	/**
	 * Given Game.WIDTH, BRICK.WIDTH, BRICK.HEIGHT, BRICK.BRICK_OFFSET, this
	 * populates the game with some number of rows
	 * @param pRows number of rows to add
	 */
	public static void populateWithBricks(int pRows) {
		int curX = BRICK_OFFSET;
		int curY = BRICK_OFFSET;
		
		for (int i = 0; i < pRows; i++) {
			while (curX + BRICK_WIDTH < Game.WIDTH + BRICK_OFFSET) {
				Observer.add(new Brick(curX, curY, GameObject.ObjType.BRICK));
				curX += Brick.BRICK_WIDTH + Brick.BRICK_OFFSET;
			}
			curY += Brick.BRICK_HEIGHT + BRICK_OFFSET;
			curX = Brick.BRICK_OFFSET;
		}
	}
}
