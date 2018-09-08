import javax.swing.JOptionPane;

public class QuadradoCubo
{
	public static void main(String args[])
	{
    String cadeia;
    cadeia=" ";
		for(int i=0;i<=10;i++)
      cadeia=cadeia+i+"    " + (i*i)+"    " + (i*i*i) + "\n";
    JOptionPane.showMessageDialog(null,cadeia);
    System.exit(0);
  }

}
