/* file name  : RMISessionsHandler.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Feb 14, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.rmi;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;

import session.FrameworkDriver;
import session.SessionDriver;


/**
 * An implementation of the <tt>RemoteInstantiator</tt> interface using the Java's RMI as the Remote Interaction Level.
 * It handles all sessions at its location.
 * 
 * @author Ricardo Corr�a (correa@lia.ufc.br)
 */
public final class RMISessionsHandler extends UnicastRemoteObject implements SessionsHandler, RMICompliant {

	/**
	 * The map indexed by the sessions' names, and valued with a 2-element arrays containing the sessions' signatures and session handlers.
	 * @uml.property  name="sessionMap"
	 * @uml.associationEnd  qualifier="sessionName:java.lang.String [Ljava.lang.Object;"
	 */
	private Map sessionMap;
	/**
	 * The encoded RMIAppl's public key.
	 * @uml.property  name="encKey" multiplicity="(0 -1)" dimension="1"
	 */
	private byte[] encKey;
	/**
	 * The RMIAppl's public key.
	 * @uml.property  name="pubKey"
	 */
	private PublicKey pubKey;

	/**
	 * @throws RemoteException TODO
	 */
	public RMISessionsHandler() throws RemoteException {
		super();
		sessionMap = new LinkedHashMap();
		// TODO initialize the RMIAppl's public key
//		pubKey = KeyFactory.getInstance("DSA", "SUN").generatePublic(new X509EncodedKeySpec(encKey));
	}
	
	private boolean isAuthorized(String sessionName, byte[] sessionSig) {
		System.out.print("Checking authorization for session " + sessionName + "...");
		Object sig = ((Object[]) sessionMap.get(sessionName))[0];
		
		boolean ret = false;
		
		if (sig == SessionDriver.insecure) ret = true;
		else
		// Signature verification to certificate that the caller is authorized
		try {
			 ret = sig != null && ((Signature) sig).verify(sessionSig);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(ret ? "OK" : "denied");
		return ret;
	}

	/* (non-Javadoc)
	 * @see session.SignableSessionHandler#getHandler()
	 */
	private SessionDriver getDriver(String sessionName, byte[] sessionSig) {
		try {
			if (isAuthorized(sessionName, sessionSig)) {
				return (SessionDriver) ((Object[]) sessionMap.get(sessionName))[1];
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see session.rmi.SessionHandler#insecureLogOn(java.lang.String)
	 */
	public boolean insecureLogOn(String sdriverClassName, String sessionName) {
		try {
			// initialize the session entry with an empty list of active models
			System.out.println("SessionDriver : " + sdriverClassName);
			System.out.println("Session name : " + sessionName);
			sessionMap.put(sessionName, new Object[] { SessionDriver.insecure, Class.forName(sdriverClassName).getConstructor(new Class[] { String.class, SessionsHandler.class }).newInstance(new Object[] { sessionName, this }) });
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return true;
	}
	
	/* (non-Javadoc)
	 * @see service.rmi.ServiceHandler#logOn(java.security.cert.Certificate)
	 */
	public boolean secureLogOn(String sdriverClassName, Certificate sessionCert) {
		// TODO acertar verifica��o da assinatura e fazer logon como no insecureLogOn acima
		try {
			// checks whether the caller is an RMIAppl object
			sessionCert.verify(pubKey);
			String sessionName = ((X509Certificate) sessionCert).getSubjectDN().getName();
			
			// create a signature for this session
			Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
			sig.initVerify(sessionCert.getPublicKey());
			
			// initialize the session entry with its signature
			sessionMap.put(sessionName, new Object[] { sig.sign(), Class.forName(sdriverClassName).getConstructor(new Class[] { String.class, Object.class, SessionsHandler.class }).newInstance(new Object[] { sessionName, OldSessionDriver.secure, this }) });
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see session.SessionsHandler#invokeAt(java.lang.String, java.rmi.Remote[], java.lang.String, byte[])
	 */
	public Object invokeAt(String methodName, Object[] args, String sessionName, byte[] sessionSig) throws RemoteException {
		try {
			// Instantiate a model impl class
			SessionDriver sdriver = getDriver(sessionName, sessionSig);
			if (sdriver != null) {
				Class[] param = new Class[args.length];
				for (int i = 0; i < args.length; i++)
					param[i] = args[i].getClass();
				return SessionDriver.class.getDeclaredMethod(methodName, param).invoke(sdriver, args);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// If the signature verification fails, return null
		return null;
	}

	/* (non-Javadoc)
	 * @see session.AbstractSessionLauncher#getHandler(java.lang.String)
	 */
	public FrameworkDriver getRemoteFramework(String locationName) throws RemoteException {
		SessionsHandler handler = null;
		
		try {
			System.out.println("getting rmi://" + locationName + "/" + RMICompliant.registeredName + "...");
			Remote r = Naming.lookup("rmi://" + locationName + "/" + RMICompliant.registeredName);
			handler = (SessionsHandler) r; 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return handler;
	}
	
//	/* (non-Javadoc)
//	 * @see service.rmi.ServiceHandler#newInstance(java.security.Signature)
//	 */
//	public boolean newModel(String className, ModelEmulator modelEmu, String sessionName, byte[] sessionSig) {
//		try {
//			// Instantiate a model impl class
//			SessionDriver handler = getDriver(sessionName, sessionSig);
//			return handler.startNewModel(SessionDriver.hostName, className, null);
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		}
//		
//		// If the signature verification fails, return null
//		return false;
//	}
//	
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see model.factory.vision.ModelLauncher#modelImplList()
//	 */
//	public String[] implList(String[] modelName, String sessionName, byte[] sessionSig) {
//		
//		try {
//			SessionDriver handler = getDriver(sessionName, sessionSig);
//			System.out.println("RMIHandler is authorized -> Session " + sessionName + "  models: ");
//			for (int i = 0; i < modelName.length; i++) System.out.println(modelName[i]);
//			return handler.implList(SessionDriver.hostName, modelName);
//		} catch (SessionHandlerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//
//	/* (non-Javadoc)
//	 * @see session.SignableSessionHandler#bindUsesPort(java.lang.String, java.lang.String, java.lang.String, java.lang.String, byte[])
//	 */
//	public boolean bindUsesPort(String usesPortName, String providesPortName, String linkName, int packageSize, String sessionName, byte[] sessionSig) {
//		try {
//			// Instantiate a model impl class
//			SessionDriver handler = getDriver(sessionName, sessionSig);
//			return handler.bindUsesPort(usesPortName, providesPortName, linkName, packageSize);
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
//
//	/* (non-Javadoc)
//	 * @see session.SignableSessionHandler#bindProvidesPort(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, byte[])
//	 */
//	public boolean bindProvidesPort(String providesPortName, String linkName, String sessionName, byte[] sessionSig) {
//		try {
//			// Instantiate a model impl class
//			SessionDriver handler = getDriver(sessionName, sessionSig);
//			return handler.registerProvidesPort(providesPortName, linkName);
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
//
//	/* (non-Javadoc)
//	 * @see session.SignableSessionHandler#probe(java.lang.String[], java.lang.String, byte[])
//	 */
//	public boolean[] probe(String[] implName, String sessionName, byte[] sessionSig) {
//		try {
//			// Instantiate a model impl class
//			SessionDriver handler = getDriver(sessionName, sessionSig);
//			return handler.probe(SessionDriver.hostName, implName);
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		}
//		
//		// If the signature verification fails, return null
//		return null;
//	}
//
//	/* (non-Javadoc)
//	 * @see session.SignableSessionHandler#startGUI(java.lang.String, byte[])
//	 */
//	public boolean startGUI(String sessionName, byte[] sessionSig) {
//		try {
//			// Instantiate a model impl class
//			SessionDriver handler = getDriver(sessionName, sessionSig);
//			return handler.startGUI() && handler.startFrontEnd();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		}
//		
//		// If the signature verification fails, return null
//		return false;
//	}
//
//	/* (non-Javadoc)
//	 * @see session.SignableSessionHandler#providesPortList(java.lang.String, java.lang.String, byte[])
//	 */
//	public String[] providesPortList(String modelName, String sessionName, byte[] sessionSig) {
//		try {
//			// Instantiate a model impl class
//			SessionDriver handler = getDriver(sessionName, sessionSig);
//			return handler.providesPortList(SessionDriver.hostName, modelName);
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (SessionHandlerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
//
//	/* (non-Javadoc)
//	 * @see session.SignableSessionHandler#usesPortList(java.lang.String, java.lang.String, byte[])
//	 */
//	public String[] usesPortList(String modelName, String sessionName, byte[] sessionSig) {
//		try {
//			// Instantiate a model impl class
//			SessionDriver handler = getDriver(sessionName, sessionSig);
//			return handler.usesPortList(SessionDriver.hostName, modelName);
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (SessionHandlerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
//
//	/* (non-Javadoc)
//	 * @see session.SignableSessionHandler#assign(java.lang.String, java.lang.String, java.lang.String, byte[])
//	 */
//	public boolean assign(String workName, String workImplName, String sessionName, byte[] sessionSig) throws RemoteException {
//		try {
//			// Instantiate a model impl class
//			SessionDriver handler = getDriver(sessionName, sessionSig);
//			handler.startNewWorkspace(workName, workImplName);
//			return handler.assign(SessionDriver.hostName, workName);
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
//
//	/* (non-Javadoc)
//	 * @see session.SignableSessionHandler#newLink(java.lang.String, java.lang.String, java.lang.String, java.lang.String, byte[])
//	 */
//	public boolean newLink(String linkName, String workName, String locationName, String sessionName, byte[] sessionSig) throws RemoteException {
//		try {
//			// Instantiate a model impl class
//			SessionDriver handler = getDriver(sessionName, sessionSig);
//			return handler.startNewLink(linkName, workName, SessionDriver.hostName, locationName);
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
}