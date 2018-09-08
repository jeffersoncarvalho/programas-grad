package frontendParserCCACaffeine_test_dummy;

import frontendParserCCACaffeine_command_interfaces.ICommandDisplayAction;
import frontendParserCCACaffeine_command_util.Writer;


/**
 * Call the CCAfeine action for 'display' command and respective parameters
 * @author Jefferson
 *
 */
public class DummyCommandDisplayAction implements ICommandDisplayAction{

	 
	
	public void displayPallet() {
		
		Writer.print("display list...");
	}

	public void displayArena() {
		
		Writer.print("display arena...");
	}
	
	public void displayComponent(String componentIntanceName) {
		
		Writer.print("display component for "+ componentIntanceName+"...");
	}
	
	public void displayChain( ) {
		
		Writer.print("display chain ");
	}
}
