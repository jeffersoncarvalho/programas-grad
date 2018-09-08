package br.ufc.lia.sd.morphufc.communication;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufc.lia.sd.morphufc.observer.Observer;
import br.ufc.lia.sd.morphufc.packet.Packet;
import br.ufc.lia.sd.morphufc.util.Constants;
import br.ufc.lia.sd.morphufc.util.FileUtil;
import br.ufc.lia.sd.morphufc.util.MetaFile;

public class Client extends Thread{

	protected ObjectOutputStream out;
	protected ObjectInputStream in;
	protected Socket socket;
	private Observer observer;
	private String fileRequired = "";
	
	protected boolean connected = false;
	
	 

	public void send(Packet packet){
		try {
			out.writeObject(packet);
			out.flush();
	    }
	    catch (Exception ex) {
	    	ex.printStackTrace();
	    }

	}
	
	public void receive(){
		
		Packet packetReceveid = null;
		Packet packetToSend = null;
		Packet packetToSayBye = new Packet();
		packetToSayBye.setCode(Constants.BYE);
		
		boolean breakListening = false;
		 
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
				case Constants.DATA:
					
					Server.printer.clientPrinter("Receiving DATA");
					if(packetReceveid.getNumber()==1)
						this.observer.getLogPanel().setBarSize(packetReceveid.getTotal()); 
					this.observer.getLogPanel().addProgress();
					this.writeData(packetReceveid.getFileName(),packetReceveid.getData());
					 
					 
				break;
				case Constants.FILE_NOT_FOUND:
					Server.printer.clientPrinter("Receiving FILE_NOT_FOUND");
					Server.printer.clientPrinter("The file required was not found in any place.");
					JOptionPane.showMessageDialog(null,"The file required was not found in any place.","Error",JOptionPane.ERROR_MESSAGE);
					breakListening = true; 
				break;
				case Constants.HAS_FILE_LOCAL:
					Server.printer.clientPrinter("Receiving HAS_FILE_LOCAL");
					List filesFound = packetReceveid.getFiles();
					for(int i=0;i<filesFound.size();i++){
						this.observer.getFileListPanel().addFilesToList((MetaFile)filesFound.get(i));
						 
					}
					//mostra na gui, que o que o usuario quer
					//envia o nome do arquivo escolhido, caso queira algum
					this.observer.getFileListPanel().setClient(this);
					 
				break;
				case Constants.HAS_FILE_REMOTE:
					 
					 Server.printer.clientPrinter("Receiving HAS_FILE_REMOTE");
					 //desconectando do que não tem, o local
					 this.disconect();
					 //conectando ao remoto
					 String remoteHost = packetReceveid.getRemoteHost();
					 String ip_port[] = remoteHost.split(":");
					 String ip = ip_port[0];
					 int port = Integer.parseInt(ip_port[1]);
					 this.connect(ip, port);
					 
					 //envia
					 
					 
					 packetToSend = new Packet();
					 packetToSend.setCode(Constants.REQUIRE_FILE);
					 packetToSend.setFileName(this.fileRequired);
					 this.send(packetToSend);
				break;
			}
		}while(packetReceveid.getCode()!=Constants.BYE && !breakListening);
			
			
		 
		
	}
	
	public void connect(String ip, int port){
		if(this.connected == true)
			return;
		
		 
			try {
				this.socket = new Socket(InetAddress.getByName(ip),port);
				this.out = new ObjectOutputStream(socket.getOutputStream());
				this.in = new ObjectInputStream(socket.getInputStream());
				this.connected = true;
			} catch (UnknownHostException e) {
				Server.printer.clientPrinter("ERROR: Could not connect to " + ip + ":" +port);
				//e.printStackTrace();
			} catch (IOException e) {
				Server.printer.clientPrinter("ERROR: Could not connect to " + ip + ":" +port);
				//e.printStackTrace();
			}
			
			

	    
	}
	
	 
	
	public void disconect()
	{
		try {
			if(socket!= null)
			{
				socket.close();
				out.close();
				in.close();
				this.connected = false;
			}
				
				
	    }
	    catch (Exception ex) {
	    	Server.printer.clientPrinter("ERROR: Could not disconnect from " + this.socket.getInetAddress().getHostAddress());
	    }
		
	}
	
	/**
	 * Funcioanamento normal.
	 */
	public void run() {
	
		  
		 this.observer.getLogPanel().reset();
		//conectando
		 
		this.connect("localhost", Constants.SERVER_PORT);
		if(!this.connected)
			Server.printer.clientPrinter("ERROR: Could not connect to my own Server!!!");
		//pacote re requisicao
		Packet requestFilePkt = new Packet();
		requestFilePkt.setFileName(this.fileRequired);
		
		requestFilePkt.setCode(Constants.REQUIRE_FILE);
		
		//enviando
		Server.printer.clientPrinter("Sending REQUIRE_FILE");
		this.send(requestFilePkt);
		
		//recebendo
		this.receive();
		
		//xau
		this.disconect();
		Server.printer.clientPrinter("Desconnecting.");
		
		this.observer.getControlPanel().enableControls();
	}

	public boolean isConnected() {
		return connected;
	}

	public Observer getObserver() {
		return observer;
	}

	public void setObserver(Observer observer) {
		this.observer = observer;
	}

	public String getFileRequired() {
		return fileRequired;
	}

	public void setFileRequired(String fileRequired) {
		this.fileRequired = fileRequired;
	}

	private void writeData(String fileName, byte[] data){
		String outPath = FileUtil.curDir+"received"+File.separatorChar;
		outPath += fileName;
		FileOutputStream file;
		try {
			file = new FileOutputStream(outPath,true);
			DataOutputStream out   = new DataOutputStream(file);
			out.write(data);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	 
}
