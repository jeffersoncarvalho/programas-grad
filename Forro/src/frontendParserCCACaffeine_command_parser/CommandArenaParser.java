package frontendParserCCACaffeine_command_parser;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_interfaces.ICommandArenaAction;
import frontendParserCCACaffeine_exceptions.FactoryException;

public class CommandArenaParser extends AbstractParser{

	private ICommandArenaAction cmdArnAction;
	
	public CommandArenaParser(ICCACommandActionFactory factory) throws FactoryException{
			this.setFactory(factory);
			this.cmdArnAction = (ICommandArenaAction)this.getFactory().create(FactoryConstants.ARENA);
		 
		 
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) {
		
		this.cmdArnAction.arena();
		
	}

}
