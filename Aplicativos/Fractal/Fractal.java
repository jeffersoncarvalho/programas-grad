import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Fractal extends JFrame
{
  final int pontoX1=400,pontoY1=0;
  final int pontoX2=0,pontoY2=600;
  final int pontoX3=800,pontoY3=600;

  int pSelecionadoX,pSelecionadoY;
  int criadoX,criadoY;
  int calculadoX,calculadoY;

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
	for(cont=0;cont<100000;cont++)
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

        window.setSize(800,600);
	      window.show();
  }

  public void paint(Graphics g)
  {
	  desenha(g);
  }
}