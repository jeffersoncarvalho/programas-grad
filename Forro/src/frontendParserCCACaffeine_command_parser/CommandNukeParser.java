package frontendParserCCACaffeine_command_parser;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_interfaces.ICommandNukeAction;
import frontendParserCCACaffeine_exceptions.FactoryException;

public class CommandNukeParser extends AbstractParser{

	private ICommandNukeAction cmdNkAction;
	
	public CommandNukeParser(ICCACommandActionFactory factory) throws FactoryException{
	
		 this.setFactory(factory);
		 this.cmdNkAction = (ICommandNukeAction)this.getFactory().create(FactoryConstants.NUKE);
		 
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) {
		
		this.cmdNkAction.nuke();
		
	}

}
