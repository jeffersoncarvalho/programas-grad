package tetris.gui;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;

import tetris.util.Point;

public class DrawPanel extends JPanel {
	
  private Color[][] matDraw;	
  private ArrayList piece;
  private ImageIcon background;
  private boolean lost = false;
  private boolean entered = false;
  /**
   * Cria uma nova instï¿½ncia do DrawPanel
   */
  public DrawPanel(Color[][] matDraw, ArrayList piece)
  {
    this.matDraw = matDraw; // criada pela clase Table
    this.piece = piece;
    char sep = File.separatorChar;
    //System.out.println (sep);
    //background = new ImageIcon("."+ sep +"tetris "+ sep +"gui"+ sep +"fundo1.jpg");
    background = new ImageIcon(this.getClass().getResource("/tetris/gui/fundo1.jpg"));
   // System.out.println (this.background.getIconHeight());
    
    super.setPreferredSize(new Dimension(200,400));
    super.setBackground(Color.white); 
    
    //System.out.println (this.matDraw.length);
    //System.out.println (this.matDraw[0].length);
  }

  /**
   * Pinta painel.
   */
   public void paint(Graphics g)
   {
     	  super.paint(g);
     	  this.background.paintIcon(this,g,0,0);
     	  
     	  //for(int i=0;i<matDraw.length;i++)
     	   //for(int j=0;j<matDraw[0].length;j++)
     	     //g.drawLine(j*20,0,j*20,400);
     	    //g.drawRect(j*20,i*20,20,20);
     	  
     	  Graphics2D g2d = (Graphics2D)g;
     	  //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
     	  g2d.setStroke( new BasicStroke(8,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND));
     	 
     	  
     	  if(!lost)
     	  {
	     	  //desenha a matriz
	     	  for(int i=0; i<matDraw.length; i++)
	     	   for(int j=0; j <matDraw[i].length; j++)
	     	    if(matDraw[i][j] != null)
	     	    {
	     	      g2d.setColor(matDraw[i][j]);
	     	      g2d.fill3DRect(j*20,i*20,20,20,false);	
	     	      //g2d.fillOval(j*20,i*20,20,20);
	     	     //g2d.drawRect(j*20,i*20,20,20);
	     	    }
	     	   
	     	  //desenha a peça que se move
	     	 if(!entered)
	     	  for(int i =0; i<this.piece.size(); i++)
	     	  {
	     	  	Point p = (Point)this.piece.get(i);
	     	  	g2d.setColor(p.getColor());
	     	  	g2d.fill3DRect(p.getX()*20,p.getY()*20,20,20,true);
	     	  	//g2d.setColor(Color.white);
	     	  	//g2d.fill(p.getX()*20,p.getY()*20,20,20);
	 			
				//g2d.drawRect(p.getX()*20,p.getY()*20,20,20);
	     	  }
	      }
	      
   
   }
   
   /**
    *
    */
   public void updatePiece(ArrayList piece)
   {
   	 this.piece = piece;
   }
   
   /**
    *
    */
   public void setLost(boolean value)
   {
   		this.lost = value;
   }
   
   public void boundaryFill(int x, int y, Color fill,Graphics g)
   {
   	   
   	   super.paint(g);
   	   Graphics2D g2d = (Graphics2D)g;
   	   
   	   
   	   if((y>=0) && (y<this.matDraw.length) &&  //20
   	      (x>=0) && (x<this.matDraw[0].length))  //10
   		if((this.matDraw[y][x] == null) || (!this.matDraw[y][x].equals(fill)))
   		{
   			matDraw[y][x] = fill;
   			//   g2d.fill3DRect(a*20,b*20,20,20,true);
   			//try{Thread.sleep(50);}catch(Exception e){}
   			
	     	this.repaint();
   			this.boundaryFill(x,y-1,fill,g2d);
   			this.boundaryFill(x,y+1,fill,g2d);
   			this.boundaryFill(x+1,y,fill,g2d);
   			this.boundaryFill(x-1,y,fill,g2d);
   		}
   }

	
	public void setMatDraw(Color[][] matDraw) {
		this.matDraw = matDraw; 
	}

	
	public void setEntered(boolean entered) {
		this.entered = entered; 
	}



	public boolean getEntered() {
		return (this.entered); 
	}
}