/*
 * Agente.java
 *
 * Created on 24 de Agosto de 2004, 21:23
 */

package agentes;

import util.Ponto;
import util.VetorDeDistancias;
import gui.GridPanel;
import ambiente.Ambiente;
import util.Distancia;


import java.util.Vector;

/**
 *
 * @author  knoppix
 */
public abstract class Agente extends Thread{
    
    private int sleepTime=200;
    private Ponto posicao;
    protected int[][] grid;
    protected GridPanel target;
    private Ponto objetivo;
    protected Vector passosAnteriores;
    protected Ponto proximoPasso;
    protected Distancia d1,d2,d3,d4,d5,d6,d7,d8;
    protected Ambiente ambiente;
    protected VetorDeDistancias ds;
    protected boolean paused;
    protected boolean stoped;
    
    /** Creates a new instance of Agente */
    public Agente() {
        this.setPriority(Thread.MAX_PRIORITY);
        
        //todas as oito posições possíveis
        d1 = new Distancia(1,0);
        d2 = new Distancia(2,0);
        d3 = new Distancia(3,0);
        d4 = new Distancia(4,0);
        d5 = new Distancia(5,0);
        d6 = new Distancia(6,0);
        d7 = new Distancia(7,0);
        d8 = new Distancia(8,0);
        
        ds = new VetorDeDistancias();
        this.passosAnteriores = new Vector();   
        this.proximoPasso = new Ponto(0,0);
    }
    
    protected void alimentarPassosAnteriores()
    {
      
        this.passosAnteriores.add(0,new Ponto(this.posicao.getX(),this.posicao.getY()));
		/*if(this.passosAnteriores.size()==25)
		  this.passosAnteriores.removeElementAt(this.passosAnteriores.size()-1);  */
    }
    
    protected void zerarPassosAnteriores()
    {

        this.passosAnteriores.clear();
    }
    
    protected boolean isProximoPassoInPassosAnteriores()
    {
        return (this.passosAnteriores.contains(this.proximoPasso));
    }
    
    protected Ponto geraPonto()
    {
        int x;
    	int y;
    	
    	if(grid != null)
    	{
    	  do{
    		x = (int)(Math.random()*grid.length);
    		y = (int)(Math.random()*grid[0].length);
    	  }while(grid[x][y]!=0);
      
          
          return new Ponto(x,y);
        }
        
        return null;
    }
    public void gerarPosicaoInicial()
    {
    	
           if(grid !=null){
    		this.posicao = this.geraPonto(); 
                this.alimentarPassosAnteriores();
           }
    	
    	
    }

        
   protected double calculaDistancia(int x1, int y1, int x2, int y2)
   {
            return Math.sqrt(  ( x1- x2 )*( x1-x2 ) + (y1-y2)*(y1-y2) );
   }
        
   public boolean alcancouObjetivo()
   {
            return this.getPosicao().equals(this.getObjetivo());
   }
   
        
        /**
     * Getter for property posicao.
     * @return Value of property posicao.
     */
    public util.Ponto getPosicao() {
        return posicao;
    }
    
    /**
     * Setter for property posicao.
     * @param posicao New value of property posicao.
     */
    public void setPosicao(util.Ponto posicao) {
        this.posicao = posicao;
    }
    
    /**
     * Getter for property sleepTime.
     * @return Value of property sleepTime.
     */
    public int getSleepTime() {
        return sleepTime;
    }
    
    /**
     * Setter for property sleepTime.
     * @param sleepTime New value of property sleepTime.
     */
    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }
	
	public void setGrid(int[][] grid) {
		this.grid = grid; 
	}

	public int[][] getGrid() {
		return (this.grid); 
	}
    
        /**
         * Getter for property taget.
         * @return Value of property taget.
         */
        public gui.GridPanel getTarget() {
            return target;
        }
        
        /**
         * Setter for property taget.
         * @param taget New value of property taget.
         */
        public void setTarget(gui.GridPanel target) {
            this.target = target;
        }
        
        /**
         * Getter for property objetivo.
         * @return Value of property objetivo.
         */
        public util.Ponto getObjetivo() {
            return objetivo;
        }
        
        /**
         * Setter for property objetivo.
         * @param objetivo New value of property objetivo.
         */
        public void setObjetivo(util.Ponto objetivo) {
            this.objetivo = objetivo;
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
        }
        
        public abstract void iniciar();
        
        public void pause()
        {
        	this.paused = true;
        }
        
        public void unpause()
        {
        	this.paused = false;
                //this.iniciar();
        	
        }

	
	public void setPassosAnteriores(Vector passosAnteriores) {
		this.passosAnteriores = passosAnteriores; 
	}

	public void setProximoPasso(Ponto proximoPasso) {
		this.proximoPasso = proximoPasso; 
	}

	public void setD1(Distancia d1) {
		this.d1 = d1; 
	}

	public void setD2(Distancia d2) {
		this.d2 = d2; 
	}

	public void setD3(Distancia d3) {
		this.d3 = d3; 
	}

	public void setD4(Distancia d4) {
		this.d4 = d4; 
	}

	public void setD5(Distancia d5) {
		this.d5 = d5; 
	}

	public void setD6(Distancia d6) {
		this.d6 = d6; 
	}

	public void setD7(Distancia d7) {
		this.d7 = d7; 
	}

	public void setD8(Distancia d8) {
		this.d8 = d8; 
	}

	public void setDs(VetorDeDistancias ds) {
		this.ds = ds; 
	}

	public void setPaused(boolean paused) {
		this.paused = paused; 
	}

	public void setStoped(boolean stoped) {
		this.stoped = stoped; 
	}

	public Vector getPassosAnteriores() {
		return (this.passosAnteriores); 
	}

	public Ponto getProximoPasso() {
		return (this.proximoPasso); 
	}

	public Distancia getD1() {
		return (this.d1); 
	}

	public Distancia getD2() {
		return (this.d2); 
	}

	public Distancia getD3() {
		return (this.d3); 
	}

	public Distancia getD4() {
		return (this.d4); 
	}

	public Distancia getD5() {
		return (this.d5); 
	}

	public Distancia getD6() {
		return (this.d6); 
	}

	public Distancia getD7() {
		return (this.d7); 
	}

	public Distancia getD8() {
		return (this.d8); 
	}

	public VetorDeDistancias getDs() {
		return (this.ds); 
	}

	public boolean getPaused() {
		return (this.paused); 
	}

	public boolean getStoped() {
		return (this.stoped); 
	}
        
        
}
