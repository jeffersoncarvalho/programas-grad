package frontendParserCCACaffeine_test_dummy;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_interfaces.ICommandQuitAction;
import frontendParserCCACaffeine_command_util.Writer;


/**
 * Call the CCAfeine action for 'help' command.
 * @author Jefferson
 *
 */
public class DummyCommandQuitAction implements ICommandQuitAction{

	 
	public void quit(ForroDriverRMIInterface forroDriver) {
		 Writer.print("Executing quit, x, bye, exit command...");
		
	}
}
