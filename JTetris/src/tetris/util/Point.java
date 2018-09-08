package tetris.util;

import java.awt.Color;

public class Point {

  private int x,y;
  private Color color;

  /**
   * Cria um nova instância de Point
   */
  public Point(int a, int b, Color c)
  {
     x=a;
     y=b;
     color = c;
  }
  
  public void setAll(int a, int b)
  {
  	x=a;
  	y=b;
  }
  /**
   * Muda valor de x
   */
   public void setX(int a)
   {
     x=a;
   }

   /**
    * Pega valor de x
    */
   public int getX()
   {
      return x;
   }

    /**
     * Muda valor de y
     */
   public void setY(int a)
   {
     y=a;
   }

   /**
    * Pega valor de y
    */
   public int getY()
   {
      return y;
   }
   
   /**
    *Setando a cor
    */
   public void setColor(Color c)
   {
   	color = c;
   }   
	
   public Color getColor()
   {
   	return color;
   }
   
}