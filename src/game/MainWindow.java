package game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainWindow extends Canvas {
	private static final long serialVersionUID = 6596273530124848110L;

	public MainWindow(int pWidth, int pHeight, String pTitle, Game pGame) {
		JFrame frame = new JFrame(pTitle);
		
		//Setting dimensions
		Dimension dimension = new Dimension(pWidth, pHeight);
		frame.setPreferredSize(dimension);
		frame.setMaximumSize(dimension);
		frame.setMinimumSize(dimension);
		frame.setResizable(false);
		
		//Setting Settings
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); //Puts in center of the screen
		frame.setVisible(true);
	
		frame.add(pGame);
		pGame.start();

	}
}
