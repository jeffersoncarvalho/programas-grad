package frontendParserCCACaffeine_command_interfaces;

import forrocore.ForroDriverRMIInterface;



/**
 * Call the CCAfeine action for 'go, run' command and respective parameters
 * @author Jefferson
 *
 */
public interface ICommandGoAction extends IAction{

	 public void go(String instance, String port, ForroDriverRMIInterface forroDriver);
}
