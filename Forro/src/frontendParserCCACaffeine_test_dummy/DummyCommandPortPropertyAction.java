package frontendParserCCACaffeine_test_dummy;

import frontendParserCCACaffeine_command_interfaces.ICommandPortPropertyAction;
import frontendParserCCACaffeine_command_util.Writer;


/**
 * Call the CCAfeine action for 'instance' command and respective parameters
 * @author Jefferson
 *
 */
public class DummyCommandPortPropertyAction implements ICommandPortPropertyAction{

	 
	
	public void portProperty(String componentInstanceName, String portName) {
		
		Writer.print("portProperty for " + componentInstanceName+ " " + portName);
		
	}

	public void portProperty(String componentInstanceName, String portName, String key) {
		Writer.print("portProperty for " + componentInstanceName+ " " + portName + " " + key);
	}
	
	public void portProperty(String componentInstanceName, String portName, String key, String type, String value) {
		
		Writer.print("portProperty for " + componentInstanceName+ " " + portName + " " + key + " " + type + " " + value);
	}
	 
}
