/*
 * VetorDeDistancias.java
 *
 * Created on 1 de Setembro de 2004, 13:56
 */

package util;

import java.util.Vector;
/**
 *
 * @author  jefferson
 */
public class VetorDeDistancias extends Vector{
    
    /** Creates a new instance of VetorDeDistancias */
    public VetorDeDistancias() {
    }
    
    public void adicionarDistancia(Distancia d)
    {
        
          for(int i=0;i<this.size();i++)
          {
              if(((Distancia)this.get(i)).valor >= d.valor)
              {
                  this.add(i,d);
                  return;
              }
          }
          this.add(d);
        
    }
    
    /*public static void main(String args[])
    {
        
        Distancia d1 = new Distancia(1,2.5);
        Distancia d2 = new Distancia(1,0.2);
        Distancia d3 = new Distancia(1,3);
        Distancia d4 = new Distancia(1,1.5);
        Distancia d5 = new Distancia(1,2.5);
        
        VetorDeDistancias vd = new VetorDeDistancias();
        vd.adicionarDistancia(d1);
        vd.adicionarDistancia(d2);
        vd.adicionarDistancia(d3);
        vd.adicionarDistancia(d4);
        vd.adicionarDistancia(d5);
        
        for(int i=0;i<vd.size();i++)
            System.out.println(((Distancia)vd.get(i)).valor );
    }*/
    
    
    
}
