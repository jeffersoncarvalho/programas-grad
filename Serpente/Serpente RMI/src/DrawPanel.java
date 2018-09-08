import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

/**
 * Onde serï¿½o plotados todos os jogadores.
 */
public class DrawPanel extends JPanel {

 
  
  private String theGame = "";
  private ImageIcon  iconFood;
  private ImageIcon  iconHead;
  private ImageIcon  iconAnime;
  private Graphics2D g2D;
  private Point food;

  /**
   * Cria uma nova instï¿½ncia do DrawPanel
   */
  public DrawPanel()
  {
    
    super.setPreferredSize(new Dimension(400,400)); 
    iconFood = new ImageIcon("food.gif");
    iconHead = new ImageIcon("head.gif");
    iconAnime = new ImageIcon("anime.jpg");
    food = new Point(-10,-10);
  }

  
  

  /**
   * Pinta painel.
   */
   public void paint(Graphics g)
   {
     	  super.paint(g);
     	  iconAnime.paintIcon(this,g,0,0);
     	  
     	  //gradear
  	      //for(int i = 0; i<40; i++)
  	       //for(int j = 0; j<40; j++)
     	  	// g.drawRect(i*10,j*10,10,10);
     	  	 
     	  //Anti Aliasing 
     	  Graphics2D g2d = (Graphics2D)g;
     	  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON);
          
          //desenhando a comida!
          iconFood.paintIcon(this,g,food.getX()*10,food.getY()*10);
          
          if(!this.theGame.equals(""))
           this.process(this.theGame, g2d);
                             
          

   }

   /**
   *Adicionando cobras ao painel.
   */
    public void addObject(String s)
    {
   	  this.theGame = s;
    }
    
    private void process(String s, Graphics2D g2d)
    {
    	//Food
   	  StringTokenizer foodToken = new StringTokenizer(s,"#",false);
   	  StringTokenizer subFoodToken = new StringTokenizer(foodToken.nextToken());
   	  subFoodToken.nextToken(); // Cor
   	  this.setFood(Integer.parseInt(subFoodToken.nextToken()),
   	  			   Integer.parseInt(subFoodToken.nextToken())
   	  			   );
   	  
   	  //Snakes
   	  s = foodToken.nextToken();
   	  System.out.println (s);
//      tableSnakes.clear();
      StringTokenizer tokens = new StringTokenizer(s,"COR");
      while(tokens.hasMoreTokens())
      {

          String tok = tokens.nextToken();
          StringTokenizer subToken = new StringTokenizer(tok);
          int R = Integer.parseInt(subToken.nextToken());//RED
          int G = Integer.parseInt(subToken.nextToken());//GREEN
          int B = Integer.parseInt(subToken.nextToken());//BLUE
          g2d.setColor(new Color(R,G,B));
          //ArrayList arrayPoints = new ArrayList();
          boolean head = true;
          while(subToken.hasMoreTokens())
          {
              int x = Integer.parseInt(subToken.nextToken());
              int y = Integer.parseInt(subToken.nextToken());
              //arrayPoints.add(new Point(x,y));
              if(head)
              {
               iconHead.paintIcon(this,g2d,x*10,y*10);
               head =false;
              }
              else
               g2d.fillOval(x*10,y*10, 10, 10);   
               
          }
          
          
      }

    }
    
    /*
     * Ajustando a posição da comida.
     */
     public void setFood(int x, int y)
     {
     	food.setX(x);
     	food.setY(y);	
     }

}