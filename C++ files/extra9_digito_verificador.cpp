
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
int main(int argc, char* argv[])
{
        int dec=1,t=0,i,soma=0,peso=2,resto,resultado,dgve,valor,*vetor;
        long int num;

        printf("Digite um numero: ");
        scanf("%d",&num);
        valor=num;

        do
        {
                dec*=10;
                t+=1;
        }while(num>dec);

        vetor=new int[t];

        vetor[0]=num%10;
        for(i=1;i<t;i++)
        {
                if(i==t-1)
                        vetor[i]=num/1;
                else
                        vetor[i]=num%10;
                num=(num-vetor[i])/10;
        }


        for(i=0;i<t;i++)
        {
                soma+=vetor[i]*peso;
                peso+=1;
        }

        resto=soma%11;
        resultado=11-resto;

        if(resultado>=10)
                dgve=0;
        else
                dgve=resultado;

        if(dgve==vetor[0])
                printf("O numero e consistente.DV=%d",dgve);
        else
                printf("O numero nao e consistente. DV=%d. Motivo: %d diferente de %d",dgve,dgve,vetor[0]);
        getch();
        return 0;
}
