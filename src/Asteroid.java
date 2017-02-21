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

	@Override
	public void paint(Graphics brush, Color color) 
	{
		if(!stationary)
		{
			move();
		}
		points = this.getPoints();
		int[] xValues = new int[3];
		int[] yValues = new int[3];
		for(int i = 0; i < points.length; i++)
		{
			xValues[i] = (int) points[i].x + translationSeq;
			yValues[i] = (int) points[i].y - translationSeq;
//			System.out.println(xValues[i]);
//			System.out.println(yValues[i]);
		}
		brush.drawPolygon(xValues,yValues, 3);
	}

	@Override
	public void move() 
	{
		if(increase)
		{
			translationSeq++;
			if(translationSeq > 200)
			{
				increase = false;
			}
		}
		else
		{
			translationSeq--;
			if(translationSeq < 0)
			{
				increase = true;
			}
		}
		if(this.warp % 250 == 0)
		{
			translationSeq = 0 - translationSeq;
		}
	}
	
	

}
