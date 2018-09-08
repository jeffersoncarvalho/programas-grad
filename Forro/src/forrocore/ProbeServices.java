package forrocore;

import ccacore.Port;
import ccacore.Services;
import exceptions.CCAException;

public interface ProbeServices extends Port {
	Port lookupPort(String className, String portName) throws CCAException; 
	Services lookupServices(String className) throws CCAException; 


}
