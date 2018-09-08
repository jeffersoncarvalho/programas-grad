import java.net.*;
import java.util.*;
import java.util.zip.*;

public class ServerSnake
{

    private DatagramSocket socket;
    private DatagramPacket sendPacket, receivePacket;
    private static Hashtable tableSnakes;
    private int id;
    private Point food;

    /** Creates a new instance of Server */
    public ServerSnake() {
        id = 0;
        
        food = createFood();
        System.out.println ("Servidor Ativo.");
        try {
        	tableSnakes = new Hashtable();
            socket = new DatagramSocket( 5000 );//conecto na port 5000
        }
        catch( Exception e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

		Thread t = new Thread(){
		    public void run(){
		
		
		          try {
		            System.out.println("Chamando Begin");
		            begin();
		          }
		          catch (Exception ex) {
		          }
		
		
		
		    }
		};
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();

    }//Server

    //**************************************************************************
    private void begin()
    {
        Thread t = new Thread(){
            public void run(){

               while(true){   
                processAll();
  				echoAllSnakes();
                try{this.sleep(100);}
                catch(Exception e){}
                //System.out.println("Saindo do run");
               }

            }
        };
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();


        while(true)
  		{

                        
  			String s = this.receive();
  			
  			if(s.startsWith("JOGAR"))
  			{
				if(tableSnakes.size()<6)
				{
  			 	
                                
                                Snake snake = this.createSnake();
                                snake.Port = receivePacket.getPort();
  								tableSnakes.put(receivePacket.getAddress(), snake);
                                
              	}
              	else
              	{
              		this.send("FALHOU",receivePacket.getAddress(),receivePacket.getPort());
              	}
              	
  			}
  			else		 
  		  		if(s.startsWith("DIRECAO"))
  		  		{
  		  			//System.out.println (s);
  		  			StringTokenizer tokens = new StringTokenizer(s);
  		  			tokens.nextToken();//lixo
  		  			int d = Integer.parseInt(tokens.nextToken());
  		  			this.changeDirection(receivePacket.getAddress(),d);
  		  		}
  		  		else
  		  			if(s.startsWith("EX"))
  		  			{	
  		  				System.out.println ("Alguem saiu");
  		  				tableSnakes.remove(receivePacket.getAddress());
  		  				Enumeration keys = tableSnakes.keys();
  		  				while(keys.hasMoreElements())
  		  				{
  		  					InetAddress address = (InetAddress)keys.nextElement();
  		  					Snake snake = (Snake)tableSnakes.get(address);
  		  					this.send("EXIT",address,snake.Port);
  		  				}
  		  					
  		  			}
  		  			
  		  	try { Thread.sleep(10);}
  		  	catch(Exception e){}
                        

    	}
    }//start
    //**************************************************************************

	
	/**
	 *Recebendo pacote
	 */
    public String receive()
    {
    	try {
    		byte data[] = new byte[ 1500 ];
        	receivePacket = new DatagramPacket( data, data.length );//dados a receber.
        	socket.receive( receivePacket );//pegando o pacote.
        	return new String(receivePacket.getData(), 0, receivePacket.getLength() );
	    }
	    catch (Exception ex) {
	    }
	    return null;
	    
	    
    }

	/**
	 *Enviando pacote
	 */
    public void send(String msg, InetAddress address, int port)
    {
    	try {


                //System.out.println("Entrando no Send");
    		byte data[] = msg.getBytes();
    		sendPacket = new DatagramPacket(data,
                                            data.length,
                                            address,
                                            port);//setando o meu pacote a enviar.
            socket.send( sendPacket );//enviando pacote.
            //System.out.println("Servidor ecou o pacote.");
	    }
	    catch (Exception ex) {
                ex.printStackTrace();
	    }
    }

  /**
   * Calcular novas posiï¿½ï¿½es para todas as cobras
   */
  public void processAll()
  {
  	Enumeration e = tableSnakes.elements();
  	Enumeration k = tableSnakes.keys();
  	int i =0;
  	
  	while(e.hasMoreElements())
  	{
  		Snake snake = (Snake)e.nextElement();
  		InetAddress address = (InetAddress)k.nextElement();
  		snake.move();
  		if(snake.getHead().getX() == food.getX() && snake.getHead().getY() == food.getY() )
  		{
  		 snake.addPoint(); 	
  		 this.send("PONTO "+ (snake.getPoint()) ,address,snake.Port);//enviando ponto pro cliente.
  		 if(snake.getSnake().size()<100) 
          switch(snake.getDirection())
          {
             case Direction.DOWN:
                snake.increase(food.getX(),food.getY()+1);
             break;
             case Direction.UP:
                snake.increase(food.getX(),food.getY()-1);
             break;
             case Direction.LEFT:
                snake.increase(food.getX()-1,food.getY());
             break;
             case Direction.RIGHT:
                snake.increase(food.getX()+1,food.getY());
             break;
          }
           int x =  (int)(Math.random()*39);
           int y =  (int)(Math.random()*39);
           food.setX(x);
           food.setY(y);
        }//testa se comeu

		Point head = snake.getHead();
		Enumeration sube = tableSnakes.elements();
		int subi=0;
		while(sube.hasMoreElements())
		{
			Snake subsnake = (Snake)sube.nextElement();
			ArrayList subbody = subsnake.getSnake();
			if(i==subi)
			{
				for(int cont=1;cont<subbody.size();cont++)
				 {
				 	Point piece = (Point)subbody.get(cont);
				 	if(head.getX()==piece.getX() && head.getY()==piece.getY())
				 	{
				 		
				 		snake.killSnake();
				 	}
				 		
				 }
			}//sou eu mesmo
			else
			{
				for(int cont=0;cont<subbody.size();cont++)
				 {
				 	Point piece = (Point)subbody.get(cont);
				 	if(head.getX()==piece.getX() && head.getY()==piece.getY())
				 	{
				 		snake.killSnake();
				 		subsnake.killSnake();
				 	}	
				 }
			}	
			subi++;
		}//while
  		i++;
  	}//while
  }


  /**
   *muda a direçaõ de alguma cobra
   */
  public void changeDirection(InetAddress ip , int direction)
  {
     Snake snake = (Snake)tableSnakes.get(ip);
     if(snake!=null)
      snake.setDirection(direction);
  }

  /**
   * Devolve uma nova cobra para um jogador que acabade entrar
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
   * Transforma as cobras em String e manda para o cliente que requisitou
   */
  public void echoAllSnakes()
  {
  	

        Enumeration keys  = tableSnakes.keys();
        ArrayList arrayKeys = new ArrayList();
        while(keys.hasMoreElements())
            arrayKeys.add(keys.nextElement());

        Enumeration elem  = tableSnakes.elements();
        ArrayList arrayValues = new ArrayList();
        while(elem.hasMoreElements())
            arrayValues.add(elem.nextElement());

        
		String s = "";
        for(int i=0;i<arrayKeys.size();i++)
        {
            InetAddress thisKey = (InetAddress)arrayKeys.get(i);
            for(int j=0;j<arrayValues.size();j++)
            {
                Snake snake = (Snake)arrayValues.get(j);
                s+= "COR"+" " +snake.toString();
            }
            s+=" ";
            Snake snake2 = (Snake)arrayValues.get(i);
            
            this.send("FOOD " + food.getX() + " " + food.getY() +" ",thisKey,snake2.Port);
            this.send(s,thisKey,snake2.Port);
        }




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
    *
    */
    public static void main(String[] args) {
        ServerSnake server = new ServerSnake();//iniciando servidor.
    }
}