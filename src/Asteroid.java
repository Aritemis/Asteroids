import java.awt.Color;
import java.awt.Graphics;

/**
 *	@author Ariana Fairbanks
 */

public class Asteroid extends Polygon 
{

	private boolean stationary;
	private Point[] points;
	private int translationSeq = 0;
	private boolean increase = true;
	private int warp;

	public Asteroid(Point[] inShape, Point inPosition, double inRotation, boolean stationary, int warp) 
	{
		super(inShape, inPosition, inRotation);
		this.stationary = stationary;
		this.points = inShape;
		this.warp = warp;
	}
	
	public Asteroid(Point[] inShape, Point inPosition, double inRotation) 
	{
		super(inShape, inPosition, inRotation);
		this.points = inShape;
	}

	@Override
	public void paint(Graphics brush, Color color) 
	{
		move();
		points = this.getPoints();
		int npts = points.length;
		int[] xValues = new int[npts];
		int[] yValues = new int[npts];
		for(int i = 0; i < points.length; i++)
		{
			xValues[i] = (int) points[i].x;
			yValues[i] = (int) points[i].y;
//			System.out.println(xValues[i]);
//			System.out.println(yValues[i]);
		}
		brush.drawPolygon(xValues,yValues, npts);
	}

	@Override
	public void move() 
	{
//		';f(translationSeq > 200)
//			{
//				increase = false;
//			}
//		}
//		else
//		{
//			translationSeq--;
//			if(translationSeq < 0)
//			{
//				increase = true;
//			}
//		}
//		if(this.warp % 250 == 0)
//		{
//			translationSeq = 0 - translationSeq;
//		}
		position.x += Math.cos(Math.toRadians(rotation));
		position.y += Math.sin(Math.toRadians(rotation));
		int maxWidth = Asteroids.SCREEN_WIDTH;
		int maxHeight = Asteroids.SCREEN_HEIGHT;
		if(position.x < -25)
		{
			position.x = position.x + maxWidth + 50;
		}
		else if(position.x - 25 > maxWidth)
		{
			position.x = position.x - maxWidth - 50;
		}
		if(position.y < -25)
		{
			position.y = position.y + maxHeight + 50;
		}
		else if(position.y - 25 > maxHeight)
		{
			position.y = position.y - maxHeight - 50;
		}
	}
	
	

}
