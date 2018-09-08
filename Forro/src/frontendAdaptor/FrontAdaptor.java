package frontendAdaptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_main.CommandParser;
import frontendParserCCACaffeine_exceptions.ParserException;

public class FrontAdaptor {
	
	public enum FrontEndType {
		ParserCCACaffeine, ParserCCA	};
	
		private static FrontEndType frontType;
		
		
		/**
		 * Constructor 
		 * @param front
		 * @param forroDriver TODO
		 */
		public FrontAdaptor(FrontEndType front, ForroDriverRMIInterface forroDriver)  {
			FrontAdaptor.setFrontType(front);
			
			switch (FrontAdaptor.getFrontType())
			{
				case ParserCCACaffeine:
					
					String factoryClass = "frontendParserCCACaffeine_test_dummy_factory.DummyFactory";
					// Incluir no parser a interface com execute (ForroDriver) e substituir as chamadas 
					CommandParser cmdParser = new CommandParser(factoryClass);
					
					 
					
					try {
				        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				        String str = "";
				        while (str != null) {
				            System.out.print("cca> ");
				            str = in.readLine();
				            cmdParser.parse(str, forroDriver);
				            //System.out.println("read: " + str);
				        }
				    } 
					catch (IOException e) {
						e.printStackTrace();
				    } catch (ParserException e) {
						e.printStackTrace();
					}				
					
					break;
					
					
				default :
					System.out.println ("Invalid FrontEnd.");
					break;
			}
			
			
		}

		/**
		 * @return the frontType
		 */
		public static FrontEndType getFrontType() {
			return frontType;
		}

		/**
		 * @param frontType the frontType to set
		 */
		public static void setFrontType(FrontEndType front) {
			FrontAdaptor.frontType = front;
		}
		
		
		

	
}
