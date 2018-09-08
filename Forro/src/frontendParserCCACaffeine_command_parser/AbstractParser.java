package frontendParserCCACaffeine_command_parser;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_exceptions.ParserException;

 

public abstract class AbstractParser {

	private ICCACommandActionFactory factory;
	
	public abstract void parse(ForroDriverRMIInterface forroDriver) throws ParserException;

	public ICCACommandActionFactory getFactory() {
		return factory;
	}

	public void setFactory(ICCACommandActionFactory factory) {
		this.factory = factory;
	}
}
