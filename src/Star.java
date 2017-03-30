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
		if(Ship.isForward()) 
		{
			center.x -= 2 * Math.cos(Math.toRadians(rotation));
			center.y -= 2 * Math.sin(Math.toRadians(rotation));
		}
		if(Ship.isBackward()) 
		{
            center.x += 2 * Math.cos(Math.toRadians(rotation));
            center.y += 2 * Math.sin(Math.toRadians(rotation));
		}

		center.x -= Math.cos(Math.toRadians(rotation));
		center.y -= Math.sin(Math.toRadians(rotation));
		center.x -= Math.cos(Math.toRadians(Math.random()*50));
		center.y -= Math.cos(Math.toRadians(Math.random()*50));
		center.x += Math.cos(Math.toRadians(Math.random()*50));
		center.y += Math.cos(Math.toRadians(Math.random()*50));

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
	public void paint(Graphics brush, Color color) {
		// TODO Auto-generated method stub

	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

}
