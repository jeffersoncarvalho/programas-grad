/*
 * Created on Oct 21, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pacman.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import pacman.componentes.Componente;
import pacman.componentes.ComponenteMovel;
import pacman.componentes.Pacman;
import pacman.comunicacao.Jogador;
import pacman.comunicacao.JogadorImpl;
import pacman.comunicacao.JogadorProxy;


/**
 * @author tiago
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Protocolo {
	public static final String JOGAR = "Jogar";
	public static final String RESP_JOGAR = "Resp_jogar";
	public static final String INICIAR = "Iniciar";
	public static final String MOVER = "Mover";
	public static final String ATUALIZAR = "Resp_mover";
	public static final String SAIR = "Sair";
	public static final String FIM_MSG = "@";
	public static final String SEP_COMP = "$";
//	public static final String 
	
	public static String jogar() {
	    return JOGAR+FIM_MSG;
	}
	
	public static String respJogar(Jogador jog) {
	    Pacman pac = jog.getPacman();
	    StringBuffer sb = new StringBuffer(RESP_JOGAR).append(" ");
	    sb.append(jog.getId()).append(" ").append(componente(pac)).append(FIM_MSG);
	    return sb.toString();
	}
	
	public static String iniciar() {
	    return INICIAR+FIM_MSG;
	}
	
	public static String sair(Jogador jog) {
	    return SAIR+" "+jog.getId()+FIM_MSG;
	}
	
	public static String mover(Jogador jog, int direcao) {
	    int id = jog.getId();
	    return MOVER+" "+id+" "+direcao+FIM_MSG;
	}
	
	public static String atualizar(List componentes) {
	    StringBuffer sb = new StringBuffer(ATUALIZAR);
	    for (Iterator it=componentes.iterator(); it.hasNext();) {
	        sb.append(SEP_COMP);
	        sb.append( componente((Componente)it.next()) );
	    }
	    sb.append(FIM_MSG);
	    return sb.toString();
	}
	
	public static Jogador interpretarSair(String msg) {
		StringTokenizer tokens = new StringTokenizer(msg);
	    tokens.nextToken();
	    Jogador jog = new JogadorProxy();
	    jog.setId(Integer.parseInt(tokens.nextToken()));
	    return jog;
	}
	
	public static Jogador interpretarMover(String msg) {
	    StringTokenizer tokens = new StringTokenizer(msg);
	    tokens.nextToken();
	    Jogador jog = new JogadorProxy();
	    jog.setId(Integer.parseInt(tokens.nextToken()));
	    Pacman pac = new Pacman();
	    pac.setDirecao(Integer.parseInt(tokens.nextToken()));
	    jog.setPacman(pac);
	    return jog;
	}
	
	public static List interpretaAtualizar(String msg) {
	    ArrayList lista = new ArrayList();
	    StringTokenizer comps = new StringTokenizer(msg, SEP_COMP);
	    comps.nextToken();
	    while (comps.hasMoreTokens()) {
	        StringTokenizer tokens = new StringTokenizer(comps.nextToken());
	        lista.add(interpretaComponente(tokens));
	    }
	    return lista;
	}
	
	public static Jogador interpretaRespJogar(String msg) {
	    StringTokenizer tokens = new StringTokenizer(msg);
	    tokens.nextToken();
	    JogadorImpl jogador = new JogadorImpl();
	    jogador.setId(Integer.parseInt(tokens.nextToken()));
	    jogador.setPacman((Pacman)interpretaComponente(tokens));
	    return jogador;
	}
	
	protected static Componente interpretaComponente(StringTokenizer tokens) {
	    Componente comp = null;
	    Posicao pos = new Posicao();
	    String tipo = tokens.nextToken();
	    pos.setX(Integer.parseInt(tokens.nextToken()));
	    pos.setY(Integer.parseInt(tokens.nextToken()));
	    if (tipo.equals(Pacman.class.getName())) {
	        int direcao = Integer.parseInt(tokens.nextToken());
	        comp = new Pacman(pos, direcao);
	        ((Pacman)comp).setCor(tokens.nextToken());
	    }
	    return comp;
	}
	
	protected static String componente(Componente comp) {
	    Posicao pos = comp.getPosicao();
	    StringBuffer sb = new StringBuffer(comp.getClass().getName()).append(" ");
	    sb.append(pos.getX()).append(" ");
	    sb.append(pos.getY()).append(" ");
	    if (comp instanceof ComponenteMovel){
	        sb.append(((ComponenteMovel)comp).getDirecao()).append(" ");
	        if (comp instanceof Pacman)
	            sb.append(((Pacman)comp).getCor());
	    }
	    return sb.toString();
	}
}
