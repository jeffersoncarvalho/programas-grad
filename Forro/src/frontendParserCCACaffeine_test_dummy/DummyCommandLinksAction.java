package frontendParserCCACaffeine_test_dummy;

import frontendParserCCACaffeine_command_interfaces.ICommandLinksAction;
import frontendParserCCACaffeine_command_util.Writer;

/**
 * Call the CCAfeine action for 'links, chain' command. No parameters needed.
 * @author Jefferson
 *
 */
public class DummyCommandLinksAction implements ICommandLinksAction {

	public void links() {
		 Writer.print("links...");
		
	}

	 
	  

}
