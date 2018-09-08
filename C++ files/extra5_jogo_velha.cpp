
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
#include<math.h>
#include<vcl.h>


char v1,v2,carac;
int t,tempo,x1,y1,x2,y2;
void desenha()
{
        int i,dx,dy,npontos;
        float incx,incy,x,y;
        dx=(x2-x1);
        dy=(y2-y1);
        if (abs(dx)>abs(dy))
                npontos=abs(dx);
        else
                npontos=abs(dy);
        incx=(float)dx/npontos;
        incy=(float)dy/npontos;
        x=x1;y=y1;
        for(i=0;i<=npontos;i++)
        {
                gotoxy(x,y);
                Sleep(tempo);
                printf("%c",carac);
                Sleep(tempo);
                //clrscr();
                x=x+incx;
                y=y+incy;
        }
}
void teste(char matriz[][4])
{

     if ((matriz[1][1]==matriz[1][2]) && (matriz[1][1]==matriz[1][3]))
      {
           if (matriz[1][1]=='x')
            v1='v';
           if (matriz[1][1]=='o')
            v2='v';
            x1=8;y1=2;x2=28;y2=2;
      }
     if ((matriz[2][1]==matriz[2][2]) && (matriz[2][1]==matriz[2][3]))
      {
           if (matriz[2][1]== 'x')
            v1='v';
           if (matriz[2][1]== 'o')
            v2='v';
             x1=8;y1=5;x2=28;y2=5;
      }
     if ((matriz[3][1]==matriz[3][2]) && (matriz[3][1]==matriz[3][3]))
      {
           if (matriz[3][1]== 'x')
            v1='v';
           if (matriz[3][1]== 'o')
            v2='v';
             x1=8;y1=8;x2=28;y2=8;
      }
     if ((matriz[1][1]==matriz[2][1]) && (matriz[1][1]==matriz[3][1]))
      {
           if (matriz[1][1]== 'x')
            v1='v';
           if (matriz[1][1]== 'o')
            v2='v';
             x1=8;y1=2;x2=8;y2=8;
      }
     if ((matriz[1][2]==matriz[2][2]) && (matriz[1][2]==matriz[3][2]))
      {
           if (matriz[1][2]== 'x')
            v1='v';
           if (matriz[1][2]== 'o')
            v2='v';
             x1=18;y1=2;x2=18;y2=8;
      }
     if ((matriz[1][3]==matriz[2][3]) && (matriz[1][3]==matriz[3][3]))
      {
           if (matriz[1][3]== 'x')
            v1='v';
           if (matriz[1][3]== 'o')
            v2='v';
             x1=28;y1=2;x2=28;y2=8;
      }
     if ((matriz[1][1]==matriz[2][2]) && (matriz[1][1]==matriz[3][3]))
      {
           if (matriz[1][1]== 'x')
            v1='v';
           if (matriz[1][1]== 'o')
            v2='v';
             x1=8;y1=2;x2=28;y2=9;
      }
      if ((matriz[1][3]==matriz[2][2]) && (matriz[1][3]==matriz[3][1]))
      {
           if (matriz[1][3]== 'x')
            v1='v';
           if (matriz[1][3]== 'o')
            v2='v';
             x1=8;y1=8;x2=29;y2=2;
      }
}

void exibe(char matriz[][4])
{
        int i,j,t=0;
        char mostra[4][4],vetor[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P'};;
     printf("\n");
      for(i=1;i<=3;i++)
       {
          for(j=1;j<=3;j++)
           {
                printf("     ");
                if ((matriz[i][j]=='x') || (matriz[i][j]=='o'))
                 {
                  mostra[i][j]=UpCase(matriz[i][j]);
                  printf("  %c  ",mostra[i][j]);                  }
                else
                  printf("  %c  ",vetor[t]);

                t++;
           }
                printf("\n");
                printf("\n");
                printf("\n");

        }

     printf("\n");
        tempo=0;
        carac='|';
        x1=13;y1=1;x2=13;y2=10;
                desenha();
        x1=24;y1=1;x2=24;y2=10;
                desenha();
        carac='-';
        x1=3;y1=4;x2=33;y2=4;
                desenha();
        x1=3;y1=7;x2=33;y2=7;
                desenha();
}

void limpajogo(char matriz[][4])
{
        int i,j;
      for(i=1;i<=3;i++)
       for(j=1;j<=3;j++)
        matriz[i][j]=' ';
      t=0;
      v1='f';
      v2='f';
}

void transforma(char tecla,int &a,int &b)
{
        if(tecla=='a')
        {a=1;b=1;}
        if(tecla=='b')
        {a=1;b=2;}
        if(tecla=='c')
        {a=1;b=3;}
        if(tecla=='d')
        {a=2;b=1;}
        if(tecla=='e')
        {a=2;b=2;}
        if(tecla=='f')
        {a=2;b=3;}
        if(tecla=='g')
        {a=3;b=1;}
        if(tecla=='h')
        {a=3;b=2;}
        if(tecla=='i')
        {a=3;b=3;}
}

void main()
{
        int sai,a,b,troca;
        char mat[4][4],resp,cad1[80],cad2[80],tecla;
 do
 {
  clrscr();

     v1='f';
     v2='f';
     sai=0;
     printf("Digite o nome do jogador 1: ");
     gets(cad1);
     printf("Digite o nome do jogador 2: ");
     gets(cad2);
     troca=1;
     clrscr();
     exibe(mat);
     fflush(stdin);
     do
     {
           fflush(stdin);
           if (troca==1)
           {
            printf("\n\n\n\n\n\nDigite sua jogada %s: ", cad1 );
            scanf("%c",&tecla);
            transforma(tecla,a,b);
            clrscr();
           }
           else
           {
            printf("\n\n\n\n\n\nDigite sua jogada %s: ", cad2 );
            scanf("%c",&tecla);
            transforma(tecla,a,b);
            clrscr();
           }



           if (troca==1)
            mat[a][b]= 'x';
           else
            mat[a][b]='o';

           t=t+1;
           exibe(mat);
           if (t>4)
            teste(mat);

           if((v1=='v') || (v2=='v') || (t==9))
                sai=1;
           troca*=-1;

     }while(sai==0);
     tempo=100;
     carac=1;
     if ((v1=='v')||(v2=='v'))
        desenha();
     if ((v1=='f')&&(v2=='f'))
     {
        tempo=20;
        x1=8;y1=2;x2=28;y2=9;
         desenha();
        x1=8;y1=8;x2=29;y2=2;
         desenha();
     }


     if (v1=='v')
      printf("\n\n\n\n\n\n\n\n\n\n\nO jogador %s VENCEU a partida!",cad1);
     if (v2=='v')
      printf("\n\n\n\n\n\n\n\n\n\n\nO jogador %s VENCEU a partida!",cad2);
     if ((t==9) && (v1=='f') && (v2=='f'))
      printf("\n\n\n\n\n\n\n\n\n\n\nA partida esta EMPATADA.");

     printf("\nDeseja continuar?(s|n): ");
     resp=getch();
     limpajogo(mat);
     fflush(stdin);
 }while(resp=='s');
}
