
#pragma hdrstop
#include <condefs.h>


//---------------------------------------------------------------------------
#pragma argsused
#include <stdio>
#include <conio>
int main(int argc, char* argv[])
{
        int opcao;
        do
        {
        printf("\nESCOLHA UM OPCAO:");
        printf("\n1:Par ou Impar.");
        printf("\n2:Equacao do segundo grau.");
        printf("\n3:Calculadora.");
        printf("\n4:Fatorial.");
        printf("\n5:Primos.");
        printf("\n6:SAIR.");
        printf("\n");
        scanf("%d",&opcao);
        clrscr();

          switch(opcao)
          {
                case 1:
                printf("\nVc escolheu Par ou Impar.");
                printf("\n");
                break;
                case 2:
                printf("\nVc escolheu Equacao.");
                printf("\n");
                break;
                case 3:
                printf("\nVc escolheu Calculadora.");
                printf("\n");
                break;
                case 4:
                printf("\nVc escolheu Fatorial.");
                printf("\n");
                break;
                case 5:
                printf("\nVc escolheu Primos.");
                printf("\n");
                break;

          }
        }while(opcao!=6);
        return 0;
}
