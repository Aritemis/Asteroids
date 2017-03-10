import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ship extends Polygon implements KeyListener
{

	private int yVelocity;
	private int xVelocity;
	private boolean turningLeft;
	private boolean turningRight;
	private boolean forward;
	
	public Ship(Point[] inShape, Point inPosition, double inRotation)
	{
		super(inShape, inPosition, inRotation);
	}

	public void paint(Graphics brush, Color color) 
	{
		Point[] points = this.getPoints();
		int npts = points.length;
		int[] xValues = new int[npts];
		int[] yValues = new int[npts];
		for(int i = 0; i < points.length; i++)
		{
			xValues[i] = (int) points[i].x;
			yValues[i] = (int) points[i].y;
		}
		brush.setColor(color);
		brush.drawPolygon(xValues,yValues, npts);

	}

	public void move() 
	{	
			if(turningLeft)
			{
				rotate(3);
				position.x += 3 * Math.cos(Math.toRadians(rotation));
				position.y += 3 * Math.sin(Math.toRadians(rotation));
			}
			if(turningRight)
			{
				rotate(-3);
				position.x += 3 * Math.cos(Math.toRadians(rotation));
				position.y += 3 * Math.sin(Math.toRadians(rotation));
			}
			if(forward)
			{
				position.x += 3 * Math.cos(Math.toRadians(rotation));
				position.y += 3 * Math.sin(Math.toRadians(rotation));
			}
			
			position.x += xVelocity; 
			position.y += yVelocity;
			
		
		
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
	
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_UP) 
		{
			yVelocity = -1;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) 
		{
			yVelocity = 1;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			turningLeft = true;
			xVelocity = -1;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			turningRight = true;
			xVelocity = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_UP) 
		{
			yVelocity = 0;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) 
		{
			yVelocity = 0;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			turningLeft = false;
			xVelocity = 0;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			turningRight = false;
			xVelocity = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

}
