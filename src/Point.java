/**
 * Original code by Dan Leyzberg and Art Simon
*/

public class Point implements Cloneable 
{
  double x,y;
  public Point(double inX, double inY) { x = inX; y = inY; }
  
  public Point clone() 
  {
	  return new Point(x, y);
  }
  
  public String toString()
  {
	  return x + " " + y;
  }
}