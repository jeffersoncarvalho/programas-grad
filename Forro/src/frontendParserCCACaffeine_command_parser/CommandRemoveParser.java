package frontendParserCCACaffeine_command_parser;

import java.util.ArrayList;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_constants.ErrorsConstants;
import frontendParserCCACaffeine_command_interfaces.ICommandRemoveAction;
import frontendParserCCACaffeine_exceptions.FactoryException;
import frontendParserCCACaffeine_exceptions.ParserException;


public class CommandRemoveParser extends AbstractParser{

	private ArrayList<String> tokens;
	private ICommandRemoveAction cmdRmvAction;
	
	public CommandRemoveParser(ArrayList<String> tokens, ICCACommandActionFactory factory) throws FactoryException {
	
		this.setFactory(factory);
		this.tokens = tokens;
		this.cmdRmvAction = (ICommandRemoveAction)this.getFactory().create(FactoryConstants.REMOVE);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) throws ParserException{
		if (tokens.size()<1)
			throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
		else if (tokens.size()>1)
			throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
		
		this.cmdRmvAction.remove(tokens.remove(0), forroDriver);
	}

}
