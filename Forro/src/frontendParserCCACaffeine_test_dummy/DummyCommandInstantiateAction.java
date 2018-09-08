package frontendParserCCACaffeine_test_dummy;

import java.rmi.RemoteException;

import exceptions.CCAException;
import forrocore.ForroDriver;
import forrocore.ForroDriverRMIInterface;
import forrocore.ForroSessionDriver;
import frontendParserCCACaffeine_command_interfaces.ICommandInstantiateAction;
import frontendParserCCACaffeine_command_util.Writer;


/**
 * Call the CCAfeine action for 'instantiate, pulldown, create' command and respective parameters
 * @author Jefferson
 *
 */
public class DummyCommandInstantiateAction implements ICommandInstantiateAction{

	 public void instantiate(String className, String locationName, ForroDriverRMIInterface forroDriver){
		String optionKey[] = new String[4];  
		String optionValue[] = new String[4];
		 
		optionKey[0] = ForroDriver.componentNameLabel;                                	        			
		optionKey[1] = ForroDriver.instanceNameLabel;
		optionKey[2] = ForroDriver.locationNameLabel;
		                             									
			
		optionValue[0] = className;
		optionValue[1] = className;                                	        							
		optionValue[2] = locationName;
		            	        							
			// Keep the links names in the link map
			try {
				String sessionName = ((ForroSessionDriver) ForroDriver.getCurrentSessionDriver()).getSessionName();
				if (sessionName.equals(""))
				{
					throw new CCAException("Current session not defined.");
				}				
				forroDriver.execute(sessionName, locationName,"createComponentCommand", optionKey, optionValue);

			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CCAException e) {
				e.printStackTrace();
			}         
		 Writer.print("instantiating " + className);
	 }

	public void createLink(String linkName, String workspaceName, String locationName, ForroDriverRMIInterface forroDriver) {
		String optionKey[] = new String[3];  
		String optionValue[] = new String[3];
		 
		optionKey[0] = ForroDriver.linkNameLabel;                                	        			
		optionKey[1] = ForroDriver.workspaceNameLabel;
		optionKey[2] = ForroDriver.locationNameLabel;
		                             									
			
		optionValue[0] = linkName;
		optionValue[1] = workspaceName;                                	        							
		optionValue[2] = locationName;                                	        							
		            	        							
			// Keep the links names in the link map
			try {
				String sessionName = ((ForroSessionDriver) ForroDriver.getCurrentSessionDriver()).getSessionName();
				if (sessionName.equals(""))
				{
					throw new CCAException("Current session not defined.");
				}				
				forroDriver.execute(sessionName, locationName,"createLinkCommand", optionKey, optionValue);

			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CCAException e) {
				e.printStackTrace();
			}         
		 Writer.print("\n instantiating " + linkName + "\n");	}

	public void createLocation(String locationName, String middlewareName, String portName, ForroDriverRMIInterface forroDriver) {
		String optionKey[] = new String[3];  
		String optionValue[] = new String[3];
		 
		optionKey[0] = ForroDriver.locationNameLabel;                                	        			
		optionKey[1] = ForroDriver.middlewareNameLabel;
		optionKey[2] = ForroDriver.portNameLabel;
		                             									
			
		optionValue[0] = locationName;
		optionValue[1] = middlewareName;                                	        							
		optionValue[2] = portName;                                	        							
		            	        							
			// Keep the links names in the link map
			try {
				String sessionName = ((ForroSessionDriver) ForroDriver.getCurrentSessionDriver()).getSessionName();
				if (sessionName.equals(""))
				{
					throw new CCAException("Current session not defined.");
				}				
				forroDriver.execute(sessionName, locationName,"createLocationCommand", optionKey, optionValue);

			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CCAException e) {
				e.printStackTrace();
			}         
		 Writer.print("instantiating " + locationName + "\n");
		 
	}

	public void createWorkspace(String workspaceClassType, String workspaceName, String locationName, ForroDriverRMIInterface forroDriver) {
		String optionKey[] = new String[3];  
		String optionValue[] = new String[3];
		 
		optionKey[0] = ForroDriver.workspaceTypeLabel;                                	        			
		optionKey[1] = ForroDriver.workspaceNameLabel;
		optionKey[2] = ForroDriver.locationNameLabel;
		                             									
			
		optionValue[0] = workspaceClassType;
		optionValue[1] = workspaceName;                                	        							
		optionValue[2] = locationName;                                	        							
		            	        							
			// Keep the links names in the link map
			try {
				String sessionName = ((ForroSessionDriver) ForroDriver.getCurrentSessionDriver()).getSessionName();
				if (sessionName.equals(""))
				{
					throw new CCAException("Current session not defined.");
				}				
				forroDriver.execute(sessionName, locationName,"createWorkspaceCommand", optionKey, optionValue);

			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CCAException e) {
				e.printStackTrace();
			}         
		 Writer.print("instantiating " + workspaceName + "\n");
	}

	public void createRelationWorkspaceLocation(String workspaceName, String locationRelationName, String locationName, ForroDriverRMIInterface forroDriver) {
		String optionKey[] = new String[4];  
		String optionValue[] = new String[4];
		 
		optionKey[0] = ForroDriver.workspaceNameLabel;                                	        			
		optionKey[1] = ForroDriver.locationRelationNameLabel;
		optionKey[2] = ForroDriver.locationNameLabel;
		                             									
			
		optionValue[0] = workspaceName;
		optionValue[1] = locationRelationName;                                	        							
		optionValue[2] = locationName;                                	        							
		            	        							
			// Keep the links names in the link map
			try {
				String sessionName = ((ForroSessionDriver) ForroDriver.getCurrentSessionDriver()).getSessionName();
				if (sessionName.equals(""))
				{
					throw new CCAException("Current session not defined.");
				}				
				forroDriver.execute(sessionName, locationName,"createRelationLocationWorkspaceCommand", optionKey, optionValue);

			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CCAException e) {
				e.printStackTrace();
			}         
		 Writer.print("\n instantiating relation between " + locationName+ ":"+ workspaceName + " and " + locationRelationName +"\n");
	}
}
