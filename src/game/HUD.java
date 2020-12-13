package game;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	private static int brickDestroyed = 0;
	
	public static void resetDestroyed() { 
		brickDestroyed = 0;
	}
	
	public static void increment() {
		brickDestroyed++;
	}
	
	public static void displayHUD(Graphics pGraphics) {
		pGraphics.setColor(Color.white);
		pGraphics.drawString(brickDestroyed + "", Game.WIDTH/2-7, 32);
	}

}
