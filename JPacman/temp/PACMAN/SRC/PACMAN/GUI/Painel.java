/*
 * File Painel.java
 * Created on 21/10/2004
 * 
 */
package pacman.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MediaTracker;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.net.InetSocketAddress;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pacman.componentes.Componente;
import pacman.componentes.ComponenteMovel;
import pacman.componentes.Pacman;
import pacman.comunicacao.Cliente;
import pacman.util.Posicao;

/**
 * Class Painel
 * @author Tiago Cordeiro Marques
 */
public class Painel extends JPanel implements Runnable, KeyListener {
	
	private Cliente cliente;
	public final static int TAM = 600;
	private int direcaoAntiga;
	
	/**
	 * 
	 */
	public Painel() {
		super();
		direcaoAntiga = -1;
		while (Pacman.IMAGEM.getImageLoadStatus() != MediaTracker.COMPLETE)
			;
		setPreferredSize(new Dimension(TAM, TAM));
		try {
            cliente = new Cliente(new InetSocketAddress("localhost", 3578));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new Thread(this).start(); 
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g; 
		
		Iterator it = cliente.getControlador().getComponentes().iterator();
		while (it.hasNext()) {
		    Componente comp = (Componente)it.next();
		    desenharComponente(comp , g2, Color.BLUE);
		}
	}
	
	private void desenharComponente(Componente c, Graphics2D g2, Color cor) {
	    if (c instanceof Pacman) {
	        Pacman pac = (Pacman)c;
	        desenharCompMovel(pac, g2, Color.decode(pac.getCor()));
	    }
	    else 
	        ;//desenhar componente estatico
	}
	
	private void desenharCompMovel(ComponenteMovel c, Graphics2D g2, Color cor) {
		Posicao pos = c.getPosicao();
		double angulo = 0;
        Posicao trans = null;
        
        switch (c.getDirecao()) {
        case KeyEvent.VK_UP: {
        	angulo = -Math.PI/2;
        	break;
        }
        case KeyEvent.VK_DOWN: {
        	angulo = Math.PI/2;
        	break;
        }
        case KeyEvent.VK_RIGHT: {
        	break;
        }
        case KeyEvent.VK_LEFT: {
        	angulo = Math.PI;
        	break;
        }}

		AffineTransform antiga = g2.getTransform();
		ImageIcon img = c.getImagem();
		int largura = img.getIconWidth();
		int altura = img.getIconHeight();
		AffineTransform nova = AffineTransform.getTranslateInstance(
        		pos.getX()+largura/2, pos.getY()+altura/2);
		nova.rotate(angulo);
        nova.translate(-(pos.getX()+largura/2), -(pos.getY()+altura/2));
        g2.setTransform(nova);
        img.paintIcon(null, g2, pos.getX(), pos.getY());
        if (c instanceof Pacman) {
            g2.setColor(cor);
            g2.fillOval(pos.getX()+largura/3, pos.getY()+altura/3, 10, 10);
        }
        g2.setTransform(antiga);
	}
	
	public void run() {
		while (true) {
			repaint();
			esperar(200);
		}
	}
	
	public void esperar(int t) {
		try {
			Thread.sleep(t);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void sair() {
	    cliente.getControlador().sair();
	}
	
	public void keyPressed(KeyEvent ev) {
		int dir = ev.getKeyCode();
		if (dir>36 && dir<41 && dir!=direcaoAntiga) {
			cliente.getControlador().mover(dir);
			direcaoAntiga = dir;
		}
	}
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
	    final Painel p = new Painel();
		JFrame janela = new JFrame("Pacman Distribuido");
		janela.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
			    p.sair();
				System.exit(0);
			}
		});
		
		janela.addKeyListener(p);
		janela.getContentPane().add(p);
		janela.pack();
		janela.show();
	}
    
}
