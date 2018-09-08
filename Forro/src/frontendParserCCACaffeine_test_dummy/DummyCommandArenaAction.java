package frontendParserCCACaffeine_test_dummy;

import frontendParserCCACaffeine_command_interfaces.ICommandArenaAction;
import frontendParserCCACaffeine_command_util.Writer;

/**
 * Call the CCAfeine action for 'pallet,pallette,classes' command. No parameters needed.
 * @author Jefferson
 *
 */
public class DummyCommandArenaAction  implements ICommandArenaAction{

	 
	  

	public void arena() {
		Writer.print("showing arena...");
		
	}

}
