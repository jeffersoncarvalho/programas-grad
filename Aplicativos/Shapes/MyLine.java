import java.awt.Graphics;

public class MyLine extends MyShape
{
  public MyLine()
  {
	super();
  }
  public MyLine(int a1, int b1, int a2, int b2)
  {
	super(a1,a2,b1,b2);
  }
  public void draw(Graphics g)
  {
	g.drawLine(getX1(),getY1(),getX2(),getY2());
  }
}