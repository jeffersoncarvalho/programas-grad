import javax.swing.JOptionPane;
public class Exponencial
{
	public static void main(String args[])
	{
    int numero,cont=0,x,exp;
    double exponencial=0;
    long fatorial=1,potencia=1;
    String cadeia;
    cadeia=JOptionPane.showInputDialog("Valor de x: ");
    numero=Integer.parseInt(cadeia);


    while(cont!=30)
    {
      //Fatorial
      if(cont!=0)
      {
        x=cont;
        while(x!=1)
         {fatorial*=x;
          x--;
         }
       }

       //Potencia
       exp=cont;
       while(exp!=0)
       {
        potencia*=numero;
        exp--;
       }
       //Exponecial

       exponencial+=(double)potencia/fatorial;
       potencia=1;
       fatorial=1;
       cont++;
  }//Fim while principal

    JOptionPane.showMessageDialog(null,"Valor de exponencial de "+numero+" é " +exponencial);
    System.exit(0);


}//Fim Main
 /* public long int fatorial(int x)
  {
    long int fat=1;
    if(x==0)
      return fat;
    else
      while(x!=1)
      {fat*=x;
       x--;
      }
      return fat;
  }//Fim metodo fatorial
  public long int potencia(int base,int exp)
  {
    long result=1;
    while(exp!=0)
    {
      result*=base;
      exp--;
    }
    return result;
  }//Fim metodo potencia*/

}//Fim classe

