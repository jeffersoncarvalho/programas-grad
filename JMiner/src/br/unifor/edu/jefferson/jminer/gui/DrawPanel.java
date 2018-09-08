/*
 * Created on Mar 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jminer.gui;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import br.unifor.edu.jefferson.jminer.painter.Painter;
import br.unifor.edu.jefferson.jminer.table.Table;
import br.unifor.edu.jefferson.jminer.util.JMinerConstants;

/**
 * Onde ser�o plotados todos os jogadores.
 */
public class DrawPanel extends JPanel {

  private Graphics2D g2D;
  private Table table;
  
  /**
   * Cria uma nova inst�ncia do DrawPanel
   */
  public DrawPanel()
  {
    super.setPreferredSize(new Dimension(JMinerConstants.height*JMinerConstants.macroPixel,
    									 JMinerConstants.width*JMinerConstants.macroPixel));
	setBackground(Color.white);  
	//this.table = table;
 
			 
  }
  
  /**
   * Pinta painel.
   */
   public void paint(Graphics g)
   {
     	  super.paint(g);
     	  
     	  
     	  	
     	  
     	  //gradear
     	 // this.buildGrid(g);
     	  
     	  //Anti Aliasing 
     	  Graphics2D g2d = (Graphics2D)g;
     	  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON);
     	  //desenha everything
     	  this.drawTable(g2d);
     	  
     	  Painter.paintSignal(g2d,14*JMinerConstants.macroPixel,14*JMinerConstants.macroPixel);
                               
         
   }

	/**
	 * Efeito de gradeamento no painel
	 * @param g
	 */
   private void buildGrid(Graphics g)
   {
 
			g.setColor(Color.black);
			for(int i = 0; i<JMinerConstants.height; i++)
				g.drawLine(i*JMinerConstants.macroPixel,0,i*JMinerConstants.macroPixel, JMinerConstants.width*JMinerConstants.macroPixel);
			 
			for(int i = 0; i<JMinerConstants.width; i++)
				g.drawLine(0,i*JMinerConstants.macroPixel, JMinerConstants.height*JMinerConstants.macroPixel,i*JMinerConstants.macroPixel);
			
   }
   
   private void drawMiners(Graphics2D g, int x, int y)
   {
   		Painter.paintMinerNOGOLD(g,x,y);
   }
   
   private void drawCC(Graphics2D g, int x, int y)
   {
   		Painter.paintControlCenter(g,x,y);
   }
   
   
   private void drawTable(Graphics2D g)
   {
   		int value = 0;
   		int clue = 0;
   		for(int i=0;i<JMinerConstants.height;i++)
   			for(int j=0;j<JMinerConstants.width;j++)
   			{	
   				 value = this.table.getMatrixPoint(i,j);
   				 switch(value)
				 {
   				 	case JMinerConstants.MINER_NOGOLD:
   				 		Painter.paintMinerNOGOLD(g,i,j);
   				 	break;
   				 	case JMinerConstants.MINER_HASGOLD:
   				 		Painter.paintMinerHASGOLD(g,i,j);
				 	break;
   				 	case JMinerConstants.CC:
   				 		
				 		this.drawCC(g,i,j);
				 	break;
				 	//ouro
   				 	case 10:case 9:case 8:case 7:case 6:case 5:case 4:case 3:case 2:case 1:
				 		g.setFont(new Font("Arial",Font.BOLD,8));
				 		g.setColor(Color.black);
				 		g.drawString(value+"",i*JMinerConstants.macroPixel+8,j*JMinerConstants.macroPixel+8);
				 		Painter.paintGold(g,i,j);
				 	break;
				 		
				 	/*default:
				 		if(value!=0)
						g.drawOval(i*JMinerConstants.macroPixel+8,j*JMinerConstants.macroPixel+8,10,10);
				 	break;*/
				 }
   				 
   				 //clue
   				clue = table.getMatrixCluePoint(i,j);
   				if(clue>0)
   				{
   					g.setColor(Color.red);
					g.setFont(new Font("Arial",Font.BOLD,8));
					g.drawString(clue+"",i*JMinerConstants.macroPixel+5,j*JMinerConstants.macroPixel+12);
					
   				}
   			}
   }
	/**
	 * @return Returns the table.
	 */
	public Table getTable() {
		return table;
	}
	/**
	 * @param table The table to set.
	 */
	public void setTable(Table table) {
		this.table = table;
	}
}
