/*
 * Copyright (c) 2002 Extreme! Lab, Indiana University. All rights reserved.
 *
 * This software is open source. See the bottom of this file for the licence.
 *
 * $Id: Component.java,v 1.6 2002/11/11 19:46:45 aslom Exp $
 * @version $Revision: 1.6 $
 * http://www.extreme.indiana.edu/xcat
 */

/**
 * The CCA object model: the interface that all CCA 
 * components must implement. 
 * Every component that the component developer
 * defines needs to extened from this class.
 * This class is part of the CCA specs. 
 */

/**
 * @version $Revision: 1.6 $ $Author: aslom $ $Date: 2002/11/11 19:46:45 $ (GMT) 
 * @author Madhusudhan Govindaraju [mailto:mgovinda@extreme.indiana.edu]
 */

package ccacore;


import exceptions.CCAException;

/**
 * All components must implement this interface.
 */  
public interface Component {
   /** 
     * Obtain Services handle, through which the 
     * component communicates with the framework. 
     * This is the one method that every CCA Component
     * must implement. The component will be called
     * with a nil/null Services pointer when it is
     * to shut itself down.
     */	
	
  public void setServices(Services s) throws CCAException;
}
