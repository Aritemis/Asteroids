/**
 *	@author Ariana Fairbanks
 */

import java.awt.Color;
import java.awt.Graphics;

public class Asteroid extends Polygon
{
	@SuppressWarnings("unused")
	private Point[] points;
	
	public Asteroid(Point[] inShape, Point inPosition, double inRotation) 
	{
		super(inShape, inPosition, inRotation);
		this.points = inShape;
	}

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
		brush.setColor(Color.black);
		brush.fillPolygon(xValues, yValues, npts);
		brush.setColor(color);
		brush.drawPolygon(xValues, yValues, npts);
	}

	public void move() 
	{
		position.x += Math.cos(Math.toRadians(rotation));
		position.y += Math.sin(Math.toRadians(rotation));
		int maxWidth = 2 * Asteroids.maxAsteroidWidth;
		int screenWidth = Asteroids.SCREEN_WIDTH;
		int screenHeight = Asteroids.SCREEN_HEIGHT;
		if(position.x > screenWidth + maxWidth) 
		{
			position.x -= screenWidth + (1.5 * maxWidth);
		} 
		else if(position.x + maxWidth < 0)
		{
			position.x += screenWidth + (1.5 * maxWidth);
		}
		
		if(position.y > screenHeight + maxWidth) 
		{
			position.y -= screenHeight + (1.5 * maxWidth);
		} 
		else if(position.y + maxWidth < 0) 
		{
			position.y += screenHeight + (1.5 * maxWidth);
		}
		
	}
}
