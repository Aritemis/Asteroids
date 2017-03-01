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
import java.util.List;

public class Asteroids extends Game 
{
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	private ArrayList<Asteroid> asteroidList;
	private Ship ship;
//	private Asteroid firstAsteroid;
//	private Asteroid secondAsteroid;
//	private Asteroid thirdAsteroid;
	static int counter = 0;
	private List<Asteroid> randomAsteroids = new ArrayList<Asteroid>();

	public Asteroids() 
	{
		super("Asteroids!",800,600);
		this.setFocusable(true);
		this.requestFocus();
		Point[] shipShape = new Point[3];
		shipShape[0] = new Point(0.0,0.0);
		shipShape[1] = new Point(10.0,-20.0);
		shipShape[2] = new Point(-10.0,-20.0);
		Point shipPosition = new Point(400,300);
		ship = new Ship(shipShape, shipPosition, 90.0);
//		this.asteroidList = new ArrayList<Asteroid>();
//		this.firstAsteroid = addAsteroid(50, true, 0.0);
//		this.secondAsteroid = addAsteroid(100, false, 50.0);
//		this.thirdAsteroid = addAsteroid(250, false, 175.0);
//		this.asteroidList.add(firstAsteroid);
//		this.asteroidList.add(secondAsteroid);
//		this.asteroidList.add(thirdAsteroid);
		
		randomAsteroids = createRandomAsteroids(10,60,30);
	}

	
	private List<Asteroid> createRandomAsteroids(int numberOfAsteroids, int maxAsteroidWidth,
			int minAsteroidWidth) 
	{
		List<Asteroid> asteroids = new ArrayList<>(numberOfAsteroids);

		for(int i = 0; i < numberOfAsteroids; ++i) 
		{
			// Create random asteroids by sampling points on a circle
			// Find the radius first.
			int radius = (int) (Math.random() * maxAsteroidWidth);
			if(radius < minAsteroidWidth)
			{
				radius += minAsteroidWidth;
			}
			// Find the circles angle
			double angle = (Math.random() * Math.PI * 1.0/2.0);
			if(angle < Math.PI * 1.0/5.0)
			{
				angle += Math.PI * 1.0/5.0;
			}
			// Sample and store points around that circle
			ArrayList<Point> asteroidSides = new ArrayList<Point>();
			double originalAngle = angle;
			while(angle < 2*Math.PI) {
				double x = Math.cos(angle) * radius;
				double y = Math.sin(angle) * radius;
				asteroidSides.add(new Point(x, y));
				angle += originalAngle;
			}
			// Set everything up to create the asteroid
			Point[] inSides = asteroidSides.toArray(new Point[asteroidSides.size()]);
			Point inPosition = new Point(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
			double inRotation = Math.random() * 360;
			asteroids.add(new Asteroid(inSides, inPosition, inRotation));
		}
		return asteroids;
	}
	
	
	public void paint(Graphics brush) 
	{
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);
//		for(Asteroid asteroid:asteroidList)
//		{
//			asteroid.paint(brush, Color.white);
//		}
		// sample code for printing message for debugging
		// counter is incremented and this message printed
		// each time the canvas is repainted
		counter++;
		brush.setColor(Color.white);
		brush.drawString("Counter is " + counter,10,10);
		
		brush.setColor(Color.blue);
		ship.paint(brush, Color.blue);
		brush.setColor(Color.white);
		for (Asteroid asteroid : randomAsteroids)
		{
			asteroid.paint(brush,Color.white);
			asteroid.move();
		}
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