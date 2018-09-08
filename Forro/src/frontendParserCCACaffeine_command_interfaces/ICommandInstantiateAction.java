package frontendParserCCACaffeine_command_interfaces;

import forrocore.ForroDriverRMIInterface;



/**
 * Call the CCAfeine action for 'instantiate, pulldown, create' command and respective parameters
 * @author Jefferson
 *
 */
public interface ICommandInstantiateAction extends IAction{

	 public void instantiate(String className, String locationName, ForroDriverRMIInterface forroDriver);
	 public void createLocation(String locationName, String middlewareName, String portName, ForroDriverRMIInterface forroDriver);
	 public void createWorkspace(String workspaceClassType, String workspaceName, String locationName, ForroDriverRMIInterface forroDriver);
	 public void createLink(String linkName, String workspaceName, String locationName, ForroDriverRMIInterface forroDriver);
	 public void createRelationWorkspaceLocation(String workspaceName, String locationRelationName, String locationName, ForroDriverRMIInterface forroDriver);
}
