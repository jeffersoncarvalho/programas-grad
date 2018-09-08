import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.StringTokenizer;
/**
 * 
 */
public class NimbusImpl extends UnicastRemoteObject implements NimbusRemote
{
	private String allSnakes ="";//remote
	private Repository repository;
	
	public NimbusImpl() throws RemoteException
	{
			repository = new Repository();
			Thread t = new Thread(){
            public void run(){
             while(true){
             	if(repository.getNumberOfClients()>0)
             		allSnakes = repository.snakesInfo();
				try {Thread.sleep(50);}
				catch (Exception ex) {}
			 }
            }
        };//fim da classe thread
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
	}
	
	/**
	 * remote
	 */
	public String getAllSnakes() throws RemoteException
	{
		return this.allSnakes;
	}
	
	/**
	 *remote
	 */
	public void msgReceived(int idClient, String msg) throws RemoteException
	{
		System.out.println ("Cliente: " + idClient + " Msg:" + msg);
		if(msg.startsWith("JOGAR"))
		{
			
			repository.addClient(idClient);
		}
		else
		 if(msg.startsWith("DIRECAO"))
	  	 {
	  			System.out.println ("Entrando no mudar direcao.");
	  		  	StringTokenizer tokens = new StringTokenizer(msg);
	  		  	tokens.nextToken();//lixo
	  		  	int d = Integer.parseInt(tokens.nextToken());
	  		  	this.repository.setSnakeDirection(idClient,d);
	  		  	System.out.println ("Saindo do mudar direcao.");
	  	}
	  	else
	  	 if(msg.startsWith("EXIT"))
	  	 {
	  	 	this.repository.removeClient(idClient);
	  	 }
	}
   
   
}