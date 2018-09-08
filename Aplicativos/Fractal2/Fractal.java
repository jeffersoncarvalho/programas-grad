import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Fractal extends JFrame implements MouseListener,ActionListener
{
  boolean dentro;

  int pontoX1,pontoY1;
  int pontoX2,pontoY2;
  int pontoX3,pontoY3;

  int pSelecionadoX,pSelecionadoY;
  int criadoX,criadoY;
  int calculadoX,calculadoY;

  int ncor=1;
  int cliques;

  Color cor;

  JLabel statusBar,labelPontos;
  JButton botaoCor;
  JPanel Painel;
  JTextField campoN;

  public Fractal()
  {
	super("Desenhando Fractais");

	Container c=getContentPane();
        Painel=new JPanel();
	Painel.setLayout(new GridLayout(4,1));

	cor=Color.blue;
	
	

	statusBar=new JLabel();
	Painel.add(statusBar);
        botaoCor=new JButton("AZUL");
	botaoCor.addActionListener(this);
	Painel.add(botaoCor);
	labelPontos=new JLabel("Número de Pontos:");
	Painel.add(labelPontos);
	campoN=new JTextField(10);
	campoN.setText("0");		
	Painel.add(campoN);	
	
	cliques=1;
	addMouseListener(this);

	c.add(Painel,BorderLayout.WEST);
	setSize(700,500);
	show();
  }
 
  public void selecionaPonto()
  {
    int ponto=1+(int)(Math.random()*3);

    switch(ponto)
    {
      case 1:
        pSelecionadoX=pontoX1;
        pSelecionadoY=pontoY1;
      break;
       case 2:
        pSelecionadoX=pontoX2;
        pSelecionadoY=pontoY2;
      break;
       case 3:
        pSelecionadoX=pontoX3;
        pSelecionadoY=pontoY3;
      break;
    }//switch;

  }

  public void criarPonto()
  {
    criadoX=1+(int)(Math.random()*800);
    criadoY=1+(int)(Math.random()*600);
  }

  public void calculaPonto()
  {
    calculadoX=(int)((pSelecionadoX+criadoX)/2);
    calculadoY=(int)((pSelecionadoY+criadoY)/2);
  }

  public void desenha(Graphics g)
  {
	long cont;
        criarPonto();
	for(cont=0;cont<Long.parseLong(campoN.getText());cont++)
	{
		selecionaPonto();
		calculaPonto();
		g.drawString(".",calculadoX,calculadoY);
                criadoX=calculadoX;
                criadoY=calculadoY;
	}

  }

  public static void main(String args[])
  {
	Fractal window=new Fractal();

        window.addWindowListener(
	  new WindowAdapter()
          {
	  	public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	  }
	);

    
  }
  
  public void mouseClicked(MouseEvent e)
  {
	
	
	switch(cliques)
	{
		case 1:
			pontoX1=e.getX();
			pontoY1=e.getY();
			break;
		case 2:
			pontoX2=e.getX();
			pontoY2=e.getY();
			break;
		case 3:
			pontoX3=e.getX();
			pontoY3=e.getY();
			break;		
	}

	

    if(dentro==true)
    cliques++;
	
    statusBar.setText("Clicado: ["+e.getX()+","+e.getY()+"]; vezes:"+cliques);	
    repaint();
  }

  public void mousePressed(MouseEvent e)
  {
    statusBar.setText("Pressinonado :["+e.getX()+","+e.getY()+"]");
	
  }

  public void mouseReleased(MouseEvent e)
  {
    statusBar.setText("Solto :["+e.getX()+","+e.getY()+"]");
  }
 
  public void mouseEntered(MouseEvent e)
  {
	dentro=true;
	statusBar.setText("Mouse na janela");
  } 

  public void mouseExited(MouseEvent e)
  {
	dentro=false;
	statusBar.setText("Mouse fora da janela");
  } 
 
  public void actionPerformed(ActionEvent e)
  {
	ncor++;
	
	switch(ncor)
        {
		case 1:
			cor=Color.blue;
			botaoCor.setText("AZUL");
			break;
		
		case 2:
			cor=Color.orange;
			botaoCor.setText("LARANJA");
			break;
		
		case 3:
			cor=Color.yellow;
			botaoCor.setText("AMARELO");
			break;
		
		case 4:
			cor=Color.pink;
			botaoCor.setText("ROSA");
			break;
		case 5:
			cor=Color.black;
			botaoCor.setText("PRETO");
			break;
		case 6:
			cor=Color.green;
			botaoCor.setText("VERDE");
			break;
		case 7:
			cor=Color.cyan;
			botaoCor.setText("CIANO");
			break;
		case 8:
			cor=Color.white;
			botaoCor.setText("BRANCO");
			break;
		
		case 9:
			cor=Color.red;
			botaoCor.setText("VERMELHO");
			ncor=0;
			break;
	}
	
  }

  public void desenhaOval(Graphics g)
  {
	if(cliques==2)
		g.fillOval(pontoX1,pontoY1,5,5);
	else
	  if(cliques==3)
		 g.fillOval(pontoX2,pontoY2,5,5);
          else
	     if(cliques==4)
   		g.fillOval(pontoX3,pontoY3,5,5);
  }

  public void paint(Graphics g)
  {
	g.setColor(cor);
	desenhaOval(g);
	if(cliques>=4)
        {
	  statusBar.setText("Desenhando...");
	  desenha(g);
	  statusBar.setText("Pronto!");
	  cliques=1;
        }
  }
}