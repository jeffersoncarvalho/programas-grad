/*
 * Created on Feb 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package session.gui;

import forrocore.SessionDriver;


/**
 * @author cfreire
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FrontEnd implements Runnable {

	/**
	 * @uml.property  name="handler"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private SessionDriver handler;
	
	/**
	 * @param handler
	 */
	public FrontEnd(SessionDriver handler) {
		super();
		this.handler = handler;
	}
	
	/* (non-Javadoc)
	 * @see model.Visual#run()
	 */
	public void run() {
		System.out.println("Front-end");
//		try {
//			System.out.println("Run, Simfra! run!");
//			Runtime runtime = Runtime.getRuntime();
//			Process process;
//			process = runtime.exec("../../../simfra1.0/simfra3d/bin/Linux/SimfraMain");
//			System.out.println("Waiting Simfra3D termination.");
//			process.waitFor();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println("exited.");

	}

}
