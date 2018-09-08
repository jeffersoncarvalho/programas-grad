package frontendParserCCACaffeine_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import frontendParserCCACaffeine_command_main.CommandParser;
import frontendParserCCACaffeine_exceptions.ParserException;


public class Test {

	public static void main(String[] args) {
		
		String factoryClass = "frontendParserCCACaffeine_test_dummy_factory.DummyFactory";
		 
		CommandParser cmdParser = new CommandParser(factoryClass);
		
		 
		
		try {
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        String str = "";
	        while (str != null) {
	            System.out.print("cca> ");
	            str = in.readLine();
	            cmdParser.parse(str, null);
	            //System.out.println("read: " + str);
	        }
	    } 
		catch (IOException e) {
			e.printStackTrace();
	    } catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}

 