import java.net.*;
import java.io.*;
import java.util.*;

public class ClientSnake
{
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private DrawPanel drawPanel;
	private ResultPanel resultPanel;
	Socket socket;
	
	/**
	 *Contrutor de ClientSnake
	 */
	public ClientSnake(DrawPanel drawPanel, ResultPanel resultPanel)
	{
		this.drawPanel = drawPanel;
		this.resultPanel = resultPanel;  
	}
	
	/**
	 *Envia msg ao servidor conectado
	 */
	public void send(String msg)
	{
		try {
			out.writeObject(msg);
			out.flush();
	    }
	    catch (Exception ex) {
	    	System.out.println (ex.toString());
	    }
	}
	
	/**
	 * Recebendo Strings.
	 */
	public void receive()
	{
		//recebendo no laço.
		
		String msg = "JAVA";
		do{
		  //if(socket!=null){
		  	//System.out.println ("2");
			try {
				msg = (String)in.readObject();
				this.processMessage(msg);
			//	System.out.println (msg);
		    }
		    catch (Exception ex) {
		    	System.out.println ("problemas no recebimento de msgs.");
		    	msg = "END";
		    }
		  //}//if
		}while(!msg.equals("END"));
		
		//fechando tudo.
		try {
			out.close();
			in.close();
			socket.close();
	    }
	    catch (Exception ex) {
	    }
	}
	
	/**
	 * Escutando o servidor numa Thread
	 */
	public void listenServer()
	{
		Thread t = new Thread(){
            public void run(){
            
             
              
                            	
				receive();
				
			  
            }
        };//fim da classe thread
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
		
	}
	
	/**
	 * Processando mensagem vinda do servidor.
	 */
	public void processMessage(String msg)
	{
		
		if(msg.startsWith("COR"))
		{
			drawPanel.addObject(msg);
            drawPanel.repaint();//pinta
		}
		else
		 if(msg.startsWith("FOOD"))
		 {
		 	StringTokenizer tokens = new StringTokenizer(msg);
			tokens.nextToken();//token FOOD
			drawPanel.setFood(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
		 }
		 else
		 	if(msg.startsWith("PONTO"))
		 	{
		 		resultPanel.setPoint(msg);
		 	}
		 	
	}
	
	/**
	 * Cancelando a conexão
	 */
	public void disconect()
	{
		try {
			if(socket!= null)
				socket.close();
			drawPanel.clearPanel();	
	    }
	    catch (Exception ex) {
	    	System.out.println ("Problemas ao fechar a conexao.");
	    }
		
	}
	
	public void connect(String ip)
	{
		try {
			socket = new Socket(InetAddress.getByName(ip),23);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			this.listenServer();

	    }
	    catch (Exception ex) {
	    	System.out.println ("falha na conexao.");
	    }
	}
	
}