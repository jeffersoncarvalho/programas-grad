import java.awt.Graphics;
import javax.swing.*;

public class Operacoes extends JApplet
{
  float soma,diferenca,quociente,produto;
	public void init()
	{
    float n1,n2;
    String cad1,cad2;
    cad1=JOptionPane.showInputDialog("Valor 1");
    cad2=JOptionPane.showInputDialog("Valor 2");
    n1=Float.parseFloat(cad1);
    n2=Float.parseFloat(cad2);
    soma=n1+n2;
    produto=n1*n2;
    quociente=n1/n2;
    diferenca=n1-n2;
	}
  public void paint(Graphics g)
  {
    g.drawRect(15,10,270,100);
    g.drawOval(15,10,270,100);
    g.drawString("Soma = "+soma,25,25);
    g.drawString("Produto = "+produto,25,40);
    g.drawString("Quociente = "+quociente,25,55);
    g.drawString("Diferença = "+diferenca,25,70);
  }

}
