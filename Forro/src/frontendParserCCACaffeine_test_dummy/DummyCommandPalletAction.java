package frontendParserCCACaffeine_test_dummy;

import frontendParserCCACaffeine_command_interfaces.ICommandPalletAction;
import frontendParserCCACaffeine_command_util.Writer;

/**
 * Call the CCAfeine action for 'arena, instances' command. No parameters needed.
 * @author Jefferson
 *
 */
public class DummyCommandPalletAction  implements ICommandPalletAction{

	public void pallet() {
		Writer.print("printig pallet");
		
	}

	 
	  

}
