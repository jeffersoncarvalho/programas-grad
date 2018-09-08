/*
 * File Cliente.java
 * Created on 18/10/2004
 * 
 */
package pacman.comunicacao;

import java.io.IOException;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import pacman.util.Protocolo;

/**
 * Class Cliente
 * @author Tiago Cordeiro Marques
 */
public class Cliente implements Runnable {
    
    private SocketChannel channel;
    private Selector selector;
    private ControladorProxy controlador;
    private static final long TIMEOUT = 1000; // 1 segundo
    private static final int TENTATIVAS = 10;
    
    /**
     * 
     */
    public Cliente(InetSocketAddress servidor) throws Exception {
    	selector = Selector.open();
    	channel = SocketChannel.open();
    	channel.configureBlocking(false);
    	channel.connect(servidor);
    	boolean sucesso = channel.finishConnect();
    	int tentativas = 0;
    	while (! sucesso && tentativas < TENTATIVAS) {
    		tentativas ++;
    		System.out.println("Aguardando conexao (tentativa " + tentativas + ")");
    		sucesso = channel.finishConnect();
    		if (! sucesso) {
    			try {
    				Thread.sleep(TIMEOUT);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	System.out.println("Conexao estabelecida com "+
    			channel.socket().getRemoteSocketAddress()); 
    	controlador = new ControladorProxy(channel);
    	controlador.jogar();
    	
    	channel.register(selector, SelectionKey.OP_READ);
    	new Thread(this).start();
    	
    }
    
    /**
	 * @return Returns the channel.
	 */
	public SocketChannel getChannel() {
		return channel;
	}
    
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		//Wait for events
	    while (true) {
	        try {
	            // Wait for an event
	            selector.select();
	        } catch (IOException e) {
	            e.printStackTrace();
	            break;
	        }
	    
	        // Get list of selection keys with pending events
	        Iterator it = selector.selectedKeys().iterator();
	    
	        // Process each key at a time
	        while (it.hasNext()) {
	            // Get the selection key
	            SelectionKey selKey = (SelectionKey)it.next();
	    
	            // Remove it from the list to indicate that it is being processed
	            it.remove();
	    
	            try {
	            	if (selKey.isValid() && selKey.isReadable()) 
	            		processarServidor((SocketChannel)selKey.channel());
	            } catch (IOException e) {
	                // Handle error with channel and unregister
	                selKey.cancel();
	            }
	        }
	    }
	}
	
	public void processarServidor(SocketChannel socketChannel) throws IOException {
//		System.out.println("Processando msg do servidor " + socketChannel.socket().getRemoteSocketAddress());
		// codigo de leitura/escrita do socket viria aqui
		ByteBuffer buff = ByteBuffer.allocate(1024);
		int num = socketChannel.read(buff);
		StringBuffer sb = new StringBuffer(buff.position());
		while (num > 0) {
			int pos = buff.position();
			buff.flip();
			sb.append(new String(buff.array(), 0, pos));
			buff.clear();
			num = socketChannel.read(buff);
		}
		this.processarMsgs(sb.toString());
		if (num == -1) {
			System.out.println("Fim");
			socketChannel.close();
		}
	}
	
	private void processarMsgs(String msgs) {
		StringTokenizer tokens = new StringTokenizer(msgs, Protocolo.FIM_MSG);
		while (tokens.hasMoreTokens())
		    processarMsg(tokens.nextToken());
	}
	
	private void processarMsg(String msg) {
		if (msg.startsWith(Protocolo.RESP_JOGAR)) {
			Jogador jog = Protocolo.interpretaRespJogar(msg);
			System.out.println("Jogar id = "+jog.getId());
			controlador.setJogador(jog);
		}
		else if (msg.startsWith(Protocolo.ATUALIZAR)) {
		    List lista = Protocolo.interpretaAtualizar(msg);
//		    System.out.println("Atualizar lista = "+lista.size());
		    controlador.setComponentes(lista);
		}
		else
			System.out.println("Msg desconhecida = "+msg);
	}
	
    public ControladorProxy getControlador() {
        return controlador;
    }
    
    public void setControlador(ControladorProxy controlador) {
        this.controlador = controlador;
    }
    
    public static void main(String[] args) {
        try {
//        	SocketChannel chann = 
//        			SocketChannel.open(new InetSocketAddress("localhost", 3578));
//        	Thread.sleep(2000);
//        	ByteBuffer buff = ByteBuffer.allocate(100);
//        	buff.put("Tiago Cordeiro 0010051/0".getBytes());
//        	buff.flip();
//        	chann.write(buff);
//        	Thread.sleep(5000);
//        	
//        	buff.flip();
//        	buff.put("Tiago Cordeiro msg2".getBytes());
//        	buff.flip();
//        	chann.write(buff);
//        	chann.close();
        	
        	Cliente c = new Cliente(new InetSocketAddress("localhost", 3578));
//        	Thread.sleep(4000);
        	SocketChannel chann = c.getChannel();
//        	ByteBuffer buff = ByteBuffer.allocate(100);
//        	buff.put("Tiago Cordeiro 0010051/0".getBytes());
//        	buff.flip();
//        	chann.write(buff);
        	Writer w = Channels.newWriter(chann, "latin1");
        	w.write(Protocolo.jogar());
        	w.flush();
        	Thread.sleep(4000);
//        	chann.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }	
}
