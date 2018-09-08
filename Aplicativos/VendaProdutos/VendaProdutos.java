import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.text.DecimalFormat;

public class VendaProdutos
{
	public static void main(String args[])
	{
    double prod1=2.98,
           prod2=4.50,
           prod3=9.98,
           prod4=4.49,
           prod5=6.87;
    double tot1,tot2,tot3,tot4,tot5;
           tot1=0.00;
           tot2=0.00;
           tot3=0.00;
           tot4=0.00;
           tot5=0.00;
    double totalTudo=0.00;
    String cadeia;
    int qtd,numPedido;

    do
    {
      cadeia=JOptionPane.showInputDialog("Número do produto(zero ou negativo para sair):");
      numPedido=Integer.parseInt(cadeia);
      if(numPedido>0 && numPedido<6)
      {
        cadeia=JOptionPane.showInputDialog("Quantidade Vendida: ");
        qtd=Integer.parseInt(cadeia);

        switch(numPedido)
        {
          case 1:
            tot1+=qtd*prod1;
            break;
          case 2:
            tot2+=qtd*prod2;
            break;
          case 3:
            tot3+=qtd*prod3;
            break;
          case 4:
            tot4+=qtd*prod4;
            break;
          case 5:
            tot5+=qtd*prod5;
            break;
        }//Fim switch
      }//Fim if(lá em cima)
        if(numPedido>=6)
          JOptionPane.showMessageDialog(null,"Pedido Inexistente!","Aviso",JOptionPane.WARNING_MESSAGE);


	    }while(numPedido>0);//Fim do

    JTextArea areaTexto=new JTextArea(10,40);
    JScrollPane barraRolagem=new JScrollPane(areaTexto);
    DecimalFormat precisaoDupla=new DecimalFormat("0.00");

    cadeia=("Número do Produto\tTotal Vendido($)\n");

    for(int i=1;i<=5;i++)
    {
      switch(i)
      {
        case 1:
          cadeia+=i+"\t\t"+precisaoDupla.format(tot1)+"\n";
          break;
        case 2:
          cadeia+=i+"\t\t"+precisaoDupla.format(tot2)+"\n";
          break;
        case 3:
          cadeia+=i+"\t\t"+precisaoDupla.format(tot3)+"\n";
          break;
        case 4:
          cadeia+=i+"\t\t"+precisaoDupla.format(tot4)+"\n";
          break;
        case 5:
          cadeia+=i+"\t\t"+precisaoDupla.format(tot5)+"\n";
          break;
      }//Fim switch
    }//Fim for
    cadeia+="\n";
    totalTudo=tot1+tot2+tot3+tot4+tot5;
    cadeia+="TOTAL\t\t"+precisaoDupla.format(totalTudo);

    areaTexto.setText(cadeia);
    JOptionPane.showMessageDialog(null,barraRolagem,"Resultados",JOptionPane.INFORMATION_MESSAGE);
    System.exit(0);
  }//Fim do método main
}//Fim da classe

