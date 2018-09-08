package session.rmi;

import java.util.Map;

import model.ComponentOID;
import ccacore.GoPort;
import ccacore.Services;
import ccacore.TypeMap;
import exceptions.CCAException;
import forrocore.ForroComponent;

public class ComponentA  extends ForroComponent {
	Services services;

	Map properties;
	ComponentOID compOID;
	/**
	 * Constructor
	 */
	public ComponentA() {
		super();
	}
	
	/**
	 * Constructor
	 */
	public void newInstance() {
		System.out.println("\n New Instance A!!"); 
	}


	public void setServices(Services s) throws CCAException {
		GoPort portSayhello = new MyPort();
		String portNameA = "sayHelloA";
		
		TypeMap map = s.createTypeMap();
		s.addProvidesPort(portSayhello,portNameA , "provides", map);
		System.out.println("\n After create Port Provides: " + portNameA + " !");
		

		System.out.println(" Component A - Executing the setServices method. \n\n");
	}

	
	public String sayHelloA()
	{
		
		return "Hello. I´m a component A!!!!!!!!!!";
	}
	
	/**
	 * @author Administrador
	 *
	 */
	private final class MyPort implements GoPort {

		/* (non-Javadoc)
		 * @see ccacore.GoPort#go()
		 */
		public int go() {
			System.out.println(sayHelloA());
			return 0;
		}

	}

}
