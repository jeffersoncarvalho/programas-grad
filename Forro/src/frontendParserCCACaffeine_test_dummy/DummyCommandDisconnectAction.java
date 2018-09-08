package frontendParserCCACaffeine_test_dummy;

import frontendParserCCACaffeine_command_interfaces.ICommandDisconnectAction;
import frontendParserCCACaffeine_command_util.Writer;


/**
 * Call the CCAfeine action for 'disconnect' command and respective parameters
 * @author Jefferson
 *
 */
public class DummyCommandDisconnectAction implements ICommandDisconnectAction{

	 public void disconnect(String usingInstance, String usedPortName,
			             String providingInstance, String providedPortName){
		 
		 Writer.print("disconnecting " + usingInstance + " " + usedPortName + " " + providingInstance + " " + providedPortName);
	 }
}
