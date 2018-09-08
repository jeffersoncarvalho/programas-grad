package br.ufc.lia.sd.morphufc.communication;

 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.ufc.lia.sd.morphufc.packet.Packet;
import br.ufc.lia.sd.morphufc.util.Constants;
import br.ufc.lia.sd.morphufc.util.FileUtil;

/**
 * Thread criada no servidor para tratar um cliente em particular.
 * @author Jefferson
 *
 */
public class ServerThread extends Thread{

	
	private Socket client;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Server parent;
	private int sendTimes = 0;

	public ServerThread(Socket client, Server parent){
		 
		this.client = client;
		this.parent = parent;
		Server.printer.serverPrinter("Client "+client.getInetAddress().getHostAddress()+":"+client.getPort()+" connected.");
		 
		try {
			this.out = new ObjectOutputStream(this.client.getOutputStream()); 
			this.in = new ObjectInputStream(this.client.getInputStream());
	    }
	    catch (IOException ex) {
	    	ex.printStackTrace();
	    }

	}
	
	 
	public void send(Packet packet){
		try {
			out.writeObject(packet);
			if(sendTimes%25==0)
				out.reset();
			out.flush();
			
	    }
	    catch (Exception ex) {
	    	ex.printStackTrace();
	    }

	    this.sendTimes++;
	}

	public void receive(){
		Packet packetReceveid = null;
		Packet packetToSayBye = new Packet();
		packetToSayBye.setCode(Constants.BYE);
		Packet packetToSend = null;
		List files = null;
		boolean jobFinished = false;
		

		 
			do{
				try {
					packetReceveid =  (Packet)in.readObject();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				switch(packetReceveid.getCode()){
				case Constants.REQUIRE_FILE:
					Server.printer.serverPrinter("Receiving REQUIRE_FILE");
					//procuro se eu tenho o arquivo 
					files = this.searchForLocalFiles(packetReceveid.getFileName());
					if(files.size()!=0){
						//tenho o(s) arquivo(s)
						packetToSend = new Packet();
						packetToSend.setCode(Constants.HAS_FILE_LOCAL);
						packetToSend.setFiles(files);
						Server.printer.serverPrinter("Sending HAS_FILE_LOCAL");
						this.send(packetToSend);
					}else{
						//nao tenho, mando procurar remotamente	
						//devo fazer o broadcast pra grid conhecida.
						String host = this.broadCast(packetReceveid.getFileName());
						 
						//se o meu metodo broadcat retornar um host, então envio para o cliente o host que ele deve se conectar agora.
						if(host!=null){
							packetToSend = new Packet();
							packetToSend.setCode(Constants.HAS_FILE_REMOTE);
							packetToSend.setRemoteHost(host);
							Server.printer.serverPrinter("Sending HAS_FILE_REMOTE");
							this.send(packetToSend);
							jobFinished = true;
						}
						//se o meu broadcast não encontra ningeum, então o cliente deve levantar um erro.
						else{
							packetToSend = new Packet();
							packetToSend.setCode(Constants.FILE_NOT_FOUND);
							packetToSend.setRemoteHost(host);
							Server.printer.serverPrinter("Sending FILE_NOT_FOUND");
							this.send(packetToSend);
							jobFinished = true;
						}
						
					}
				 
				break;
				case Constants.CRAWLER_SEARCH:
					Server.printer.serverPrinter("Receiving CRAWLER_SEARCH");
					//procuro se eu tenho o arquivo 
					files = this.searchForLocalFiles(packetReceveid.getFileName());
					if(files.size()!=0){
						//tenho o(s) arquivo(s), mas soh digo que tenho...depois o cliente se vira comigo
						packetToSend = new Packet();
						packetToSend.setCode(Constants.CRAWLER_HAS_FILE);
						packetToSend.setStartedClients(this.parent.getStartedClients());
						Server.printer.serverPrinter("Sending CRAWLER_HAS_FILE");
						this.send(packetToSend);
						jobFinished = true;
					}else{
						//nao tenho. Como foi um Crawler Search, repondo bye, pra o cliente desconectar
						//fiz meu servico, tchau!
						Server.printer.serverPrinter("Sending BYE");
						this.send(packetToSayBye);
						jobFinished = true;
					}
				 
				break;
				case Constants.DOWNLOAD:
					 //montando pacote de interesse do cliente.
					 Server.printer.serverPrinter("Receiving DOWNLOAD");
					 
					 
					 this.readFileAndSend(packetReceveid.getFileName());
					 
					  
					 
					 //fiz meu servico, tchau!
					 Server.printer.serverPrinter("Sending BYE");
					 this.send(packetToSayBye);
					 jobFinished = true;
					 
				break;
				case Constants.NACK:
				break;
				}
			}while(packetReceveid.getCode()!=Constants.BYE && !jobFinished);
			
			 Server.printer.serverPrinter("Job Finished");
		 
		
		try {
			out.close();
			in.close();
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public void run() {
		 
		//oooh..eu sou tao passivo...
		this.receive();
		//thread terminada, informo ao servidor.
		this.parent.decStartedClients();
	}
	
	private List searchForLocalFiles(String fileName){
		File dir = new File(FileUtil.curDir+"shared");
		ArrayList allFiles = new ArrayList();
		FileUtil.visitAllFiles(dir, allFiles, fileName);
		return allFiles;
	}
	
	/**
	 * Faz um "broadcast" atrás do arquivo. Retorna host que tem o arquivo, retorna null se não encontrou.
	 * Alem disso, o broadcast verifica qual servidor tem a menor carga.
	 * @param fileName
	 */
	private String broadCast(String fileName){
		List grid = this.parent.getGridMachines();
		List<Crawler> crawlerList = new ArrayList<Crawler>();

		int minorBalance = Integer.MAX_VALUE;
		String hostSelected = null;
		
		//o crawler ser conecta e busca com cada grid.
		for(int i=0; i<grid.size();i++){
			
			//creating crawler.
			Crawler crawler = new Crawler();
			crawler.setSearchFile(fileName);
			
			
			String host = (String)grid.get(i);
			String ip_port[] = host.split(":");
			String ip = ip_port[0];
			int port = Integer.parseInt(ip_port[1]);
			crawler.connect(ip, port);
			
			if(!crawler.isConnected())
				continue;
			crawler.setRemoteHost(host);
			crawlerList.add(crawler);
			crawler.start();
			Server.printer.serverPrinter("Crawler connect to " + ip + ":"+port+".");
			 
			 
			
				
		}
		
		//joining
		for(int i=0;i<crawlerList.size();i++){
			Crawler crawlerRef = crawlerList.get(i);
			try {
				crawlerRef.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//pegando o menor balance
		for(int i=0;i<crawlerList.size();i++){
			Crawler crawlerRef = crawlerList.get(i);
			if(crawlerRef.getHostFound())
			{	
				if(crawlerRef.getStartedClientsOnRemoteHost() < minorBalance){
					minorBalance = crawlerRef.getStartedClientsOnRemoteHost();
					hostSelected = crawlerRef.getRemoteHost();
				}
			}
		}
		
		 
		return hostSelected;
	}
	
	private void readFileAndSend(String fileName){
			try {
						
					String inPath = FileUtil.curDir+"shared"+File.separatorChar;
					inPath+=fileName;	 
					File f = new File(inPath);
					FileInputStream fi = new FileInputStream(inPath);
					BufferedInputStream in = new BufferedInputStream(fi);
					
					int packetSize = 1048576;
					int packetSizeClone = packetSize;
					int numberPackets = 0;
					int data=0;
					int length = (int)f.length();
					if(length<packetSize)
			    		numberPackets = 1;
			    	else
			    		numberPackets = length/packetSize+1;
					int counter =0;
					int packetNumber = 0;
					int internalPacketByteCounter = 0;
					 
					byte[] packet = null;
					Packet packetToSend = null;
		            while((data = in.read())> -1){
		            	if(counter%packetSize==0 && (length-counter>=packetSize)){
		            		 
		            		packet = new byte[packetSize];
		            		packetNumber ++;
		            		packetToSend = this.createPacketToSend(fileName, length, packet);
	            			packetToSend.setNumber(packetNumber);
	            			packetToSend.setTotal(numberPackets);
		            		internalPacketByteCounter = 0;
		            		 
		            	}else if(length-counter<packetSize && counter%packetSize==0){
		            		 
		            		packet = new byte[length-counter];
		            		packetSizeClone =length-counter;
		            		packetNumber ++;
		            		packetToSend = this.createPacketToSend(fileName, length, packet);
	            			packetToSend.setNumber(packetNumber);
	            			packetToSend.setTotal(numberPackets);
		            		internalPacketByteCounter = 0;
		            		 
		    			 }
		            	packet[internalPacketByteCounter] = (byte)data; 
		            	if(internalPacketByteCounter==packetSizeClone-1){
		            		//sending
				            Server.printer.serverPrinter("Sending DATA");
		        			this.send(packetToSend);
		        			
		            	}
		            	 
		            	counter++;
		                internalPacketByteCounter++;
		            }//while
		            fi.close();
		            in.close();
		            
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					 
	}
		
	private Packet createPacketToSend(String fileName, int bigDataSize, byte[] data){
		 Packet packetToSend = new Packet();
		 packetToSend.setCode(Constants.DATA);
		 packetToSend.setFileName(fileName);
		 packetToSend.setBigDataSize(bigDataSize);
		 packetToSend.setData(data);
		 
		 return packetToSend;
	}
	
}
