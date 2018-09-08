package frontendParserCCACaffeine_command_parser;

import java.util.ArrayList;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_constants.ErrorsConstants;
import frontendParserCCACaffeine_command_interfaces.ICommandPropertyAction;
import frontendParserCCACaffeine_exceptions.FactoryException;
import frontendParserCCACaffeine_exceptions.ParserException;


public class CommandPropertyParser extends AbstractParser{

	private ArrayList<String> tokens;
	private ICommandPropertyAction cmdPropAction;
	
	public CommandPropertyParser(ArrayList<String> tokens, ICCACommandActionFactory factory) throws FactoryException{
		
		this.setFactory(factory);
		this.tokens = tokens;
		this.cmdPropAction = (ICommandPropertyAction)this.getFactory().create(FactoryConstants.PROPERTY);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) throws ParserException {
		
		if (tokens.size()==0)
			throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
		
		 
		if(tokens.size()==1){
			
			 this.cmdPropAction.property(tokens.remove(0));
			
		}else if(tokens.size()==2){
		
			 String componentInstanceName = tokens.remove(0);
			 String key = tokens.remove(0);
			 this.cmdPropAction.property(componentInstanceName, key);
			
		}else if(tokens.size()==3){
		
			 String componentInstanceName = tokens.remove(0);
			 String key = tokens.remove(0);
			 String value = tokens.remove(0);
			 this.cmdPropAction.property(componentInstanceName, key, value);
			 
		}else throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
	}

}
