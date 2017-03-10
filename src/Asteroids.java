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
	static int counter = 0;
	private List<Asteroid> randomAsteroids = new ArrayList<Asteroid>();
	private boolean collide;
	private int collideCount;
	private Star[] stars;

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
		this.addKeyListener(ship);
		randomAsteroids = createRandomAsteroids(10,60,30);
		stars = createStars(150, 5);
		collideCount = 0;
		collide = false;
	}

	public Star[] createStars(int numberOfStars, int maxRadius) 
	{
		Star[] stars = new Star[numberOfStars];
		for(int i = 0; i < numberOfStars; ++i) 
		{
			Point center = new Point
					(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);


			int radius = (int) (Math.random() * maxRadius);
			if(radius < 1)
			{
				radius = 1;
			}
			stars[i] = new Star(center, radius);
		}


	return stars;
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
			while(angle < 2*Math.PI) 
			{
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
		
		counter++;
		brush.setColor(Color.white);
		brush.drawString("Counter is " + counter,10,10);
		brush.setColor(Color.white);
		for (Asteroid asteroid : randomAsteroids)
		{
			asteroid.paint(brush,Color.gray);
			asteroid.move();
			if(asteroid.collision(ship))
			{
				collideCount = 20;
				collide = true;
			}
		}
		
		for (Star star : stars)
		{
			star.paint(brush, Color.white);
		}
		
		if(!collide)
		{
			ship.paint(brush, Color.blue);
		}
		else
		{
			ship.paint(brush, Color.red);
			collideCount--;
			if(collideCount == 0)
			{
				collide = false;
			}
		}
		ship.move();
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



}