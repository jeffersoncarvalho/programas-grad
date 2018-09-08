import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NimbusRemote extends Remote{
	public String getAllSnakes () throws RemoteException;
	public void msgReceived(int idClient, String msg) throws RemoteException;
}
