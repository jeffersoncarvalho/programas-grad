import javax.swing.JOptionPane;

public class Adicao{
	public static void main(String args[])
	{
	 String numero1,
	         numero2;
	 int sum,
             n1,
	     n2;

             numero1=JOptionPane.showInputDialog("Primeiro numero");
	     numero2=JOptionPane.showInputDialog("Segundo numero");
	     n1=Integer.parseInt(numero1);
	     n2=Integer.parseInt(numero2);
	     sum=n1+n2;
             JOptionPane.showMessageDialog(null,"A soma é" + sum,"Resultado",JOptionPane.PLAIN_MESSAGE);
             System.exit(0);

	}
		
}