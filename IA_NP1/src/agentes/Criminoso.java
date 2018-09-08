/*
 * Criminoso.java
 *
 * Created on 24 de Agosto de 2004, 21:27
 */

package agentes;

import javax.swing.*;
import java.util.*;


import util.Ponto;
import agentes.AgentesContantes;
import ambiente.PontoNotavel;
import ambiente.AmbienteConstantes;
import util.Distancia;

/**
 *
 * @author  knoppix
 */
public class Criminoso extends Agente {
    
    
    private PontoNotavel alvoDeAssalto;
    private Vector areaDeAssalto;
    private Ponto[] pontosCandidadatos; //a objetivo
    private int repeticoes = 1;
    private int inicioRepeticoes = 0;
    private ObjetivoQuente objetivoQuente;
    private boolean assaltou = false;
    
    private int tempoDeAssalto = 0;
    private int qtdAfastamentos = 0;
    private JTextArea relatorio;
    
    /** Creates a new instance of Criminoso */
    public Criminoso() {
        objetivoQuente = new ObjetivoQuente();
        pontosCandidadatos = new Ponto[8];
        for(int i=0;i<this.pontosCandidadatos.length;i++)
            this.pontosCandidadatos[i] = new Ponto(0,0);
    }
    
    /**
     * Lógica do Criminoso, inserida no método run()
     */
    public void iniciar() {
        int i;
        int tempoDeAssaltoTotal = 0;
        for(i=inicioRepeticoes;(i<this.repeticoes) && (!this.getStoped());i++)
        {
           
            
            while(!this.assaltou && (!this.getStoped()))
            {
                while(paused)
                {
                    try{Thread.sleep(1000);}catch(java.lang.InterruptedException ie){}
                }
                
                this.target.repaint();
                this.andaAoObjetivo();
                try{Thread.sleep(this.getSleepTime());}catch(java.lang.InterruptedException ie){}
            }
            
            if(this.getStoped())
            	return;
                
                //assaltou
                //atualiza o relatorio	   
                this.relatorio.append("Tempo de Assalto - Criminoso "+ (i+1) + ": " + this.tempoDeAssalto + "\n");
                tempoDeAssaltoTotal += this.tempoDeAssalto; 
                this.tempoDeAssalto = 0;
                //atualiza ambiente
                this.getAmbiente().setCrimesCometidos(this.getAmbiente().getCrimesCometidos() + 1);
                this.alvoDeAssalto.setAquecimento(this.alvoDeAssalto.getAquecimento()+1);
                this.ambiente.feedGrid(this.getPosicao().getX(),this.getPosicao().getY(), AmbienteConstantes.VAZIO);
                this.target.repaint();

                //atualizando o objetivo mais quente
                if(this.alvoDeAssalto.getAquecimento() > this.objetivoQuente.aquecimento)
                {
                    this.objetivoQuente.ID = this.alvoDeAssalto.getId();
                    this.objetivoQuente.aquecimento = this.alvoDeAssalto.getAquecimento();
                    //System.out.println("HOTNESS: " + this.objetivoQuente.aquecimento);
                }

                //reiniciar criminoso
                this.assaltou = false;
                this.zerarPassosAnteriores();
                this.gerarPosicaoInicial();
                this.gerarObjetivo();
                try{Thread.sleep(400);}catch(java.lang.InterruptedException ie){}
            
        }
        
        this.relatorio.append("\nTempo de Assalto Médio: " + tempoDeAssaltoTotal/i);
        this.relatorio.append("\nQuantidade de Inibições: " + this.qtdAfastamentos+"\n\n");
        inicioRepeticoes = i;
    } 
    
    /**
     * Roda o Criminoso
     */
    public void run()
    {
        iniciar();
    }
   
    protected void gerarObjetivoAleatorio()
    {
        Set keys = this.getAmbiente().keySet();
        Object[] keysArray = keys.toArray();
        
        //gerando um índice aleatório
        int keyEscolhido = (int)(Math.random()*keysArray.length);
        //pegando a chave
        Integer keyID = (Integer)keysArray[keyEscolhido];
        //pegando o ponto notável da Hash
        this.alvoDeAssalto = (PontoNotavel)this.getAmbiente().get(keyID);
        this.getTarget().setAreaDeCrime(this.alvoDeAssalto.getArea());
        this.areaDeAssalto = this.alvoDeAssalto.getArea();
        this.setObjetivo((Ponto)this.areaDeAssalto.get(0));
        //System.out.println("Escolhido: " + this.alvoDeAssalto.getId());
    }
    
    protected void gerarObjetivoQuente()
    {
       this.alvoDeAssalto = (PontoNotavel)this.getAmbiente().get(new Integer(this.objetivoQuente.ID));
       this.getTarget().setAreaDeCrime(this.alvoDeAssalto.getArea());
       this.areaDeAssalto = this.alvoDeAssalto.getArea();
       this.setObjetivo((Ponto)this.areaDeAssalto.get(0));
    }
    
    
    public  boolean efetuouAssalto()
    {
        int meuX = this.getPosicao().getX();
        int meuY = this.getPosicao().getY();
        
        this.pontosCandidadatos[0].setX(meuX+1);
        this.pontosCandidadatos[0].setY(meuY);
        
        this.pontosCandidadatos[1].setX(meuX);
        this.pontosCandidadatos[1].setY(meuY+1);
        
        this.pontosCandidadatos[2].setX(meuX-1);
        this.pontosCandidadatos[2].setY(meuY);
        
        this.pontosCandidadatos[3].setX(meuX);
        this.pontosCandidadatos[3].setY(meuY-1);
        
        this.pontosCandidadatos[4].setX(meuX+1);
        this.pontosCandidadatos[4].setY(meuY+1);
        
        this.pontosCandidadatos[5].setX(meuX+1);
        this.pontosCandidadatos[5].setY(meuY-1);
        
        this.pontosCandidadatos[6].setX(meuX-1);
        this.pontosCandidadatos[6].setY(meuY+1);
        
        this.pontosCandidadatos[7].setX(meuX-1);
        this.pontosCandidadatos[7].setY(meuY-1);
        
        
        for(int i=0;i<this.pontosCandidadatos.length;i++)
            if(this.areaDeAssalto.contains(this.pontosCandidadatos[i]))
            {
            	this.assaltou = true;
				return true;
            }
                
        
        return false;
    }
    
    public void gerarObjetivo()
    {
    	if(grid == null)
    	 return;
        //this.gerarObjetivoAleatorio();
    	int num = (int)(Math.random()*2);
    	if(num==1)	
        	this.gerarObjetivoAleatorio();
        else
        {
        	if(this.objetivoQuente.aquecimento==-1)
        		this.gerarObjetivoAleatorio();
        	else
        		this.gerarObjetivoQuente();
        }   
    }
    
    protected void calculaDistancias(Ponto p)
    {
       
       int meuX = p.getX();
       int meuY = p.getY();
       
       int objX = this.getObjetivo().getX();
       int objY = this.getObjetivo().getY();
       
       //definindo as distï¿½ncias
       double distancia1 = -1; // dir
       double distancia2 = -1; // baixo
       double distancia3 = -1; // esq
       double distancia4 = -1; // cima
       
       double distancia5 = -1; //  ++
       double distancia6 = -1; //  +-
       double distancia7 = -1; //  -+
       double distancia8 = -1; //  --
       
      
                //prï¿½ximo passo2 dir
               if((meuX+1 < grid.length) && (grid[meuX+1][meuY]==0 || grid[meuX+1][meuY] == AgentesContantes.PRESENCA))
                   distancia1 = this.calculaDistancia(meuX+1, meuY, objX, objY);

               //prï¿½ximo passo2 baixo

               if((meuY+1 < grid[0].length) && (grid[meuX][meuY+1]==0 || grid[meuX][meuY+1] == AgentesContantes.PRESENCA))
                   distancia2 = this.calculaDistancia(meuX, meuY+1, objX, objY);
               
               //prï¿½ximo passo4 cima
       
               if((meuY-1 >= 0) && (grid[meuX][meuY-1]==0 || grid[meuX][meuY-1] == AgentesContantes.PRESENCA))
                   distancia4 = this.calculaDistancia(meuX, meuY-1, objX, objY);
       
               //prï¿½ximo passo3 esq

               if((meuX-1 >= 0) && (grid[meuX-1][meuY]==0 || grid[meuX-1][meuY] == AgentesContantes.PRESENCA))
                   distancia3 = this.calculaDistancia(meuX-1, meuY, objX, objY);

               //+-
               if((meuX+1 < grid.length && meuY-1 >= 0) && (grid[meuX+1][meuY-1]==0 || grid[meuX+1][meuY-1] == AgentesContantes.PRESENCA))
                   distancia6 = this.calculaDistancia(meuX+1, meuY-1, objX, objY);

               //++
               if((meuX+1 < grid.length && meuY+1 < grid[0].length) && (grid[meuX+1][meuY+1]==0 || grid[meuX+1][meuY+1] == AgentesContantes.PRESENCA))
                   distancia5 = this.calculaDistancia(meuX+1, meuY+1, objX, objY);
                  
               //-+
               if((meuX-1 >= 0 && meuY+1 < grid[0].length) && (grid[meuX-1][meuY+1]==0 || grid[meuX-1][meuY+1] == AgentesContantes.PRESENCA))
                   distancia7 = this.calculaDistancia(meuX-1, meuY+1, objX, objY);
                  
               //--
               if((meuX-1 >= 0 && meuY-1 >=0) && (grid[meuX-1][meuY-1]==0 || grid[meuX-1][meuY-1] == AgentesContantes.PRESENCA))
                   distancia8 = this.calculaDistancia(meuX-1, meuY-1, objX, objY);
       
       //coloco no array
      ds.clear();
      if(distancia1>=0)
      {
          d1.valor = distancia1;
          d1.setId(1);
          ds.adicionarDistancia(d1);
      }
      if(distancia2>=0)
      {
          d2.valor = distancia2;
          d2.setId(2);
          ds.adicionarDistancia(d2);
      }
      if(distancia3>=0)
      {
          d3.valor = distancia3;
          d3.setId(3);
          ds.adicionarDistancia(d3);
      }   
      if(distancia4>=0)
      {
         d4.valor = distancia4;
         d4.setId(4);
         ds.adicionarDistancia(d4);
      }
      if(distancia5>=0)
      {
          d5.valor = distancia5;
          d5.setId(5);
          ds.adicionarDistancia(d5);
      }
      if(distancia6>=0)
      {
          d6.valor = distancia6;
          d6.setId(6);
          ds.adicionarDistancia(d6);
      }
      if(distancia7>=0)
      {
          d7.valor = distancia7;
          d7.setId(7);
          ds.adicionarDistancia(d7);
      }   
      if(distancia8>=0)
      {
         d8.valor = distancia8;
         d8.setId(8);
         ds.adicionarDistancia(d8);
      }
   }
   
   protected void escolheProximoPasso()
   {
        
       //incrementando o tempo de assalto a cada passo.
       this.tempoDeAssalto++; 
         
       int meuX = this.getPosicao().getX();
       int meuY = this.getPosicao().getY();
       if(ds.size()==0)
           return;
 
               
       this.proximoPasso.setX(meuX + ((Distancia)ds.get(0)).x);        
       this.proximoPasso.setY(meuY + ((Distancia)ds.get(0)).y);
       
       
       
       //para não passar por onde o agente já andou.
         
         int i = 0;
         try{
                   while((i<ds.size()-1 && this.isProximoPassoInPassosAnteriores()) || ( grid[meuX + ((Distancia)ds.get(i)).x ][meuY+((Distancia)ds.get(i)).y]==AgentesContantes.PRESENCA) )
                   {
                           if( grid[meuX + ((Distancia)ds.get(i)).x ][meuY+((Distancia)ds.get(i)).y]==AgentesContantes.PRESENCA)
                           {
                           		this.apagarCaudaDePassosAnterirores();
                           		this.qtdAfastamentos++;
                                //sentiu o policial então perde o aquecimento do ponto notável
                                this.alvoDeAssalto.decrementaAquecimento();
                                //this.zerarPassosAnteriores();
                           }


                                    this.proximoPasso.setX(meuX + ((Distancia)ds.get(i+1)).x); 
                                    this.proximoPasso.setY(meuY + ((Distancia)ds.get(i+1)).y);


                           i++;
                   }
          }
          catch(java.lang.ArrayIndexOutOfBoundsException e)
          {
                this.proximoPasso.setX(meuX);        
                this.proximoPasso.setY(meuY);
                return;
          }
      
   }
     
    protected void andaAoObjetivo()
    {
        Ponto p = this.getPosicao();
       
       //apando o passo anterior no grid;
       this.ambiente.feedGrid(p.getX(),p.getY(),AmbienteConstantes.VAZIO);
       
       if(!this.efetuouAssalto())
       {
           //calculando as distancias
           this.calculaDistancias(p);  

          //qual a menor distancia 
           this.escolheProximoPasso();

          //atualizo o passo anterior (alimentação  dos passo anteriores)
           this.alimentarPassosAnteriores();

          //andando de fato.
           p.setX(this.proximoPasso.getX());
           p.setY(this.proximoPasso.getY());

           //atualizando o grid
            this.ambiente.feedGrid(p.getX(),p.getY(),AgentesContantes.CRIMINOSO);
       }
       
      
    }
    
    public void apagarCaudaDePassosAnterirores()
    {
    	if(this.passosAnteriores.size()>9)
    	{
    		for(int i=9;i<this.passosAnteriores.size();i++)
    			this.passosAnteriores.removeElementAt(i);
    	}
    }
    
    /**
     * Getter for property repeticoes.
     * @return Value of property repeticoes.
     */
    public int getRepeticoes() {
        return repeticoes;
    }    
    
    /**
     * Setter for property repeticoes.
     * @param repeticoes New value of property repeticoes.
     */
    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
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

class ObjetivoQuente
{
    public int ID = 0 ;
    public int aquecimento = -1 ;
}
