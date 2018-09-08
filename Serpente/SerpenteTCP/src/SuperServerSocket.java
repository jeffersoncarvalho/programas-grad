import java.net.*;
import java.util.*;

public class SuperServerSocket
{
	private Vector clientsVector;
	private Point food;
	
	private ServerSocket server;
	public SuperServerSocket()
	{
		clientsVector = new Vector();
		food = this.createFood();
		
		try {
			server = new ServerSocket(23);
			System.out.println ("Ativo");
			this.waitingConnection();//esperando a conexão em uma Thread
			this.sendNCast();//enviando resultados em outra Thread
			
	    }
	    catch (Exception ex) {
	    	System.out.println ("Servidor não inicializado.");
	    }
	    
	}
	
	
	/**
	 *Esperando conexão
	 */
	public void waitingConnection()
	{
		Thread t = new Thread(){
            public void run(){
                	
	            while(true)
				{
					//aceitando a conexão.
					try {
						Socket client = server.accept();
						Snake snake = createSnake();
						ServerThread serverThread = new ServerThread(client,snake,food);//boto a cobra aqui dentro e acomida tb
						serverThread.send("FOOD " + food.getX() + " " + food.getY());//enviando a comida para o que acbaou de chegar
						clientsVector.add(serverThread);//adicionando o cliente para posterior n-cast.
						serverThread.start();				    
				    }
				    catch (Exception ex) {
				    	System.out.println ("Conexao recusada");
				    }	
				}//WHILE
				
            }//run
        };//fim da classe thread
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
	}
	
	/**
	 *Enviando n-cast
	 */
	public void sendNCast()
	{
		Thread t = new Thread(){
            public void run(){
          	
            	Vector desconectedClients = new Vector();
            	while(true)
            	{
			            	if(clientsVector.size()>0) // só mando o cast caso tenha gente conectada.
			            	{
			            		
								//mandando string com dados de todos as cobras.
								for(int i=0;i<clientsVector.size();i++)
								{
									ServerThread st = (ServerThread)clientsVector.get(i);
									//testar colisoes com outras cobras e consigo mesmo
									testSnakeCollision(st);
									//enviando de fato
									if(st.send(snakesInfo()))
									{
										st.send("FOOD " + food.getX() + " " + food.getY());//enviando a comida.
									}
									else
										desconectedClients.add(new Integer(i));//guardando desconectados
									 
									 	
								}
								
								//apagando os desconectados.
								for(int i=0;i<desconectedClients.size();i++)
								{
									Integer j = (Integer)desconectedClients.get(i);
									System.out.println ("Removendo cliente desconectado.");
									((ServerThread)clientsVector.get(j.intValue())).interrupt();
									clientsVector.remove(j.intValue());
								}
								
								desconectedClients.clear();
							 }
							 
							 try {Thread.sleep(100);}
							 catch (Exception ex) {}
				 }//while true de teste
						
            }//run
        };//fim da classe thread
        t.start();
	}
	
	
   /**
   	* Devolve uma nova cobra para um jogador que acaba de entrar
   	*/
    public Snake createSnake()
    {
     	int x =  (int)(Math.random()*39);
     	int y =  (int)(Math.random()*39);

     	int direction = 1+(int)(Math.random()*4);

     	Snake snk = new Snake(x,y,direction,400,400);
     	return   snk;
  	}
  	
  	/**
  	 * Cria String com info das cobras
  	 */
  	public String snakesInfo()
  	{
  		String result = "";
		for(int i = 0; i<clientsVector.size(); i++)
		{
			Snake snake = ((ServerThread)clientsVector.get(i)).getSnake();
            result += "COR"+" " +snake.toString();
			result += " ";
		} 
		
		return result; 		
  	}
  	
  /**
   * Criar comida;
   */
   public Point createFood()
   {
          int x =  (int)(Math.random()*39);
          int y =  (int)(Math.random()*39);
          return new Point(x,y);
   }
  
   /**
    *Testa de duas cobras se comeram
    */
   public void testSnakeCollision(ServerThread st)
   {
   		Point head = st.getSnake().getHead();   		
   		//testo se st não esbarrou em nenhuma outra cobra
   		for(int i=0; i<clientsVector.size(); i++)//pego um cliente
        {	
        	ServerThread clientThread = (ServerThread)clientsVector.get(i);
        	if(!clientThread.equals(st))//testo se não sou eu mesmo
        	{
        		ArrayList othersBody = clientThread.getSnake().getSnake();//pego a sua cobra.ui!
        		for(int j = 0; j<othersBody.size(); j++) //leio todos os pontos da cobra
        		{
        			Point piece = (Point)othersBody.get(j);
        			if((head.getX() == piece.getX()) && (head.getY() == piece.getY()))
        			{
        				st.getSnake().killSnake();
        				clientThread.getSnake().killSnake();
        				break;
        			}
        		}
          		
        	}
        } 		
        
   }
   
	/*****************************************/
	/**************PRINCIPAL******************/
	/*****************************************/
	public static void main(String args[])
	{
		new SuperServerSocket();
	}
}