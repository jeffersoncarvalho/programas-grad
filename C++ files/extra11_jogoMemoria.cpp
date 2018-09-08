//10:24 29/04/02//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

//---------------------------------------------------------------------------

#pragma argsused
#include<stdio.h>
#include<conio.h>
#include<vcl.h>
#include<stdlib.h>

void carrega(void)
{
	
	printf("\nVerificando Aceleracao 3D");
	Sleep(1000);
	printf("  OK");
	Sleep(500);

	printf("\nVerificando Memoria");
	Sleep(1000);
	printf("  OK");
	Sleep(500);

	printf("\nVerificando versao DirectX");
	Sleep(1000);
	printf("  OK");
	Sleep(500);

	printf("\nVerificando inteligencia do usuario: ");
	Sleep(1000);
	printf("Insatisfatoria");
	Sleep(1000);

	clrscr();
	gotoxy(35,13);
	printf("***JOGO DA MEMORIA***");
	gotoxy(35,15);
	printf("Hit any Key");
	getch();
	fflush(stdin);
	clrscr();
}
	
void alimenta_auto(int mp[][5],int b[])
{
        int i,j,t=0,h=0;
        for(i=0;i<4;i++)
         for(j=0;j<4;j++)
          {
                t+=1;
                mp[i][j]=t;
                b[h]=t;
                h+=1;
                if(t==8)
                  t=0;

          }


}

void embaralha(int b[])
{
        int i,x,aux;
        randomize();
        for(i=0;i<16;i++)
        {
                do
                {

                        x=random(16);
                }while(x==i);

                aux=b[i];
                b[i]=b[x];
                b[x]=aux;
        }

}

void matriz_prim_rec_vet(int mp[][5],int b[])
{
        int i,j,h=0;
        for(i=0;i<4;i++)
         for(j=0;j<4;j++)
         {
                mp[i][j]=b[h];
                h+=1;
         }


}

void estetico(int mp[][5],char mos[][5])
{
        int i,j;
        for(i=0;i<4;i++)
         for(j=0;j<4;j++)
         {
                if(mp[i][j]==1)
                 mos[i][j]='A';
                if(mp[i][j]==2)
                 mos[i][j]='B';
                if(mp[i][j]==3)
                 mos[i][j]='C';
                if(mp[i][j]==4)
                 mos[i][j]='D';
                if(mp[i][j]==5)
                 mos[i][j]='E';
                if(mp[i][j]==6)
                 mos[i][j]='F';
                if(mp[i][j]==7)
                 mos[i][j]='G';
                if(mp[i][j]==8)
                 mos[i][j]='H';
         }

}

void miolo(int mp[][5],char mos[][5],int &ten)
{
        int i,j,mpaux[5][5],cont=0,a,b,c,d;
        for(i=0;i<4;i++)
         for(j=0;j<4;j++)
             mpaux[i][j]=1;

        do
        {
                ten+=1;
                gotoxy(40,1);
                printf("Coordenada 1: ");
                scanf("%d%d",&a,&b);
                a-=1;b-=1;clrscr();

                for(i=0;i<4;i++)
                {
                 for(j=0;j<4;j++)
                 {
                        if( ((i==a)&&(j==b))||(mpaux[i][j]==0))
                           printf("  %c  ",mos[i][j]);
                        else
                           printf(" %d %d ",i+1,j+1);
                 }
                 printf("\n\n");
                }

                gotoxy(40,2);
                printf("Coordenada 2: ");
                scanf("%d%d",&c,&d);
                c-=1;d-=1;clrscr();

                for(i=0;i<4;i++)
                {
                 for(j=0;j<4;j++)
                 {
                        if( ((i==a)&&(j==b)) || ((i==c)&&(j==d))||(mpaux[i][j]==0))
                           printf("  %c  ",mos[i][j]);
                        else
                           printf(" %d %d ",i+1,j+1);
                 }
                 printf("\n\n");
                }

                Sleep(1000);clrscr();

                if(mp[a][b]==mp[c][d])
                {
                        cont+=1;
                        mpaux[a][b]=0;
                        mpaux[c][d]=0;
                }

                for(i=0;i<4;i++)
                {
                 for(j=0;j<4;j++)
                 {
                        if(mpaux[i][j]==0)
                           printf("  %c  ",mos[i][j]);
                        else
                           printf(" %d %d ",i+1,j+1);
                 }
                 printf("\n\n");
                }

        }while(cont<7);
}

void main()
{
        char most[5][5];
        int t=0,i,j,vetor[16],matprin[5][5];

	carrega();

        alimenta_auto(matprin,vetor);
        embaralha(vetor);
        matriz_prim_rec_vet(matprin,vetor);
        estetico(matprin,most);

        for(i=0;i<4;i++)
        {
         for(j=0;j<4;j++)
            printf("  %c  ",most[i][j]);
         printf("\n\n");
        }

        Sleep(5000);clrscr();

        for(i=0;i<4;i++)
        {
         for(j=0;j<4;j++)
            printf(" %d %d ",i+1,j+1);
         printf("\n\n");
        }

        miolo(matprin,most,t);
        clrscr();

        for(i=0;i<4;i++)
        {
         for(j=0;j<4;j++)
            printf("  %c  ",most[i][j]);
         printf("\n\n");
        }
        gotoxy(1,13);
        printf("YOU GOT IT ASSHOLE!!!");
        gotoxy(1,15);
        printf("TENTATIVAS: %d",t);
        Sleep(3000);
        fflush(stdin);
        getch();
}

