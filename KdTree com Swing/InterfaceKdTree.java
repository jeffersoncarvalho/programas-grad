import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;



public class InterfaceKdTree extends JFrame
{
	private PainelDesenho pd;
	private PainelControle pc;
	private PainelResposta pr;
	
	public InterfaceKdTree()
	{
		super("Cadê3");
		Container c=getContentPane();
		c.setLayout(new BorderLayout());
		
		
		pd = new PainelDesenho();
		pr = new PainelResposta();
		pc = new PainelControle(pd,pr);
		
		c.add(pr,BorderLayout.NORTH);	
		c.add(pc,BorderLayout.SOUTH);
		c.add(pd,BorderLayout.CENTER);
		
		
		
		setSize(500,500);
		show();
		
	}//construtor
	
	public static void main(String args[])
	{
		InterfaceKdTree ikt = new InterfaceKdTree();
		
		ikt.addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}
		);
		
		
	}//main
	
	
    public void destroy() 
    {
        remove(pd);
        remove(pc);
        remove(pr);
    }

}//class main

//******************************************************************************
class PainelDesenho extends JPanel
{
	private final int partidaX=95,partidaY=50;
    private byte matriz[][]; 
    private byte contaPontos;
	
	public PainelDesenho()
	{
	
		FlowLayout layout=new FlowLayout();
		layout.setAlignment(FlowLayout.CENTER);	
    	setLayout(layout);
    	
	    super.setBackground(Color.LIGHT_GRAY);
	    //super.setForeground(Color.white);
	    super.setPreferredSize(new Dimension(400,400));
	    matriz = new byte[302][302];	
	    
	    contaPontos = 2;
	    
	    
	    for(int i=0;i<302;i++)
	     matriz[i][0]=1;
	     
	    for(int i=0;i<302;i++)
	     matriz[i][301]=1;
	     
	    for(int j=0;j<302;j++)
	     matriz[0][j]=1;
	     
	    for(int j=0;j<302;j++)
	     matriz[301][j]=1;
	     
	     
       
	}//constutor
	
	public void paint(Graphics g)
	{	
		super.paint(g);
		g.drawString("1",94,362);
		g.drawString("100",393,362);
		
		
		
			
        for(int i = 0;i<302;i++)
         for(int j = 0;j<302;j++)
          if(matriz[i][j]>=1)
          {
            if(matriz[i][j]==1)
            {
              
			  g.setColor(Color.red);	
              g.drawOval(partidaX+j,(partidaY+301-i),0,0);
            }
			else
			{
			  g.setColor(Color.BLACK);
			  g.drawOval(partidaX+j,partidaY+(301-i),2,2);
			  g.drawOval(partidaX+j,partidaY+(301-i),1,1);
			  int aux=matriz[i][j]-1;
			  String resp=Integer.toString(aux)+"("+Integer.toString(j/3)+","+Integer.toString(i/3)+")";
			  g.drawString("P"+resp,j+2+partidaX,(301-i-4)+partidaY);
			  
			}           
          }
	}
	
	public void setMatriz(String arvore)
    {
      repaint(); 
      StringTokenizer tokens = new StringTokenizer(arvore);
      byte px,py,disc,count;
      count = 0;
      px=0;
      py=0;
      contaPontos=0;
      zerarMatriz();
      
      while(tokens.hasMoreTokens())
      {
      	if((count % 4)==0)
      	 px = Byte.parseByte(tokens.nextToken());
      	else
      	 if((count % 4)==1)
      	  py = Byte.parseByte(tokens.nextToken());
      	 else
      	  if((count % 4)==2)
      	   contaPontos = Byte.parseByte(tokens.nextToken());
      	  else 
      	   if((count % 4)==3)
      	   {
      	      disc = Byte.parseByte(tokens.nextToken());
      	     
      	      //matriz[linha][coluna]<--LEMBRE-SE!!
      	     
      	      if(disc==0)
      	      {
      	     	int iterator=1;
      	     	int linha,coluna;
      	     	linha = 3*py;
      	     	coluna = 3*px;
      	     	
      	     	matriz[linha][coluna]=contaPontos;
      	     	
      	     	while(matriz[linha+iterator][coluna]!=1)
      	     	{
      	     		matriz[linha+iterator][coluna]=1;
      	     		iterator++;
      	     	}//subo
      	     	
      	     	iterator=-1;
      	     	
      	     	while(matriz[linha+iterator][coluna]!=1)
      	     	{
      	     		matriz[linha+iterator][coluna]=1;
      	     		iterator--;
      	     	}//desço
      	     		
      	      }//corto o eixo x
      	      else
      	      {
      	     	int iterator=1;
      	     	int linha,coluna;
      	     	linha = 3*py;
      	     	coluna = 3*px;
      	     	
      	     	matriz[linha][coluna]=contaPontos;
      	     	
      	     	while(matriz[linha][coluna+iterator]!=1)
      	     	{
      	     		matriz[linha][coluna+iterator]=1;
      	     		iterator++;
      	     	}//direita
      	     	
      	     	iterator=-1;
      	     	
      	     	while(matriz[linha][coluna+iterator]!=1)
      	     	{
      	     		matriz[linha][coluna+iterator]=1;
      	     		iterator--;
      	     	}//esquerda
      	     	
      	      }//corto o eixo y
      	
      	
      	  }//já tenho todos os valores
      	count++;
      }//while tokens...
      
     
      
    }// setMatriz
    
    private void zerarMatriz()
    {
    		for(int i=1;i<301;i++)
    		 for(int j=1;j<301;j++)
    		  matriz[i][j]=0;
    }
	
}
//******************************************************************************

class PainelControle extends JPanel 
{
  private PainelDesenho target;
  private PainelResposta targetResposta;	
  private JLabel labelX,labelY;
  private JTextField campoX,campoY,campoResultado;
  private JButton botaoInserir,botaoPesquisa,botaoRemover;
  private JRadioButton discriminadorX,discriminadorY;
  private ButtonGroup grupoDiscriminadores;
  private int disEscolhido;
  private Utilitarios util;
  private KdTree kdtree;
  
  public PainelControle(PainelDesenho target, PainelResposta targetResposta)
  {
  	this.target=target;
  	this.targetResposta=targetResposta;
  	ManejadorBotoes buttonHandler = new ManejadorBotoes();
  	ManejadorItem itemHandler = new ManejadorItem();
  	
  	FlowLayout layout;
  	layout = new FlowLayout();
	layout.setAlignment(FlowLayout.CENTER);	
    setLayout(layout);
    setBorder(new BevelBorder(BevelBorder.RAISED));

    disEscolhido=0;
    kdtree = new KdTree();
    util = new Utilitarios();

    labelX=new JLabel("X :");
    add(labelX);
    campoX=new JTextField(2);
    add(campoX);

    labelY=new JLabel("Y :");
    add(labelY);
    campoY=new JTextField(2);
    add(campoY);

    discriminadorX=new JRadioButton("eixoX",true);
    add(discriminadorX);
    discriminadorY=new JRadioButton("eixoY",false);
    add(discriminadorY);

    discriminadorX.addItemListener(itemHandler);
    discriminadorY.addItemListener(itemHandler);

    grupoDiscriminadores=new ButtonGroup();
    grupoDiscriminadores.add(discriminadorX);
    grupoDiscriminadores.add(discriminadorY);


    botaoInserir = new JButton("Inserir");
    botaoInserir.addActionListener(buttonHandler);
    add(botaoInserir);
 
    botaoRemover = new JButton("Remover");
    botaoRemover.addActionListener(buttonHandler);
    add(botaoRemover);
    
    botaoPesquisa = new JButton("Pesquisar");
    botaoPesquisa.addActionListener(buttonHandler);
    add(botaoPesquisa);
  	
  }//construtor

  
  //INÍCIO DA DEFINIÇÃO DAS CLASSES INTERNAS PARA TRATAMENTO DE EVENTOS
  private class ManejadorBotoes implements ActionListener
  {
  	public void actionPerformed(ActionEvent e)
    {
  		if(util.valoresValidos(campoX.getText(),campoY.getText()))
        { 	
          if(e.getSource()==botaoInserir)
          { 
                int px,py;
                px = Integer.parseInt(campoX.getText());
                py = Integer.parseInt(campoY.getText());
             	if(kdtree.pesquisaPonto(px,py)==null)
             	{
        	      if(!kdtree.inserirNo(px,py,disEscolhido))
        	       JOptionPane.showMessageDialog(null,"Não é possível inserir filhos com a mesma orientação do pai!","Erro",JOptionPane.ERROR_MESSAGE);      
        	      else
        	      { 
 			      	target.setMatriz(kdtree.getArvore());
 			      	targetResposta.setCampoResposta(util.formatarArvoreString(kdtree.getArvore()));
 			      }
 			    }//não tenho o ponto 
 			    else 
 			       JOptionPane.showMessageDialog(null,"Ponto existente na Árvore.","Erro",JOptionPane.ERROR_MESSAGE);      
 			    	
		  }//inserir
		  else
		   if(e.getSource()==botaoPesquisa)
		   {
		   	 String valX,valY;
		 	  valX = campoX.getText();
		 	  valY = campoY.getText();
		 	  if(kdtree.pesquisaPonto(Integer.parseInt(valX),Integer.parseInt(valY))!=null)
		 	 	  JOptionPane.showMessageDialog(null,"Valor encontrado!","Resultado",JOptionPane.PLAIN_MESSAGE);
		 	  else
		 		  JOptionPane.showMessageDialog(null,"Valor não encontrado!","Resultado",JOptionPane.PLAIN_MESSAGE);
	     
		   }//pesquisar
		   else
		    if(e.getSource()==botaoRemover)
		    {
		    	int px,py;
                px = Integer.parseInt(campoX.getText());
                py = Integer.parseInt(campoY.getText());
		    	if(kdtree.removerNo(px,py))
		    	{
		    	  JOptionPane.showMessageDialog(null,"Ponto removido!","Resultado",JOptionPane.PLAIN_MESSAGE);     
		    	  target.setMatriz(kdtree.getArvore());
		    	  targetResposta.setCampoResposta(util.formatarArvoreString(kdtree.getArvore()));	 	
		    	}
		    	else
		    	  JOptionPane.showMessageDialog(null,"Ponto não existente ou não nó folha.","Erro",JOptionPane.ERROR_MESSAGE); 	 
		     
		   		
		    }//remover	        
        
        }//if do valores válidos  
        
        campoX.setText("");
        campoY.setText("");   
 
    }//ação botões
  	
  }//classe Manejadora de botoes
  
  private class ManejadorItem implements ItemListener
  {
  	  public void itemStateChanged(ItemEvent e)
  	  {
    	if(e.getSource()==discriminadorX)
      		disEscolhido=0;
    	else
      		disEscolhido=1;
  	  }//radios buttons ação!
  	  
  }//classe Manejadora de items
  
  //FIM DA DEFINIÇÃO DAS CLASSES INTERNAS
    
}//classe do painel de controle
//******************************************************************************
class PainelResposta extends JPanel
{
	private JTextField campoResposta;
	private JLabel l;
	
	public PainelResposta()
	{
		FlowLayout layout=new FlowLayout();
		layout.setAlignment(FlowLayout.CENTER);	
    	setLayout(layout);
    	setBorder(new BevelBorder(BevelBorder.RAISED));
    	
    	l=new JLabel("Árvore em Pré-Ordem:");
    	add(l);
    	campoResposta = new JTextField(30);
    	campoResposta.setEditable(false);
    	add(campoResposta);
    	
	}
	public void setCampoResposta(String cad)
	{
		campoResposta.setText(cad);
	}
}//class PainelResposta
//******************************************************************************
class Utilitarios
{
  public Utilitarios()
  {
  	
  }
  
  public boolean valoresValidos(String valorCampoX, String valorCampoY)
  {
  	if(valorCampoX.length()==0 || valorCampoY.length()==0)
  	{
         JOptionPane.showMessageDialog(null,"Não deixe campo(s) vazios","Erro",JOptionPane.ERROR_MESSAGE);
		 return false;
	}   
    else
         if(!isNumber(valorCampoX) || !isNumber(valorCampoY))
         {
          JOptionPane.showMessageDialog(null,"Valores não numéricos!","Erro",JOptionPane.ERROR_MESSAGE);
      	  return false;
      	 }	
         else
         {
           int ptX=Integer.parseInt(valorCampoX);
           int ptY=Integer.parseInt(valorCampoY);
           if((ptX<=0 || ptX>100) ||(ptY<=0 || ptY>100))
           {
              JOptionPane.showMessageDialog(null,"Valores devem ser de 1 a 100!","Erro",JOptionPane.ERROR_MESSAGE);
              return false;
           }
         }
         
         return true;
         
  }//valores validos
  
  public boolean isNumber(String val)
  {
    int j=0;
    if((val.charAt(j)=='+')||(val.charAt(j)=='-'))
     j=1;

    if(j==val.length())
     return false;

    for(int i=j;i<val.length();i++)
     if(!Character.isDigit(val.charAt(i)))
      return false;
     return true ;
  }//is number?
  
  public String formatarArvoreString(String arvore)
  
  {
  	String resultado="";
  	StringTokenizer tokens = new StringTokenizer(arvore);
      byte px,py,count;
      count = 0;
      px=0;
      py=0;
    
      while(tokens.hasMoreTokens())
      {
      	if((count % 4)==0)
      	 px = Byte.parseByte(tokens.nextToken());
      	else
      	 if((count % 4)==1)
      	  py = Byte.parseByte(tokens.nextToken());
      	 else
      	  if((count % 4)==2)
      	   tokens.nextToken();
      	  else 
      	   if((count % 4)==3)
      	   {
      	      resultado+="("+Byte.toString(px)+","+Byte.toString(py)+")";
      	      tokens.nextToken();
      	     
      	      //matriz[linha][coluna]<--LEMBRE-SE!!
      	     
      	   }//já tenho todos os valores
      	count++;
      }//while tokens...
      
      
  	return resultado;  
  }//formata 
	
}//utilitarios