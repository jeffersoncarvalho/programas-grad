package locadora.util;

import java.io.*;


public class Teclado
{
	public static String leConsole()
	{
		String str = "";
		try 
		{
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        
	        while (str != null) {
	            str = in.readLine();
	            return str;
	        }
	    } 
	    catch (IOException e) {}
	  return str;
	}
	
	public static int leConsoleInteger()
	{
		return Integer.parseInt(leConsole());	
	}
	
}