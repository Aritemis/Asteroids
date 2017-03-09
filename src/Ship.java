import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ship extends Polygon
{
	
	public Ship(Point[] inShape, Point inPosition, double inRotation)
	{
		super(inShape, inPosition, inRotation);
	}

	public void paint(Graphics brush, Color color, boolean up, boolean down, boolean left, boolean right) 
	{
		move(up, down, left, right);
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

	public void move(boolean up, boolean down, boolean left, boolean right) 
	{	
		moveUp(up);
		moveDown(down);
		moveLeft(left);
		moveRight(right);
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
	
	private void moveUp(boolean up)
	{
		if(up)
		{
			position.x += 3 * Math.cos(Math.toRadians(rotation));
			position.y += 3 * Math.sin(Math.toRadians(rotation));
		}
	}
	private void moveDown(boolean down)
	{
		if(down)
		{
			position.y+=1;
		}
	}
	private void moveLeft(boolean left)
	{
		if(left)
		{
			position.x-=1;
		}
	}
	private void moveRight(boolean right)
	{
		if(right)
		{
			position.x+=1;
		}
	}

	@Override
	public void paint(Graphics brush, Color color) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() 
	{
		// TODO Auto-generated method stub
		
	}

}
