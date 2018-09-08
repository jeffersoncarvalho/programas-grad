/*
 * Created on 30/09/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pacman.componentes;

import javax.swing.ImageIcon;

import pacman.util.Posicao;

/**
 * @author Lbd18-22
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Pacman extends ComponenteMovel {
	
	public static ImageIcon IMAGEM = 
	    new ImageIcon(ClassLoader.getSystemResource("pacman/imagens/pacman.gif"));
	private boolean imortal;
	private String cor; 
	private int qtdVidas;
	
	public Pacman() {
		super(IMAGEM, new Posicao(0,0));
		imortal = false;
	}
	
	public Pacman(Posicao pos, int dir) {
		this();
		this.posicao = pos;
		this.direcao = dir;
	}
	public boolean isImortal() {
		return imortal;
	}
	
	public void setImortal(boolean poderoso) {
		this.imortal = poderoso;
	}
	
    public int getQtdVidas() {
        return qtdVidas;
    }
    
    public void setQtdVidas(int qtdVidas) {
        this.qtdVidas = qtdVidas;
    }
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
}
