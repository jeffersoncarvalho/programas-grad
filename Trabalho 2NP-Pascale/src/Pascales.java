//ANALISADOR SINTÁTICO DA LINGUAGEM PASCALE
import java.io.*;


public class Pascales
{
	public Pascales()
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
	
    	
    	Pascales pascale=new Pascales();
    	String codigo = pascale.leArquivo("teste.txt");
    	AnalisadorSintatico as = new AnalisadorSintatico();	
    	as.analisaSintaticamente(codigo); 
    	
    	
				
	}
}