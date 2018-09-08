package frontendParserCCACaffeine_command_interfaces;

import forrocore.ForroDriverRMIInterface;


/**
 * Call the CCAfeine action for 'help' command. No parameters needed.
 * @author Jefferson
 *
 */
public interface ICommandHelpAction extends IAction {

	 
	 public void  help(ForroDriverRMIInterface forroDriver);

}
