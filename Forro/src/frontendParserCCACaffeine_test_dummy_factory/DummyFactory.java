package frontendParserCCACaffeine_test_dummy_factory;

import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_interfaces.IAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandArenaAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandConfigureAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandConnectAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandDebugAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandDisconnectAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandDisplayAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandGoAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandHelpAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandInstantiateAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandLinksAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandNoDebugAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandNukeAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandPalletAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandPathAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandPortPropertyAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandPropertyAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandQuitAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandRemoveAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandRepositoryAction;
import frontendParserCCACaffeine_test_dummy.DummyCommandShellAction;

public class DummyFactory implements ICCACommandActionFactory{

	public IAction create(int commandCode) {
		
		switch (commandCode) {
		case FactoryConstants.HELP:
			
			return new DummyCommandHelpAction();
			
		case FactoryConstants.EXIT:
			
			return new DummyCommandQuitAction();
			
		case FactoryConstants.SHELL:
			
			return new DummyCommandShellAction();
			
		case FactoryConstants.REPOSITORY:
			
			return new DummyCommandRepositoryAction();
			
		case FactoryConstants.REMOVE:
			
			return new DummyCommandRemoveAction();
			
		case FactoryConstants.PROPERTY:
			
			return new DummyCommandPropertyAction();
		
			
		case FactoryConstants.PATH:
			
			return new DummyCommandPathAction();
		
			
		case FactoryConstants.PALLET:
			
			return new DummyCommandPalletAction();
		
			
		case FactoryConstants.PORTPROPERTY:
			
			return new DummyCommandPortPropertyAction();
			
			
		case FactoryConstants.NUKE:
			
			return new DummyCommandNukeAction();
			
			
		case FactoryConstants.NODEBUG:
			
			return new DummyCommandNoDebugAction();
			
		case FactoryConstants.LINKS:
			
			return new DummyCommandLinksAction();
			
		case FactoryConstants.INSTANTIATE:
			
			return new DummyCommandInstantiateAction();
			
		case FactoryConstants.GO:
			
			return new DummyCommandGoAction();
			
		case FactoryConstants.DISPLAY:
			
			return new DummyCommandDisplayAction();
		
		case FactoryConstants.DISCONNECT:
			
			return new DummyCommandDisconnectAction();

		case FactoryConstants.DEBUG:
			
			return new DummyCommandDebugAction();
		

		case FactoryConstants.CONNECT:
			
			return new DummyCommandConnectAction();

		case FactoryConstants.CONFIGURE:
			
			return new DummyCommandConfigureAction();

		case FactoryConstants.ARENA:
			
			return new DummyCommandArenaAction();
			
		default:
			break;
		}
		
		return null;
	}

}
