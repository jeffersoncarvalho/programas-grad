package frontendParserCCACaffeine_test_dummy;

import frontendParserCCACaffeine_command_interfaces.ICommandDebugAction;
import frontendParserCCACaffeine_command_util.Writer;

/**
 * Call the CCAfeine action for 'debug, noisy' command. No parameters needed.
 * @author Jefferson
 *
 */
public class DummyCommandDebugAction  implements ICommandDebugAction{

	 
	 public void action(){
		 
		 Writer.print("debuggiing ...");
	 }

}
