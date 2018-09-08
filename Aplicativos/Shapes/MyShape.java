import java.awt.Graphics;
public abstract class MyShape extends Object
{
  private int x1,x2,y1,y2;
  
  public MyShape()
  {
	x1=0;
	x2=0;
	y1=0;
	y2=0;
  }

  public MyShape(int a1,int a2,int b1,int b2)
  {
	x1=a1;
	x2=a2;
	y1=b1;
	y2=b2;
  }
  public int getX1(){return x1;}
  public int getX2(){return x2;}
  public int getY1(){return y1;}
  public int getY2(){return y2;}

  public void setX1(int i) {x1=(i>=0)?i:0;}
  public void setX2(int i) {x2=(i>=0)?i:0;}
  public void setY1(int i) {y1=(i>=0)?i:0;}
  public void setY2(int i) {y2=(i>=0)?i:0;}

  public abstract void draw(Graphics g);
}