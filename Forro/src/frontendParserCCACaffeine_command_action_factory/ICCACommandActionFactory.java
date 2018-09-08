package frontendParserCCACaffeine_command_action_factory;

import frontendParserCCACaffeine_command_interfaces.IAction;
import frontendParserCCACaffeine_exceptions.FactoryException;

public interface ICCACommandActionFactory {
	
	public IAction create(int commandCode) throws FactoryException;

}
