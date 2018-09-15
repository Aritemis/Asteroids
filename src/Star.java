/**
 * @author Ariana Fairbanks
 */

import java.awt.Color;
import java.awt.Graphics;

public class Star extends Circle 
{

	public Star(Point center, int radius)
	{
		super(center, radius);
	}

	public void paint(Graphics brush, Color color, double rotation) 
	{
		move(rotation);
		brush.setColor(color);
		brush.fillOval((int) center.x, (int) center.y, radius, radius);
	}

	public void move(double rotation) 
	{
		double radians = Math.toRadians(rotation);
		double mod = -3;
		
		if(Ship.forward) 
		{
			mod = -5;
		}
		if(Ship.backward) 
		{
            mod = 3;
		}
		
		center.x += mod * Math.cos(radians);
		center.y += mod * Math.sin(radians);


		if(center.x > Asteroids.SCREEN_WIDTH) 
		{
			center.x -= Asteroids.SCREEN_WIDTH;
		} 
		else if(center.x < 0)
		{
			center.x += Asteroids.SCREEN_WIDTH;
		}
		if(center.y > Asteroids.SCREEN_HEIGHT) 
		{
			center.y -= Asteroids.SCREEN_HEIGHT;
		} 
		else if(center.y < 0) 
		{
			center.y += Asteroids.SCREEN_HEIGHT;
		}
	}

	@Override
	public void move() 
	{
		
	}

}
