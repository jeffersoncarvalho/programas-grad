package frontendParserCCACaffeine_command_parser;

import java.util.ArrayList;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_constants.CommandsContants;
import frontendParserCCACaffeine_command_constants.ErrorsConstants;
import frontendParserCCACaffeine_command_interfaces.ICommandPathAction;
import frontendParserCCACaffeine_exceptions.FactoryException;
import frontendParserCCACaffeine_exceptions.ParserException;


public class CommandPathParser extends AbstractParser{

	private ArrayList<String> tokens;
	private ICommandPathAction cmdPthAction;
	
	public CommandPathParser(ArrayList<String> tokens, ICCACommandActionFactory factory) throws FactoryException{
	
		this.setFactory(factory);
		this.tokens = tokens;
		this.cmdPthAction = (ICommandPathAction)this.getFactory().create(FactoryConstants.PATH);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) throws ParserException {
		
		
		
		if(tokens.size()==0){
			
			this.cmdPthAction.path();
			
		}else {
			
			//taking ou a reserved word
			String firstToken = tokens.remove(0);
			 
			//analysing
			if(firstToken.equals(CommandsContants.PATH_APPEND)){
				
				if (tokens.size()==0)
					throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
				this.cmdPthAction.pathAppend(tokens.remove(0));
				
			}else if(firstToken.equals(CommandsContants.PATH_PREPEND)){
			
				if (tokens.size()==0)
					throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
				this.cmdPthAction.pathPrepend(tokens.remove(0));
				
			}else if(firstToken.equals(CommandsContants.PATH_SET)){
			
				if (tokens.size()==0)
					throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
				this.cmdPthAction.pathSet(tokens.remove(0));
				
			}else if(firstToken.equals(CommandsContants.PATH_INIT)){
			
				this.cmdPthAction.pathInit();
				 
			}else throw new ParserException(ErrorsConstants.NOT_RECOG_COMMAND);
			
		}//for other commands
	}

}
