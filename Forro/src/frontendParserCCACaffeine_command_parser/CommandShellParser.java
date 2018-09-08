package frontendParserCCACaffeine_command_parser;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_interfaces.ICommandShellAction;
import frontendParserCCACaffeine_exceptions.FactoryException;

/**
 * Command for shell or system. 
 * @author Jefferson
 *
 */
public class CommandShellParser extends AbstractParser{

	private ICommandShellAction cmdShellAction;
	
	public CommandShellParser(ICCACommandActionFactory factory, ForroDriverRMIInterface forroDriver) throws FactoryException{
		
		this.setFactory(factory);
		this.cmdShellAction = (ICommandShellAction)this.getFactory().create(FactoryConstants.SHELL);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) {
		
		this.cmdShellAction.shell(forroDriver);
	}

}
