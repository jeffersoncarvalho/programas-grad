/*
 * InterfaceMain.java
 *
 * Created on 25 de Agosto de 2004, 08:35
 */

package gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import ambiente.*;

import java.io.*;


/**
 *
 * @author  knoppix
 */
public class InterfaceMain extends JFrame {
    
    
    
    private GridPanel gridPanel ;
    private ControlPanel controlPanel;
    private Ambiente ambiente;
    private JTextArea relatorioMemo;
    /** Creates a new instance of InterfaceMain */
    public InterfaceMain() {
       Runtime.getRuntime().gc();
        //quando a janela for fechada...
        setTitle("Simulador de Crimes");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
                });
        
        //alocando memória do painel de desenho
        this.gridPanel = new GridPanel(30,30);
        JPanel panelDesenho = new JPanel();
        panelDesenho.setLayout(new BorderLayout());
        panelDesenho.add(this.gridPanel, BorderLayout.CENTER);	
        panelDesenho.setBorder(new EmptyBorder(5,5,5,5));
        panelDesenho.setBackground(Color.BLACK);
        
        //relatório
        this.relatorioMemo = new JTextArea(20,25);
        this.relatorioMemo.setEditable(false);
        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(new BorderLayout());
        JLabel lbRel = new JLabel("RELATÓRIO");
        lbRel.setHorizontalAlignment(JLabel.CENTER);
        panelRelatorio.add(lbRel,BorderLayout.NORTH);
        panelRelatorio.add(new JScrollPane(this.relatorioMemo), BorderLayout.CENTER);
        panelRelatorio.add(new JLabel(" "),BorderLayout.WEST);	
       
        
        //criando o ambiente
        CidadeFactory cf = new CidadeFactory();
        //this.ambiente = cf.criarAmbiente(this.gridPanel);
        this.gridPanel.setAmbiente(this.ambiente);
       
        //alocando memória da barra de controle
        this.controlPanel = new ControlPanel(this.ambiente, this.gridPanel);
        this.controlPanel.setRelatorio(this.relatorioMemo);
        
        //adcionando os painéis ao layout do conteiner
        Container c = getContentPane();
		c.setLayout(new BorderLayout());
        c.add(this.controlPanel.getBar(),BorderLayout.NORTH);
        c.add(panelDesenho,BorderLayout.WEST);
        c.add(new JLabel("     "),BorderLayout.CENTER);
        c.add(panelRelatorio,BorderLayout.EAST);
        
        
        //mostrando o frame
        this.setResizable(false);
        this.setLocation(60, 20);
        this.show();
        this.pack();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new InterfaceMain();
    }
    
    public static String formatString(String in)
	{
		String out = in.replaceAll("%20"," ");
		out = out.replaceAll("%5","\\");
		return out;
	}

}

//painel de controle
class ControlPanel extends JPanel
{
	private JMenuBar bar;
	private Ambiente ambiente;
        private GridPanel gridPanel;
        private JFileChooser fileChooser;
        private CidadeFactory cf;
        private Configuracao conf;
        private JButton botaoPlay,botaoPause,botaoStop;
        private JLabel labelCrimesCometidos;
        private JTextArea relatorio;
        
	public ControlPanel(Ambiente target, GridPanel targetPanel)
	{
                this.ambiente = target;
                this.gridPanel = targetPanel;
                this.cf = new CidadeFactory();
                this.fileChooser = new JFileChooser();
                this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				this.bar = new JMenuBar();
                this.conf = new Configuracao(targetPanel);
          		this.labelCrimesCometidos = new JLabel("Crimes Cometidos: 0 de 0");
                
                
                botaoPlay = new JButton();
                botaoPlay.setToolTipText("Play");
                botaoPlay.setBorder(null);
                botaoPlay.setIcon(new javax.swing.ImageIcon( 
                		InterfaceMain.formatString( ClassLoader.getSystemResource("images/play.gif").getFile())
                ));
                botaoPlay.setPressedIcon(new javax.swing.ImageIcon( 
                		InterfaceMain.formatString( ClassLoader.getSystemResource("images/playPressed.gif").getFile())
                ));
                botaoPlay.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                    //play
                                                    if(ambiente!=null)
                                                        ambiente.unPause();
                                                }
                                            });
                
                botaoPause = new JButton();
                botaoPause.setToolTipText("Pause");
                botaoPause.setBorder(null);
                botaoPause.setIcon(new javax.swing.ImageIcon( 
                		InterfaceMain.formatString( ClassLoader.getSystemResource("images/pause.gif").getFile())
                ));
                botaoPause.setPressedIcon(new javax.swing.ImageIcon( 
                		InterfaceMain.formatString( ClassLoader.getSystemResource("images/pausePressed.gif").getFile())
                ));
                botaoPause.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                    //play
                                                    if(ambiente!=null)
                                                        ambiente.pause();
                                                }
                                            });
                                            
                /*botaoStop = new JButton();
                botaoStop.setToolTipText("Stop");
                botaoStop.setBorder(null);
                botaoStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("./images/stop.jpg")));*/
				
				//item apagar de File
		JMenuItem apagarMemoItem = new JMenuItem("Apagar relatório");
		apagarMemoItem.setMnemonic('A');
		apagarMemoItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{

						
                    relatorio.setText("");

				}
			}
		);
		
		//item apagar de File
		JMenuItem pararItem = new JMenuItem("Parar simulação");
		pararItem.setMnemonic('P');
		pararItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					if(ambiente !=null)
					{
						ambiente.matarAplicação();
						ambiente = null;
						
					}  

				}
			}
		);
				
				
		JMenu fileMenu = new JMenu("Arquivo");
		JMenu helpMenu = new JMenu("Ajuda");
		fileMenu.setMnemonic('A');
		helpMenu.setMnemonic('j');

		//item construir de File
		JMenuItem construirItem = new JMenuItem("Construir...");
		construirItem.setMnemonic('C');
		construirItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{

						//chama tela de construção
                                                 new PontosNotaveisPanel();

				}
			}
		);

		//item iniciar de File
		JMenuItem iniciarItem = new JMenuItem("Iniciar Simulação");
		iniciarItem.setMnemonic('I');
		iniciarItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					//inicia a simulação
                                        File temp = retornaArquivo();
                                        if(temp!=null)
                                        {
                                        	if(ambiente!=null)
                                        		ambiente.matarAplicação();
                                        	labelCrimesCometidos.setText("Crimes Cometidos: 0 de 0");
                                            ambiente = cf.criarAmbienteDoArquivoDoJeitoQueOVascoQuer(gridPanel, temp); 
                                            ambiente.setLabesCrimesCometidos(labelCrimesCometidos);
                                            ambiente.setRelatorio(relatorio);
                                            gridPanel.repaint();
                                            //JOptionPane.showMessageDialog(null,"Ambiente carregado com sucesso!","",JOptionPane.PLAIN_MESSAGE);
                                            conf.setAmbiente(ambiente);
                                            conf.setLocation(400, 250);
                                            conf.show();
                                            //ambiente.iniciarSimulacao();
                                            //teste
                                        }
				}
			}
		);
		
		//item siar de File
		JMenuItem exitItem = new JMenuItem("Sair");
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
		JMenuItem aboutItem = new JMenuItem("Sobre");
		aboutItem.setMnemonic('S');
		aboutItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					JOptionPane.showMessageDialog(null,"Assunto: Simulador de Crimes\nAluno: Jefferson de Carvalho Silva\nProfessor: Vasco Furtado",
												  "Sobre",JOptionPane.PLAIN_MESSAGE);
				}
			}
		);


		//menu file
		//fileMenu.add(construirItem);
		fileMenu.add(iniciarItem);
		fileMenu.add(pararItem);
		fileMenu.add(apagarMemoItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		//menu help
		helpMenu.add(aboutItem);
		//barra principal
		bar.add(fileMenu);
		bar.add(helpMenu);
        bar.add(botaoPlay);
        bar.add(new JLabel("  "));
        bar.add(botaoPause);
        /*bar.add(new JLabel("  "));
        bar.add(botaoStop);*/
        bar.add(new JLabel("                                                                                                                                                                               "));
        bar.add(this.labelCrimesCometidos);
	}

	public JMenuBar getBar()
	{
		return bar;
	}
        
        public File retornaArquivo()
        {
            int result = this.fileChooser.showOpenDialog(this);
            if(result == JFileChooser.CANCEL_OPTION)
                return null;
            return fileChooser.getSelectedFile();
        }

        /** Getter for property relatorio.
         * @return Value of property relatorio.
         *
         */
        public javax.swing.JTextArea getRelatorio() {
            return relatorio;
        }        
	
        /** Setter for property relatorio.
         * @param relatorio New value of property relatorio.
         *
         */
        public void setRelatorio(javax.swing.JTextArea relatorio) {
            this.relatorio = relatorio;
            this.conf.setRelatorioMemo(relatorio);
        }
        
}
