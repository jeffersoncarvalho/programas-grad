
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

bool cheia(pilha &p,char cad[])
{
        return (p.topo==strlen(cad))? true:false;
}

bool vazia(pilha &p,char cad[])
{
        return (p.topo<0)? true:false;
}

void empilhar(pilha &p, char cad[])
{
        p.topo=0;
        while(cheia(p,cad)==false)
        {
                p.letra[p.topo]=cad[p.topo];
                p.topo++;
        }

}

bool desempilhar(pilha &p,char cad[])
{

        int x=0;
        p.topo=strlen(cad)-1;
        while(vazia(p,cad)==false)
        {

                if(p.letra[p.topo]!=cad[x])
                        return false;
                --p.topo;
                x++;
        }
        return true;


}

void main()
{
        char cad[100];
        printf("Cadeia: ");
        gets(cad);
        empilhar(p,cad);
        if(desempilhar(p,cad)==false)
                printf("\nNao palindroma.");
        else
                printf("\nPalindroma.");

        getch();
}


