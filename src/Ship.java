import java.awt.Color;
import java.awt.Graphics;

public class Ship extends Polygon {
	
	public Ship(Point[] inShape, Point inPosition, double inRotation)
	{
		super(inShape, inPosition, inRotation);
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
//			System.out.println(xValues[i]);
//			System.out.println(yValues[i]);
		}
		brush.drawPolygon(xValues,yValues, npts);

	}

	@Override
	public void move() 
	{
		if(position.x > -25)
		{
			position.x = position.x - 1;
		}
		else
		{
			position.x = position.x + 25 + Asteroids.SCREEN_WIDTH;
		}

	}

}
