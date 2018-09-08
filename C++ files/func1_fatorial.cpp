
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
int fatorial(int nu)//Função
{
        int i,fat;
        fat=1;
        for(i=1;i<=nu;i++)
                fat*=i;

        return fat;
}

void main()//Programa Principal
{
        int fat,n;
        printf("\nDigite um numero: ");
        scanf("%d",&n);

        fat=fatorial(n);

        printf("\nO fatorial de %d e %d:",n,fat);
        getch();
}

