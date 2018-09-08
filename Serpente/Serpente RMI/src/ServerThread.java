import java.net.*;
import java.io.*;
import java.util.*;

class ServerThread extends Thread {
	
	private Snake snake;
	private Point food;
	
	public ServerThread(Snake snake, Point food) {
		
		this.snake = snake;
		this.food = food;
		
	}
	
	public void run()
	{
	
			this.processSnake();
			//this.receive();			
	   
	}
	
	/**
	 * processando a msg
	 */
	private void processMessage(String msg)
	{
		
		if(msg.startsWith("DIRECAO"))
  		{
  		  	StringTokenizer tokens = new StringTokenizer(msg);
  		  	tokens.nextToken();//lixo
  		  	int d = Integer.parseInt(tokens.nextToken());
  		  	this.snake.setDirection(d);
  		}
	}
	
	/**
	 * movimenta a cobra
	 */
	public void processSnake()
	{
		Thread t = new Thread(){
            public void run(){
             while(true){
             	
             	testMyColision();
             	testFoodColision();
				snake.move();
				try {Thread.sleep(100);}
				catch (Exception ex) {}
			 }
            }
        };//fim da classe thread
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
	}
	
  /**
   * testa se uma cobra comeu a comida.
   */
   public void testFoodColision()
   {
   		Point head = this.getSnake().getHead();
   		if((head.getX() == food.getX()) &&
   		   (head.getY() == food.getY()) )
   	    {
   	    	getSnake().addPoint();//dou ponto
   	    	getSnake().increase(food.getX(),food.getY());//aumento a cobra!ui!
   	    	//calculando nova posição para a comida
   	    	int x =  (int)(Math.random()*39);
          	int y =  (int)(Math.random()*39);
          	food.setX(x);
          	food.setY(y);
          //	send("PONTO " + getSnake().getPoint());
          	//clientThread.send("FOOD " + food.getX() + " " + food.getY());//enviando a comida.
          	
   		}
   }
   
   /**
    *testa se eu me comi.
    *
    */
   public void testMyColision()
   {
   		Point head = this.getSnake().getHead();
   		//testo se ele fez autofagia??!
   		ArrayList myBody = this.getSnake().getSnake();
   		for(int i=1;i<myBody.size();i++)
   		{
   			Point piece = (Point)myBody.get(i);
   		 	if((head.getX() == piece.getX()) && (head.getY() == piece.getY()))
   		 	{
   		 	   	getSnake().killSnake();
   		 	   	break;
   		    }
   		}
   }
	
	/**
	 *
	 */
	public Snake getSnake()
	{
		return this.snake;
	}
	

}
