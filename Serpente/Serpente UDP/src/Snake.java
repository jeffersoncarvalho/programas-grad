import java.util.*;
import java.awt.*;

public class Snake {

  private int limitWidth,limitHeight;
  private ArrayList snake;
  private final int pixelFactor = 1;
  private int direction;
  private int id;
  private int point;
  private Color colorBody;
  public int Port;
  

  /**
   * Cria uma nova instï¿½ncia de Snake
   */
   public Snake()
   {
     snake = new ArrayList();
     snake.add(new Point(0,0));
     this.direction = Direction.DOWN;
     this.limitHeight = 100;
     this.limitWidth = 100;
	 this.point = 0;
	 colorBody = this.createColor();
   }

   public Snake(Point p, int direction, int lh, int lw)
   {
     snake = new ArrayList();
     snake.add(p);
     this.direction = Direction.DOWN;
     this.limitHeight = lh;
     this.limitWidth = lw;
	 this.point = 0;
	 colorBody = this.createColor();
   }

   public Snake(int x, int y, int direction, int lh, int lw)
   {
     snake = new ArrayList();
     snake.add(new Point(x,y));
     this.direction = direction;
     this.limitHeight = lh;
     this.limitWidth = lw;
     this.point = 0;
     colorBody = this.createColor();
   }

   /**
    * Alterando o point
    */
   public void setHead(int x, int y)
   {
     Point head = (Point)snake.get(0);
     head.setX(x);
     head.setY(y);
   }

   /**
    * Retorna o ponto
    */
   public ArrayList getSnake()
   {
    return snake;
   }

   /**
    * Mï¿½todo para calcularn
    */
   public void move()
   {
      Point head = (Point)snake.get(0);//head
      this.rebuild();
      switch(direction)
      {
        case Direction.UP:
         if(head.getY()-pixelFactor >=0)
         {
         	this.setHead(head.getX(),head.getY()-pixelFactor);

         }
        break;
        case Direction.DOWN:
         if(head.getY()+pixelFactor < this.limitHeight/10)
         {
         	this.setHead(head.getX(),head.getY()+pixelFactor);
         }
        break;
        case Direction.LEFT:
         if(head.getX()-pixelFactor >=0)
         {
         	this.setHead(head.getX()-pixelFactor,head.getY());

         }
        break;
        case Direction.RIGHT:
         if(head.getX()+pixelFactor <this.limitWidth/10)
         {
         	this.setHead(head.getX()+pixelFactor,head.getY());
         }
        break;
      }



   }

   /**
    * Reformula o corpo//move
    */
   private void rebuild()
   {

      for(int i = snake.size()-1; i>0 ;i--)
      {
        Point pointBefore = (Point)snake.get(i-1);
        Point pointActual = (Point)snake.get(i);
        pointActual.setX(pointBefore.getX());
        pointActual.setY(pointBefore.getY());
      }
   }

   /**
    * Aumenta cobra
    */
   public void increase(int x, int y)
   {

      snake.add(0,new Point(x,y));
   }

   /**
    * retorna Direcao da cobra
    */
   public int getDirection()
   {
     return direction;
   }

   /**
    * Muda direcao de movimento
    */
   public void setDirection(int direction)
   {
   	if(this.getSnake().size() > 1)
   	{
   	 if((this.direction == Direction.UP && direction != Direction.DOWN) ||
   	    (this.direction == Direction.LEFT && direction != Direction.RIGHT) ||
   	    (this.direction == Direction.DOWN && direction != Direction.UP) ||
   	    (this.direction == Direction.RIGHT && direction != Direction.LEFT)  
   	   )
      this.direction = (direction>=1 && direction<=4)? direction : Direction.DOWN;
    }
    else
       this.direction = (direction>=1 && direction<=4)? direction : Direction.DOWN;
   }

 

    public Point getHead()
    {
           return (Point)snake.get(0);
    }

   /**
    *Mata a cobra e mostra o PAU.
    */
   public void killSnake()
   {
   		snake.clear();
   		int x =  (int)(Math.random()*39);
        int y =  (int)(Math.random()*39);
        snake.add(new Point(x,y));
   }
   
  /**
   * adiciona ponto
   *
   */
   public void addPoint()
   {
   		
   		
   		point +=  snake.size();
   }
   
   /**
    *
    */
    public int getPoint()
    {
    	return point;	
    }
    
    /**
     * Cria cor aleatória
     */
    private Color createColor()
    {
    	int r =  (int)(Math.random()*255);
     	int g =  (int)(Math.random()*255);
    	int b =  (int)(Math.random()*255);
    	return new Color(r,g,b);
    }
    
  /**
   *Tranforma cobra em String
   */
   public String toString()
   {
       String result = " ";
       //result += this.color;
       result += this.colorBody.getRed() + " " + this.colorBody.getGreen() + " " + this.colorBody.getBlue();
       for(int i = 0;i<snake.size();i++)
       {
         Point point = (Point)snake.get(i);
         result += " " + point.getX() + " " + point.getY();
       }
       return result;
   }


}