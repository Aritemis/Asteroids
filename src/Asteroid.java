import java.awt.Color;
import java.awt.Graphics;

/**
 *	@author Ariana Fairbanks
 */

public class Asteroid extends Polygon 
{

	private boolean stationary;

	public Asteroid(Point[] inShape, Point inPosition, double inRotation, boolean stationary) 
	{
		super(inShape, inPosition, inRotation);
		this.stationary = stationary;
	}

	@Override
	public void paint(Graphics brush, Color color) 
	{
		
	}

	@Override
	public void move() 
	{

	}

}
