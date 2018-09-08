package frontendParserCCACaffeine_command_parser;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_interfaces.ICommandQuitAction;
import frontendParserCCACaffeine_exceptions.FactoryException;

/**
 * Command for shell or system. 
 * @author Jefferson
 *
 */
public class CommandQuitParser extends AbstractParser{

	private ICommandQuitAction cmdQuitAction;
	
	public CommandQuitParser(ICCACommandActionFactory factory, ForroDriverRMIInterface forroDriver) throws FactoryException{
		
		this.setFactory(factory);
		this.cmdQuitAction = (ICommandQuitAction)this.getFactory().create(FactoryConstants.EXIT);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) {
		
		this.cmdQuitAction.quit(null);
	}

}
