
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
void lervetor(int a[],int tam)
{
        int i;
        for(i=1;i<=tam;i++)
        {
                printf("Digite o elemento %d: ",i);
                scanf("%d",&a[i]);
        }
}
void inserir(int b[],int tam,int v1,int v2)
{
        int i,aux[50];
        if(v2==1)
        {
                aux[1]=v1;
                for(i=1;i<=tam;i++)
                {
                        aux[i+1]=b[i];
                }
        }
        else
        {
                for(i=1;i<v2;i++)
                        aux[i]=b[i];
                for(i=v2;i<=tam;i++)
                        aux[i+1]=b[i];
                aux[v2]=v1;
        }
        for(i=1;i<=(tam+1);i++)
                b[i]=aux[i];
}
void exibe(int c[],int tam)
{
        int i;
                for(i=1;i<=(tam+1);i++)
                        printf("%d,",c[i]);
}
void main()
{
        int vetor[50],t,x,p;
        printf("\nDigite o tamanho do vetor:");
        scanf("%d",&t);
        lervetor(vetor,t);
        printf("\nDigite o valor e a posicao respectivamente: ");
        scanf("%d%d",&x,&p);
        inserir(vetor,t,x,p);
        exibe(vetor,t);
        getch();
}





 