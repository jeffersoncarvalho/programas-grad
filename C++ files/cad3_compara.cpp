
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include <stdio.h>
#include <conio.h>

int compara(char cad1[],char cad2[])
{
        int i;
        long int soma1=0,soma2=0;

        for(i=0;cad1[i];i++)
                soma1+=cad1[i];

        for(i=0;cad2[i];i++)
                soma2+=cad2[i];

        if(soma1==soma2)
                return 0;
        else
                if(soma1>soma2)
                        return 1;
                else
                        return -1;
}

void main()
{
        char cad1[80],cad2[80];
        int r;

        printf("Cadeia 1: ");
        gets(cad1);
        printf("Cadeia 2: ");
        gets(cad2);

        r=compara(cad1,cad2);

        printf("\n%d",r);
        getch();
}


