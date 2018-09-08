package frontendParserCCACaffeine_test_dummy;

import frontendParserCCACaffeine_command_interfaces.ICommandConfigureAction;
import frontendParserCCACaffeine_command_util.Writer;


/**
 * Call the CCAfeine action for 'configure' command and respective parameters
 * @author Jefferson
 *
 */
public class DummyCommandConfigureAction implements ICommandConfigureAction{

	  
		
		 

	public void configure(String componentName) {
		Writer.print("configure " + componentName);
	 }
		

}
