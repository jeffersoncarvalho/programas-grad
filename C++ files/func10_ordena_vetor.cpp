
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
void ordena(int a[],int tam)
{
        int aux,j,i;
        for(i=0;i<tam-2;i++)
          for(j=tam-1;j>=(i+1);j--)
              if(a[j] < a[j-1])
              {
                    aux=a[j-1];
                    a[j-1]=a[j];
                    a[j]=aux;
               }

}
void lervetor(int a[],int tam)
{
        int i;
        for(i=0;i<tam;i++)
        {
                printf("Digite o %d elemento: ",i+1);
                scanf("%d",&a[i]);
        }
}
void exibe(int b[],int tam)
{
        int i;
        for(i=0;i<tam;i++)
                printf("%d,",b[i]);
}
void main()
{
        int t,vetor[50];
        printf("Digite o tamanho do vetor: ");
        scanf("%d",&t);
        lervetor(vetor,t);
        ordena(vetor,t);
        exibe(vetor,t);
        getch();
}

