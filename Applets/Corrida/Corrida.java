import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Corrida extends JApplet implements ActionListener
{
  final int LEBRE=1,TARTARUGA=2;
  final int NINGUEM=0,EMPATE=3;

  int vencedor=NINGUEM;
  int pista[];
  int passosTartaruga,posicaoTartaruga=1,
      passosLebre,posicaoLebre=1;

  boolean mordida=false;


  String cadeiaLebre,cadeiaTartaruga,cadeia;

  JButton botaoComecar;
  JTextArea areaTexto;
  JScrollPane barraRolagem;

  public void init()
  {
    Container c=getContentPane();
    c.setLayout(new FlowLayout());

    pista=new int[71];
    cadeia="";
    cadeiaLebre="";
    cadeiaTartaruga="";

    botaoComecar=new JButton("Largada!");
    botaoComecar.addActionListener(this);
    c.add(botaoComecar);

    areaTexto=new JTextArea(25,45);
    areaTexto.setEditable(false);
    barraRolagem=new JScrollPane(areaTexto);
    c.add(barraRolagem);
  }//init

  public void actionPerformed(ActionEvent e)
  {


    areaTexto.append("BANG!\nFOI DADA A LARGADA!!!\n\n");

    do
    {
      calculaPassos();
      movimentaLebreETartaruga();
      desenha();
      Thread.sleep(3000);
      //retardo();
    }while(vencedor==NINGUEM);

    limpar();

    


  }//a��o!

  public void calculaPassos()
  {
    int randomTartaruga,
        randomLebre;

        randomTartaruga=1+(int)(Math.random()*10);
        randomLebre=1+(int)(Math.random()*10);

        switch(randomTartaruga)
        {
          case 1:case 2:case 3:case 4:case 5:
            cadeiaTartaruga="Caminhada R�pida!";
            passosTartaruga=+3;
            break;
          case 6:case 7:
            cadeiaTartaruga="Escorreg�o!";
            passosTartaruga=-6;
            break;
          default:
            cadeiaTartaruga="Caminhada Lenta...";
            passosTartaruga=+1;
            break;
        }//switch

        switch(randomLebre)
        {
          case 1:case 2:
            cadeiaLebre="Dormindo...";
            passosLebre=+0;
            break;
          case 3:case 4:
            cadeiaLebre="Salto Grande!";
            passosLebre=+9;
            break;
          case 5:
            cadeiaLebre="Escorreg�o Grande!";
            passosLebre=-12;
            break;
          case 6:case 7:case 8:
            cadeiaLebre="Salto Pequeno...";
            passosLebre=+2;
            break;
          default:
            cadeiaLebre="Escorreg�o Pequeno";
            passosLebre=-2;
            break;

        }//switch

  }//calculaPassos

  public void movimentaLebreETartaruga()
  {
    posicaoTartaruga+=passosTartaruga;
    posicaoLebre+=passosLebre;

    if(posicaoTartaruga<=0) posicaoTartaruga=1;
    if(posicaoLebre<=0) posicaoLebre=1;

    if(posicaoTartaruga>70) posicaoTartaruga=70;
    if(posicaoLebre>70) posicaoLebre=70;


    if(posicaoLebre!=posicaoTartaruga)
    {
      if(posicaoLebre==70) vencedor=LEBRE;
      if(posicaoTartaruga==70) vencedor=TARTARUGA;
    } // if diferente

    if(posicaoLebre==posicaoTartaruga)
    {
      if(posicaoLebre==70)
        vencedor=EMPATE;
      else
       if(posicaoLebre!=1)
          mordida=true;
    }//if igual
  }//movimenta

  public void desenha()
  {
    pista[posicaoLebre]=LEBRE;
    pista[posicaoTartaruga]=TARTARUGA;

   for(int i=1;i<=70;i++)
     if(pista[i]==LEBRE)
      cadeia+="L";
     else
      if(pista[i]==TARTARUGA)
       cadeia+="T";
      else
       if(i==70)
        cadeia+="_";
       else
        cadeia+=" ";

     cadeia+="\n";
     cadeia+="Tartaruga: "+cadeiaTartaruga;
     cadeia+="\n";
     cadeia+="Lebre: "+cadeiaLebre;
     cadeia+="\n";

     if(mordida==true)
     {
      cadeia+="Ouch!Desgra�ada!";
      mordida=false;
     }
     cadeia+="\n";

     if(vencedor==LEBRE)
      cadeia+="Lebre venceu!\n";
     else
      if(vencedor==TARTARUGA)
       cadeia+="\nTartaruga venceu!\n";
      else
       if(vencedor==EMPATE)
        cadeia+="\nTemos Empate!\n";


     areaTexto.append(cadeia);
     showStatus("Lebre:"+passosLebre+"; Tartaruga:"+passosTartaruga);
     pista[posicaoLebre]=NINGUEM;
     pista[posicaoTartaruga]=NINGUEM;

  }//desenha

  public void retardo()
  {
    for(long i=0;i<100000;i++);
     //retardo...
  }//retardo

  public void limpar()
  {
    posicaoTartaruga=1;
    posicaoLebre=1;
    vencedor=NINGUEM;
    mordida=false;
    cadeia="";
    cadeiaLebre="";
    cadeiaTartaruga="";


    for(int i=0;i<71;i++)
     pista[i]=NINGUEM;

  }

}
