package frontendParserCCACaffeine_command_parser;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_interfaces.ICommandPalletAction;
import frontendParserCCACaffeine_exceptions.FactoryException;

public class CommandPalletParser extends AbstractParser{

	private ICommandPalletAction cmdPltAction;
	
	public CommandPalletParser(ICCACommandActionFactory factory) throws FactoryException{
	
		 this.setFactory(factory);
		 this.cmdPltAction = (ICommandPalletAction)this.getFactory().create(FactoryConstants.PALLET);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) {
		
		this.cmdPltAction.pallet();
	}

}
