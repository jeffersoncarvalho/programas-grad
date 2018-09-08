package frontendParserCCACaffeine_command_interfaces;

import forrocore.ForroDriverRMIInterface;



/**
 * Call the CCAfeine action for 'shell, system' command.
 * @author Jefferson
 *
 */
public interface ICommandShellAction extends IAction{

	 public void shell(ForroDriverRMIInterface forroDriver);
}
