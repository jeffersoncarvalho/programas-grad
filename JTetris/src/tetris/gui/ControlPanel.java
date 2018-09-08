package tetris.gui;

import javax.swing.*;
import java.awt.event.*;

//painel de controle
class ControlPanel extends JPanel
{
	private JMenuBar bar;
    
	public ControlPanel( )
	{
       this.bar = new JMenuBar();
       //combo menus
       JMenu fileMenu = new JMenu("File");
	   JMenu helpMenu = new JMenu("Help");
		
	   fileMenu.setMnemonic('A');
	   helpMenu.setMnemonic('j');
 
                        
	    //item apagar de File
		JMenuItem startGameItem = new JMenuItem("Start game");
		startGameItem.setMnemonic('i');
		startGameItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{

				}
			}
		);

		//item siar de File
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('S');
		exitItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			}
		);

		//About de Help
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.setMnemonic('S');
		aboutItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					JOptionPane.showMessageDialog(null,"Title: Tetris Game\nStudent: Jefferson de Carvalho Silva",
												  "About",JOptionPane.PLAIN_MESSAGE);
				}
			}
		);


		//menu file
		//fileMenu.add(construirItem);
		fileMenu.add(startGameItem);
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
     
}