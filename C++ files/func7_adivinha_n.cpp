
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include <stdlib.h>
#include <stdio.h>
#include<conio.h>
#include<vcl.h>
int aleatorio(void)
{
        int x;
        randomize();
        x=random(100);
        x=x+1;
        return x;
}

void main()
{
        int x,n,cont;
        cont=0;
        x=aleatorio();
        do
        {
                printf("\n Digite um numero de 1 a 100: ");
                scanf("%d",&n);
                if (n<x)
                {
                        printf("\nO numero digitado e MENOR(Aguarde).");
                        Sleep(3000);
                }
                if (n>x)
                {
                        printf("\nO numero digitado e MAIOR(Aguarde).");
                        Sleep(3000);
                }
                clrscr();
                cont+=1;
        }while(n!=x);
        printf("\nParabens, voce acertou!");
        printf("\nO numero correto era %d.",x);
        printf("\nVc tentou %d vezes.",cont);
        getch();
}

