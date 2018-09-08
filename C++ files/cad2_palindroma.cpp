
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include <conio.h>
#include <stdio.h>
#include <string.h>
void main()
{
        char cadeia[200];
        int j=0,x;
        bool pal=true;
        //socorram me subi no onibus em marrocos
        printf("Digite cadeia: ");
        gets(cadeia);

        x=strlen(cadeia);

        do
        {
                x-=1;
                if(cadeia[x]!=cadeia[j])
                        pal=false;
                j++;
        }while((pal==true)&&(j!=strlen(cadeia)));


        if(pal==true)
                printf("\nCadeia Palindroma.");
        else
                printf("\nCadeia nao Palindroma .");

        getch();
}

