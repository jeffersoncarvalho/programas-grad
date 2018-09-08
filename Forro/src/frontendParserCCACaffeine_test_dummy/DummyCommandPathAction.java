package frontendParserCCACaffeine_test_dummy;

import frontendParserCCACaffeine_command_interfaces.ICommandPathAction;
import frontendParserCCACaffeine_command_util.Writer;


/**
 * Call the CCAfeine action for 'display' command and respective parameters
 * @author Jefferson
 *
 */
public class DummyCommandPathAction implements ICommandPathAction{

	 
	
	public void pathAppend(String directory) {
	
		Writer.print("appending " + directory + "...");
	}

	public void pathPrepend(String directory) {
	
		Writer.print("prepending " + directory + "...");
		
	}
	
	public void pathInit() {
		
		Writer.print("path init...");
	}
	
	public void pathSet(String directory) {
		
		Writer.print("seting " + directory + "...");
	}
	
	public void path() {
		
		Writer.print("command path ...");
	}
}
