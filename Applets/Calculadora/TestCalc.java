import javax.swing.*;
import java.awt.event.*;
import java.awt.*;



public class TestCalc extends JApplet implements ActionListener
{
	JLabel labelTotal;
	JButton botaoSoma,botaoSub,botaoDiv,botaoMult,botaoIgual,botaoCE,vetBotoes[];
	JTextField campoNum;
	MaquinaCientifica ms;
  boolean campoVazio;

  private final int SOMA=0,SUB=1,DIV=2,MULT=3;
  private int operacao;


  public void init()
  {
	Container c=getContentPane();
	c.setLayout(new FlowLayout());

  vetBotoes=new JButton[10];
  for(int i=0;i<10;i++)
    vetBotoes[i]=new JButton(Integer.toString(i));

	labelTotal=new JLabel("Total:");
	c.add(labelTotal);
	campoNum=new JTextField(15);
  campoNum.setEditable(false);
  campoNum.setText("");
	c.add(campoNum);

  for(int i=0;i<10;i++)
  {
    vetBotoes[i].addActionListener(this);
    c.add(vetBotoes[i]);
  }

	botaoSoma=new JButton("+");
	botaoSoma.addActionListener(this);
	c.add(botaoSoma);
	botaoSub=new JButton("-");
	botaoSub.addActionListener(this);
	c.add(botaoSub);
  botaoMult=new JButton("*");
	botaoMult.addActionListener(this);
	c.add(botaoMult);
	botaoDiv=new JButton("/");
	botaoDiv.addActionListener(this);
	c.add(botaoDiv);
	botaoIgual=new JButton("=");
	botaoIgual.addActionListener(this);
	c.add(botaoIgual);
  botaoCE=new JButton("CE");
	botaoCE.addActionListener(this);
	c.add(botaoCE);
  campoVazio=true;

	  ms=new MaquinaCientifica();
  }

  public void actionPerformed(ActionEvent e)
  {
      for(int i=0;i<10;i++)
       if(e.getSource()==vetBotoes[i])
       {
          campoNum.setText(campoNum.getText()+Integer.toString(i));
          campoVazio=false;
       }

      if(e.getSource()== botaoSoma)
      {
        operacao=SOMA;
        //ms.somar(Double.parseDouble(campoNum.getText()));
        ms.setTotal(Double.parseDouble(campoNum.getText()));
        campoNum.setText("");
        campoVazio=true;
      }
      else
      if(e.getSource()==botaoSub)
      {
        if(campoVazio==true)
         campoNum.setText("-");
        else
        {
          operacao=SUB;
          //ms.subtrair(Double.parseDouble(campoNum.getText()));
          ms.setTotal(Double.parseDouble(campoNum.getText()));
          campoNum.setText("");
          campoVazio=true;
        }
      }

	    else
      if(e.getSource()==botaoDiv)
      {
        operacao=DIV;
        //ms.div(Double.parseDouble(campoNum.getText()));
        ms.setTotal(Double.parseDouble(campoNum.getText()));
        campoNum.setText("");
        campoVazio=true;
      }

	    else
      if(e.getSource()==botaoMult)
      {
        operacao=MULT;
        //ms.mult(Double.parseDouble(campoNum.getText()));
        ms.setTotal(Double.parseDouble(campoNum.getText()));
        campoNum.setText("");
        campoVazio=true;
      }

      //---->
	    else
      if(e.getSource()== botaoIgual)
      {
        escolheOperacao();
        campoVazio=false;
      }
      else
      if(e.getSource()==botaoCE)
      {
         ms.total=0;
		     campoNum.setText("");
         campoVazio=true;
      }

  }

  public void escolheOperacao()
  {
      switch(operacao)
      {
        case SOMA:
         ms.somar(Double.parseDouble(campoNum.getText()));
        break;
        case SUB:
         ms.subtrair(Double.parseDouble(campoNum.getText()));
        break;
        case DIV:
          ms.div(Double.parseDouble(campoNum.getText()));
        break;
        case MULT:
         ms.mult(Double.parseDouble(campoNum.getText()));
        break;

      }
      campoNum.setText(Double.toString(ms.retTotal()));
  }


}