package frontendParserCCACaffeine_command_interfaces;



/**
 * Call the CCAfeine action for 'disconnect' command and respective parameters
 * @author Jefferson
 *
 */
public interface ICommandDisconnectAction extends IAction{

	 public void disconnect(String usingInstance, String usedPortName,
			             String providingInstance, String providedPortName);
}
