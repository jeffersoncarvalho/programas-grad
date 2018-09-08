package frontendParserCCACaffeine_test_dummy;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_interfaces.ICommandHelpAction;
import frontendParserCCACaffeine_command_util.Writer;


/**
 * Call the CCAfeine action for 'help' command.
 * @author Jefferson
 *
 */
public class DummyCommandHelpAction implements ICommandHelpAction{
 

 

	public void help(ForroDriverRMIInterface forroDriver) {
		 //testing
		 Writer.print("Executing help, system command...");
		
	}
}
