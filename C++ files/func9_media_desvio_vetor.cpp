
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<math.h>
#include<stdio.h>
void lervetor(int a[],int tam)
{
        int i;
        for(i=1;i<=tam;i++)
        {
                printf("Digite o %d elemento: ",i);
                scanf("%d",&a[i]);
        }
}
float media(int b[],int tam)
{
        int i;
        float soma=0;
        for(i=1;i<=tam;i++)
                soma+=b[i];
        return (float)soma/tam;
}
float desvio(int c[],int tam,float med)
{
        int i;
        float soma=0;
        for(i=1;i<=tam;i++)
                soma+=pow((c[i]-med),2);
        return sqrt((float)soma/tam);
}
void main()
{
        int vetor[50],t;
        printf("Digite o tamanho do vetor: ");
        scanf("%d",&t);
        lervetor(vetor,t);
        printf("A media do elementos e %4.2f:",media(vetor,t));
        printf("\nO desvio padrao sera %4.2f:",desvio(vetor,t,(media(vetor,t))));
        getch();
}

