import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Ensinando extends JApplet implements ActionListener
{
  int dificuldade,resposta,calculoGerado,operacao;
  boolean acertou,continuar=true;


  JLabel labelDificuldade,labelOperacao,labelPergunta;
  JTextField campoDificuldade,campoOperacao,campoResposta;
  JButton botaoProcessar;

  public void init()
  {
    Container c= getContentPane();
    c.setLayout(new FlowLayout());

    labelDificuldade=new JLabel("Dificuldade(1-3)");
    c.add(labelDificuldade);

    campoDificuldade=new JTextField(5);
    c.add(campoDificuldade);

    labelOperacao=new JLabel("Operacao(+,-,x)");
    c.add(labelOperacao);

    campoOperacao=new JTextField(5);
    c.add(campoOperacao);

    labelPergunta=new JLabel("Esperando entradas anteriores...");
    c.add(labelPergunta);

    campoResposta=new JTextField(5);
    c.add(campoResposta);

    botaoProcessar=new JButton("Processar");
    botaoProcessar.addActionListener(this);
    c.add(botaoProcessar);


  }//init

  public void getDificuldade()
  {
    dificuldade=Integer.parseInt(campoDificuldade.getText());
  }//getDificuldade

  public void getOperacao()
  {
    operacao=Integer.parseInt(campoOperacao.getText());
  }//getOperacao

  public void gerarCalculo()
  {
    int x,y;
    String cadOp="";
    getDificuldade();
    getOperacao();


    x=1+(int)(Math.random()*Math.pow(10,dificuldade));
    y=1+(int)(Math.random()*Math.pow(10,dificuldade));


    switch(operacao)
    {
      case 1:
        calculoGerado=x+y;
        cadOp=" mais ";
        break;
      case 2:
        calculoGerado=x-y;
        cadOp=" menos ";
        break;
      case 3:
        calculoGerado=x*y;
        cadOp=" vezes ";
        break;
    }//switch


    labelPergunta.setText("Quanto vale "+Integer.toString(x)+cadOp+Integer.toString(y)+"?");
  }//GerarCalculo

  public void avaliarResposta()
  {
    resposta=Integer.parseInt(campoResposta.getText());

    if(resposta==calculoGerado)
     acertou=true;
    else
     acertou=false;
  }//avaliarResposta

  public void play()
  {
    if(continuar==true)
    {
      gerarCalculo();
      campoDificuldade.setEditable(false);
      campoOperacao.setEditable(false);
      campoResposta.setEditable(true);
      continuar=false;
      showStatus("Entre com a Resposta");

    }
    else
    {
      avaliarResposta();
      if(acertou==true)
      {
        campoDificuldade.setText("");
        campoOperacao.setText("");
        campoDificuldade.setEditable(true);
        campoOperacao.setEditable(true);
        campoResposta.setEditable(false);
        campoResposta.setText("");
        labelPergunta.setText("Esperando entradas anteriores...");
        showStatus(escolherStrings());
        continuar=true;
      }
      else
      {
       campoResposta.setText("");
       showStatus(escolherStrings()+" Tente novamente.");
      }
    }


  }//Play

  public String escolherStrings()
  {
    String vit1="Parabéns, você acertou!",
           vit2="Legal cara, tá arrasando!",
           vit3="Ótimo, você é muito esperto!",
           vit4="Certíssimo!Você é demais!",
           vit5="Demais cara!Cada vez melhor";

    String perd1="Estúpido! Tente novamente!",
           perd2="Sua jumentice é normal?",
           perd3="Retardado, errou!",
           perd4="Deixa de ser imbecil!",
           perd5="Mas como você é idiota";

    String cadeiaEscolhida="";
    int escolha;

    escolha=1+(int)(Math.random()*5);

    switch(escolha)
    {
      case 1:
        cadeiaEscolhida=(acertou==true)?vit1:perd1;
        break;
      case 2:
        cadeiaEscolhida=(acertou==true)?vit2:perd2;
        break;
      case 3:
        cadeiaEscolhida=(acertou==true)?vit3:perd3;
        break;
      case 4:
        cadeiaEscolhida=(acertou==true)?vit4:perd4;
        break;
      case 5:
        cadeiaEscolhida=(acertou==true)?vit5:perd5;
        break;
    }//switch

    return cadeiaEscolhida;
  }//ecolher Strings

  public void actionPerformed(ActionEvent e)
  {
   play();
  }//Acao!!

  public void start()
  {
    campoResposta.setEditable(false);

  }



}//Fim classe
