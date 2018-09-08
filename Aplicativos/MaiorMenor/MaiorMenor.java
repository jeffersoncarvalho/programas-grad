import javax.swing.JOptionPane;
public class MaiorMenor
{
  public static void main(String args[])
  {
    String val1,val2,val3,val4,val5;
    int n1,n2,n3,n4,n5,maior,menor;

    val1=JOptionPane.showInputDialog("Valor 1");
    n1=Integer.parseInt(val1);
    menor=n1;
    maior=n1;

    val2=JOptionPane.showInputDialog("Valor 2");
    n2=Integer.parseInt(val2);
    if(n2>maior)
     maior=n2;
    if(n2<menor)
     menor=n2;

    val3=JOptionPane.showInputDialog("Valor 3");
    n3=Integer.parseInt(val3);
    if(n3>maior)
     maior=n3;
    if(n3<menor)
     menor=n3;

    val4=JOptionPane.showInputDialog("Valor 4");
    n4=Integer.parseInt(val4);
    if(n4>maior)
     maior=n4;
    if(n4<menor)
     menor=n4;

    val5=JOptionPane.showInputDialog("Valor 5");
    n5=Integer.parseInt(val5);
    if(n5>maior)
     maior=n5;
    if(n5<menor)
     menor=n5;

    JOptionPane.showMessageDialog(null,"O maior elemento é: " + maior +"\n"+
                                       "O menor elemento é: " + menor,"Resultados",JOptionPane.PLAIN_MESSAGE);
    System.exit(0);
  }//Fim Método
}//Fim Classe

     


