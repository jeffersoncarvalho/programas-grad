/*
 * Created on 30/09/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pacman.componentes;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import pacman.util.Posicao;

/**
 * @author Lbd18-22
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class ComponenteMovel extends Componente {
	
	protected int direcao;
	
	public ComponenteMovel(ImageIcon img, Posicao pos) {
		super(img, pos);
		this.direcao = KeyEvent.VK_RIGHT;
	}
	
	public ComponenteMovel(ImageIcon img, Posicao pos, int dir) {
		super(img, pos);
		this.direcao = dir;
	}
	
	public int getDirecao() {
		return direcao;
	}
	
	public void setDirecao(int direcao) {
		this.direcao = direcao;
	}
}
