
#include <condefs.h>
#pragma hdrstop
#include<conio.h>
#include<stdio.h>

//---------------------------------------------------------------------------
#pragma argsused
int compara(char cad1[],char cad2[])
{
        int i,soma1=0,soma2=0;
        for(i=0;cad1[i];i++)
                soma1+=cad1[i]-100;
        for(i=0;cad2[i];i++)
                soma2+=cad2[i]-100;

        if(soma1<soma2)
                return -1;
        else
                if(soma1==soma2)
                        return 0;
        return 1;
}
void main()
{
        char c1[80],c2[80];
        printf("\nNome1: ");
        gets(c1);
        fflush(stdin);
        printf("\nNome2: ");
        gets(c2);
        printf("\nResultado= %d",compara(c1,c2));
        getch();
}
