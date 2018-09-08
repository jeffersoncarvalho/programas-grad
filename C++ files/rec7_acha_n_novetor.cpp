
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
void ler(int a[],int tam,int i)
{
        if(i<tam)
        {
                printf("Elemento %d: ",1+i);
                scanf("%d",&a[i]);
                ler(a,tam,i+1);
        }
}
int procura(int b[],int tam,int i, int x,int cont)
{
        if(i<tam)
        {
                if((b[i]==x))
                        cont+=1;
                procura(b,tam,i+1,x,cont);
        }
        else
                return cont;
}
void main()
{
        int t,resp,n,*vetor;
        printf("Tamanho do Vetor: ");
        scanf("%d",&t);
        vetor=new int[t];
        ler(vetor,t,0);
        printf("Numero procurado: ");
        scanf("%d",&n);
        resp=procura(vetor,t,0,n,0);
        printf("\nQuantidade de %d no vetor e %d.",n,resp);
        getch();
}


