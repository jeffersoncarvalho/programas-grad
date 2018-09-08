package frontendParserCCACaffeine_command_parser;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_interfaces.ICommandHelpAction;
import frontendParserCCACaffeine_exceptions.FactoryException;

/**
 * Command for shell or system. 
 * @author Jefferson
 *
 */
public class CommandHelpParser extends AbstractParser{

	private ICommandHelpAction cmdHelpAction;
	
	public CommandHelpParser(ICCACommandActionFactory factory, ForroDriverRMIInterface forroDriver) throws FactoryException{
		this.setFactory(factory);
		this.cmdHelpAction = (ICommandHelpAction)this.getFactory().create(FactoryConstants.HELP);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) {
		
		this.cmdHelpAction.help(forroDriver);
	}

}
