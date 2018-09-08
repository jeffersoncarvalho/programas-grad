package br.ufc.lia.sd.morphufc.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import br.ufc.lia.sd.morphufc.packet.Packet;

public class Test {

	public static void main(String[] args) {
		try {
			
			 
			 
			String infileName = "C:\\morphufc\\shared\\pirate.avi";
			String outFileName = "C:\\morphufc\\shared\\bpirate.avi";
			File f = new File(infileName);
			File fileout = new File(outFileName);
			System.out.println(fileout.exists());
			FileInputStream fi = new FileInputStream(infileName);
			BufferedInputStream in = new BufferedInputStream(fi);
			
			int packetSize = 1048576;
			int packetSizeClone = packetSize;
			int numberPackets = 0;
			int data=0;
			int length = (int)f.length();
			System.out.println("l: " + length);
			if(length<packetSize)
	    		numberPackets = 1;
	    	else
	    		numberPackets = length/packetSize+1;
			int counter =0;
			int packetNumber = 0;
			int internalPacketByteCounter = 0;
			 
			byte[] packet = null;
            while((data = in.read())> -1){
            	 
            	if(counter%packetSize==0 && (length-counter>=packetSize)){
            		 
            		packet = new byte[packetSize];
            		packetNumber ++;
            		internalPacketByteCounter = 0;
            		 
            	}else if(length-counter<packetSize && counter%packetSize==0){
            		 
            		packet = new byte[length-counter];
            		packetSizeClone =length-counter;
            		 
            		packetNumber ++;
            		internalPacketByteCounter = 0;
            		 
    			 }
            	packet[internalPacketByteCounter] = (byte)data; 
            	if(internalPacketByteCounter==packetSizeClone-1)
            		Test.write(packet,outFileName);
            	//System.out.println((byte)data);
                counter++;
                internalPacketByteCounter++;
            }
           
            //Test.write(packet,outFileName,packetSize);
            fi.close();
            in.close();
            System.out.println("lf: " + fileout.length());
            System.out.println(packetNumber);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	
	
	public static void write(byte[] data, String fileName){
		System.out.println(data.length); 
		FileOutputStream file;
		try {
			file = new FileOutputStream(fileName,true);
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
		  
		/* // Write bbuf to filename
		System.out.println(data.length);
		ByteBuffer bbuf = ByteBuffer.wrap(data);
		//ByteBuffer bbuf = ByteBuffer.wrap(data);
		 
	    
	    
	    // Set to true if the bytes should be appended to the file;
	    // set to false if the bytes should replace current bytes
	    // (if the file exists)
		boolean append = true;
		 
	    
	    try {
	        // Create a writable file channel
	        FileChannel wChannel = new FileOutputStream(file, append).getChannel();
	    
	        // Write the ByteBuffer contents; the bytes between the ByteBuffer's
	        // position and the limit is written to the file
	        wChannel.write(bbuf);
	    
	        // Close the file
	        wChannel.close();
	        
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }*/
		
		 
	}
}
