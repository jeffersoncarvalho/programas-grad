package br.ufc.lia.sd.morphufc.packet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import br.ufc.lia.sd.morphufc.util.FileUtil;


public class Packet implements Serializable{

	private int code; //tipo de msg
	private String fileName; //nome do arquivo
	private byte[] data; //arquivo em bytes
	 
	private List files; //arquivos encontrados
	private String remoteHost; //visited host
	private int startedClients; //on this host
	private int number;
	private int total;
	private int bigDataSize ;
	
	
	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	/**
	 * Have the filepacket read iteself in from the
	 * file it represents in name
	 * @throws IOException
	 * @throws FileNotFoundException
	 **/
	public void read() {
  	 
    	File file=null;
    	 
		String inPath = FileUtil.curDir+"shared"+File.separatorChar;
		inPath+=fileName;
		 
		file = new File(inPath);
		 
    	 
    	this.setFileName(file.getName());
     	data = new byte[ (int)(file.length()) ];
     	//System.out.println(file.length()); 
        try {
        	FileInputStream fis = new FileInputStream( file );
			fis.read( data );
			fis.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
	}

	/**
	 * Have the file packet recreate itself, used
	 * after sending it to another location
	 * file will have same name and contents
	 * @param out The outputStream to write itself to
	 **/
	public void write(){
		OutputStream out =null;
		
		String outPath = FileUtil.curDir+"received"+File.separatorChar;
		 
		
		try {
			out = new FileOutputStream(outPath+ fileName);
			out.write( data );
			out.flush();
			out.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	 
	    	
	    
	  	
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public List getFiles() {
		return files;
	} 

	public void setFiles(List files) {
		this.files = files;
	}

	public int getStartedClients() {
		return startedClients;
	}

	public void setStartedClients(int startedClients) {
		this.startedClients = startedClients;
	}

	 
	public static void main(String[] args) {
		
	 int lenght = 7855;
	 int packetSize = 1000;
	 int count = 0;
	  
	 while(count<lenght){
		 if(count%packetSize==0 && ((lenght-count)>=packetSize)){
			 System.out.println(count);
			 System.out.println("Criando pacote de tamanho: " + packetSize);
		 }
			 
		 else
			 if(lenght-count<packetSize && count%packetSize==0){
				 System.out.println(count);
				 System.out.println("ultimo pacote de tamanho " + (lenght-count));
				  
			 }
				 
		 count++;
	 }
		
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getBigDataSize() {
		return bigDataSize;
	}

	public void setBigDataSize(int bigDataSize) {
		this.bigDataSize = bigDataSize;
	}

	 
	
	

}
