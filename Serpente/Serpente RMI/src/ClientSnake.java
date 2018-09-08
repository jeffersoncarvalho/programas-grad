import java.rmi.*;
import javax.naming.*;
import java.util.StringTokenizer;

public class ClientSnake
{
	
	
	private DrawPanel drawPanel;
	private Invoker invoker;
	private int ID;
	
	
	/**
	 *Contrutor de ClientSnake
	 */
	public ClientSnake(DrawPanel drawPanel)
	{
		this.drawPanel = drawPanel;
		ID = (int)(Math.random()*100000);
		invoker = new Invoker(this);
		invoker.start();
		invoker.send("JOGAR");
		
	}
	
	
	
	/**
	 * Processando mensagem pega do servidor.
	 */
	public void processMessage(String msg)
	{
		
		if(msg.startsWith("COR"))
		{
			drawPanel.addObject(msg);
            drawPanel.repaint();//pinta
		}
		else
		 if(msg.startsWith("FOOD"))
		 {
		 	StringTokenizer tokens = new StringTokenizer(msg);
			tokens.nextToken();//token FOOD
			drawPanel.setFood(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
		 }
		 else
		 	if(msg.startsWith("PONTO"))
		 	{
		 		//resultPanel.setPoint(msg);
		 	}
		 	
	}

	
	public void setDrawPanel(DrawPanel drawPanel) {
		this.drawPanel = drawPanel; 
	}

	public void setInvoker(Invoker invoker) {
		this.invoker = invoker; 
	}

	public void setID(int ID) {
		this.ID = ID; 
	}

	public DrawPanel getDrawPanel() {
		return (this.drawPanel); 
	}

	public Invoker getInvoker() {
		return (this.invoker); 
	}

	public int getID() {
		return (this.ID); 
	}
	
}

class Invoker extends Thread
{
	private ClientSnake clientSnake;
	private NimbusRemote nimbus;
	public Invoker(ClientSnake clientSnake)
	{
		this.clientSnake = clientSnake;
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
	    }
		try {
			nimbus = (NimbusRemote) Naming.lookup("nimbusrmi");
			
		} 
		catch (Exception e) {
			if (e instanceof RuntimeException)
			throw (RuntimeException)e;
			System.out.println("" + e);
		}
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				String situation = nimbus.getAllSnakes();
				if((!situation.equals("")) &&(situation!=null)) 
					clientSnake.processMessage(situation);
				try {Thread.sleep(100);}
				catch (Exception ex) {}	
			}
			catch(RemoteException re)
			{
				System.out.println ("Problemas com o servidor remoto!");
				System.out.println (re.toString());
			}
		}//while true
		
	}
	
	public void  send(String msg)
	{
		
			try
			{
				
				nimbus.msgReceived(clientSnake.getID(),msg);	
				
				
			}
			catch(RemoteException re)
			{
				System.out.println ("Problemas com o servidor remoto!");
				System.out.println (re.toString());
			}
		
	}
}