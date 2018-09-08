/*
 * Created on Oct 21, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pacman.comunicacao;

import pacman.componentes.Pacman;

/**
 * @author tiago
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JogadorImpl implements Jogador {
    
    protected int id;
    protected Pacman pacman;
    
	public JogadorImpl() {
		
	}
	
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Pacman getPacman() {
        return pacman;
    }
    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }
}
