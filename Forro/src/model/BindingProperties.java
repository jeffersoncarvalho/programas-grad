/*
 * Created on Aug 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package model;

import java.io.Serializable;

/**
 * @author correa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BindingProperties implements Serializable {
	/**
	 * @uml.property  name="packSize"
	 */
	private int packSize;
	/**
	 * @uml.property  name="startNew"
	 */
	private boolean startNew;
	
	/**
	 * @param packSize
	 * @param startNew if <tt>true</tt>, indicates that a new provides port should be instantiated at each invocation of the
	 *                 <tt>getProvidesPort</tt> method; otherwise, a previously instantiated provides port, if any,
	 *                 is returned by the <tt>getProvidesPort</tt> method.
	 */
	public BindingProperties(int packSize, boolean startNew) {
		super();
		this.packSize = packSize;
		this.startNew = startNew;
	}
	
	/**
	 * Returns the package size used by the iterators of the provides port.
	 * 
	 * @return the package size
	 */
	public int getPackSize() {
		return packSize;
	}
	
	public boolean getStartNew() {
		return startNew;
	}
}
