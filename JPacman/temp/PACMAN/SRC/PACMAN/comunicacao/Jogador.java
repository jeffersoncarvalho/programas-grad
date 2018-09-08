/*
 * File Jogador.java
 * Created on 18/10/2004
 * 
 */
package pacman.comunicacao;

import pacman.componentes.Pacman;

/**
 * Class Jogador
 * @author Tiago Cordeiro Marques
 */
public interface Jogador {
    public Pacman getPacman();
    public void setPacman(Pacman pacman);
	public int getId();
	public void setId(int id);
}
