/**
 * 
 */
package forrocore;

import ccacore.ComponentID;
import ccacore.EventType;

public final class EventTypes implements EventType {

	/**
	 * @param comp
	 * @param type TODO
	 */
	public static EventType newEventType(ComponentID comp, int type) {
		return new ForroEventType(comp, type);
	}
	
	/**
	 * Answers whether the specified event has been created with <tt>newEventType</tt> with the
	 * specified type as a parameter.
	 * 
	 * @param e the event being tested
	 * @param type the tested type
	 * 
	 * @return <tt>true</tt> if the specified event has been created with <tt>newEventType</tt> with the
	 * specified type as a parameter.
	 */
	public static boolean hasType(EventType e, int type) {
		if (e instanceof ForroEventType) {
			ForroEventType fe = (ForroEventType) e;
			return fe.type == type;
		}
		return false;
	}
	
	/**
	 * Answers whether the specified event has been created with <tt>newEventType</tt> with the
	 * specified component as a parameter.
	 * 
	 * @param e the event being tested
	 * @param comp the tested component
	 * 
	 * @return <tt>true</tt> if the specified event has been created with <tt>newEventType</tt> with the
	 * specified component as a parameter.
	 */
	public static boolean hasComponent(EventType e, ComponentID comp) {
		if (e instanceof ForroEventType) {
			ForroEventType fe = (ForroEventType) e;
			return fe.comp == comp;
		}
		return false;
	}
	
	private static final class ForroEventType implements EventType {
		/**
		 * 
		 */
		private final int type;

		private final ComponentID comp;

		/**
		 * @param comp
		 * @param type
		 */
		ForroEventType(final ComponentID comp, final int type) {
			this.type = type;
			this.comp = comp;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return comp.hashCode();
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof ForroEventType) {
				ForroEventType e = (ForroEventType) obj;
				return comp.equals(e.comp);
			}
			return false;
		}
	}
}