import javax.swing.*;
import java.awt.*;
public class Primos extends JApplet
{
  JTextArea areaTexto=new JTextArea(25,15);
  JScrollPane barraRolagem=new JScrollPane(areaTexto);


  public void init()
  {
    Container c=getContentPane();
    c.setLayout(new FlowLayout());

    String output="";

    for(int j=1;j<=10000;j++)
     if (Primo(j)==true)
      output+=j+"\n";
    areaTexto.setText(output);
    c.add(barraRolagem);



  }//init

	public boolean Primo(int n)
  {
    boolean primo=true;

    if(n==1)
     return false;

    for(int i=2;(i<=Math.floor(Math.sqrt(n)))&&primo!=false;i++)
     if(n%i==0) primo=false;

    return primo;
  }//primo
}
