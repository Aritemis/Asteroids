/**
 * @author Ariana Fairbanks
 * Original code by Dan Leyzberg and Art Simon
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Asteroids extends Game 
{
	private static final long serialVersionUID = 2868242801801916948L;
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	public static boolean paused;
	public static boolean limbo;
	public static boolean reset;
	private Ship ship;
	static int counter = 0;
	private List<Asteroid> randomAsteroids = new ArrayList<Asteroid>();
	private static boolean collide;
	private int collideCount;
	private int shipColor;
	private Star[] stars;
	private int colorPosition;
	private int lives;
	private boolean invincible;
	private BufferedImage lose;
	private BufferedImage win;
	@SuppressWarnings("unused")
	private BufferedImage any;
	private BufferedImage pause;
	private BufferedImage proceed;
	public static int numAsteroids = 20;
	public static int maxAsteroidWidth = 35;
	public static int minAsteroidWidth = 30;

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
		randomAsteroids = createRandomAsteroids(numAsteroids, maxAsteroidWidth, minAsteroidWidth);
		stars = createStars(200, 5);
		collideCount = 0;
		colorPosition = 0;
		lives = 5;
		invincible = false;
		limbo = false;
		reset = false;
		paused = false;
		collide = false;
		try 
		{
			lose = ImageIO.read(this.getClass().getResourceAsStream("lose.png"));
			win = ImageIO.read(this.getClass().getResourceAsStream("win.png"));
			any = ImageIO.read(this.getClass().getResourceAsStream("any.png"));
			pause = ImageIO.read(this.getClass().getResourceAsStream("pause.png"));
			proceed = ImageIO.read(this.getClass().getResourceAsStream("enter.png"));
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
			double randomX = Math.random() * SCREEN_WIDTH;
			double randomY = Math.random() * SCREEN_HEIGHT;
			boolean xTooNearShip = false;
			boolean yTooNearShip = false;
			if((randomX > 350) && (randomX <450)){xTooNearShip = true;}
			if((randomY > 250) && (randomY <350)){yTooNearShip = true;}
			if((xTooNearShip) && (yTooNearShip))
			{
				if(xTooNearShip)
				{
					if((int)randomX % 2 == 0){	randomX += 100;	}else{ randomX -= 100; }
				}
				else
				{
					if((int)randomY % 2 == 0){	randomY += 100;	}else{ randomY -= 100; }
				}
			}
			Point inPosition = new Point(randomX, randomY);
			double inRotation = Math.random() * 360;
			asteroids.add(new Asteroid(inSides, inPosition, inRotation));
		}
		return asteroids;
	}
	
	public void paint(Graphics brush) 
	{
		if(limbo)
		{
			if(reset){	reset(); }
		}
		else if(paused)
		{
			brush.drawImage(pause , 200, 200, frame);
			brush.drawImage(proceed , 215, 508, frame);
		}
		else
		{
			Color shipColor = rainbow();
			List<Bullet> shots = ship.getBullets();
			List<Bullet> removeList = new ArrayList<Bullet>();
			List<Asteroid> splodePlz = new ArrayList<Asteroid>();
			
			if(collide)
			{
				shipColor = danger();
			}
			
			//draw background
			brush.setColor(Color.black);
			brush.fillRect(0,0,width,height);
			
			//draw stars
			for (Star star : stars)
			{
				star.paint(brush, Color.white, ship.getRotation());
			}
		
			//draw asteroids 
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
				asteroid.paint(brush, Color.white);
				
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
		
			
			//draw counter
			counter++;
			brush.setColor(Color.green);
			if(lives < 2)
			{
				brush.setColor(Color.red);
			}
			brush.drawString("Lives Left: " + lives, 25, 25);
			
			
			if(!collide)
			{
				ship.paint(brush, shipColor);
			}
			else
			{
				ship.paint(brush, shipColor);
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
				shot.paint(brush, shipColor);
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
				brush.drawImage(proceed , 215, 508, frame);
				limbo = true;
			}
			
			if(lives < 1)
			{
				brush.setColor(Color.red);
				brush.drawString("Lives Left: " + lives, 25, 25);
				brush.setColor(Color.black);
				//brush.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				brush.drawImage(lose , 200, 200, frame);
				brush.drawImage(proceed , 215, 508, frame);
				limbo = true;
			}
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
	
	private void reset()
	{
		Point[] shipShape = 
			{
					new Point(0, 0),
					new Point(0, 20),
					new Point(30, 10)
			};
		Point shipPosition = new Point(400,300);
		ship = new Ship(shipShape, shipPosition, 270);
		this.addKeyListener(ship);
		randomAsteroids = createRandomAsteroids(numAsteroids, maxAsteroidWidth, minAsteroidWidth);
		stars = createStars(200, 5);
		collideCount = 0;
		colorPosition = 0;
		lives = 5;
		invincible = false;
		limbo = false;
		reset = false;
		paused = false;
		collide = false;
	}

}