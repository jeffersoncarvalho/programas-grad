/*
 * Ambiente.java
 *
 * Created on 24 de Agosto de 2004, 21:32
 */

package ambiente;

import java.util.*;
import java.awt.*;
import javax.swing.*;

import util.Ponto;
import gui.GridPanel;
import agentes.*;

/**
 *
 * @author  knoppix
 */
public class Ambiente extends Hashtable{
    
    private int[][] grid;
    private int numeroPoliciais = 4;//teste
    private int numeroCriminosos;
    private GridPanel gridPanel;
    private int fatorAceleracao=200;
    private int presencaPolicial = 1;
    private int tempoPatrulha = 20;
    private boolean paused = false;
    private Vector vetPoliciais;
    private Criminoso criminoso;
    private int crimesCometidos = 0;
    
    private JLabel labesCrimesCometidos;
    private JTextArea relatorio;
    /** Creates a new instance of Ambiente */
    public Ambiente(int linhas,int colunas, GridPanel target) {
       
        this.grid = new int[linhas][colunas];
	
		this.vetPoliciais = new Vector();       
       
        this.gridPanel = target;
        target.setGrid(grid);
        target.setAmbiente(this);
        
        
    }
    
    public void adicionarPontosNotaveis(PontoNotavel pn)
    {
        this.put(new Integer(pn.getId()), pn);
        this.alimentaGrid(pn);
    }
    
    public Color retornaCorDoPontoNotavel(int id)
    {
        PontoNotavel pn = (PontoNotavel)this.get(new Integer(id));
        return pn.getCor();
    }
    
    private void alimentaGrid(PontoNotavel pn)
    {
        Vector area = pn.getArea();
        for(int i=0;i<area.size();i++)
        {
            Ponto p = (Ponto)area.get(i);
            grid[p.getX()][p.getY()] = pn.getId();
        }
    }
    
    protected void criaPoliciais()
    {
    	for(int i=0; i<this.numeroPoliciais;i++)
    	{
    		Policial policial = new Policial();
                //CONFIGURAR AGENTE INÍCIO
    		this.vetPoliciais.add(policial);
    		policial.setGrid(this.grid); // como ï¿½ o ambiente?
                policial.setAmbiente(this);//este Ambiente
    		policial.gerarPosicaoInicial();//onde eu ficarei.
                policial.distribuiPresenca(policial.getPosicao().getX(),policial.getPosicao().getY(), policial.getPresenca());//meu 'cheiro'
                policial.setTarget(this.gridPanel);//assim, o agente sabe quando apagar o painel
                policial.gerarObjetivo();
                policial.setSleepTime(this.fatorAceleracao);
                policial.setPresenca(this.presencaPolicial);
                //CONFIGURAR AGENTE FIM
              
               //bora trabalhar!
                policial.start();
               
    	}
    }
    
    protected void criaCriminosos()
    {
        criminoso = new Criminoso();
        //CONFIGURAR AGENTE INÍCIO
    	criminoso.setGrid(this.grid); // como ï¿½ o ambiente?
        criminoso.setAmbiente(this);//este Ambiente
    	criminoso.gerarPosicaoInicial();//onde eu ficarei.
        criminoso.setTarget(this.gridPanel);//assim, o agente sabe quando apagar o painel
        criminoso.gerarObjetivo();//onde vou assaltar
        criminoso.setSleepTime(this.fatorAceleracao);
        criminoso.setRepeticoes(this.numeroCriminosos);
        criminoso.setRelatorio(this.relatorio);
        //CONFIGURAR AGENTE FIM
        
        //bora trabalhar!
        criminoso.start();
    }
    
    public void matarAplicação()
    {
    	for(int i=0;i<this.vetPoliciais.size();i++)
    	{
    		Object o = this.vetPoliciais.get(i);
    		Agente a = (Agente)o;
    		a.setStoped(true);
    		o = null;
    	} 
    	this.vetPoliciais = null;
    	this.criminoso.setStoped(true);
    	this.criminoso = null;
    	this.grid = null;
    	this.gridPanel = null;
    	this.labesCrimesCometidos = null;
    	this.relatorio = null;
    	
    }
    
    public void iniciarSimulacao()
    {
        this.criaPoliciais();
        try{Thread.sleep(400);}catch(java.lang.InterruptedException ie){}
        this.criaCriminosos();
    }
    	
	public void setGrid(int[][] grid) {
		this.grid = grid; 
	}

	public void setNumeroPoliciais(int numeroPoliciais) {
		this.numeroPoliciais = numeroPoliciais; 
	}

	public void setNumeroCriminosos(int numeroCriminosos) {
		this.numeroCriminosos = numeroCriminosos; 
	}

	public void setGridPanel(GridPanel gridPanel) {
		this.gridPanel = gridPanel; 
	}

	public void setVetPoliciais(Vector vetPoliciais) {
		this.vetPoliciais = vetPoliciais; 
	}

	public void setCriminoso(Criminoso criminoso) {
		this.criminoso = criminoso;
	}

	public int[][] getGrid() {
		return (this.grid); 
	}

	public int getNumeroPoliciais() {
		return (this.numeroPoliciais); 
	}

	public int getNumeroCriminosos() {
		return (this.numeroCriminosos); 
	}

	public GridPanel getGridPanel() {
		return (this.gridPanel); 
	}

	public Vector getVetPoliciais() {
		return (this.vetPoliciais); 
	}
	

	public Criminoso getCriminoso() {
		return (this.criminoso); 
	}
        
        public synchronized  void feedGrid(int i, int j, int valor )
        {
           if(this.grid!=null)
            this.grid[i][j] = valor;
        }
        
        public void pause()
        {
            this.paused = true;
            if(vetPoliciais!=null && criminoso !=null)
            {
                criminoso.pause();
                for(int i=0;i<vetPoliciais.size();i++)
                {
                    Policial pol= (Policial)vetPoliciais.get(i);
                    pol.pause();
                }
                
            }
        }
        
        public void unPause()
        {
            if(vetPoliciais!=null && criminoso !=null)
            {
                if(paused)
                {
                   paused = false;
                   
                   criminoso.unpause();

                   for(int i=0;i<vetPoliciais.size();i++)
                   {
                        Policial pol= (Policial)vetPoliciais.get(i);
                        pol.unpause();

                   }
                }
            }//if
        }
        
        /**
         * Getter for property fatorAcelaracao.
         * @return Value of property fatorAcelaracao.
         */
        public int getFatorAceleracao() {
            return fatorAceleracao;
        }
        
        /**
         * Setter for property fatorAcelaracao.
         * @param fatorAcelaracao New value of property fatorAcelaracao.
         */
        public void setFatorAcelaracao(int fatorAceleracao) {
            this.fatorAceleracao = fatorAceleracao;
        }
        
        /**
         * Getter for property presencaPolicial.
         * @return Value of property presencaPolicial.
         */
        public int getPresencaPolicial() {
            return presencaPolicial;
        }
        
        /**
         * Setter for property presencaPolicial.
         * @param presencaPolicial New value of property presencaPolicial.
         */
        public void setPresencaPolicial(int presencaPolicial) {
            this.presencaPolicial = presencaPolicial;
        }
        
        /**
         * Getter for property tempoPatrulha.
         * @return Value of property tempoPatrulha.
         */
        public int getTempoPatrulha() {
            return tempoPatrulha;
        }
        
        /**
         * Setter for property tempoPatrulha.
         * @param tempoPatrulha New value of property tempoPatrulha.
         */
        public void setTempoPatrulha(int tempoPatrulha) {
            this.tempoPatrulha = tempoPatrulha;
        }

	
	public void setFatorAceleracao(int fatorAceleracao) {
		this.fatorAceleracao = fatorAceleracao; 
	}

	public void setPaused(boolean paused) {
		this.paused = paused; 
	}

	public void setCrimesCometidos(int crimesCometidos) {
		this.crimesCometidos = crimesCometidos; 
		this.getLabesCrimesCometidos().setText("Crimes Cometidos: " + crimesCometidos + " de " + this.getNumeroCriminosos());
	}

	public boolean getPaused() {
		return (this.paused); 
	}

	public int getCrimesCometidos() {
		return (this.crimesCometidos); 
	}

	
	public void setLabesCrimesCometidos(JLabel labesCrimesCometidos) {
		this.labesCrimesCometidos = labesCrimesCometidos; 
	}

	public JLabel getLabesCrimesCometidos() {
		return (this.labesCrimesCometidos); 
	}
        
        /** Getter for property relatorio.
         * @return Value of property relatorio.
         *
         */
        public javax.swing.JTextArea getRelatorio() {
            return relatorio;
        }
        
        /** Setter for property relatorio.
         * @param relatorio New value of property relatorio.
         *
         */
        public void setRelatorio(javax.swing.JTextArea relatorio) {
            this.relatorio = relatorio;
        }
        
}
