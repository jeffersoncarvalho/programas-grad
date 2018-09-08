package frontendParserCCACaffeine_test;

import frontendParserCCACaffeine_command_main.CommandParser;
import frontendParserCCACaffeine_exceptions.ParserException;

public class FireTest {

	public static void main(String[] args) {
		String[] test = {"exit",
						 "bye",
						 "x",
						 "quit",
						 
						 "help",
						 
						 "shell",
						 
						 "repository list",
						 "repository get <something>",
						 "repository get-ports <something>",
						 "repository get-global <something>",
						 "repository get-lazy <something>",
						 "repository get-lazy-global <something>",
						 "repository init <sessionName>",
						 "repository init-lazy <something>",
						 "repository init-lazy-global <something>",
						 "repository init-global <something1> <something2>",
						 
						 "remove <something>",
						 
						 "property <something1>",
						 "property <something1> <something2>",
						 "property <something1> <something2> <something3>",
						 
						 "path ",
						 "path prepend <something>",
						 "path append <something>",
						 "path set <something>",
						 "path init",
 
						  "pallet",
						  "palette",
						  "classes",
							
						  "port-property 1 2",
						  "port-property 1 2 3",
						  "port-property 1 2 3 4 5",
						  
						  "nuke",
							
						  "nodebug",
						  "quiet",
							
						
						   "links",
						   "chain",
							
							 
						   "instantiate <something> <where>",
						   "pulldown <something>",
						   "create <something>",
							
						   "go <component> <port>",
						   "run <something>",
							
							
							 
							"display palette",
							"display pallet",
							"display arena",
							"display chain",
							"display component <something>",
							"display state",
							
							"disconnect 1 2 3 4",
							"debug",
							"noisy",
							
							
							"connect usingInstance usedPortName  String providingInstance providedPortName",
							
							
							"configure <something>",
							"parameters <something>",
							
							
							"arena",
							"instances"
						};
		
		String factoryClass = "frontendParserCCACaffeine_test_dummy_factory.DummyFactory";
		CommandParser cmdParser = new CommandParser(factoryClass);
		
		for(int j=0;j<100;j++)
			for(int i=0;i<test.length;i++){
				System.out.println("###Analysing commmand: " + test[i]);
				try {
					cmdParser.parse(test[i], null);
				} catch (ParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
	}
}
