package frontendParserCCACaffeine_test_dummy;

import frontendParserCCACaffeine_command_interfaces.ICommandNoDebugAction;
import frontendParserCCACaffeine_command_util.Writer;

/**
 * Call the CCAfeine action for 'nodebug, quiet' command. No parameters needed.
 * @author Jefferson
 *
 */
public class DummyCommandNoDebugAction implements ICommandNoDebugAction {

	public void noDebug() {
		 Writer.print("Nodebugging...");
		
	}

	 
	  

}
