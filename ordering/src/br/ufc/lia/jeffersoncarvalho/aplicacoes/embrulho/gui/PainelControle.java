package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui;

 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Face;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Util3D;

public class PainelControle  
{
	ArrayList vertices = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar bar;
	
    private JFileChooser fileChooser;
          
    private boolean threadDisparada = false;
	public PainelControle(final EmbrulhoPainel tela)
	{
		//super.setPreferredSize(new Dimension(100,100));
        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		this.bar = new JMenuBar();
        
        
        
        
		 
		
		 
				
				
		JMenu fileMenu = new JMenu("Arquivo");
		JMenu helpMenu = new JMenu("Ajuda");
		fileMenu.setMnemonic('A');
		helpMenu.setMnemonic('j');

		//item construir de File
		JMenuItem construirItem = new JMenuItem("Rodar GW");
		construirItem.setMnemonic('W');
		construirItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{

						 if(vertices!=null){
							 tela.tiraPontos();
							 ArrayList res=  tela.rodaEmbrulho(vertices);
							 threadDesenhista(res,tela);
							 //tela.display();
						 }else
							 JOptionPane.showMessageDialog(null,"Carregue o arquivo primeiro!","Erro",JOptionPane.ERROR_MESSAGE);

				}
			}
		);
		
		JMenuItem construirItemFast = new JMenuItem("Rodar GW (Rápido)");
		construirItemFast.setMnemonic('G');
		construirItemFast.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{

						 if(vertices!=null){
							 tela.setTsleep(0);
							 tela.tiraPontos();
							 ArrayList res=  tela.rodaEmbrulho(vertices);
							 threadDesenhista(res,tela);
						 }else
							 JOptionPane.showMessageDialog(null,"Carregue o arquivo primeiro!","Erro",JOptionPane.ERROR_MESSAGE);

				}
			}
		);
		
		
//		item construir de File
		JMenuItem construirItemTetra = new JMenuItem("Tetraedralizar");
		construirItemTetra.setMnemonic('T');
		construirItemTetra.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{

						 if(vertices!=null){
							 tela.rodaTetraedralizacao(vertices);
						 }else
							 JOptionPane.showMessageDialog(null,"Carregue o arquivo primeiro!","Erro",JOptionPane.ERROR_MESSAGE);

				}
			}
		);
		
		
//		item construir de File
		JMenuItem construirFechoDaMorte = new JMenuItem("Fecho da Morte");
		construirFechoDaMorte.setMnemonic('F');
		construirFechoDaMorte.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{

						 if(vertices!=null){
							 tela.rodarAnimacaoFechoDaMorte();
						 }else
							 JOptionPane.showMessageDialog(null,"Carregue o arquivo primeiro!","Erro",JOptionPane.ERROR_MESSAGE);

				}
			}
		);
		
		//item iniciar de File
		JMenuItem iniciarItem = new JMenuItem("Carregar Arquivo...");
		iniciarItem.setMnemonic('C');
		iniciarItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					//inicia a simulação
                    File temp = retornaArquivo();
                    if(temp!=null)
                    {
                    	tela.limpa();
                    	vertices = Util3D.gerarVerticesDeArquivo(temp.toString(),tela);
                    	tela.tiraPontos();
                        tela.display();
                    	 
                    	if(threadDisparada==false){
                    		tela.rotacionaTela();
                        	 
                        	threadDisparada = true;
                    	}
                            //tela.rodaEmbrulho(vertices);
                    }
				}
			}
		);
		
		//item radomizar de File
		JMenuItem randomizarItemCubo = new JMenuItem("Gerar Pontos(Cubo)...");
		randomizarItemCubo.setMnemonic('B');
		randomizarItemCubo.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					String qtd = JOptionPane.showInputDialog(null,"Entre com o número de pontos.");
					try {
						int n = Integer.parseInt(qtd);
						tela.limpa();
						tela.display();
						vertices = Util3D.gerarVerticesAleatoriosCubo(n,tela);
						tela.tiraPontos();
                   	 	tela.display();
                    	 
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null,"Formato de número inválido!","Erro",JOptionPane.ERROR_MESSAGE);
					}
					 
				}
			}
		);
		
//		item radomizar de File
		JMenuItem randomizarItemGaussian = new JMenuItem("Gerar Pontos(Gaussian)...");
		randomizarItemGaussian.setMnemonic('S');
		randomizarItemGaussian.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					String qtd = JOptionPane.showInputDialog(null,"Entre com o número de pontos.");
					try {
						int n = Integer.parseInt(qtd);
						tela.limpa();
						tela.tiraPontos();
						tela.display();
						vertices = Util3D.gerarVerticesAleatoriosGaussian(n,tela);
						tela.display();
                    	 
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null,"Formato de número inválido!","Erro",JOptionPane.ERROR_MESSAGE);
					}
					 
				}
			}
		);
		
		JMenuItem randomizarItemEsfera = new JMenuItem("Gerar Pontos(Esfera)...");
		randomizarItemEsfera.setMnemonic('G');
		randomizarItemEsfera.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					String qtd = JOptionPane.showInputDialog(null,"Entre com o número de pontos.");
					try {
						int n = Integer.parseInt(qtd);
						tela.limpa();
						tela.tiraPontos();
						tela.display();
						vertices = Util3D.gerarVerticesAleatoriosEsfera(n,tela);
						tela.display();
                    	 
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null,"Formato de número inválido!","Erro",JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null,"Assunto: Embrulho 3D\nAluno: Jefferson de Carvalho Silva\nProfessor: Bento" +
							"\nComandos:" +
							"\nI -> Zoom In" +
							"\nO -> Zoom Out" +
							"\nS -> Diminui a velocidade" +
							"\nF -> Aumenta a velocidade" +
							"\nC -> Colorir" +
							"\nP -> Pontos" +
							"\nL -> Aramado" +
							"\nE -> Eixos" +
							"\nSetas -> Deslocam a figura na direção pressionada",
												  "Sobre",JOptionPane.PLAIN_MESSAGE);
				}
			}
		);


		 
		
		//menu file
		
		fileMenu.add(iniciarItem);
		fileMenu.addSeparator();
		fileMenu.add(randomizarItemCubo);
		fileMenu.add(randomizarItemEsfera);
		fileMenu.add(randomizarItemGaussian);
		fileMenu.addSeparator();
 		fileMenu.add(construirItem);
 		fileMenu.add(construirItemFast);
		fileMenu.addSeparator();
		fileMenu.add(construirItemTetra);
		fileMenu.addSeparator();
		fileMenu.add(construirFechoDaMorte);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		helpMenu.add(aboutItem);
		//barra principal
		bar.add(fileMenu);
		bar.add(helpMenu);
		//bar.add(triangulos);
		
        
	}

	public File retornaArquivo()
    {
        int result = this.fileChooser.showOpenDialog(null);
        if(result == JFileChooser.CANCEL_OPTION)
            return null;
        return fileChooser.getSelectedFile();
    }

	public JMenuBar getBar()
	{
		return bar;
	}
	
	private void threadDesenhista(final ArrayList res, final EmbrulhoPainel tela){
		Thread t = new Thread(){
			public void run() {
				  
				 for(int i=0;i<res.size();i++)
				 {
					 Face f = (Face)res.get(i);
					 tela.desenhaFace(f);
				 }
				 tela.trianguloDidatico = null;
				 tela.display();
				 tela.repaint();
				 
			};
		};
		t.setPriority(Thread.MAX_PRIORITY)	;
		t.start();
	}
   
}
