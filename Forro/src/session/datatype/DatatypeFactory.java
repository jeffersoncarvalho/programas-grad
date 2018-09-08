/* file name  : Factory.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Apr 3, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.datatype;

import java.util.HashMap;
import java.util.Map;




/**
 * Instantiates a specified datatype implementation at this session handler location.
 * This interface provides methods for creating remote instances of <tt>Datatype</tt>
 * objects. In particular, an implementation of this interface should be used by a 
 * <tt>RemoteInstantiator</tt> implementation in order to allow that instantiator to ask 
 * for the creation of a new instance of a given model implementation.
 * 
 * <p>When a datatype implementation is instantiated by an object implementing this interface,
 * then a stub and an invoker class files for remote <tt>Datatype</tt> objects from the names of 
 * compiled Java classes are generated.
 *  
 * <p>All of its remote methods throw the <tt>RemoteException</tt> for compatibility with Java's RMI
 * framework.
 * 
 * @author Ricardo Corr�a (correa@lia.ufc.br)
 */
public class DatatypeFactory {
	/**
	 * @uml.property  name="repoMap"
	 * @uml.associationEnd  inverse="this$0:session.datatype.DatatypeFactory$Repository" qualifier="name:java.lang.String session.datatype.DatatypeFactory$Repository"
	 */
	private Map repoMap;
	/**
	 * This is a singleton class
	 */
	private static DatatypeFactory instance = null;
	/**
	 * 
	 */
	private DatatypeFactory() {
		super();
		repoMap = new HashMap();
		repoMap.put(SimpleDatatype.class.getName(), new Repository());
	}
	/**
	 * Instantiates a specified class.
	 * 
	 * @param instanceName the implementation's name
	 * 
	 * @return the object created
	 */
	public static synchronized DatatypeFactory getInstance() {
		if (instance == null) instance = new DatatypeFactory();
		return instance;
	}
	
	/**
	 * @param name of the Datatype implementation required
	 * @return
	 */
	public Datatype newInstance(String name) {
		Repository repo = (Repository) repoMap.get(name);
		if (repo == null) {
			repo = new Repository();
			repoMap.put(name, repo);
		}
		Datatype ret = repo.get();
		if (ret == null)
			try {
				if (name.equals(SimpleDatatype.class.getName()))
					ret = new SimpleDatatype();
				else
					ret = (Datatype) Class.forName(name).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return ret;
	}

	public Datatype newInstance(Datatype contents) {
		Datatype ret = newInstance();
		setContents(ret, contents);
		return ret;
	}
	
	public Datatype newInstance() {
		return newInstance(SimpleDatatype.class.getName());		
	}
	
	public boolean setContents(Datatype data, Datatype contents) {
		return ((AbstractDatatype) data).setContents(contents);
	}
	
	/**
	 * Releases a previously created Datatype object for further use. This avoids releasing it by the 
	 * garbagge collector. Next calls to <tt>newInstance</tt> may return this released object.
	 * 
	 * @param data the Datatype object to release
	 */
	public void recycle(Datatype data) {
		if (data.getContents() != data)
			recycle(data.getContents());
		setContents(data, data);
		String dataClassName = data.getClass().getName();
		Repository repo = (Repository) repoMap.get(dataClassName);
		if (repo != null)
			repo.add(data);
	}

	/**
	 * @author  gisele
	 */
	private final class Repository {
   	   ListEntry head;
	   ListEntry free;
		
		/**
		 * @author  gisele
		 */
		class ListEntry {
			ListEntry next;
			Datatype content;
		}



		private ListEntry createFree() {
			ListEntry[] entry = new ListEntry[16];
			entry[15] = new ListEntry();
			entry[15].next = null;
			for (int i = 14; i >= 0; i--) {
				entry[i] = new ListEntry();
				entry[i].next = entry[i + 1];
			}
			
			return entry[0];					
		}

		private Datatype removeHead() {
			ListEntry ret = head;
			head = head.next;
			ret.next = free;
			free = ret;
			return ret.content;
		}

		public Datatype get() {
			// it is a queue. So, index must always be zero
			return head == null ? null : removeHead();
		}

		public boolean add(Datatype o) {
			ListEntry f = free == null ? createFree() : free;
			free = f.next;
			f.content = o;
			f.next = head;
			head = f;
			return true;
		}
	}

	/**
	 * The Datatype class used as the result of the <tt>invoke</tt> method. It simply overrides the <tt>setContents</tt>
	 * method of AbstractDatatype, which allows the link to write the result of the remote method invocation once.
	 * 
	 * @see AbstractDatatype
	 * 
	 * @author correa
	 */
	private final class SimpleDatatype extends AbstractDatatype implements Datatype {
		
		/**
		 * 
		 */
		public SimpleDatatype() {
			super();
		}
		
	}
}