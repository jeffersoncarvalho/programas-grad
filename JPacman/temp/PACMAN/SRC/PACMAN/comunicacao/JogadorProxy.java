/*
 * File JogadorProxy.java
 * Created on 18/10/2004
 * 
 */
package pacman.comunicacao;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import pacman.componentes.Pacman;
import pacman.util.Protocolo;

/**
 * Class JogadorProxy
 * @author Tiago Cordeiro Marques
 */
public class JogadorProxy extends JogadorImpl {
    
    private Writer writer;
    
    public JogadorProxy() {
    	super();
    }
    
    public JogadorProxy(int id, Pacman pac) {
    	this.id = id;
        this.pacman = pac;
    }

	/**
	 * @return Returns the writer.
	 */
	public Writer getWriter() {
		return writer;
	}
	
	/**
	 * @param writer The writer to set.
	 */
	public void setWriter(Writer writer) {
		this.writer = writer;
	}
	
	public void jogar() {
	    try {
            writer.write(Protocolo.respJogar(this));
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public void atualizar(List jogadores) {
	    try {
            writer.write(Protocolo.atualizar(jogadores));
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
    public boolean equals(Object obj) {
        if (obj instanceof JogadorProxy) {
            JogadorProxy jp = (JogadorProxy)obj;
            if (this.id == jp.id)
                return true;
        }
        return false;
    }
	
}
