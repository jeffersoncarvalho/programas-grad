package frontendParserCCACaffeine_command_interfaces;

import forrocore.ForroDriverRMIInterface;


/**
 * Call the CCAfeine action for 'quit, x, bye, exit' command. No parameters needed.
 * @author Jefferson
 *
 */
public interface ICommandQuitAction extends IAction {

	 
	 public void  quit(ForroDriverRMIInterface forroDriver);

}
