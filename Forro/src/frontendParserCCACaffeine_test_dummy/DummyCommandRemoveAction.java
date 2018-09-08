package frontendParserCCACaffeine_test_dummy;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_interfaces.ICommandRemoveAction;
import frontendParserCCACaffeine_command_util.Writer;


/**
 * Call the CCAfeine action for 'remove' command and respective parameters
 * @author Jefferson
 *
 */
public class DummyCommandRemoveAction implements ICommandRemoveAction {

	 public void remove(String componentInstanceName, ForroDriverRMIInterface forroDriver){
		 
		 Writer.print("Executing remove command for parameter: " + componentInstanceName+"...");
	 }
}
