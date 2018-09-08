package br.ufc.lia.sd.morphufc.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import br.ufc.lia.sd.morphufc.communication.Server;
import br.ufc.lia.sd.morphufc.observer.Observer;
import br.ufc.lia.sd.morphufc.util.Constants;

/**
 * Esta classe tem duas funçõe principais: Servir como GUI para o usuário e
 * criar o servidor de conexoes.
 * @author Jefferson
 *
 */
public class MorphUFC extends JFrame{
	
	Server server;
	ControlPanel controlPanel;
	LogPanel logPanel;
	FileListPanel fileListPanel;
	
	private Observer observer;
	
	public MorphUFC(){
		
		this.setPreferredSize(new Dimension(500,445));
		this.setResizable(false);
		this.observer = new Observer();
		//criando painel
		controlPanel = new ControlPanel();
		controlPanel.setObserver(observer);
		logPanel = new LogPanel();
		fileListPanel = new FileListPanel();
		fileListPanel.setObserver(observer);
		//observer??
		this.observer.setControlPanel(controlPanel);
		this.observer.setFileListPanel(fileListPanel);
		this.observer.setLogPanel(logPanel);
		
		
		//setando tipo de container
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		//distribuindo paineis
		c.add(controlPanel, BorderLayout.NORTH);
		c.add(fileListPanel,BorderLayout.CENTER);
		c.add(logPanel, BorderLayout.SOUTH);
		
		//fechando a janela
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				Server.printer.serverPrinter("System terminated.");
				System.exit(0);
			}
        });

		//iniciando servidor
		this.waitClients();
		
		this.setTitle("MorphUFC 1.0 - " + this.server.getServerIp()+":"+Constants.SERVER_PORT);
		//mostrando
		this.setVisible(true);
        this.pack();

	}
	
	private void waitClients(){
		
		this.server = new Server();
		 
		this.server.start();
		
	}

	public static void main(String[] args) {
		
		if(args.length>0){
			try {
				int port = Integer.parseInt(args[0]);
				Constants.SERVER_PORT = port;
			} catch (NumberFormatException e) 
			{
				System.out.println("Port format error: Enter a valid number.");
				System.out.println("usage: morphufc.jar <port>");
				
				System.exit(0);
			}
			
			
		}
		
		new MorphUFC();
		 
	}
	
	 
}
