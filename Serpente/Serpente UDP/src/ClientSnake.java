import java.net.*;
import java.util.StringTokenizer;

public class ClientSnake {

  
  private DatagramSocket socket;
  private DatagramPacket sendPacket, receivePacket;
  private InetAddress address;
  private DrawPanel drawPanel;
  private ResultPanel resultPanel;
  private Zip zipar;
  
  public ClientSnake(DrawPanel drawPanel, ResultPanel resultPanel)
  {
    this.drawPanel = drawPanel;
    this.resultPanel = resultPanel;
    zipar = new Zip();
  }

  

  /**
   * Inicia a lógica do recebimento de pacotes.
   */
   public void start()
   {
      this.play();
      Thread t = new Thread(){
            public void run(){

               while(true){   
                listenServer();
                
                //System.out.println("Saindo do run");
               }

            }
        };
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
   }



  /**
   * envia pacote
   */
   public void send(String msg)
   {
   		
    	try {
    		byte data[] = msg.getBytes();
             	 //enviando
        	sendPacket = new DatagramPacket( data, data.length,
                                              address, 5000 );//ajustando o pacote e o endereÄ‡o de destino,
        	socket.send( sendPacket );
	    }
	    catch (Exception ex) {
	    	System.out.println (ex.toString());
		}
	    
	    
	    

   }

   /**
    * recebe pacote
    */
   public String receive()
   {
    	try {
    		byte dataReceived[] = new byte[ 1500 ];
        	receivePacket = new DatagramPacket( dataReceived, dataReceived.length );
        	socket.receive( receivePacket );
        	return new String(receivePacket.getData(),0,receivePacket.getLength());
	    }
	    catch (Exception ex) {
	    	System.out.println (ex.toString());
	    }


       return null;
   }

  /**
   * Envia requisição de jogo.
   */
   public void play()
   {
     	this.send("JOGAR");

   }
   
   /**
    *
    */
   public void listenServer()
   {
   	 String s = this.receive();
   	  if(s!=null){
      	if(s.startsWith("COR"))
      	{

         	drawPanel.addObject(s);
            drawPanel.repaint();//pinta


      	}
      	else
      	 if(s.startsWith("FALHOU"))
      	 {
      	 	System.out.println ("Conexao recusada: servidor lotado!");
      	 }//servidor lotado.
      	 else
      	 	if(s.startsWith("EX"))
      	 	{
      	 		
      	 		drawPanel.clearPanel();
      	 	}
      	 	else
      	 		if(s.startsWith("PON"))
      	 		{
      	 			  	 		
      	 			resultPanel.setPoint(s);
      	 		}
      	 		else
      	 			if(s.startsWith("FOOD"))
      	 			{
      	 				StringTokenizer tokens = new StringTokenizer(s);
						tokens.nextToken();//token FOOD
						drawPanel.setFood(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
      	 			}
     }

      
   }
   
   /**
    * conecta com um determinado IP
    */
    public void connect(String IP) 
    {
    	try {

         socket = new DatagramSocket();
         address = InetAddress.getByName(IP);
         this.start();
      }
      catch( Exception e ) {
         System.out.println ("Host Desconhecido. Tente novamente.");
         //System.exit( 0 );
      }
    }
    
    /**
     * Cliente se "desconecta".
     */
     public void disconect()
     {
     	if(socket != null)
     		this.send("EXIT");
     	resultPanel.setPoint("PONTO 0");
     	drawPanel.clearPanel();
     }
     
}