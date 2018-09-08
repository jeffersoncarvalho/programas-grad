package frontendParserCCACaffeine_command_parser;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_interfaces.ICommandLinksAction;
import frontendParserCCACaffeine_exceptions.FactoryException;


public class CommandLinksParser extends AbstractParser{

	private ICommandLinksAction cmdLnkAction;
	
	public CommandLinksParser(ICCACommandActionFactory factory) throws FactoryException{
		this.setFactory(factory);
		this.cmdLnkAction = (ICommandLinksAction)this.getFactory().create(FactoryConstants.LINKS);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) {
		 
		//There is no need to parse, just call the action.
		this.cmdLnkAction.links();
		
	}

}
