package frontendParserCCACaffeine_command_parser;

import java.util.ArrayList;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_constants.ErrorsConstants;
import frontendParserCCACaffeine_command_interfaces.ICommandGoAction;
import frontendParserCCACaffeine_exceptions.FactoryException;
import frontendParserCCACaffeine_exceptions.ParserException;


public class CommandGoParser extends AbstractParser{

	private ArrayList<String> tokens;
	private ICommandGoAction cmdGoAction;
	
	public CommandGoParser(ArrayList<String> tokens, ICCACommandActionFactory factory) throws FactoryException {
	
		this.setFactory(factory);
		this.tokens = tokens;
		this.cmdGoAction = (ICommandGoAction)this.getFactory().create(FactoryConstants.GO);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) throws ParserException{
		if((tokens.size()<2)){
			throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
		}else if((tokens.size()>2)){
			throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
		}
		String componentName = tokens.remove(0);
		String portName = tokens.remove(0);

		this.cmdGoAction.go(componentName, portName, forroDriver);
	}
}


