
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include <stdio.h>
#include<conio.h>
#include <string.h>

struct pilha
        {
            char letra[100];
            int topo;
        }p;
void empilhar(pilha &p, char cad[])
{
        int i=0;
        while(cad[i])
        {
                p.letra[i]=cad[i];
                i++;

        }

}

bool desempilhar(pilha &p,char cad[])
{
        int i=strlen(cad)-1,x=0;
        bool pal=true;

        while(cad[x])
        {

                if(p.letra[i]!=cad[x])
                        pal=false;
                --i;
                x++;
        }

        return pal;
}

void main()
{
        char cad[100];
        bool r;
        printf("Cadeia: ");
        gets(cad);

        empilhar(p,cad);
        r=desempilhar(p,cad);

        if(r==false)
                printf("\nNao palindroma");
        else
                printf("\nPalindroma");

        getch();
}


