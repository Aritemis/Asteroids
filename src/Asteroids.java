/*
CLASS: AsteroidsGame
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
Original code by Dan Leyzberg and Art Simon
 */

import java.awt.*;
import java.awt.Polygon;
import java.awt.event.*;
import java.util.ArrayList;

public class Asteroids extends Game 
{
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	private ArrayList<Asteroid> asteroidList;
	private Asteroid firstAsteroid;
	private Asteroid secondAsteroid;
	private Asteroid thirdAsteroid;
	static int counter = 0;

	public Asteroids() 
	{
		super("Asteroids!",800,600);
		this.setFocusable(true);
		this.requestFocus();
		this.asteroidList = new ArrayList<Asteroid>();
		this.firstAsteroid = addAsteroid(50, true, 0.0);
		this.secondAsteroid = addAsteroid(100, false, 50.0);
		this.thirdAsteroid = addAsteroid(250, false, 175.0);
		this.asteroidList.add(firstAsteroid);
		this.asteroidList.add(secondAsteroid);
		this.asteroidList.add(thirdAsteroid);
	}

	public void paint(Graphics brush) 
	{
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);
		brush.setColor(Color.white);
		for(Asteroid asteroid:asteroidList)
		{
			asteroid.paint(brush, Color.white);
		}
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
	
	public void addToList(Asteroid newAsteroid)
	{
		asteroidList.add(newAsteroid);
	}
	
	private Asteroid addAsteroid(int warp, boolean rotate, double rotation)
	{
		Point[] allPoints = new Point[3];
		double warpedPoint = warp * 1.0;
		Point a = new Point(0.0,0.0);
		Point b = new Point(0.0,warpedPoint);
		Point c = new Point(warpedPoint,warpedPoint);
		Point d = new Point(warpedPoint, 0.0);
		allPoints[0] = a;
		allPoints[1] = b;
		allPoints[2] = c;
//		allPoints[3] = d;
		return new Asteroid(allPoints, allPoints[2], rotation, rotate, warp);
	}
	
}