/*
 * File ControladorImpl01.java
 * Created on 18/10/2004
 * 
 */
package pacman.comunicacao;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import pacman.componentes.Componente;
import pacman.componentes.Pacman;
import pacman.gui.Painel;
import pacman.util.Posicao;

/**
 * Class ControladorImpl01
 * @author Tiago Cordeiro Marques
 */
public class ControladorImpl implements Runnable {

    private static int cont = 0;
    private Vector jogadores;
    private List fantasmas;
    private List vitaminas;
    private List obstaculos;
    private boolean modificado;
    private static final int DESLOC = 10;
    private static final String[] CORES = 
    		{"#FF0000", "#0000FF", "#00FF00", "#000000", "#FFFFFF"};
    
    public ControladorImpl() {
        jogadores = new Vector();
        fantasmas = new ArrayList();
        vitaminas = new ArrayList();
        obstaculos = new ArrayList(); 
        new Thread(this).start();
    }
    
    public JogadorProxy jogar(JogadorProxy jogador) {
        Pacman pac = new Pacman();
        pac.setCor(CORES[cont%CORES.length]);
        jogador.setId(cont);
        jogador.setPacman(pac);
        jogadores.add(jogador);
        jogador.jogar(); //envia msg resp_jogar p/ o cliente
        ++cont;
        return jogador;
    }
    
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (true) {
		    atualizarJogadores();
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void atualizarJogadores() {
	    List lista = new ArrayList(jogadores.size());
		Iterator it = jogadores.iterator();
		while (it.hasNext()) {
		    Jogador jog = (Jogador)it.next();
		    mover(jog);
		    lista.add( jog.getPacman() );
		}
		it = jogadores.iterator();
		while (it.hasNext()) {
			JogadorProxy jog = (JogadorProxy)it.next();
			jog.atualizar(lista);
		}
	}
	
	public void setDirecao(Jogador jogador) {
	    int direcao = jogador.getPacman().getDirecao();
        jogador = (Jogador)jogadores.get(jogadores.indexOf(jogador));
        if (true) { //testar colisoes com paredes, fantasmas e vitaminas
            Pacman pac = jogador.getPacman();
            pac.setDirecao(direcao);
        }
	}
	
    public void mover(Jogador jogador) {
        //obs: so enviar direcoes validas
        Posicao trans = null;
        Pacman pac = jogador.getPacman();
        int direcao = pac.getDirecao();
        
        switch (direcao) {
        case KeyEvent.VK_UP: {
        	trans = new Posicao(0, -DESLOC);
        	break;
        }
        case KeyEvent.VK_DOWN: {
        	trans = new Posicao(0, DESLOC);
        	break;
        }
        case KeyEvent.VK_RIGHT: {
        	trans = new Posicao(DESLOC, 0);
        	break;
        }
        case KeyEvent.VK_LEFT: {
        	trans = new Posicao(-DESLOC, 0);
        	break;
        }}
        
        Posicao pos = pac.getPosicao();
        trans.transladar(pos.getX(), pos.getY());
        if (!colideComParede(pac, trans)) { //testar colisoes com paredes, fantasmas e vitaminas
            pac.setPosicao(trans);
        }
    }
    
    private boolean colideComParede(Componente c, Posicao nova) {
        int x = nova.getX();
        int y = nova.getY();
        int dx = c.getImagem().getIconWidth();
        if ((x < 0-DESLOC/2) || (x+dx>Painel.TAM+DESLOC/2))
            return true;
        else if ((y < 0-DESLOC/2) || (y+dx>Painel.TAM+DESLOC/2)) {
            return true;
        }
        return false;
    }
    
    public List getJogadores() {
    	return jogadores;
    }
    
    public void sair(Jogador jog) {
    	jogadores.remove(jog);
    }
}
