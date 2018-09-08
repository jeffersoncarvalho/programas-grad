package forrocore;

import java.rmi.Remote;
import ccacore.Component;
import ccacore.Services;
import exceptions.CCAException;

public class ForroComponent implements Remote, Component {
	public ForroComponent() {
		super();
	}
 	public void setServices(Services s) throws CCAException{};
 	public void newInstance() throws CCAException{};
}
