package frontendParserCCACaffeine_test_dummy;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_interfaces.ICommandShellAction;
import frontendParserCCACaffeine_command_util.Writer;


/**
 * Call the CCAfeine action for 'shell, system' command.
 * @author Jefferson
 *
 */
public class DummyCommandShellAction implements ICommandShellAction{
 

	public void shell(ForroDriverRMIInterface forroDriver) {
//		testing
		 Writer.print("Executing shell, system command...");
		
	}
}
