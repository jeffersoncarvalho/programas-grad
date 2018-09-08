package frontendParserCCACaffeine_command_parser;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_interfaces.ICommandNoDebugAction;
import frontendParserCCACaffeine_exceptions.FactoryException;


public class CommandNoDebugParser extends AbstractParser{

	private ICommandNoDebugAction cmdNDbgAction;
	
	public CommandNoDebugParser(ICCACommandActionFactory factory) throws FactoryException{
		
		this.setFactory(factory);
		this.cmdNDbgAction = (ICommandNoDebugAction)this.getFactory().create(FactoryConstants.NODEBUG);
		
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) {
		 
		//There is no need to parse, just call the action.
		this.cmdNDbgAction.noDebug();
	}

}
