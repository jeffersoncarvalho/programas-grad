/*
 * PontoNotavel.java
 *
 * Created on 24 de Agosto de 2004, 21:32
 */

package ambiente;

import java.util.Vector;
import util.Ponto;
import java.awt.*;
/**
 *
 * @author  knoppix
 */
public class PontoNotavel {
    
    private Color cor;
    private int aquecimento;
    private int tipo; //farmácia,banco,...
    private Vector area; //pontos ocupados no mapa
    private int id; //identificação para cada ponto notável.
    /** Creates a new instance of PontoNotavel */
    public PontoNotavel() {
         this.area = new Vector();
         this.id = (int)(Math.random()*1000)+ (int)(Math.random()*1000)+ (int)(Math.random()*1000) +20;
        // System.out.println(id);
    }
    
    public void decrementaAquecimento()
    {
    	if(this.aquecimento>0)
    		this.aquecimento--;
    }
    
    /**
   * Adciona um ponto que faz parte do todo.
   */
    public void adicionarPonto(Ponto p)
    {
        this.area.add(p);
    }
    
    /**
     * Getter for property aquecimento.
     * @return Value of property aquecimento.
     */
    public int getAquecimento() {
        return aquecimento;
    }
    
    /**
     * Setter for property aquecimento.
     * @param aquecimento New value of property aquecimento.
     */
    public void setAquecimento(int aquecimento) {
        this.aquecimento = aquecimento;
    }
    
    /**
     * Getter for property area.
     * @return Value of property area.
     */
    public Vector getArea() {
        return area;
    }
    
    /**
     * Setter for property area.
     * @param area New value of property area.
     */
    public void setArea(Vector area) {
        this.area = area;
    }
    
    /**
     * Getter for property cor.
     * @return Value of property cor.
     */
    public java.awt.Color getCor() {
        return cor;
    }    
    
    /**
     * Setter for property cor.
     * @param cor New value of property cor.
     */
    public void setCor(java.awt.Color cor) {
        this.cor = cor;
    }
    
    /**
     * Getter for property tipo.
     * @return Value of property tipo.
     */
    public int getTipo() {
        return tipo;
    }
    
    /**
     * Setter for property tipo.
     * @param tipo New value of property tipo.
     */
    public void setTipo(int tipo) {
        switch(tipo)
        {
            case AmbienteConstantes.BANCO:
                this.setCor(Color.orange);
            break;
            case AmbienteConstantes.FARMACIA:
                this.setCor(Color.LIGHT_GRAY);
            break;
            case AmbienteConstantes.OBSTACULO:
                this.setCor(Color.BLACK);
            break;
            case AmbienteConstantes.SUPERMERCADO:
                this.setCor(Color.GREEN);
            break;
        }
        this.tipo = tipo;
    }
    
    /**
     * Getter for property id.
     * @return Value of property id.
     */
    public int getId() {
        return id;
    }
    
    
    
}
