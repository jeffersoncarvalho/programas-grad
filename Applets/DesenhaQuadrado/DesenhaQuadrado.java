import javax.swing.*;
import java.awt.Graphics;
public class DesenhaQuadrado extends JApplet
{
  int x;
  String preench;

	public void init()
	{
		int cont;

    String imput;
    x=0;
   while(x<=0)
   {
    imput=JOptionPane.showInputDialog("Lado do Quadrado?");
    x=Integer.parseInt(imput);
    if(x<=0)
    JOptionPane.showMessageDialog(null,"Valor Inválido","Aviso",JOptionPane.WARNING_MESSAGE);
   }

   imput=JOptionPane.showInputDialog("Preenchimento");
   preench=imput;

  }//Fim main
  public void paint(Graphics g)
  {
    fillSquare(x,preench,g);

  }//Fim paint

  public void fillSquare(int side,String in,Graphics o)
  {
    String cadeiaLado="";
    int y=10;
    for(int i=1;i<=side;i++)
     cadeiaLado+=in;
    for(int i=1;i<=side;i++)
     {o.drawString(cadeiaLado,10,y);y+=10;}
  }//Desenha

}
