/*
 * GridPanel.java
 *
 * Created on 24 de Agosto de 2004, 21:21
 */

package gui;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import ambiente.Ambiente;
import agentes.*;
import util.Ponto;
import ambiente.PontoNotavel;



/**
 *
 * @author  knoppix
 */
public class GridPanel extends JPanel{
    
    private int[][] grid;
    private int height,width;
    private Ambiente ambiente;
    private Vector areaDeCrime;
    private Vector policiais;
    private Criminoso criminoso;
    private Ponto obj;
    
    /** Creates a new instance of GridPanel */
    public GridPanel(int h,int w) {
    	
        this.height = h;
        this.width = w;
        this.setPreferredSize(new Dimension(h*20,w*20));
        this.setBackground(Color.WHITE);
		repaint();
    }
    
    //***********PAINT************
    public void paint(Graphics g)
    {
        super.paint(g);
        
        //teste
        /*if(this.obj!=null)
        {
        	 g.setColor(Color.MAGENTA);
             g.fillRect(obj.getX()*20, obj.getY()*20, 20, 20);
    	}*/
        //teste
        
        this.desenhaGrade(g);
        if(ambiente!=null)
        {
            this.desenhaPoliciaisUsandoObjetos(g);
            this.desenhaCriminosoUsandoObjetos(g);
            this.desenhaCidade(g);
            this.desenhaAreaDeCrime(g);
            this.desenhaAquecimento(g);
        }
       
        
    }
    //***********PAINT************
    
    private void desenhaCidade(Graphics g)
    {
        
        
        if(grid !=null )
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++)
            {
               
                //desenhando os pontos not�veis
                if(grid[i][j]>=20 && ambiente!=null)
                {
                    g.setColor(ambiente.retornaCorDoPontoNotavel(grid[i][j]));
                    g.fill3DRect(i*20, j*20, 20,20, true);
                }               
                //desenhando os policiais e sua presenca em c�rculo
               /*else if(grid[i][j] >  AgentesContantes.POLICIAL &&  grid[i][j] <  2*AgentesContantes.POLICIAL)    
                {
                    //g.drawRect(i*20, j*20, 20, 20);
                    this.desenhaPoliciais(g, i, j, grid[i][j] - 10);
                }*/
                //desenhado o pilantra
                /*else if(grid[i][j] == AgentesContantes.CRIMINOSO)
                {
                    this.desenhaCriminoso(g,i,j);
                    
                }*/
               
                //teste
                /*else if(grid[i][j] ==  AgentesContantes.PRESENCA)    
                {
                    //g.setColor(Color.MAGENTA);
                    //g.fillRect(i*20, j*20, 20, 20);
                }*/
                //teste
               
            }//for
        
            
       
    }
    
    private void desenhaAreaDeCrime(Graphics g)
    {
       if(this.areaDeCrime!=null)
        for(int z=0; z<this.areaDeCrime.size(); z++)
        {
            Ponto p = (Ponto)this.areaDeCrime.get(z);
            g.setColor(Color.RED);
            g.drawRect(p.getX()*20+1, p.getY()*20+1, 20, 20);
            g.drawRect(p.getX()*20, p.getY()*20, 20, 20);
        }
    }
    
    private void desenhaGrade(Graphics g)
    {
         g.setColor(Color.BLACK);   
        for(int i=1 ; i<this.width ; i++)
        {
             g.drawLine(i*20, 0, i*20, this.height*20);
            
        }
        
        for(int i=1 ; i<this.height ; i++)
        {
            g.drawLine(0, i*20, this.width*20, i*20);
        }
            
    }
    
    private void desenhaPoliciais(Graphics g, int x, int y, int presenca)
    {
            Graphics2D g2d = (Graphics2D)g;
     	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                                RenderingHints.VALUE_ANTIALIAS_ON);
            
    			g2d.setColor(Color.blue);
                        g2d.drawOval(x*20 -(20 *presenca) ,
                                           y*20 -(20 * presenca) ,
                                           (presenca*2+1)*20,
                                           (presenca*2+1)*20);
    			g2d.fillOval(x*20,y*20,20,20);
    		
    }
    
    private void desenhaCriminoso(Graphics g, int x, int y)
    {
            Graphics2D g2d = (Graphics2D)g;
     	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                                RenderingHints.VALUE_ANTIALIAS_ON);
            
    			g2d.setColor(Color.RED);
    			g2d.fillOval(x*20,y*20,20,20);
    		
    }
    
    private void desenhaAquecimento(Graphics g)
    {
        Enumeration e = this.ambiente.elements();
        while(e.hasMoreElements())
        {
           
            PontoNotavel pn = (PontoNotavel)e.nextElement();
            Ponto p = (Ponto)pn.getArea().get(0);
            g.setColor(Color.RED);
            g.drawString(pn.getAquecimento()+"",p.getX()*20+2, p.getY()*20-1);
        }
    }
    
    private void desenhaPoliciaisUsandoObjetos(Graphics g)
    {
       if(policiais!=null)
        for(int i=0;i<this.policiais.size();i++)
        {
            Policial policial = (Policial)this.policiais.get(i);
            Ponto p = policial.getPosicao();
            this.desenhaPoliciais(g, p.getX(), p.getY(), policial.getPresenca());
        }
    }
    
    private void desenhaCriminosoUsandoObjetos(Graphics g)
    {
        if(criminoso !=null)
        	this.desenhaCriminoso(g,criminoso.getPosicao().getX(),criminoso.getPosicao().getY());
    }
    /**
     * Getter for property grid.
     * @return Value of property grid.
     */
    public int[][] getGrid() {
        return this.grid;
    }
    
    /**
     * Setter for property grid.
     * @param grid New value of property grid.
     */
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
    
    /**
     * Getter for property ambiente.
     * @return Value of property ambiente.
     */
    public ambiente.Ambiente getAmbiente() {
        return ambiente;
    }
    
    /**
     * Setter for property ambiente.
     * @param ambiente New value of property ambiente.
     */
    public void setAmbiente(ambiente.Ambiente ambiente) {
        this.ambiente = ambiente;
        //this.policiais = ambiente.getVetPoliciais();
    }
    
    /**
     * Getter for property areaDeCrime.
     * @return Value of property areaDeCrime.
     */
    public java.util.Vector getAreaDeCrime() {
        return areaDeCrime;
    }
    
    /**
     * Setter for property areaDeCrime.
     * @param areaDeCrime New value of property areaDeCrime.
     */
    public void setAreaDeCrime(java.util.Vector areaDeCrime) {
        this.areaDeCrime = areaDeCrime;
    }
    
    /**
     * Getter for property policiais.
     * @return Value of property policiais.
     */
    public java.util.Vector getPoliciais() {
        return policiais;
    }
    
    /**
     * Setter for property policiais.
     * @param policiais New value of property policiais.
     */
    public void setPoliciais(java.util.Vector policiais) {
        this.policiais = policiais;
    }
    
    /**
     * Getter for property criminoso.
     * @return Value of property criminoso.
     */
    public agentes.Criminoso getCriminoso() {
        return criminoso;
    }
    
    /**
     * Setter for property criminoso.
     * @param criminoso New value of property criminoso.
     */
    public void setCriminoso(agentes.Criminoso criminoso) {
        this.criminoso = criminoso;
    }

	
	

	public void setObj(Ponto obj) {
		this.obj = obj; 
	}

	

	public Ponto getObj() {
		return (this.obj); 
	}

	
}
