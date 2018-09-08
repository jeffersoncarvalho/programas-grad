/*
 * File ControladorProxy.java
 * Created on 18/10/2004
 * 
 */
package pacman.comunicacao;

import java.io.IOException;
import java.io.Writer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import pacman.util.Protocolo;


/**
 * Class ControladorProxy
 * @author Tiago Cordeiro Marques
 */
public class ControladorProxy implements Controlador {   
    
    private Writer writer;
    private List componentes;
    private Jogador jogador;
	
    public ControladorProxy(SocketChannel ch) {
        writer = Channels.newWriter(ch, "latin1");
        componentes = new ArrayList();
    }
    
    public void jogar() {
        try {
            writer.write(Protocolo.jogar());
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void sair() {
    	try {
            writer.write(Protocolo.sair(jogador));
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void mover(int direcao) {
        try {
            writer.write(Protocolo.mover(jogador, direcao));
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public Jogador getJogador() {
        return jogador;
    }
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
    public List getComponentes() {
        return componentes;
    }
    public void setComponentes(List componentes) {
        this.componentes = componentes;
    }
}
