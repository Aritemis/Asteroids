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
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Asteroids extends Game 
{
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	private Ship ship;
	static int counter = 0;
	private List<Asteroid> randomAsteroids = new ArrayList<Asteroid>();
	private static boolean collide;
	private int collideCount;
	private int shipColor;
	private Star[] stars;
	private int colorPosition;
	private int starCount;
	private int lives;
	private boolean invincible;
	private BufferedImage lose;
	private BufferedImage win;

	public Asteroids() 
	{
		super("Asteroids",800,600);
		this.setFocusable(true);
		this.requestFocus();
		Point[] shipShape = 
		{
				new Point(0, 0),
				new Point(0, 20),
				new Point(30, 10)
		};
		Point shipPosition = new Point(400,300);
		ship = new Ship(shipShape, shipPosition, 270);
		this.addKeyListener(ship);
		randomAsteroids = createRandomAsteroids(15,60,30);
		stars = createStars(200, 5);
		collideCount = 0;
		colorPosition = 0;
		starCount = 0;
		lives = 5;
		invincible = false;
		collide = false;
		try 
		{
			lose = ImageIO.read(this.getClass().getResourceAsStream("lose.png"));
			win = ImageIO.read(this.getClass().getResourceAsStream("win.png"));
		} 
		catch (Exception e) {}
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
		Color shipColor = rainbow();
		List<Bullet> shots = ship.getBullets();
		List<Bullet> removeList = new ArrayList<Bullet>();
		List<Asteroid> splodePlz = new ArrayList<Asteroid>();
		
		if(collide)
		{
			shipColor = danger();
		}
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);
		
		counter++;
//		brush.setColor(Color.white);
//		brush.drawString("Counter is " + counter,10,10);
		brush.setColor(Color.gray);
		for (Asteroid asteroid : randomAsteroids)
		{
			asteroid.move();
			if(asteroid.collision(ship))
			{
				collideCount = 40;
				if(!invincible)
				{
					lives--;
				}
				collide = true;
				invincible = true;
			}
			
			asteroid.paint(brush, Color.gray);
			
			for(Bullet shot:shots)
			{
				if(asteroid.contains(shot.getCenter()))
				{
					splodePlz.add(asteroid);
					removeList.add(shot);
				}
			}
		}
		
		for(Asteroid asteroid : splodePlz)
		{
			randomAsteroids.remove(asteroid);
		}
		for(Bullet shot: removeList)
		{
			shots.remove(shot);
		}
		
		for (Star star : stars)
		{
			Color starColor = Color.white;
			if(starCount == 5)
			{
				if(colorPosition == 0)
				{
					starColor = Color.black;
				}
				starCount = -1;
			}
			starCount++;
			star.paint(brush, starColor, ship.getRotation());
		}
		
		if(!collide)
		{
			ship.paint(brush,shipColor);
		}
		else
		{
			ship.paint(brush,shipColor);
			collideCount--;
			if(collideCount == 0)
			{
				collide = false;
				invincible = false;
			}
		}
		ship.move();
		
		for(Bullet shot: shots)
		{
			shot.move();
			if(shot.outOfBounds())
			{
				removeList.add(shot);
			}
			shot.paint(brush,shipColor);
		}
		for(Bullet shot: removeList)
		{
			shots.remove(shot);
		}
		
		if(randomAsteroids.isEmpty())
		{
			brush.setColor(Color.black);
			//brush.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
			brush.drawImage(win , 200, 200, frame);
			on = false;
		}
		
		if(lives < 0)
		{
			brush.setColor(Color.black);
			//brush.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
			brush.drawImage(lose , 200, 200, frame);
			on = false;
		}
	}
	
	public Color rainbow()
	{
		Color color = null;
		
		switch(colorPosition)
		{
			case 0:
				color = Color.cyan;
				colorPosition ++;
				break;
				
			case 1: 
				color = Color.blue;
				colorPosition = 0;
				break;
				
			default:
				color = Color.gray;
				break;
		}
		return color;
	}
	
	public Color danger()
	{
		Color color = null;
		
		switch(shipColor)
		{
			case 0:
				color = Color.red;
				shipColor ++;
				break;
				
			case 1: 
				color = Color.white;
				shipColor = 0;
				break;
				
			default:
				color = Color.gray;
				break;
		}
		return color;
	}

	public static void main (String[] args) 
	{
		Asteroids a = new Asteroids();
		a.repaint();
	}

}