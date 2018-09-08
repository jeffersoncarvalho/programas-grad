package frontendParserCCACaffeine_command_parser;

import java.util.ArrayList;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_constants.ErrorsConstants;
import frontendParserCCACaffeine_command_interfaces.ICommandDisconnectAction;
import frontendParserCCACaffeine_exceptions.FactoryException;
import frontendParserCCACaffeine_exceptions.ParserException;


public class CommandDisconnectParser extends AbstractParser{

	private ArrayList<String> tokens;
	private ICommandDisconnectAction cmdDsnctAction ;
	
	public CommandDisconnectParser(ArrayList<String> tokens, ICCACommandActionFactory factory) throws FactoryException {

		this.setFactory(factory);
		this.tokens = tokens;
		this.cmdDsnctAction = (ICommandDisconnectAction)this.getFactory().create(FactoryConstants.DISCONNECT);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) throws ParserException{
		 
		if((tokens.size()<4))
				throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND); 
		else if((tokens.size()>4))
			throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
			
		String usingInstance = tokens.remove(0);
		String usedPortName = tokens.remove(0);
		String providingInstance = tokens.remove(0);
		String providedPortName = tokens.remove(0);
		
		this.cmdDsnctAction.disconnect(usingInstance, usedPortName,
									   providingInstance, providedPortName);
		 
	}

}
