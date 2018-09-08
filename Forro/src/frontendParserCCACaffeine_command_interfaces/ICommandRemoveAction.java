package frontendParserCCACaffeine_command_interfaces;

import forrocore.ForroDriverRMIInterface;



/**
 * Call the CCAfeine action for 'remove' command and respective parameters
 * @author Jefferson
 *
 */
public interface ICommandRemoveAction extends IAction{

	 public void remove(String componentInstanceName, ForroDriverRMIInterface forroDriver);
}
