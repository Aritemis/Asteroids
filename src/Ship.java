import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Ship extends Polygon implements KeyListener
{
	
	private static boolean forward;
	private static boolean backward;
	private static boolean turningRight;
	private static boolean turningLeft;
	private boolean shoot;
	private boolean mustRelease;
	private ArrayList<Bullet> shots;
	private Point front;

	public Ship(Point[] inShape, Point inPosition, double inRotation)
	{
		super(inShape, inPosition, inRotation);
		forward = false;
		backward = false;
		turningRight = false;
		turningLeft = false;
		shoot = false;
		mustRelease = false;
		shots = new ArrayList<Bullet>();
		front = inPosition;
	}

	public void paint(Graphics brush, Color color) 
	{
		Point[] points = this.getPoints();
		int npts = points.length;
		this.front = points[0];
		int[] xValues = new int[npts];
		int[] yValues = new int[npts];
		for(int i = 0; i < points.length; i++)
		{
			xValues[i] = (int) points[i].x;
			yValues[i] = (int) points[i].y;
		}
		brush.setColor(Color.black);
		brush.fillPolygon(xValues, yValues, npts);
		brush.setColor(color);
		brush.drawPolygon(xValues, yValues, npts);
	}

	public void move() 
	{	
        if(isForward()) 
        {
            position.x += 3 * Math.cos(Math.toRadians(rotation));
            position.y += 3 * Math.sin(Math.toRadians(rotation));
        }
        if(isBackward()) 
        {
            position.x -= 3 * Math.cos(Math.toRadians(rotation));
            position.y -= 3 * Math.sin(Math.toRadians(rotation));
        }
        if(isTurningRight()) 
        {
            rotate(2);
        }
        if(isTurningLeft()) 
        {
            rotate(-2);
        }
        if(shoot) 
        {
            if(!mustRelease)
            {
            	Bullet start = new Bullet(getPoints()[2].clone(), rotation);
            	start.center.x -= 2;
            	shots.add(start);
            }
            mustRelease = true;
            shoot = false;
        }

		if(position.x > Asteroids.SCREEN_WIDTH) 
		{
			position.x -= Asteroids.SCREEN_WIDTH;
		} 
		else if(position.x < 0)
		{
			position.x += Asteroids.SCREEN_WIDTH;
		}
		if(position.y > Asteroids.SCREEN_HEIGHT) 
		{
			position.y -= Asteroids.SCREEN_HEIGHT;
		} 
		else if(position.y < 0) 
		{
			position.y += Asteroids.SCREEN_HEIGHT;
		}
	}
	
	public ArrayList<Bullet> getBullets()
	{
		return shots;
	}
	
	public double getRotation()
	{
		return rotation;
	}

	public static boolean isForward()
	{
		return forward;
	}
	public static boolean isBackward()
	{
		return backward;
	}

	public static boolean isTurningRight() 
	{
		return turningRight;
	}

	public static boolean isTurningLeft() 
	{
		return turningLeft;
	}

	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_UP) 
		{
			forward = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) 
		{
			backward = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			turningLeft = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			turningRight = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) 
		{
			shoot = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_UP) 
		{
			forward = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) 
		{
			backward = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			turningLeft = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			turningRight = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) 
		{
			mustRelease = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{

	}

}


