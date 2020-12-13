package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class HUD {
	private static int brickDestroyed = 0;
	private static List<Integer> scores = new ArrayList<Integer>();
	
	public static void resetDestroyed() { 
		scores.add(brickDestroyed);
		brickDestroyed = 0;
	}
	
	public static void increment() {
		brickDestroyed++;
	}
	
	public static void displayHUD(Graphics pGraphics) {
		pGraphics.setColor(Color.white);
		pGraphics.drawString(brickDestroyed + "", Game.WIDTH/2-7, 32);
	}
	
	public static int getCurrentDestroyed() {
		return brickDestroyed;
	}
	
	public static int getMaxDestroyed() {
		int max = brickDestroyed;
		for (int i : scores) {
			if (i > max) max = i;
		}
		return max;
	}

}
