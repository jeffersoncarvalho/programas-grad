import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

/**
 * Onde serï¿½o plotados todos os jogadores.
 */
public class DrawPanel extends JPanel {

 
  
  private Hashtable tableSnakes;
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
    //super.setBackground(new Color(49,50,106));
    super.setPreferredSize(new Dimension(400,400)); 
    tableSnakes = new Hashtable();
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
                             
          Enumeration e = tableSnakes.elements();
          Enumeration keys = tableSnakes.keys();
  
  	      while(keys.hasMoreElements())
  	      {  
  	      		//uma cobra específica.
                Color key = (Color)keys.nextElement();
                ArrayList arrayTemp = (ArrayList)tableSnakes.get(key);
               
				//varrendo a cobra específica.
                for(int i=0;i<arrayTemp.size();i++)
                {
                				Point pointTemp = (Point)arrayTemp.get(i);
                				g2d.setColor(key);
                				if(i==0)
                				{
                      			  iconHead.paintIcon(this,g,pointTemp.getX()*10,pointTemp.getY()*10);
                      			}
                      			else
                      			{
                                   g2d.fillOval(pointTemp.getX()*10, pointTemp.getY()*10, 10, 10);   
                   				}
                   				g2d.setColor(Color.black);
                   				g2d.drawOval(pointTemp.getX()*10, pointTemp.getY()*10, 10, 10);
                }

  	      }//enquanto ainda hover cobras



   }

   /**
   *Adicionando cobras ao painel.
   */
   public void addObject(String s)
   {
      tableSnakes.clear();
      StringTokenizer tokens = new StringTokenizer(s,"COR");
      while(tokens.hasMoreTokens())
      {

          String tok = tokens.nextToken();
          StringTokenizer subToken = new StringTokenizer(tok);
          int R = Integer.parseInt(subToken.nextToken());//RED
          int G = Integer.parseInt(subToken.nextToken());//GREEN
          int B = Integer.parseInt(subToken.nextToken());//BLUE
          
          ArrayList arrayPoints = new ArrayList();
          while(subToken.hasMoreTokens())
          {
              int x = Integer.parseInt(subToken.nextToken());
              int y = Integer.parseInt(subToken.nextToken());
              arrayPoints.add(new Point(x,y));
          }
          tableSnakes.put(new Color(R,G,B),arrayPoints);
          
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
     
    
    /**
    * Limpar painel de desenho.
    */
    public void clearPanel()
    {
    	tableSnakes.clear();
    	repaint();
    }

}