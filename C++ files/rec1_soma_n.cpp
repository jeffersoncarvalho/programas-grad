
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
int soma(int n)
{
        return(n==0)?0:(n+soma(n-1));
}
void main()
{
        int x,resp;
        printf("Digite um numero inteiro: ");
        scanf("%d",&x);
        resp=soma(x);
        printf("\nA soma dos algarismos de 1 ate %d e %d.",x,resp);
        getch();
}
