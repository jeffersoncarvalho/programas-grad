
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include <stdlib.h>
#include <stdio.h>
#include <conio.h>
#include <vcl.h>
#include <math.h>
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
        int x,n,cont,dif;
        cont=0;
        x=aleatorio();
        do
        {
                printf("\n Digite um numero de 1 a 100: ");
                scanf("%d",&n);
                dif=abs(n-x);
                if (dif>=1 && dif<=5)
                {
                        printf("\nTa muito quente");
                        getch();
                }
                if (dif>5 && dif<=10)
                {
                        printf("\nTa quente");
                        getch();
                }
                if (dif>10 && dif<=15)
                {
                        printf("\nTa quase la");
                        getch();
                }
                if (dif>15 && dif<=30)
                {
                        printf("\nTa Frio");
                        getch();
                }
                if (dif>30 && dif<=60)
                {
                        printf("\nTa muito Frio");
                        getch();
                }
                if (dif>60)
                {
                        printf("\nTa congelando");
                        getch();
                }

                clrscr();
                cont+=1;
        }while(n!=x);
        printf("\nParabens, voce acertou!");
        printf("\nO numero correto era %d.",x);
        printf("\nVc tentou %d vezes.",cont);
        getch();
}

