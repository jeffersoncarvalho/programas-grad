package frontendParserCCACaffeine_test_dummy;

import frontendParserCCACaffeine_command_interfaces.ICommandPropertyAction;
import frontendParserCCACaffeine_command_util.Writer;


/**
 * Call the CCAfeine action for 'instance' command and respective parameters
 * @author Jefferson
 *
 */
public class DummyCommandPropertyAction implements ICommandPropertyAction{

	 
	
	public void property(String componentInstanceName) {
		
		Writer.print("Executing command property for " + componentInstanceName+"...");
	}

	public void property(String componentInstanceName, String key) {
		
		Writer.print("Executing command property for " + componentInstanceName+" "+  key + "...");
	}
	
	public void property(String componentInstanceName, String key, String value) {
		Writer.print("Executing command property for " + componentInstanceName+" "+  key + " " + value+"...");
		
	}
	 
}
