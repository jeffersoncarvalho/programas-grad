/*
 * File Posicao.java
 * Created on 28/09/2004
 * 
 */
package pacman.util;

/**
 * Class Posicao
 * @author Tiago Cordeiro Marques
 */
public class Posicao {
    
    private int x;
    private int y;
    
    public Posicao() {
    }
    
    public Posicao(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
    
    public Posicao(Posicao pos) {
        this();
        this.x = pos.getX();
        this.y = pos.getY();
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void transladar(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    
    public float medirDistancia(Posicao outra) {
        double dx2 = Math.pow((outra.x - this.x), 2);
        double dy2 = Math.pow((outra.y - this.y), 2);
        return (float)Math.sqrt(dx2 + dy2);
    }
    
    public static void main(String []args) {
        Posicao p1 = new Posicao(3,8);
        Posicao p2 = new Posicao(2,1);
        System.out.println(p1.medirDistancia(p2));
    }
}
