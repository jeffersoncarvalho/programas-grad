/*
 * File Componente.java
 * Created on 30/09/2004
 * 
 */
package pacman.componentes;

import javax.swing.ImageIcon;

import pacman.util.Posicao;

/**
 * Class Componente
 * @author Tiago Cordeiro Marques
 */
public abstract class Componente {
    
    protected Posicao posicao;
    protected ImageIcon imagem;
    
    public Componente(ImageIcon img, Posicao pos) {
        this.imagem = img;
        this.posicao = pos;
    }
    
	public ImageIcon getImagem() {
		return imagem;
	}
	
    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
    
	public Posicao getPosicao() {
		return posicao;
	}
	
}
