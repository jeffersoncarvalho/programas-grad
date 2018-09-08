/*
 * Created on Jul 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package session.workspace.local;

import forrocore.Middleware;
import session.datatype.Datatype;
import forrocore.AbstractWorkspaceDriver;

/**
 * This worspace connects directly provides ports to their references. There is no link.
 * 
 * @author correa
 */
public class DirectAssignmentWorkspace extends AbstractWorkspaceDriver {
	/**
	 * Name of this workspace
	 * @uml.property  name="workName"
	 */
	private String workName;
	/**
	 * Driver of this location.
	 * @uml.property  name="ldriver"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Middleware ldriver;

	/**
	 * @param mw TODO
	 * 
	 */
	public DirectAssignmentWorkspace(String wname, Middleware ldriver, Middleware mw) {
		super(mw);
		this.workName = wname;
		this.ldriver = ldriver;
	}

	/* (non-Javadoc)
	 * @see session.workspace.WorkspaceDriver#registerLink(java.lang.String, java.lang.String)
	 */
	public boolean registerLink(String linkName, String locationName) {
		return false;
	}

	/* (non-Javadoc)
	 * @see session.workspace.WorkspaceDriver#invoke(java.lang.String, session.datatype.Datatype[], java.lang.String)
	 */
	public Datatype nonBlockingInvocation(String methodName, Datatype[] args, String linkName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see session.workspace.WorkspaceDriver#isRegistered(java.lang.String)
	 */
	public boolean isRegistered(String linkName) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see session.workspace.WorkspaceDriver#requireUses(java.lang.String)
	 */
	public boolean requireUses(String linkName) {
		return false;
	}

}
