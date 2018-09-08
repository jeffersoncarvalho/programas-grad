/*
 * Policial.java
 *
 * Created on 24 de Agosto de 2004, 21:27
 */

package agentes;

import ambiente.AmbienteConstantes;
import util.Ponto;
import util.Distancia;


import java.util.Vector;


/**
 *
 * @author  knoppix
 */
public class Policial extends Agente{
    
    private int presenca=2; //máximo de 9 mínimo de 1
    private Vector pontosDePresenca;
    private boolean emPatrulha = false;
    private int tempoDePatrulha = 20;
    private int contaTempoDePatrulha = 1;
    private int paciencia = 200;
    private int contaPaciencia = 1;
    
    /** Creates a new instance of Policial */
    public Policial() {
        pontosDePresenca = new Vector();
    }
    
    public void iniciar()
    {
        
        while(true && !this.getStoped())
    	{
                
            if(!paused)
            {
                //andando até ponto de patrulha..
                if(!this.emPatrulha)
                {
                    // começo s.t.a
                    if(this.contaPaciencia == this.paciencia )
                    {
                        this.contaPaciencia = 1;
                        this.gerarObjetivo();
                        this.zerarPassosAnteriores();
                        System.out.println("Merda!!!");
                        
                    }
                    
                    this.contaPaciencia ++ ;
                    // fim s.t.a
                    
                    this.target.repaint();
                    this.contaTempoDePatrulha = 1;
                    this.apagaPresenca();
                    this.andaAoObjetivo();
                    this.distribuiPresenca(this.getPosicao().getX(), this.getPosicao().getY(), this.getPresenca());
                    
                }
                else//empatrulha: reinicie o policial definindo novos objetivos
                {
                    this.distribuiPresenca(this.getPosicao().getX(), this.getPosicao().getY(), this.getPresenca());
                    this.contaPaciencia = 1;//s.t.a
                    if(this.contaTempoDePatrulha == this.tempoDePatrulha)
                    {
                        this.emPatrulha = false;//sai da patrulha
                        this.gerarObjetivo();//define novo objetivo
                    }
                    
                    this.contaTempoDePatrulha ++;
                    
                }
                    
                try{Thread.sleep(this.getSleepTime());}catch(java.lang.InterruptedException ie){}
            }//paused!
            if(paused)
             try{Thread.sleep(1000);}catch(java.lang.InterruptedException ie){}//sleep do pause
    	}//while
    }
    
    public void run()
    {
        iniciar();
    	
    }
    
    public void distribuiPresenca(int x,int y, int presenca) 
    {
        int inicioX = x - presenca;
        int inicioY = y - presenca;
        int raio = presenca*2+1;
        
        for(int i=inicioX; i<(inicioX + raio);i++)
            for(int j=inicioY; j<(inicioY+raio); j++)
                if( i>=0 && j>=0 && i<grid.length && j<grid[0].length && grid[i][j]==AmbienteConstantes.VAZIO)
                {
                   
                    if(i != x || j != y) // nï¿½o pinta policial
                    {

                        this.ambiente.feedGrid(i,j,AgentesContantes.PRESENCA);
                        this.pontosDePresenca.add(new Ponto(i,j));
                    }
                }
    }
    
    private void apagaPresenca()
    {
        for(int i=0; i<this.pontosDePresenca.size() ; i++)
        {
            Ponto p = (Ponto)pontosDePresenca.get(i);
            //grid[p.getX()][p.getY()] = AmbienteConstantes.VAZIO;
            this.ambiente.feedGrid(p.getX(),p.getY(),AmbienteConstantes.VAZIO);
        }
        
        //limpando o meu cheiro
        this.pontosDePresenca.clear();
    }
    
   public void gerarObjetivo()
   {
       if(grid == null)
    	 return;
       Ponto p = this.geraPonto();
       //System.out.println(p);
       this.setObjetivo(p);
       this.getAmbiente().getGridPanel().setObj(p);
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
               if((meuX+1 < grid.length) && (grid[meuX+1][meuY]==0 || grid[meuX+1][meuY]==AgentesContantes.PRESENCA || (grid[meuX+1][meuY]>10 && grid[meuX+1][meuY]<20) ))
                   distancia1 = this.calculaDistancia(meuX+1, meuY, objX, objY);

               //prï¿½ximo passo2 baixo

               if((meuY+1 < grid[0].length) && (grid[meuX][meuY+1]==0 || grid[meuX][meuY+1] == AgentesContantes.PRESENCA || (grid[meuX][meuY+1]>10 && grid[meuX][meuY+1]<20)))
                   distancia2 = this.calculaDistancia(meuX, meuY+1, objX, objY);
               
               //prï¿½ximo passo4 cima
       
               if((meuY-1 >= 0) && (grid[meuX][meuY-1]==0 || grid[meuX][meuY-1]==AgentesContantes.PRESENCA || (grid[meuX][meuY-1]>10 && grid[meuX][meuY-1]<20) ))
                   distancia4 = this.calculaDistancia(meuX, meuY-1, objX, objY);
       
               //prï¿½ximo passo3 esq

               if((meuX-1 >= 0) && (grid[meuX-1][meuY]==0 || grid[meuX-1][meuY] == AgentesContantes.PRESENCA || (grid[meuX-1][meuY]>10 && grid[meuX-1][meuY]<20) ))
                   distancia3 = this.calculaDistancia(meuX-1, meuY, objX, objY);

               //+-
               if((meuX+1 < grid.length && meuY-1 >= 0) && (grid[meuX+1][meuY-1]==0 || grid[meuX+1][meuY-1]==AgentesContantes.PRESENCA || (grid[meuX+1][meuY-1]>10 && grid[meuX+1][meuY-1]<20) ))
                   distancia6 = this.calculaDistancia(meuX+1, meuY-1, objX, objY);

               //++
               if((meuX+1 < grid.length && meuY+1 < grid[0].length) && (grid[meuX+1][meuY+1]==0 || grid[meuX+1][meuY+1]==AgentesContantes.PRESENCA || (grid[meuX+1][meuY+1]>10 && grid[meuX+1][meuY+1]<20) ))
                   distancia5 = this.calculaDistancia(meuX+1, meuY+1, objX, objY);
                  
               //-+
               if((meuX-1 >= 0 && meuY+1 < grid[0].length) && (grid[meuX-1][meuY+1]==0 || grid[meuX-1][meuY+1]==AgentesContantes.PRESENCA || (grid[meuX-1][meuY+1]>10 && grid[meuX-1][meuY+1]<20) ))
                   distancia7 = this.calculaDistancia(meuX-1, meuY+1, objX, objY);
                  
               //--
               if((meuX-1 >= 0 && meuY-1 >=0) && (grid[meuX-1][meuY-1]==0 || grid[meuX-1][meuY-1]==AgentesContantes.PRESENCA || (grid[meuX-1][meuY-1]>10 && grid[meuX-1][meuY-1]<20) ))
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
      
       int meuX = this.getPosicao().getX();
       int meuY = this.getPosicao().getY();
       
		if(this.ds.size()==0)
		   return;
               
       this.proximoPasso.setX(meuX + ((Distancia)ds.get(0)).x);        
       this.proximoPasso.setY(meuY + ((Distancia)ds.get(0)).y);
       int i = 0;
       while(i<ds.size()-1 && this.isProximoPassoInPassosAnteriores())
       {
               //System.out.println("Igual");
               this.proximoPasso.setX(meuX + ((Distancia)ds.get(i+1)).x); 
               this.proximoPasso.setY(meuY + ((Distancia)ds.get(i+1)).y);
               i++;
       } 
   }
    
   private void andaAoObjetivo()
   {
       Ponto p = this.getPosicao();
       
       //apando o passo anterior no grid;
       this.ambiente.feedGrid(p.getX(),p.getY(),AmbienteConstantes.VAZIO);
       if(!this.emPatrulha){
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
       this.ambiente.feedGrid(p.getX(),p.getY(),this.getPresenca()+AgentesContantes.POLICIAL);
       }
       
       if(this.alcancouObjetivo())
       {
           
           this.emPatrulha = true;
           this.zerarPassosAnteriores();
          
       }
   }
   
 
   /**
     * Getter for property presenca.
     * @return Value of property presenca.
     */
    public int getPresenca() {
        return presenca;
    }
    
    /**
     * Setter for property presenca.
     * @param presenca New value of property presenca.
     */
    public void setPresenca(int presenca) {
        this.presenca = (presenca>0 && presenca < 10)? presenca : 1 ; 
    }
    
   /**
    * Getter for property tempoDePatrulha.
    * @return Value of property tempoDePatrulha.
    */
   public int getTempoDePatrulha() {
       return tempoDePatrulha;
   }
   
   /**
    * Setter for property tempoDePatrulha.
    * @param tempoDePatrulha New value of property tempoDePatrulha.
    */
   public void setTempoDePatrulha(int tempoDePatrulha) {
       this.tempoDePatrulha = tempoDePatrulha;
   }
   
}
