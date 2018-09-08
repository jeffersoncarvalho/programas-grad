/*
 * Ponto.java
 *
 * Created on 24 de Agosto de 2004, 21:24
 */

package util;

/**
 *
 * @author  knoppix
 */
public class Ponto {
    
    private int x;
    private int y;
    /** Creates a new instance of Ponto */
    public Ponto(int x, int y) {
        this.setX(x);
        this.setY(y);
    }
    
    /**
     * Getter for property x.
     * @return Value of property x.
     */
    public int getX() {
        return x;
    }
    
    /**
     * Setter for property x.
     * @param x New value of property x.
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Getter for property y.
     * @return Value of property y.
     */
    public int getY() {
        return y;
    }
    
    /**
     * Setter for property y.
     * @param y New value of property y.
     */
    public void setY(int y) {
        this.y = y;
    }
    
    public boolean equals(Object p)
    {
        
        if(this.x == ((Ponto)p).getX() && this.y == ((Ponto)p).getY() )
            return true;
        return false;
    }
    
    public String toString() {
        String retValue;
        retValue = "X: "+this.getX() + " Y: "+this.getY();
        return retValue;
    }
    
}
