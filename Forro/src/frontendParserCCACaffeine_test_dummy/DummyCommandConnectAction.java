package frontendParserCCACaffeine_test_dummy;

import java.rmi.RemoteException;

import exceptions.CCAException;
import frontendParserCCACaffeine_command_interfaces.ICommandConnectAction;
import frontendParserCCACaffeine_command_util.Writer;
import forrocore.ForroDriver;
import forrocore.ForroDriverRMIInterface;
import forrocore.ForroSessionDriver;


/**
 * Call the CCAfeine action for 'connect' command and respective parameters
 * @author Jefferson
 *
 */
public class DummyCommandConnectAction implements ICommandConnectAction{

	 public void connect(String usingInstance, String usedPortName,
			             String providingInstance, String providedPortName, ForroDriverRMIInterface forroDriver){
		 
		String optionKey[] = new String[4];  
		String optionValue[] = new String[4];
		Writer.print("connecting " + usingInstance + " " + usedPortName + " " + providingInstance + " " + providedPortName);

		optionKey[0] = ForroDriver.providerComponentNameLabel;                                	        			
		optionKey[1] = ForroDriver.providesPortNameLabel;
		optionKey[2] = ForroDriver.userComponentNameLabel;
		optionKey[3] = ForroDriver.usesPortNameLabel;
			
			
		optionValue[0] = providingInstance;
		optionValue[1] = providedPortName;                                	        							
		optionValue[2] = usingInstance;
		optionValue[3] = usedPortName;
			
		// Connect two ports
		 
		try {		 
			
			String sessionName = ((ForroSessionDriver) ForroDriver.getCurrentSessionDriver()).getSessionName();
			if (sessionName.equals(""))
			{
				throw new CCAException("Current session not defined.");
			}				
			
			forroDriver.execute(sessionName, "localhost","connectCommand", optionKey, optionValue);
			
		} catch (RemoteException e) {
			e.printStackTrace();   
		} catch (CCAException e) {
			e.printStackTrace();   
		}
		
		
	 }
}	 
