import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AcerteONumero extends JApplet implements ActionListener
{
  JLabel numLabel,tentLabel;
  JTextField numCampo,tentCampo;
  int num,tentativas;

	public void init()
	{

    Container c=getContentPane();
    c.setLayout(new FlowLayout());

    tentativas=0;

    numLabel=new JLabel("Digite Número(1 a 100): ");
    c.add(numLabel);

    numCampo=new JTextField(10);
    numCampo.addActionListener(this);
    c.add(numCampo);

    tentLabel=new JLabel("Tentativas:");
    c.add(tentLabel);

    tentCampo = new JTextField(10);
    tentCampo.setEditable(false);
    c.add(tentCampo);

	}//metodo init

  public void start()
  {
    sorteiaNumero();
    tentCampo.setText(Integer.toString(tentativas));
  }

  public void sorteiaNumero()
  {
    num=1+(int)(Math.random()*100);
  }// metodo sorteianumero

  public void actionPerformed(ActionEvent e)
  {
    int numLido;
    numLido=Integer.parseInt(numCampo.getText());
    comparaNumeros(numLido);
    tentCampo.setText(Integer.toString(tentativas));
  }//Açao

  public void comparaNumeros(int n)
  {
      if(n==num)
      {
       sorteiaNumero();
       showStatus("Você acertou(Tentativas:"+tentativas+") !Jogue novamente");
       numCampo.setText("");
       tentativas=0;
      }
      else
       if(n<num)
       {
        showStatus("Seu número é menor...");
        numCampo.setText("");
        tentativas++;
       }
       else
        if(n>num)
        {
         showStatus("Seu número é maior...");
         numCampo.setText("");
         tentativas++;
        }
        else
        {
          showStatus("Valor incorreto...");
          numCampo.setText("");
        }

  }//Avaliacao


}//fim classe
