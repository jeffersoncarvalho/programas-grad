import javax.swing.JOptionPane;

public class Quantidade
{
	public static void main(String args[])
	{
    int n,negativos=0,positivos=0,zeros=0;
    String cadeia;
		for(int i=0;i<5;i++)
    {
      cadeia=JOptionPane.showInputDialog("Valor " + (i+1));
      n=Integer.parseInt(cadeia);
      if(n<0)
        negativos++;
      else
       if(n>0)
        positivos++;
       else
        zeros++;
    }

    JOptionPane.showMessageDialog(null,"Negativos = " + negativos +
                                  "\nPositivos = " + positivos +
                                  "\nZeros = " + zeros);
   System.exit(0);
  }//Fim Método
}//fim Classe
