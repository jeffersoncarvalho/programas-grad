package frontendParserCCACaffeine_command_main;

 
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import ccacore.Component;

import forrocore.ForroDriver;
import forrocore.ForroDriverRMIInterface;
import forrocore.TypeMapKeys;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_constants.CommandsContants;
import frontendParserCCACaffeine_command_constants.ErrorsConstants;
import frontendParserCCACaffeine_command_parser.CommandArenaParser;
import frontendParserCCACaffeine_command_parser.CommandConfigureParser;
import frontendParserCCACaffeine_command_parser.CommandConnectParser;
import frontendParserCCACaffeine_command_parser.CommandDebugParser;
import frontendParserCCACaffeine_command_parser.CommandDisconnectParser;
import frontendParserCCACaffeine_command_parser.CommandDisplayParser;
import frontendParserCCACaffeine_command_parser.CommandGoParser;
import frontendParserCCACaffeine_command_parser.CommandHelpParser;
import frontendParserCCACaffeine_command_parser.CommandInstantiateParser;
import frontendParserCCACaffeine_command_parser.CommandLinksParser;
import frontendParserCCACaffeine_command_parser.CommandNoDebugParser;
import frontendParserCCACaffeine_command_parser.CommandNukeParser;
import frontendParserCCACaffeine_command_parser.CommandPalletParser;
import frontendParserCCACaffeine_command_parser.CommandPathParser;
import frontendParserCCACaffeine_command_parser.CommandPortPropertyParser;
import frontendParserCCACaffeine_command_parser.CommandPropertyParser;
import frontendParserCCACaffeine_command_parser.CommandQuitParser;
import frontendParserCCACaffeine_command_parser.CommandRemoveParser;
import frontendParserCCACaffeine_command_parser.CommandRepositoryParser;
import frontendParserCCACaffeine_command_parser.CommandShellParser;
import frontendParserCCACaffeine_exceptions.FactoryException;
import frontendParserCCACaffeine_exceptions.ParserException;


public class CommandParser {

	private ArrayList<String> tokens;
	
	//parsers
	private CommandHelpParser cmdHelpParser;
	private CommandQuitParser cmdQuitParser;
	private CommandShellParser cmdShellParser;
	private CommandRepositoryParser cmdRepositoryParser;
	private CommandRemoveParser cmdRemoveParser;
	private CommandPropertyParser cmdPropertyParser;
	private CommandPathParser cmdPathParser;
	private CommandPalletParser cmdPalletParser;
	private CommandPortPropertyParser cmdPortPropertyParser;
	private CommandNukeParser cmdNukeParser;
	private CommandNoDebugParser cmdNoDebugParser;
	private CommandLinksParser cmdLinksParser;
	private CommandInstantiateParser cmdInstantiateParser;
	private CommandGoParser cmdGoParser;
	private CommandDisplayParser cmdDisplayParser;
	private CommandDisconnectParser cmdDisconnectParser;
	private CommandDebugParser cmdDebugParser;
	private CommandConnectParser cmdConnectParser;
	private CommandArenaParser cmdArenaParser;
	private CommandConfigureParser cmdConfigureParser;
	
	//Factory
	private ICCACommandActionFactory factory;
	
	public CommandParser(String factoryClassName) {
		
		try {
			this.factory = (ICCACommandActionFactory)Class.forName(factoryClassName).newInstance();
			
//	        try {		
//	        	Class[] paramTypes = {ForroDriver.class};
//	        	Object[] args = {forroDriver};
//
//				this.factory = (ICCACommandActionFactory) Class.forName(factoryClassName).getConstructor(paramTypes).newInstance(args);
//
//	        } catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				e.printStackTrace();
//			} catch (InvocationTargetException e) {
//				e.printStackTrace();
//			} catch (NoSuchMethodException e) {
//				e.printStackTrace();
//			}
			
			
		} catch (InstantiationException e) {
			 
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			 
			e.printStackTrace();
		}
		
		this.tokens = new ArrayList<String>();
	}
	
	public void parse(String commandLine, ForroDriverRMIInterface forroDriver) throws ParserException{
		
		//cleaning
		commandLine = commandLine.trim();
		this.tokens.clear();
		
		String[] commands = commandLine.split(" ");
		 
		if(commands==null || commands.length==0)
			throw new ParserException(ErrorsConstants.BLANK_COMMAND);
		
		for(int i=0;i<commands.length;i++)
			tokens.add(commands[i]);
		
		String firstToken = tokens.remove(0);
		
		 
		
		try{
			if(firstToken.equals(CommandsContants.HELP)){
				
				if(this.cmdHelpParser==null){
					 
					this.cmdHelpParser = new CommandHelpParser(this.factory, forroDriver);	
				}
				
				this.cmdHelpParser.parse(forroDriver);
			
			}else if(firstToken.equals(CommandsContants.X)
					 || firstToken.equals(CommandsContants.BYE)
					 || firstToken.equals(CommandsContants.QUIT)
					 || firstToken.equals(CommandsContants.EXIT)){
				
				if(this.cmdQuitParser==null){
					this.cmdQuitParser = new CommandQuitParser(this.factory, forroDriver);
					 
				}
				
				this.cmdQuitParser.parse(forroDriver);
				
			}else if(firstToken.equals(CommandsContants.SHELL)
			   || firstToken.equals(CommandsContants.SYSTEM) ){
			 
				if(this.cmdShellParser==null){
					this.cmdShellParser = new CommandShellParser(this.factory, forroDriver);
				}
					
				this.cmdShellParser.parse(forroDriver);
			   
			     
			} else if(firstToken.equals(CommandsContants.REPOSITORY)){
			
				 
				if(this.cmdRepositoryParser==null){
					this.cmdRepositoryParser = new CommandRepositoryParser(tokens,this.factory);
					 
				}
					
				this.cmdRepositoryParser.parse(forroDriver);
				
			} else if (firstToken.equals(CommandsContants.REMOVE)){
				
				if(this.cmdRemoveParser==null){
					this.cmdRemoveParser = new CommandRemoveParser(tokens,this.factory);
					 
				}
					
				this.cmdRemoveParser.parse(forroDriver);
				 
				
			}else if (firstToken.equals(CommandsContants.PROPERTY)){
				
				if(this.cmdPropertyParser==null){
					this.cmdPropertyParser = new CommandPropertyParser(tokens,this.factory);
					 
				}
					
				this.cmdPropertyParser.parse(forroDriver); 
				
			}else if (firstToken.equals(CommandsContants.PATH)){
				
				if(this.cmdPathParser==null){
					this.cmdPathParser = new CommandPathParser(tokens,this.factory);
					 
				}
					
				this.cmdPathParser.parse(forroDriver); 
				
			}else if (firstToken.equals(CommandsContants.PALETTE) 
					  || firstToken.equals(CommandsContants.PALLET)
					  || firstToken.equals(CommandsContants.CLASSES)){
				
				if(this.cmdPalletParser==null){
					this.cmdPalletParser = new CommandPalletParser(this.factory);
					 
				}
					
				this.cmdPalletParser.parse(forroDriver); 
				
			}else if (firstToken.equals(CommandsContants.PORTPROPERTY)){
				
				if(this.cmdPortPropertyParser==null){
					this.cmdPortPropertyParser = new CommandPortPropertyParser(tokens,this.factory);
					 
				}
					
				this.cmdPortPropertyParser.parse(forroDriver);
				
			}else if (firstToken.equals(CommandsContants.NUKE)){
				
				if(this.cmdNukeParser==null){
					this.cmdNukeParser = new CommandNukeParser(this.factory);
					 
				}
					
				this.cmdNukeParser.parse(forroDriver);
				
			}else if (firstToken.equals(CommandsContants.NODEBUG)
					  || firstToken.equals(CommandsContants.QUIET)){
				
				if(this.cmdNoDebugParser==null){
					this.cmdNoDebugParser = new CommandNoDebugParser(this.factory);
					 
				}
					
				this.cmdNoDebugParser.parse(forroDriver);
				
			}else if (firstToken.equals(CommandsContants.LINKS)
					  || firstToken.equals(CommandsContants.CHAIN)){
				
				if(this.cmdLinksParser==null){
					this.cmdLinksParser = new CommandLinksParser(this.factory);
					 
				}
					
				this.cmdLinksParser.parse(forroDriver);
				
			}else if (firstToken.equals(CommandsContants.PULLDOWN)
					  || firstToken.equals(CommandsContants.INSTANTIATE)
					  || firstToken.equals(CommandsContants.CREATE)){
				
				if(this.cmdInstantiateParser==null){
					this.cmdInstantiateParser = new CommandInstantiateParser(tokens,this.factory);
					 
				}
					
				this.cmdInstantiateParser.parse(forroDriver);
				
			}else if (firstToken.equals(CommandsContants.GO)
					  || firstToken.equals(CommandsContants.RUN)){
				
				if(this.cmdGoParser==null){
					this.cmdGoParser = new CommandGoParser(tokens,this.factory);
					 
				}
					
				this.cmdGoParser.parse(forroDriver);
				
			}else if (firstToken.equals(CommandsContants.DISPLAY)){
				
				if(this.cmdDisplayParser==null){
					this.cmdDisplayParser = new CommandDisplayParser(tokens,this.factory);
					 
				}
					
				this.cmdDisplayParser.parse(forroDriver);
				
			}else if (firstToken.equals(CommandsContants.DISCONNECT)){
				
				if(this.cmdDisconnectParser==null){
					this.cmdDisconnectParser = new CommandDisconnectParser(tokens,this.factory);
					 
				}
					
				this.cmdDisconnectParser.parse(forroDriver);
				
			}else if (firstToken.equals(CommandsContants.DEBUG)
					  || firstToken.equals(CommandsContants.NOISY) ){
				
				if(this.cmdDebugParser==null){
					this.cmdDebugParser = new CommandDebugParser(this.factory);
					 
				}
					
				this.cmdDebugParser.parse(forroDriver);
				
			}else if (firstToken.equals(CommandsContants.CONNECT)){
				
				if(this.cmdConnectParser==null){
					this.cmdConnectParser = new CommandConnectParser(tokens,this.factory);
					 
				}
					
				this.cmdConnectParser.parse(forroDriver); 
				
			}else if (firstToken.equals(CommandsContants.CONFIGURE)
					  || firstToken.equals(CommandsContants.PARAMETERS)){
				
				 if(this.cmdConfigureParser==null){
					 this.cmdConfigureParser = new CommandConfigureParser(tokens,this.factory);
					  
				 }
				 
				 this.cmdConfigureParser.parse(forroDriver);
					 
				
			}else if (firstToken.equals(CommandsContants.ARENA)
					  || firstToken.equals(CommandsContants.INSTANCES)){
				
				if(this.cmdArenaParser==null){
					this.cmdArenaParser = new CommandArenaParser( this.factory);
					 
				}
					
				this.cmdArenaParser.parse(forroDriver); 
				 
			}else if (!firstToken.equals("")){
				throw new ParserException(ErrorsConstants.NOT_RECOG_COMMAND);
			}
				
		}catch(FactoryException fexc){
			
			fexc.printStackTrace();
		}
		
	}
	
	  
}
