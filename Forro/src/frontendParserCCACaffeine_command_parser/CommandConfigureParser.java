package frontendParserCCACaffeine_command_parser;

import java.util.ArrayList;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_constants.ErrorsConstants;
import frontendParserCCACaffeine_command_interfaces.ICommandConfigureAction;
import frontendParserCCACaffeine_exceptions.FactoryException;
import frontendParserCCACaffeine_exceptions.ParserException;



public class CommandConfigureParser extends AbstractParser{

	private ArrayList<String> tokens; 
	private ICommandConfigureAction cmdConfigureAction; 
	
	public CommandConfigureParser(ArrayList<String> tokens, ICCACommandActionFactory factory) throws FactoryException {
		 
		this.setFactory(factory);
		this.tokens = tokens;
		this.cmdConfigureAction = (ICommandConfigureAction)this.getFactory().create(FactoryConstants.CONFIGURE);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) throws ParserException {
		if((tokens.size()==0))
			throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);	
		this.cmdConfigureAction.configure(tokens.remove(0));
	}

}
