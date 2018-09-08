/*
 * File Servidor.java
 * Created on 18/10/2004
 * 
 */
package pacman.comunicacao;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.StringTokenizer;

import pacman.util.Protocolo;


/**
 * Class Servidor
 * @author Tiago Cordeiro Marques
 */
public class Servidor {

	private int porta;
	private ControladorImpl controlador;
	private ServerSocketChannel channelServidor = null;
	
	public Servidor(int porta) throws IOException {
		controlador = new ControladorImpl();
		// cria um channel
		this.channelServidor = ServerSocketChannel.open();
		// e connecta-o a porta desejada
		this.channelServidor.socket().bind (new InetSocketAddress(porta));
		System.out.println("Servidor escutando na porta " + porta);
	}
	
	public void recebeConexoes() throws IOException {
		// cria um seletor
		Selector seletor = Selector.open();
		
		try {
			// coloca o channel em modo nao-bloqueante
			this.channelServidor.configureBlocking(false);
			// e registra o channel no seletor (para operacoes de aceitar conexcao)
			this.channelServidor.register(seletor, SelectionKey.OP_ACCEPT);
			
			while(true) {
//				System.out.println("Loop");
				// aguarda que alguma operacao esteja disponivel
				seletor.select();
				Iterator iterator = seletor.selectedKeys().iterator();
				
				while(iterator.hasNext()) {
					// obtem a operacao disponivel
					SelectionKey key = (SelectionKey) iterator.next();
					
					// achou uma opera��o de conexao ao servidor socket
					if (key.isAcceptable()) {
						ServerSocketChannel servidor = (ServerSocketChannel) key.channel();
						// cria um socket cliente, tambem nao bloqueante
						SocketChannel channel = servidor.accept();
						channel.configureBlocking(false);
						System.out.println("Conexao estabelecida com "+
				    			channel.socket().getRemoteSocketAddress()); 
						// e registra o socket no seletor (apenas para operacoes de leitura)
						channel.register(seletor, SelectionKey.OP_READ);
					}
					// achou uma operacao de leitura
					if (key.isReadable()) {
						// processa o socket
						processaCliente((SocketChannel) key.channel());
					}
					// finalmente, remove a operacao do conjunto
					iterator.remove();
				}
//				atualizarJogadores();
			}
		} finally { // sempre fecha o conetor
			seletor.close();
		}
	}
	
	private void processaCliente(SocketChannel socketChannel) throws IOException {
//		System.out.println("Processando cliente " + socketChannel.socket().getRemoteSocketAddress());
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
		this.processarMsgs(sb.toString(), socketChannel);
		if (num == -1) {
			System.out.println("Fim");
			socketChannel.close();
		}
	}
	
	private void processarMsgs(String msgs, SocketChannel channel) {
		StringTokenizer tokens = new StringTokenizer(msgs, Protocolo.FIM_MSG);
		while (tokens.hasMoreTokens())
		    processarMsg(tokens.nextToken(), channel);
	}
	
	private void processarMsg(String msg, SocketChannel channel) {
		if (msg.startsWith(Protocolo.JOGAR)) {
			System.out.println("Jogar recebido");
			JogadorProxy jog = new JogadorProxy();
			jog.setWriter(Channels.newWriter(channel, "latin1"));
			controlador.jogar(jog);
		}
		else if (msg.startsWith(Protocolo.MOVER)) {
//		    System.out.println("Mover recebido");
		    Jogador jog = Protocolo.interpretarMover(msg);
		    controlador.setDirecao(jog);
		}
		else if (msg.startsWith(Protocolo.SAIR)) {
			controlador.sair(Protocolo.interpretarSair(msg));
			try {
				channel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			System.out.println("Msg desconhecida = "+msg);
	}
	
	
	
	// tratamento de exe��es omitidos
	public static void main(String args[]) throws Exception {
		int porta = 3578;
		Servidor servidor = new Servidor(porta);
		servidor.recebeConexoes();
	}
}
