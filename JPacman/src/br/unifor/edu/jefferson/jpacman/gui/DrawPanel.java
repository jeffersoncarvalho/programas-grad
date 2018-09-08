/*
 * Created on 07/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jpacman.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import br.unifor.edu.jefferson.jpacman.table.Table;
import br.unifor.edu.jefferson.jpacman.util.JPacmanConstants;

/**
 * Onde ser�o plotados todos os jogadores.
 */
public class DrawPanel extends JPanel {

  private Graphics2D g2D;
  private Table tableGame;
  private int tableMatrix[][];
  private ImageIcon pacIcon = new ImageIcon(ClassLoader.getSystemResource("images"+File.separator+"pacman.gif"));
  private ImageIcon ghostIcon = new ImageIcon(ClassLoader.getSystemResource("images"+File.separator+"ghost.gif"));
  //private ImageIcon grapesIcon = new ImageIcon(ClassLoader.getSystemResource("images"+File.separator+"grapes.gif"));
  /**
   * Cria uma nova inst�ncia do DrawPanel
   */
  public DrawPanel(Table tableGame)
  {
    super.setPreferredSize(new Dimension(JPacmanConstants.gameDimension.width*JPacmanConstants.pixelValue,
										 JPacmanConstants.gameDimension.height*JPacmanConstants.pixelValue));
	setBackground(Color.white);  
	this.tableGame = tableGame;
	this.tableMatrix = tableGame.getGameMatrix();
  }
  
  /**
   * Pinta painel.
   */
   public void paint(Graphics g)
   {
     	  super.paint(g);
     	  
     	  //gradear
     	  //this.buildGrid(g);
     	  
     	  //Anti Aliasing 
     	  Graphics2D g2d = (Graphics2D)g;
     	  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON);
                               
          //wall
          this.drawMatrix(g2d);
          
		  //pacman
		  this.drawPacman(g2d);
		  
		  //ghosts
		  this.drawGhosts(g2d);
          
   }

	/**
	 * Efeito de gradeamento no painel
	 * @param g
	 */
   private void buildGrid(Graphics g)
   {
 
			g.setColor(Color.black);
			for(int i = 0; i<JPacmanConstants.gameDimension.width; i++)
			 for(int j = 0; j<JPacmanConstants.gameDimension.height; j++)
			   g.drawRect(i*JPacmanConstants.pixelValue,
						  j*JPacmanConstants.pixelValue,
						  JPacmanConstants.pixelValue,
						  JPacmanConstants.pixelValue); 
   }
   
   /**
    * Desenha pacman..
    * @param g
    */
   public void drawPacman(Graphics2D g)
   {
		 
   		int x = this.tableGame.getPacman().getX()*JPacmanConstants.pixelJump;//(int)this.tableGame.getPacman().getPosition().getX();
		int y = this.tableGame.getPacman().getY()*JPacmanConstants.pixelJump;//(int)this.tableGame.getPacman().getPosition().getY();
		
		 
		
		int direction = this.tableGame.getPacman().getDirection();
		double angle = 0;
		 
        
		switch (direction) {
		case JPacmanConstants.UP: {
			angle = -Math.PI/2;
			break;
		}
		case JPacmanConstants.DOWN: {
			angle = Math.PI/2;
			break;
		}
		case JPacmanConstants.RIGHT: {
			 
			break;
		}
		case JPacmanConstants.LEFT: {
			angle = Math.PI;
			break;
		}}

		AffineTransform antiga = g.getTransform();
		int largura = this.pacIcon.getIconWidth();
		int altura = this.pacIcon.getIconHeight();
		AffineTransform nova = AffineTransform.getTranslateInstance(
				x+largura/2, y+altura/2);
				
		nova.rotate(angle);
		
		nova.translate(-(x+largura/2), -(y+altura/2));
		g.setTransform(nova);
		this.pacIcon.paintIcon(null, g, x, y);
		 
		g.setTransform(antiga);

   		 
   }
   
   /**
    * 
    * @param g
    */
   public void drawMatrix(Graphics2D g)
   {
   		for(int i=0;i<this.tableMatrix.length;i++)
		  for(int j=0;j<this.tableMatrix.length;j++)
		  	if(this.tableMatrix[i][j]==JPacmanConstants.WALL)
		  	{
		  		g.setColor(Color.blue);
				g.fillRect(j*JPacmanConstants.pixelValue,i*JPacmanConstants.pixelValue,JPacmanConstants.pixelValue,JPacmanConstants.pixelValue);
				//this.wallIcon.paintIcon(null, g, j*JPacmanConstants.pixelValue, i*JPacmanConstants.pixelValue);
		  	}else if(this.tableMatrix[i][j]==JPacmanConstants.FOOD)
			{
					g.setColor(Color.yellow);
					g.fillOval(j*JPacmanConstants.pixelValue +7,i*JPacmanConstants.pixelValue +7,JPacmanConstants.pixelValue/2,JPacmanConstants.pixelValue/2);
					//this.grapesIcon.paintIcon(null, g, j*JPacmanConstants.pixelValue, i*JPacmanConstants.pixelValue);
			}
		  	
   }
   
   public void drawGhosts(Graphics2D g)
   {
			int x = (int)this.tableGame.testGhost.getPosition().getX();
			int y = (int)this.tableGame.testGhost.getPosition().getY();
			g.setColor(Color.black);
			this.ghostIcon.paintIcon(null, g, x, y);
			//g.fillOval(x ,y  ,JPacmanConstants.pixelValue ,JPacmanConstants.pixelValue );
   }
   
   

}
