
/**
 * @author Ariana Fairbanks
 */

import java.awt.Color;
import java.awt.Graphics;

public abstract class Circle
{
	protected Point center;
	protected int radius;

	public Circle(Point center, int radius)
	{
		this.center = center;
		this.radius = radius;
	}

	public abstract void paint(Graphics brush, Color color);

	public abstract void move();
}
