package br.ufc.lia.sd.morphufc.communication;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import br.ufc.lia.sd.morphufc.printers.IPrinter;
import br.ufc.lia.sd.morphufc.printers.MemoPrinter;
import br.ufc.lia.sd.morphufc.util.Constants;
import br.ufc.lia.sd.morphufc.util.FileUtil;

/**
 * Servidor de conexoes. Para cliente, uma nova Thread e criada.
 * 
 * @author Jefferson
 * 
 */
public class Server extends Thread{
	
	private ServerSocket serverSocket ;
	private String serverIp;
	private List gridMachines;
	 
	private int startedClients;
	
	public static final IPrinter printer = new MemoPrinter();

	public Server() {
		
		//peagndo o meu ip
		try {
	       InetAddress addr = InetAddress.getLocalHost();
	       this.serverIp = addr.getHostAddress();
	         
	    } catch (UnknownHostException e) {
	    	e.printStackTrace();
	    }
		//alimentando e conhecendo minhas grids
		Properties props = FileUtil.getPropertiesFile();
		this.gridMachines = new ArrayList(4);
		if(!props.isEmpty()){
			for(int i=1;i <=props.size(); i++){
				String gridName = props.getProperty("grid"+i);
				if(!gridName.equals(this.serverIp+":"+Constants.SERVER_PORT))
					this.gridMachines.add(gridName.trim());
			}
			
		}else
			Server.printer.serverPrinter("Grid is empty!");
			 
		
		 
		
		//criando o ServerSocket
		try {
			this.serverSocket = new ServerSocket(Constants.SERVER_PORT);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		 
		
	}

	
	public void run() {
		//rodando o servidor.
		Server.printer.serverPrinter("Server is up on " + this.serverIp+":"+Constants.SERVER_PORT+".");
		 
		this.waitingConnection();
	}
	
	/**
	 * Espera por conexoes.
	 *
	 */
	private void waitingConnection()
	{
		 
                	
    	while(true)
		{
			// aceitando a conexao
			try {
				Socket client = this.serverSocket.accept();
				ServerThread serverThread = new ServerThread(client, this);
				serverThread.start();
				this.startedClients++;
		    }
		    catch (Exception ex) {
		    	ex.printStackTrace();
		    	break;
		    }	
		} 
				
    }


	public String getServerIp() {
		return serverIp;
	}


	public List getGridMachines() {
		return gridMachines;
	}


	public ServerSocket getServerSocket() {
		return serverSocket;
	}


	public int getStartedClients() {
		return startedClients;
	}
	
	public void decStartedClients(){
	
		this.startedClients = (startedClients==0)?startedClients:startedClients-1;
	}

 

}
