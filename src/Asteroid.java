import java.awt.Color;
import java.awt.Graphics;

/**
 *	@author Ariana Fairbanks
 */

public class Asteroid extends Polygon implements Shape
{

	@SuppressWarnings("unused")
	private Point[] points;
	
	public Asteroid(Point[] inShape, Point inPosition, double inRotation) 
	{
		super(inShape, inPosition, inRotation);
		this.points = inShape;
	}

	@Override
	public void paint(Graphics brush, Color color) 
	{
		move();
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
		brush.drawPolygon(xValues, yValues, npts);
	}

	@Override
	public void move() 
	{
		position.x += Math.cos(Math.toRadians(rotation));
		position.y += Math.sin(Math.toRadians(rotation));
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
	
	

}
