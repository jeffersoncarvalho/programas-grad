/*
 * Created on 12/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jpacman.characters.heuristic;

import br.unifor.edu.jefferson.jpacman.characters.Ghost;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface Heuristic {
	
	public void calculateNextStep(Ghost me);

}
