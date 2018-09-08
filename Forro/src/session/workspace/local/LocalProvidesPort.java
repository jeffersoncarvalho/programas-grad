package session.workspace.local;

import java.util.Iterator;

import session.datatype.Datatype;
import model.BindingProperties;
import model.ProvidesPort;
import ccacore.Port;

public class LocalProvidesPort extends ProvidesPort implements Port {

	public LocalProvidesPort(String name, BindingProperties bindingProp) {
		super(name, bindingProp);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addTerm(Datatype key, Datatype term) {
		// TODO Auto-generated method stub

	}

	public Datatype getTerm(Datatype key) {
		// TODO Auto-generated method stub
		return null;
	}

	public Datatype getTerm() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void tailTerms(Datatype d) {
		// TODO Auto-generated method stub

	}

	public Datatype toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
