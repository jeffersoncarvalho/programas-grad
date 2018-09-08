
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
void alimenta(int a[],int i,int tam)
{
        if(i<tam)
        {
                printf("Elemento %d: ",i+1);
                scanf("%d",&a[i]);
                alimenta(a,i+1,tam);
        }
}
int soma(int b[],int tam,int somatorio,int i)
{
        if(i<tam)
        {
                somatorio+=b[i];
                soma(b,tam,somatorio,i+1);
        }
        else
                return somatorio;
}
void main()
{
        int *vetor,t,resp;
        printf("Tamanho do vetor: ");
        scanf("%d",&t);
        vetor=new int[t];
        alimenta(vetor,0,t);
        resp=soma(vetor,t,0,0);
        printf("Soma=%d",resp);
        getch();
}
