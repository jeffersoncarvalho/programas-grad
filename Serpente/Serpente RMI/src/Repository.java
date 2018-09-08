import java.util.Hashtable;
import java.util.Enumeration;
import java.util.ArrayList;

public class Repository extends Hashtable{

	private Point food;
	
	public Repository()
	{
		
		food = this.createFood();
		
	}
	
	public int getNumberOfClients()
	{
		return this.size();
	}
	/**
	 * Adicionando um cliente e uma Snake para ele
	 *
	 */
	public void addClient(int idClient)
	{
		ServerThread st =  new ServerThread(this.createSnake(),food);
		this.put( new Integer(idClient),st);
		st.start();
		
	}
	
	/**
	 *
	 */
	public void setSnakeDirection(int idClient, int dir)
	{
		
		ServerThread st = (ServerThread)this.get(new Integer(idClient));
		if(st !=null)
		 st.getSnake().setDirection(dir);
		
	}
	
	/**
	 * Removendo cliente
	 */
	public void removeClient(int idClient)
	{
		this.remove( new Integer(idClient));
	}
	
	/**
   	* Devolve uma nova cobra para um jogador que acaba de entrar
   	*/
    private Snake createSnake()
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
  		//String result="";
  		String result = "COR " + food.getX() + " " + food.getY() + "#";
		Enumeration e = this.elements();
		while(e.hasMoreElements())
		{
			ServerThread st = (ServerThread)e.nextElement();
			Snake snake = st.getSnake();
			this.testSnakeCollision(st);
            result += "COR"+" " +snake.toString();
			result += " ";
		}
		
		return result; 		
  	}
  	
  /**
   * Criar comida;
   */
   private Point createFood()
   {
          int x =  (int)(Math.random()*39);
          int y =  (int)(Math.random()*39);
          return new Point(x,y);
   }
  
   /**
    *Testa se duas cobras se comeram
    */
   private void testSnakeCollision(ServerThread st)
   {
   		Point head = st.getSnake().getHead();   		
   		//testo se st não esbarrou em nenhuma outra cobra
   		Enumeration e = this.elements();
   		while (e.hasMoreElements())//pego um cliente
        {	
        	ServerThread clientThread = (ServerThread)e.nextElement();
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

}
