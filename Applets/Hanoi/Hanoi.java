import javax.swing.*;
import java.awt.*;

public class Hanoi extends JApplet

{
  String output="";
  JTextArea areaTexto=new JTextArea(20,15);
  JScrollPane barraRolagem=new JScrollPane(areaTexto);

	public void init()
	{
    Container c=getContentPane();
    c.setLayout(new FlowLayout());

		int num;
    String cadeiaNum;

    cadeiaNum=JOptionPane.showInputDialog("Numero de Discos");
    num=Integer.parseInt(cadeiaNum);

    hanoi(num,1,3,2);

    areaTexto.setText(output);

    c.add(barraRolagem);

	}//principal
  public void hanoi(int n,int orig,int dest,int temp)
  {
    if(n>0)
    {
      hanoi(n-1,orig,temp,dest);
      output+=orig+"-->"+dest+"\n";
      hanoi(n-1,temp,dest,orig);
    }
  }//hanoi
}
