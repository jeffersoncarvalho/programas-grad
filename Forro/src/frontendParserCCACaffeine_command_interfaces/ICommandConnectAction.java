package frontendParserCCACaffeine_command_interfaces;

import forrocore.ForroDriverRMIInterface;



/**
 * Call the CCAfeine action for 'connect' command and respective parameters
 * @author Jefferson
 *
 */
public interface ICommandConnectAction extends IAction {

	 public void connect(String usingInstance, String usedPortName,
			             String providingInstance, String providedPortName, ForroDriverRMIInterface forroDriver);
}
