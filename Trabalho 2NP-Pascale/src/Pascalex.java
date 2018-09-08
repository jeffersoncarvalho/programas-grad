import java.io.*;
import java.util.*;
//ANALISADOR LÉXICO DA LINGUAGEM PASCALE
public class Pascalex
{
	public Pascalex()
	{
	}
	
	private String leArquivo(String path)
	{
		String output="";
		
		File name=new File(path);
		if(name.exists())
		 if(name.isFile())	
		 {
		 	try
		 	{
		 		RandomAccessFile r = new RandomAccessFile(name,"r");
		 		StringBuffer buf = new StringBuffer();
		 		String linha;
		 		
		 		while((linha = r.readLine())!=null)
		 		 buf.append(linha+"\n");
		 		 
		 		output+=buf.toString();
		 		
		 	}
		 	catch(IOException e2)
		    {
		    	System.out.println("FILE ERROR.");
		    	//System.exit(0);
		 	}
		 }
		 
		 return output;
	}
	
	public static void main(String[] args) throws IOException
	{
				
		Analex alex = new Analex();
		Pascalex pascale=new Pascalex();
		
		//o arquivo que eu analiso deve ser dito aqui, no caso,teste.txt.
		ArrayList al = alex.analisaLexicamente(pascale.leArquivo("teste.txt"));
		
		String output="";
		
		for(int i=0;i<al.size();i++)
    	{
    		output+=al.get(i).toString();
    		output+="\n";
    	}
    	
    	System.out.println(output);
    	
    	
    	
    	
    	
    	
				
	}
}