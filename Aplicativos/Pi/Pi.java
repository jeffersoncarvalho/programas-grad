import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.TextArea;
//import java.text.DecimalFormat;

public class Pi
{
	public static void main(String args[])
	{

    double valorPi=0.00;
    int topo=1,sinal=1;
    String output="Número de Termos\tValor de PI\n";
    for(int i=1;i<=2300;i++)
    {
      for(int j=1;j<=topo;j+=2)
      {
       valorPi+=(sinal*4)/(double)j;
       sinal*=-1;
      }


      output+=i+"\t\t"+valorPi+"\n";
      topo+=2;
      valorPi=0;
      sinal=1;
    }
    TextArea areaTexto=new TextArea(25,25);
    JScrollPane barraRolagem=new JScrollPane(areaTexto);

    areaTexto.setText(output);
    JOptionPane.showMessageDialog(null,barraRolagem,"Cáculos do PI",JOptionPane.PLAIN_MESSAGE);

  System.exit(0);
 }//Fim metodo

}//Fim classe
