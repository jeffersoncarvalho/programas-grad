import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ContaBotoes extends JApplet implements ActionListener
{
  JButton botao1,botao2;
  int cont1,cont2;
  public void init()
  {
    Container c;
    c=getContentPane();
    c.setLayout(new FlowLayout());

    botao1=new JButton("Bot�o 1");
    botao1.addActionListener(this);
    c.add(botao1);

    botao2=new JButton("Bot�o 2");
    botao2.addActionListener(this);
    c.add(botao2);

    cont1=0;
    cont2=0;
    showStatus("Bot�o 1: "+cont1+" Bot�o 2: "+cont2);
  }//init

  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==botao1)
     cont1++;
    else
     cont2++;

    showStatus("Bot�o 1: "+cont1+" Bot�o 2: "+cont2);
  }//Action

}//fim classe
