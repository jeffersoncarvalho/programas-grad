package frontendParserCCACaffeine_test_dummy;

import frontendParserCCACaffeine_command_interfaces.ICommandNukeAction;
import frontendParserCCACaffeine_command_util.Writer;

/**
 * Call the CCAfeine action for 'nuke' command. No parameters needed.
 * @author Jefferson
 *
 */
public class DummyCommandNukeAction implements ICommandNukeAction {

	 
	  

	public void nuke() {
		Writer.print("nuking...");
		
	}

}
