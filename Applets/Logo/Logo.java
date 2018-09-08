import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Logo extends JApplet implements ActionListener
{
  final int NORTE=0,SUL=1,LESTE=2,OESTE=3,//condicoes direcao
            CIMA=1,BAIXO=0;//condicoes caneta

   
  JLabel labelComando;
  JTextField campoComando;
  JButton botaoProcessar;
  JTextArea areaTexto;
  JScrollPane barraRolagem;

  int direcao;
  int caneta,tartaruga[][];
  int lin,col;
  int valorAndar;


  String output;

  public void init()
  {
    Container c=getContentPane();
    c.setLayout(new FlowLayout());

    output="";
    direcao=NORTE;
    tartaruga=new int[27][52];
    caneta=CIMA;
    lin=23;col=3;
    tartaruga[lin][col]=0;
    valorAndar=0;

    labelComando=new JLabel("Comando:");
    c.add(labelComando);
    campoComando=new JTextField(5);
    c.add(campoComando);

    botaoProcessar=new JButton("Processar");
    botaoProcessar.addActionListener(this);
    c.add(botaoProcessar);

    areaTexto=new JTextArea(28,35);
    areaTexto.setEditable(false);
    barraRolagem= new JScrollPane(areaTexto);
    c.add(barraRolagem);



  }//init

  public void actionPerformed(ActionEvent e)
  {
    String imput;
    imput=campoComando.getText();

    double valorDouble;
    int valorInt;

    valorDouble=Double.parseDouble(imput);
    valorInt=(int)(valorDouble);

    switch(valorInt)
    {
      case 1:
        caneta=CIMA;
      break;
      case 2:
        caneta=BAIXO;
      break;
      case 3:
        virarADireita();
      break;
      case 4:
        virarAEsquerda();
      break;
      case 5:
        separarNumero(valorDouble);
        mover();
      break;
      case 6:
        showStatus("Desenhando");
        desenhar();
      break;
      default:
       aplicarStatus();
      break;
    }//switch

     aplicarStatus();


  }//acao!

  public void mover()
  {
    switch(direcao)
    {
      case NORTE:
        for(int i=0;(i<valorAndar && lin!=0);i++)
         if(caneta==BAIXO)
          tartaruga[lin--][col]=1;
         else
          lin--;
      break;
       case SUL:
        for(int i=0;(i<valorAndar && lin!=26);i++)
         if(caneta==BAIXO)
          tartaruga[lin++][col]=1;
         else
          lin++;
      break;
       case OESTE:
        for(int i=0;(i<valorAndar && col!=0);i++)
         if(caneta==BAIXO)
          tartaruga[lin][col--]=1;
         else
          col--;
      break;
       case LESTE:
        for(int i=0;(i<valorAndar && col!=51);i++)
         if(caneta==BAIXO)
          tartaruga[lin][col++]=1;
         else
          col++;
      break;
      default:
        showStatus("Valor Inválido!");
      break;
    }//switch
  }//mover

  public void virarADireita()
  {
    switch(direcao)
    {
      case NORTE:
        direcao=LESTE;
      break;
       case LESTE:
        direcao=SUL;
      break;
       case SUL:
        direcao=OESTE;
      break;
       case OESTE:
        direcao=NORTE;
      break;
    }//switch

  }//mudar direcao(direita)

  public void virarAEsquerda()
  {
    switch(direcao)
    {
      case NORTE:
        direcao=OESTE;
      break;
       case OESTE:
        direcao=SUL;
      break;
       case SUL:
        direcao=LESTE;
      break;
       case LESTE:
        direcao=NORTE;
      break;
    }//switch

  }//mudar direcao(esquerda)

  public void desenhar()
  {
    output="";



    for(int i=0;i<tartaruga.length;i++)
    {
     for(int j=0;j<tartaruga[0].length;j++)
       if(i==lin && j==col)
        output+="8";
       else
        if(tartaruga[i][j]==1)
         output+="1";
        else
         output+="0";

      output+="\n";
      
     }//for
    areaTexto.setText(output);
  }//desenhar

  public void separarNumero(double n)
  {
    double valorDecimal=n-5+0.00999;

    valorAndar=(int)(valorDecimal*100);
  }//separaNumero
  public void aplicarStatus()
  {
    String cadeiaCaneta="",cadeiaDirecao="";

    switch(direcao)
    {
      case NORTE:
        cadeiaDirecao="NORTE";
      break;
      case SUL:
        cadeiaDirecao="SUL";
      break;
      case OESTE:
        cadeiaDirecao="OESTE";
      break;
      case LESTE:
        cadeiaDirecao="LESTE";
      break;
    }//switch

    if(caneta==BAIXO)
      cadeiaCaneta="BAIXO";
    else
      cadeiaCaneta="CIMA";

    showStatus("Caneta= "+cadeiaCaneta+" : Direção= "+cadeiaDirecao+" : Linha="+lin+" : Coluna="+col);
  }//Status;



}//fim classe...
