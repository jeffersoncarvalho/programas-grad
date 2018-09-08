package br.ufc.lia.es.solar.rme;

 
import arcademis.server.AlreadyBoundException;

public class RunServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	 
			try {
				rme.RmeConfigurator c = new rme.RmeConfigurator();
				c.configure();

				String serviceName = "solar";
				SolarServerServiceImpl solarServer = new SolarServerServiceImpl();
				rme.naming.RmeNaming.bind(serviceName, solarServer);
				solarServer.activate();
				System.out.println("\nSolarServer is up and running.\nBinded name: \""+serviceName+"\"");
				 
			} catch (arcademis.ArcademisException e) {
				e.printStackTrace();
			} catch (arcademis.concreteComponents.MalformedURLException e) {
				e.printStackTrace();
			} catch (AlreadyBoundException e) {
				e.printStackTrace();
			 
		}
	 
	}
	 

}
