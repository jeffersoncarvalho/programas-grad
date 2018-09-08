package tetris.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

import tetris.table.Table; 
import tetris.util.GameControllerFacade;

public class Interface extends JFrame
{
	private Table table;
	private GameControllerFacade gcf;
	
	public Interface()
	{
		//criando container
		super("Tetris");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		this.setResizable(false);
		 
		 
		//criando a tabela
		table = new Table();
		//facade
		gcf = new GameControllerFacade(table);
		KeyHandler keyHandler = new KeyHandler();
		keyHandler.setTable(table);
		addKeyListener(keyHandler);
		
		//Guaribando o drawPanel Principal
	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BorderLayout());
	    mainPanel.add(table.getDrawPanel(), BorderLayout.CENTER);	
	    mainPanel.setBorder(new EmptyBorder(8,8,8,0));
	    //panel.setBorder( new SoftBevelBorder(SoftBevelBorder.LOWERED,Color.black,Color.white));
	    //panel.setBorder( new CompoundBorder(new EmptyBorder(4,4,4,4),new SoftBevelBorder(SoftBevelBorder.LOWERED,Color.DARK_GRAY,Color.white)));
	    mainPanel.setBackground(Color.black);
	    
	    //Guaribando o drawPanel do next
	    JPanel nextPanel = new JPanel();
	    nextPanel.setLayout(new BorderLayout());
	    nextPanel.add(table.getDrawPaneForNext(), BorderLayout.CENTER);	
	    nextPanel.setBorder(new EmptyBorder(8,8,8,8));
	    //panel.setBorder( new SoftBevelBorder(SoftBevelBorder.LOWERED,Color.black,Color.white));
	    //panel.setBorder( new CompoundBorder(new EmptyBorder(4,4,4,4),new SoftBevelBorder(SoftBevelBorder.LOWERED,Color.DARK_GRAY,Color.white)));
	    nextPanel.setBackground(Color.black);
	    
	    
		
		
		//paínel controle 
		ControlPanel controlPanel = new ControlPanel();
		
		//adionando o painel de next piece
		c.add(mainPanel,BorderLayout.CENTER);
		c.add(nextPanel,BorderLayout.EAST);
		c.add(controlPanel.getBar(),BorderLayout.NORTH);
		
		this.setVisible(true);
    	pack();
		
		table.run();

		
		//Fechando Janela
		addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}  
		);
		
	}
	
	public static void main(String args[])
	{
		new Interface();
	}
}

