package br.ufc.lia.sd.morphufc.util;

/*
 * Created on Sep 2, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
 

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author jefferson
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FileUtil {
	public static final String curDir =System.getProperty("user.dir")+File.separatorChar;
	
	/**
	 * 
	 */
	public FileUtil() {
		super();
		 
	}
	
	//Process only files under dir
     public static void visitAllFiles(File dir, List files, String regx) {
    	 
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                visitAllFiles(new File(dir, children[i]),files, regx);
            }
        } else {
        	if(dir.getName().toLowerCase().contains(regx.toLowerCase())){
        		MetaFile metaFile = new MetaFile();
        		metaFile.setName(dir.getName());
        		metaFile.setSize(dir.length()/1048576.0);
        		files.add(metaFile);
        	}
        		 
        }
    } 
    
    /*
     * Pegando o properties
     */
    public static Properties getPropertiesFile(){
    	Properties properties = new Properties();
    	 
        try {
            properties.load(new FileInputStream(FileUtil.curDir+"morphufc.properties"));
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        return properties;
    }
    
    public static boolean existsOnReceived(String fileName){
    	File file = new File(FileUtil.curDir+"received"+File.separatorChar+fileName);
    	return file.exists();
    }
    
    public static void deleteOnReceived(String fileName){
    	File file = new File(FileUtil.curDir+"received"+File.separatorChar+fileName);
    	file.delete();
    }
    
    public static void main(String[] args) {
		
    	System.out.println(FileUtil.existsOnReceived("mozart.wma")); 
		 
		 
    	 
	}
}

