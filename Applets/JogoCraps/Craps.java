import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Craps extends JApplet implements ActionListener
{
  final int vitoria=0,derrota=1,continuar=2;

  boolean primeiraJogada=true;
  int somaDados=0;
  int meuPonto=0;
  int gameStatus=continuar;
  int meuDeposito=1000,minhaAposta;

  JLabel labelDado1,labelDado2,labelSomaDados,labelMeuPonto,labelMeuDeposito,labelMinhaAposta;
  JTextField campoDado1,campoDado2,campoSomaDados,campoMeuPonto,campoMeuDeposito,campoMinhaAposta;
  JButton roll;

	public void init()
	{
    Container c=getContentPane();
    c.setLayout(new FlowLayout());

    labelMinhaAposta=new JLabel("Aposta");
    c.add(labelMinhaAposta);
    campoMinhaAposta=new JTextField(7);
    c.add(campoMinhaAposta);

    labelDado1=new JLabel("Dado 1");
    c.add(labelDado1);
    campoDado1=new JTextField(4);
    campoDado1.setEditable(false);
    c.add(campoDado1);

    labelDado2=new JLabel("Dado 2");
    c.add(labelDado2);
    campoDado2=new JTextField(4);
    campoDado2.setEditable(false);
    c.add(campoDado2);

    labelSomaDados=new JLabel("Soma dos Dados");
    c.add(labelSomaDados);
    campoSomaDados=new JTextField(4);
    campoSomaDados.setEditable(false);
    c.add(campoSomaDados);

    labelMeuPonto=new JLabel("Minha Pontuação");
    c.add(labelMeuPonto);
    campoMeuPonto=new JTextField(4);
    campoMeuPonto.setEditable(false);
    c.add(campoMeuPonto);

    labelMeuDeposito=new JLabel("Meu Depósito");
    c.add(labelMeuDeposito);
    campoMeuDeposito=new JTextField(4);
    campoMeuDeposito.setEditable(false);
    c.add(campoMeuDeposito);

    //Botão
    roll=new JButton("Jogar Dados");
    roll.addActionListener(this);
    c.add(roll);

    campoMeuDeposito.setText(Integer.toString(meuDeposito));


	}//Método init


  public void actionPerformed(ActionEvent e)
  {
    if(primeiraJogada!=true)
     campoMinhaAposta.setEditable(false);
    else
     campoMinhaAposta.setEditable(true);

    minhaAposta=Integer.parseInt(campoMinhaAposta.getText());
    if(minhaAposta<0 || minhaAposta>meuDeposito)
      showStatus("Aposta inválida!");
    else
      play();

    if(primeiraJogada!=true)
     campoMinhaAposta.setEditable(false);
    else
     campoMinhaAposta.setEditable(true);

  }//Metodo play

  public void play()
  {
    if(primeiraJogada==true)
    {
      somaDados=jogarDados();
      switch(somaDados)
      {
        case 7:case 11:
          gameStatus=vitoria;
          campoMeuPonto.setText("");
          break;
        case 2:case 3:case 12:
          gameStatus=derrota;
          campoMeuPonto.setText("");
          break;
        default:
          gameStatus=continuar;
          meuPonto=somaDados;
          campoMeuPonto.setText(Integer.toString(meuPonto));
          primeiraJogada=false;
          break;
      }//switch
    }//if
    else
    {
      somaDados=jogarDados();

      if(somaDados==meuPonto)
       gameStatus=vitoria;
      else
       if(somaDados==7)
        gameStatus=derrota;
    }//else



    if(gameStatus==continuar)
      showStatus("Jogue novamente.");
    else
    {
      if(gameStatus==vitoria)
       {
         meuDeposito+=minhaAposta;
         campoMeuDeposito.setText(Integer.toString(meuDeposito));
         showStatus("Ganhou!Aposte novamente.");
       }

      else
      {
       meuDeposito-=minhaAposta;
       campoMeuDeposito.setText(Integer.toString(meuDeposito));
       showStatus("Perdeu.Aposte Novamente.");
      }
      primeiraJogada=true;
    }//else



  }//metodo play

  public int jogarDados()
  {
    int dado1,dado2,soma;
    dado1=1+(int)(Math.random()*6);
    dado2=1+(int)(Math.random()*6);
    soma=dado1+dado2;
    campoDado1.setText(Integer.toString(dado1));
    campoDado2.setText(Integer.toString(dado2));
    campoSomaDados.setText(Integer.toString(soma));

    return soma;
  }//fim jogarDados


}//fim Classe;
