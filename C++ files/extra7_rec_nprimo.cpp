
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
int i=2,prim=0;
void primo(int n)
{
        if (i<n)
         if(n%i==0)
                printf("O numero nao e primo");
         else
                {i+=1;primo(n);}
        if(i==n)
                prim=1;
}
void main()
{
        int x;
        printf("Digite um numero inteiro: ");
        scanf("%d",&x);
        primo(x);
        if(prim==1)
                printf("O numero e primo");
        getch();
}
