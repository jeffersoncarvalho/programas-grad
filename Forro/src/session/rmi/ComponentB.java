package session.rmi;

import java.util.Map;

import model.ComponentOID;
import ccacore.GoPort;
import ccacore.Services;
import ccacore.TypeMap;
import exceptions.CCAException;
import forrocore.ForroComponent;
import forrocore.TypeMapKeys;

public class ComponentB extends ForroComponent {
	
	Map properties;
	ComponentOID compOID;
	Services services;
	
	/**
	 * Constructor
	 */
	public ComponentB() {
		super();		
		// TODO Auto-generated constructor stub
	}

	public void setServices(Services s) throws CCAException{
		String portProvidesNameB = "sayHelloB";
		String portUsesName = "usesComponentA";

		TypeMap map = s.createTypeMap();
		
		
		s.addProvidesPort(new MyPort(), portProvidesNameB, "provides", map);
		System.out.println("\n After create the Provides Port : " + portProvidesNameB + " " + s.getPort(portProvidesNameB) + " !");

		
		
		
		TypeMap map1 = s.createTypeMap();
		map1.putString(TypeMapKeys.ComponentNameKey, this.getClass().getName());
		map1.putString(TypeMapKeys.PortNameKey, portUsesName);
		s.registerUsesPort(portUsesName, "uses", map1);
		System.out.println("\n After REGISTER the  Uses Port: " + portUsesName);
		
		services = s;

				
		System.out.println("\n \n Component B - Executing the setServices method.");
	}

	public String sayHelloB()
	{
		
		return "Hello. I´m a component B!!!!!!!!!!";
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
			
			GoPort p = null;
			if (services!= null)
			{
				try {
					p = (GoPort) services.getPort("usesComponentA");
					//p = (GoPort) services.getPort("sayHelloB");
				} catch (CCAException e) {
					e.printStackTrace();
				}
				if (p!= null)
					p.go();
				System.out.println(sayHelloB());
			}
			
			return 0;
		}

	}
}
