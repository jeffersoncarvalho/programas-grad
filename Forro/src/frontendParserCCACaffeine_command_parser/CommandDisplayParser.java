package frontendParserCCACaffeine_command_parser;

import java.util.ArrayList;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_constants.CommandsContants;
import frontendParserCCACaffeine_command_constants.ErrorsConstants;
import frontendParserCCACaffeine_command_interfaces.ICommandDisplayAction;
import frontendParserCCACaffeine_exceptions.FactoryException;
import frontendParserCCACaffeine_exceptions.ParserException;


public class CommandDisplayParser extends AbstractParser{

	private ArrayList<String> tokens;
	private ICommandDisplayAction cmdDispAction;
	
	public CommandDisplayParser(ArrayList<String> tokens, ICCACommandActionFactory factory) throws FactoryException {
	
		this.setFactory(factory);
		this.tokens = tokens;
		this.cmdDispAction = (ICommandDisplayAction)this.getFactory().create(FactoryConstants.DISPLAY);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) throws ParserException {
		
		if (tokens.size()==0)
			throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
		
		String firstToken = tokens.remove(0);
		if(firstToken.equals(CommandsContants.DISPLAY_PALETTE)
		   || firstToken.equals(CommandsContants.DISPLAY_PALLET)){
			
			this.displayPallet();
			
		}else if(firstToken.equals(CommandsContants.DISPLAY_ARENA)){
		
			this.displayArena();
			
		}else if(firstToken.equals(CommandsContants.DISPLAY_COMPONENT)){
		
			if (tokens.size()<1)
				throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
			else if (tokens.size()>1)
				throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
			this.displayComponent(tokens);
			
		}else if(firstToken.equals(CommandsContants.DISPLAY_CHAIN)){
		
			this.displayChain( );
			
		}else if(firstToken.equals(CommandsContants.DISPLAY_STATE)){
		
			this.displayArena();
			this.displayChain();
			
		}else throw new ParserException(ErrorsConstants.NOT_RECOG_COMMAND);
	}
	
	private void displayPallet()throws ParserException{
		
		this.cmdDispAction.displayPallet();
		
	}

	private void displayArena()throws ParserException{
	
		this.cmdDispAction.displayArena();
	}
	
	private void displayComponent(ArrayList<String> tokens)throws ParserException{
		
		if (tokens.size()==0)
			throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
		this.cmdDispAction.displayComponent(tokens.remove(0));
	}
	
	private void displayChain( )throws ParserException{
		
		 
		this.cmdDispAction.displayChain( );
		
	}
}
