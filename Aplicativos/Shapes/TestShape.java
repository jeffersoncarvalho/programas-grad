import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class TestShape extends JFrame
{
  private MyLine l;
  public TestShape()
  {
	super("Testando Formas");
	l=new MyLine(30,40,100,200);
  }

  public static void main(String args[])
  {
	TestShape window=new TestShape();
    
        window.addWindowListener(
	  new WindowAdapter()
          {
	  	public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	  }
	);
 
        window.setSize(400,400);
	window.show();

	
  }

  public void paint(Graphics g)
  {
	l.draw(g);
  }
}