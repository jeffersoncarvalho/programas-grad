
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
#include<stdlib.h>
#include<vcl.h>
void carrega()
{
        int i,time;
        randomize();
        for(i=0;i<25;i++)
        {

                time=(random(6)+5)*10;

                gotoxy(35,13);
                        printf("Carregando -");
                Sleep(time);
                gotoxy(35,13);
                        printf("               ");

                gotoxy(35,13);
                        printf("Carregando /");
                Sleep(time);
                gotoxy(35,13);
                        printf("               ");

                gotoxy(35,13);
                        printf("Carregando |");
                Sleep(time);
                gotoxy(35,13);
                        printf("               ");

                gotoxy(35,13);
                        printf("Carregando \\");
                Sleep(time);
                gotoxy(35,13);
                        printf("               ");
        }
}
void tela()
{
        char cadeia[16]="****MEMORIA****" ;
        int col=32,i;
        for(i=0;i<16;i++)
        {
                gotoxy(col,12);
                printf("%c",cadeia[i]);
                Sleep(100);
                col+=1;
        }
        Sleep(2000);
        gotoxy(32,12);
        printf("                           ");

        gotoxy(20,12);
        printf("POSICIONE OS DEDOS SOBRE AS TECLAS F G H J");
        Sleep(4000);
        gotoxy(20,12);
        printf("                                               ");

        gotoxy(20,12);
        printf("VERIFIQUE SE O CAPSLOCK ESTA DESLIGADO");
        Sleep(2500);
        gotoxy(20,12);
        printf("                                               ");
}

void desenha()
{
        gotoxy(28,11);
        printf("--------------------");
        gotoxy(28,13);
        printf("--------------------");
        gotoxy(28,12);
        printf("|");
        gotoxy(32,12);
        printf("|");
        gotoxy(37,12);
        printf("|");
        gotoxy(42,12);
        printf("|");
        gotoxy(47,12);
        printf("|");

        gotoxy(30,12);
        printf("F");
        gotoxy(35,12);
        printf("G");
        gotoxy(40,12);
        printf("H");
        gotoxy(45,12);
        printf("J");
}
void selecao(char &inc,int &i,char ve[])
{
                        if((inc==0)||(inc=='f'))
                        {
                                gotoxy(30,12);
                                {
                                  printf("f");
                                  ve[i]='f';
                                  Sleep(1000);
                                  printf("  ");
                                }
                                desenha();
                        }

                        if((inc==1)|| (inc=='g'))
                        {
                                gotoxy(35,12);
                                {
                                  printf("g");
                                  ve[i]='g';
                                  Sleep(1000);
                                  printf("  ");
                                }
                                desenha();
                        }

                        if((inc==2)||(inc=='h'))
                        {
                                gotoxy(40,12);
                                {
                                  printf("h");
                                  ve[i]='h';
                                  Sleep(1000);
                                  printf("  ");
                                }
                                desenha();
                        }

                        if((inc==3)||(inc=='j'))
                        {
                                gotoxy(45,12);
                                {
                                  printf("j");
                                  ve[i]='j';
                                  Sleep(1000);
                                  printf("  ");
                                }
                                desenha();
                        }
}


void movimenta(int &ponto,bool &perdeu)
{
        bool segvez=false;
        int cont,i,n=2,x,aux,rodada=0;
        char tecla,comp[80],jog[80];
        randomize();
        do{
                rodada+=1;

                aux=n;

                gotoxy(25,7);
                 printf("PRESTE ATENCAO AS LETRAS");
                 Sleep(2000);
                gotoxy(25,7);
                 printf("                               ");

                if(segvez==false)
                  for(i=0;i<=n;i++)
                  {
                       x=random(4);
                       Sleep(1000);
                       selecao(x,i,comp);
                  }
                else
                 {
                        for(i=0;i<n;i++)
                        {
                                Sleep(1000);
                                selecao(comp[i],i,comp);
                        }

                        x=random(4);
                        Sleep(1000);
                        selecao(x,n,comp);

                  }

                cont=0;


                gotoxy(25,7);
                 printf("AGORA REPITA");

                do
                {
                        fflush(stdin);
                        tecla=getch();
                        selecao(tecla,cont,jog);
                        if(jog[cont]!=comp[cont])
                                perdeu=true;
                        else
                                ponto+=1;
                        cont+=1;
                }while((perdeu==false)&&(cont<=aux));

                gotoxy(25,7);
                 printf("                   ");

                if(perdeu==false)
                {
                        gotoxy(25,7);
                        printf("MUITO BEM!");
                        Sleep(1500);
                        gotoxy(25,7);
                        printf("             ");
                 }


                  n+=1;
                  segvez=true;
        }while((perdeu==false)&&(rodada<6));
}

void main()
{
        int pon=0;
        bool perd=false;
        carrega();
        tela();
        desenha();
        movimenta(pon,perd);

        gotoxy(30,15);
        {
         if(perd==true)
                printf("VOCE ERROU!");
         else
                printf("Parabens!Vc chegou ao final do jogo!");
        }
        gotoxy(30,17);
        printf("PONTUACAO: %d letras certas",pon);
        fflush(stdin);
        Sleep(10000);
        getch();
}
