
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
void nu_primo(int nu)//Função
{
        int i,primo;
        primo=1;
        for(i=2;i<nu;i++)
         if ((nu%i)==0)
                primo=0;

        if ((primo==0)||(nu==1))
                printf("O numero %d nao e primo",nu);
        if ((primo==1)&&(nu!=1))
                printf("O numero %d e primo",nu);
}

void main()//Programa Principal
{
        int n;
        printf("\nDigite um numero inteiro: ");
        scanf("%d",&n);

        nu_primo(n);
        getch();
}

