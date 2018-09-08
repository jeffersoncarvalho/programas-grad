import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.TextArea;

public class TriplaPitagoras
{
	public static void main(String args[])
	{

		int lado1,lado2,hipotenusa;
    String output="";
    TextArea areaTexto=new TextArea(25,25);
    JScrollPane barraRolagem= new JScrollPane(areaTexto);

    for(lado1=1;lado1<=500;lado1++)
     for(lado2=1;lado2<=500;lado2++)
      for(hipotenusa=1;hipotenusa<=500;hipotenusa++)
       if(Math.pow(hipotenusa,2)==Math.pow(lado1,2) + Math.pow(lado2,2))
        output+= hipotenusa+"(e2) = "+lado1+"(e2) + " +lado2+"(e2)\n";

    areaTexto.setText(output);
    JOptionPane.showMessageDialog(null,barraRolagem,"Resultados",JOptionPane.INFORMATION_MESSAGE);
    System.exit(0);
	}//Fim metodo main

}//Fim Classe
