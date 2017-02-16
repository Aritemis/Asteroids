/*
CLASS: AsteroidsGame
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
Original code by Dan Leyzberg and Art Simon
 */

import java.awt.*;
import java.awt.event.*;

public class Asteroids extends Game 
{
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;

	static int counter = 0;

	public Asteroids() 
	{
		super("Asteroids!",800,600);
		this.setFocusable(true);
		this.requestFocus();
		
	}

	public void paint(Graphics brush) 
	{
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);

		// sample code for printing message for debugging
		// counter is incremented and this message printed
		// each time the canvas is repainted
		counter++;
		brush.setColor(Color.white);
		brush.drawString("Counter is " + counter,10,10);
	}

	public static void main (String[] args) 
	{
		Asteroids a = new Asteroids();
		a.repaint();
	}
}