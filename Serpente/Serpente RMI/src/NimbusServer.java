import java.rmi.*;
import javax.naming.*;

public class NimbusServer {
   public static void main (String[] args) 
   {
		
	  if (System.getSecurityManager() == null)
	  System.setSecurityManager(new RMISecurityManager());
	  
	  try {
	  	NimbusRemote nimbus = new NimbusImpl();
		Naming.rebind("nimbusrmi", nimbus);
		System.out.println("Remote object bound to 'nimbusrmi'.");
	  } 
	  catch (Exception e) {
		if (e instanceof RuntimeException)
		throw (RuntimeException)e;
		System.out.println("" + e);
	  }
	}
}
