/*
 * Distancia.java
 *
 * Created on 28 de Agosto de 2004, 13:37
 */

package util;

/**
 *
 * @author  knoppix
 */
public class Distancia implements Comparable{
    
    public int id;
    public double valor;
    public int x,y;
    /** Creates a new instance of Distancia */
    public Distancia(int id, double valor) {
        this.setId(id);
        this.valor = valor;   
    }
    
    public Distancia cloninng()
    {
        return new Distancia(this.id,this.valor);
    }
    
    /**
     * Getter for property id.
     * @return Value of property id.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Setter for property id.
     * @param id New value of property id.
     */
    public void setId(int id) {
        this.id = id;
        switch(id){
               case 1:
                    x = + 1;
                    y = 0;
               break;
               case 2:
                    x=0;
                    y=+1;
               break;
               case 3:
                    x=-1;
                    y=0;
               break;
               case 4:
                   x=0;
                   y=-1;
               break;
               case 5:
                    x = 1;
                    y = 1;
               break;
               case 6:
                    x =  1;
                    y = -1;
               break;
               case 7:
                    x = -1;
                    y = 1;
               break;
               case 8:
                   x = -1;
                   y = -1;
               break;
           }  
    }
    
    public int compareTo(Object o) {
        
        System.out.println("Olá");
        Distancia d = (Distancia)o;
        if(this.valor<d.valor)
            return -1;
        else
            if(this.valor == valor)
                return 0;
        return 1;
    }
    
}
