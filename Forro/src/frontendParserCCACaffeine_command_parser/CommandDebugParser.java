package frontendParserCCACaffeine_command_parser;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_interfaces.ICommandDebugAction;
import frontendParserCCACaffeine_exceptions.FactoryException;

public class CommandDebugParser extends AbstractParser{

	private ICommandDebugAction cmdDbgAction;
	
	
	public CommandDebugParser(ICCACommandActionFactory factory) throws FactoryException {
	
		 this.setFactory(factory);
		 this.cmdDbgAction = (ICommandDebugAction)this.getFactory().create(FactoryConstants.DEBUG);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) {
		
		this.cmdDbgAction.action();
		
	}

}
