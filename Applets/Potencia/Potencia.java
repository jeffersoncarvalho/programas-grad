import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Potencia extends JApplet implements ActionListener
{
	long resultado;

  JLabel labelBase,labelExpoente,labelResposta;
  JTextField campoBase,campoExpoente,campoResposta;
  JButton roll;
  public void init()
  {
    Container c=getContentPane();
    c.setLayout(new FlowLayout());

    labelBase=new JLabel("Base");
    c.add(labelBase);
    campoBase=new JTextField(7);
    c.add(campoBase);

    labelExpoente=new JLabel("Expoente");
    c.add(labelExpoente);
    campoExpoente=new JTextField(7);
    c.add(campoExpoente);

    labelResposta=new JLabel("Resposta");
    c.add(labelResposta);
    campoResposta=new JTextField(7);
    campoResposta.setEditable(false);
    c.add(campoResposta);


    //Botão
    roll=new JButton("Processar");
    roll.addActionListener(this);
    c.add(roll);

  }//init
  public long potencia(int bas,int exp)
  {
    if(exp==1)
      return bas;
    else
      return bas*potencia(bas,exp-1);

  }//potencia

  public void actionPerformed(ActionEvent e)
  {
    resultado=potencia(Integer.parseInt(campoBase.getText()),Integer.parseInt(campoExpoente.getText()));
    campoResposta.setText(Long.toString(resultado));
  }//ação!


}//classe
