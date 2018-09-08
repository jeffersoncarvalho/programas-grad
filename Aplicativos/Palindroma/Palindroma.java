import javax.swing.JOptionPane;
public class Palindroma
{
	public static void main(String args[])
	{
		int numero,
        valor1,valor2,valor3,valor4,valor5;
    String cadeia;

    numero=0;
    while(numero<10000 || numero>99999)
    {
      cadeia=JOptionPane.showInputDialog("Inteiro de 5 Digitos: ");
      numero=Integer.parseInt(cadeia);
      if(numero<10000 || numero>99999)
        JOptionPane.showMessageDialog(null,"O numero deve ter 5 Dígitos!","Aviso",JOptionPane.WARNING_MESSAGE);
    }

    valor1=numero/10000;
    numero=numero-(valor1*10000);
    valor2=numero/1000;
    numero=numero-(valor2*1000);
    valor3=numero/100;
    numero=numero-(valor3*100);
    valor4=numero/10;
    numero=numero-(valor4*10);
    valor5=numero;

    int palind=1;
    if(valor1!=valor5)
      palind=0;
    else
      if(valor2!=valor4)
        palind=0;

    if (palind==1)
      JOptionPane.showMessageDialog(null,"Numero Palindromo!");
    else
      JOptionPane.showMessageDialog(null,"Numero Não Palindromo.");

  System.exit(0);




	}
}
