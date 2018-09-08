import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import java.net.*;
import java.util.*;

public class Interface extends JFrame {

  private DrawPanel drawPanel;
//  private ControlPanel controlPanel;
  private ClientSnake clientSnake;
 // private ResultPanel resultPanel;
  
  public Interface()
  {
  	//Setando o layout
    super("Distributed Nimbus version RMI");
	Container c = getContentPane();
	c.setLayout(new BorderLayout());
	this.setResizable(false);
	
	//alocando memória para os painéis
    drawPanel = new DrawPanel();
    //controlPanel = new ControlPanel();
    //resultPanel = new ResultPanel();
    
    //Guaribando o drawPanel
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(drawPanel, BorderLayout.CENTER);	
    panel.setBorder(new EmptyBorder(8,8,8,8));
    panel.setBackground(Color.BLACK);
    
    //Adcionando Painéis ao Frame
	c.add(panel, BorderLayout.CENTER); // desenho
	//c.add(controlPanel.getBar(),BorderLayout.NORTH);
	//c.add(resultPanel,BorderLayout.SOUTH);
	//Mostrando Frame
	show();
    pack();
	
	//Criando cliente e conectando
	clientSnake = new ClientSnake(drawPanel);
	//controlPanel.setClient(clientSnake);
	
	KeyHandler keyHandler = new KeyHandler(clientSnake);
	addKeyListener(keyHandler);
    
 	//Fechando Janela
	addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent e)
				{
					clientSnake.getInvoker().send("EXIT");
					System.exit(0);
				}
			}  
		);
	
    

  } 
  
  
  public static void main(String args[])
  {
        final Interface i = new Interface();
  }

}


class KeyHandler extends KeyAdapter
{
  private ClientSnake clientSnake;
  
  public KeyHandler(ClientSnake clientSnake)
  {
  	this.clientSnake = clientSnake;
  }
  
  /**
   *Ouvindo o teclado
   */ 
  public void keyPressed(KeyEvent e)
  {
     int key = e.getKeyCode();
     
     try{
     switch(key)
      {
        case KeyEvent.VK_LEFT:
          clientSnake.getInvoker().send("DIRECAO " + Direction.LEFT);
          
         //esquerda
        break;
        case KeyEvent.VK_RIGHT:
          clientSnake.getInvoker().send("DIRECAO " + Direction.RIGHT);

         //direita
        break;
        case KeyEvent.VK_UP:
          clientSnake.getInvoker().send("DIRECAO " + Direction.UP);
         //cima
        break;
        case KeyEvent.VK_DOWN:
          clientSnake.getInvoker().send("DIRECAO " + Direction.DOWN);
         //baixo
        break;
      }//switch
	}
	catch(Exception ex)
	{
		System.out.println ("Problemas no envio da direção");
		System.out.println (ex.toString());
		
	}
  }
  
}

/*class ControlPanel extends JPanel
{
	private JMenuBar bar;
	private ClientSnake clientSnake;
	
	public ControlPanel()
	{
		bar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		fileMenu.setMnemonic('F');
		helpMenu.setMnemonic('H');
		
		//item conect de File
		JMenuItem conectItem = new JMenuItem("Conect...");
		conectItem.setMnemonic('C');
		conectItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					
						String ip = JOptionPane.showInputDialog("Enter server IP.");
						clientSnake.connect(ip);
					
					
				}
			}
		);
		
		//item disconect de File
		JMenuItem disconectItem = new JMenuItem("Disconect");
		disconectItem.setMnemonic('D');
		disconectItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					clientSnake.disconect();
				}
			}
		);
		
		//item exit de File
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('E');
		exitItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					clientSnake.disconect();
					System.exit(0);
				}
			}
		);
		
		//About de Help
		final ImageIcon iconBaby = new ImageIcon("baby.gif");
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.setMnemonic('b');
		aboutItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					JOptionPane.showMessageDialog(null,"Disbributed Nimbus \nversion TCP",
												  "About",JOptionPane.PLAIN_MESSAGE,iconBaby);
				}
			}
		);
	
		
		//menu file
		fileMenu.add(conectItem);
		fileMenu.add(disconectItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		//menu help
		helpMenu.add(aboutItem);
		//barra principal
		bar.add(fileMenu);
		bar.add(helpMenu);
	}
	
	public JMenuBar getBar()
	{
		return bar;
	}
	
	public void setClient(ClientSnake cs)
	{
		this.clientSnake = cs;
	}
}


class ResultPanel extends JPanel
{
	private JLabel labelPoint;
	public ResultPanel()
	{
		
	    setLayout(new FlowLayout());
	    ImageIcon iconAlien = new ImageIcon("smile.gif");
		labelPoint = new JLabel("Score: 0",iconAlien,SwingConstants.LEFT); 
		add(labelPoint);
	}
	public void setPoint(String msg)
	{
		StringTokenizer tokens = new StringTokenizer(msg);
	    tokens.nextToken();//token Ponto
		labelPoint.setText("Score: " + tokens.nextToken());
	}
}
*/