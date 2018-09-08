/*
 * Created on Nov 19, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pacman.componentes;

import javax.swing.ImageIcon;

import pacman.util.Posicao;

/**
 * @author tiago
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Obstaculo extends Componente {
	
	public static ImageIcon IMAGEM = 
	    new ImageIcon(ClassLoader.getSystemResource("pacman/imagens/obstaculo.gif"));
	
	/**
	 * @param img
	 * @param pos
	 */
	public Obstaculo(ImageIcon img, Posicao pos) {
		super(img, pos);
		// TODO Auto-generated constructor stub
	}

}
