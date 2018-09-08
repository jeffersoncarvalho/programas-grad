/*
 * Created on 12/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package forrocore;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author root
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TesteRMI {

	public static void main(String[] args) {

		 

		System.out.println("criando objeto remoto...");

		try {
			ForroDriverRMIInterface service = (ForroDriverRMIInterface) Naming.lookup
			("rmi://200.19.177.51:1099/ForroDriver");
			
			System.out.println(service);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
