package frontendParserCCACaffeine_command_parser;

import java.util.ArrayList;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_constants.ErrorsConstants;
import frontendParserCCACaffeine_command_interfaces.ICommandPortPropertyAction;
import frontendParserCCACaffeine_exceptions.FactoryException;
import frontendParserCCACaffeine_exceptions.ParserException;


public class CommandPortPropertyParser extends AbstractParser{

	private ArrayList<String> tokens;
	private ICommandPortPropertyAction cmdPortPropAction;
	
	public CommandPortPropertyParser(ArrayList<String> tokens, ICCACommandActionFactory factory) throws FactoryException{
	
		this.setFactory(factory);
		this.tokens = tokens;
		this.cmdPortPropAction = (ICommandPortPropertyAction)this.getFactory().create(FactoryConstants.PORTPROPERTY);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) throws ParserException {
		
		if (tokens.size()==0)
			throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
		
		 
		if(tokens.size()==2){
			
			 String componentInstanceName = tokens.remove(0);
			 String portName = tokens.remove(0);
			 this.cmdPortPropAction.portProperty(componentInstanceName, portName);
			
		}else if(tokens.size()==3){
		
			 String componentInstanceName = tokens.remove(0);
			 String portName = tokens.remove(0);
			 String key = tokens.remove(0);
			 this.cmdPortPropAction.portProperty(componentInstanceName, portName, key);
			
		}else if(tokens.size()==5){
		
			 String componentInstanceName = tokens.remove(0);
			 String portName = tokens.remove(0);
			 String key = tokens.remove(0);
			 String type = tokens.remove(0);
			 String value = tokens.remove(0);
			 this.cmdPortPropAction.portProperty(componentInstanceName, portName, key, type, value);
			 
		}else throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
	}

}
