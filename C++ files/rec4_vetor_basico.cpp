
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
void lervetor(int a[],int tam,int i)
{
        if (i<tam)
        {
                printf("\nDigite o %d elemento: ",i+1);
                scanf("%d",&a[i]);
                lervetor(a,tam,i+1);
        }

}

void exibevetor(int b[],int tam,int j)
{
        if (j<tam)
        {
                printf("%d,",b[j]);
                exibevetor(b,tam,j+1);
        }
}
void main()
{
        int t,*vetor;
        printf("Digite o tamanho do vetor: ");
        scanf("%d",&t);
        vetor=new int[t];
        lervetor(vetor,t,0);
        exibevetor(vetor,t,0);
        getch();
}

