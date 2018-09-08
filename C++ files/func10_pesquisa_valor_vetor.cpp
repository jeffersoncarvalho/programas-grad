
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
void lervetor(float a[],int tam)
{
        int i;
        for(i=1;i<=tam;i++)
        {
                printf("Digite o elemento %d: ",i);
                scanf("%f",&a[i]);
        }
}
void procura(float b[],int tam,float valor)
{

        int i,pos,achei=0;
        for(i=1;i<=tam;i++)
                if(b[i]==valor)
                {
                        achei=1;
                        pos=i;
                }
        if(achei==1)
                printf("A posicao do valor %5.2f no vetor e %d.",valor,pos);
        else
                printf("O valor %5.2f nao pertence ao vetor.",valor);
}
void main()
{
        float vetor[50],val;
        int t;
        printf("Digite o tamanho do vetor: ");
        scanf("%d",&t);
        lervetor(vetor,t);
        printf("Digite o valor procurado: ");
        scanf("%f",&val);
        procura(vetor,t,val);
        getch();
}
