package br.ufc.lia.sd.morphufc.communication;

import java.io.IOException;

import br.ufc.lia.sd.morphufc.packet.Packet;
import br.ufc.lia.sd.morphufc.util.Constants;

public class Crawler extends Client{

	private boolean hostFound = false;
	private String searchFile;
	private String remoteHost;
	private int startedClientsOnRemoteHost;
	
	 
	public int getStartedClientsOnRemoteHost() {
		return startedClientsOnRemoteHost;
	}


	public String getSearchFile() {
		return searchFile;
	}


	public void setSearchFile(String searchFile) {
		this.searchFile = searchFile;
	}


	public boolean getHostFound() {
		return hostFound;
	}
	
	 
	public void receive() {
		Packet packetReceveid = null;
		 
		
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
				 
				case Constants.CRAWLER_HAS_FILE:
					Server.printer.serverPrinter("Crawler Receiving CRAWLER_HAS_FILE");
					this.startedClientsOnRemoteHost = packetReceveid.getStartedClients();
					this.hostFound = true;
					
				break;
			}
		}while(packetReceveid.getCode()!=Constants.BYE && !this.hostFound);
	}

	public void run() {
		 Packet packet = new Packet();
		 packet.setCode(Constants.CRAWLER_SEARCH);
		 packet.setFileName(this.searchFile);
		 Server.printer.serverPrinter("Crawler Sending CRAWLER_SEARCH.");
		 this.send(packet);
		 //recebendo
		 this.receive();
			
		 //xau
		 this.disconect();
		 Server.printer.serverPrinter("Crawler Desconnecting.");
		 
	}


	public String getRemoteHost() {
		return remoteHost;
	}


	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
}
