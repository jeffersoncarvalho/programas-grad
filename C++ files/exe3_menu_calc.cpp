
#pragma hdrstop
#include <condefs.h>


//---------------------------------------------------------------------------
#pragma argsused
#include <stdio.h>
#include <conio.h>
int main(int argc, char* argv[])
{
        float num1,num2,resp;
        char ope;
        printf("\nDigite dois numeros:");
        scanf("%f%f",&num1,&num2);
        fflush(stdin);
        printf("\nEscolha um operacao");
        scanf("%c",&ope);
        switch (ope)
        {
                case '+':
                resp=num1+num2;
                printf("\nA soma e:%f",resp);
                break;

                case '-':
                resp=num1-num2;
                printf("\nA diferenca e:%f",resp);
                break;

                case '*':
                resp=num1*num2;
                printf("\nO produto e:%f",resp);
                break;

                case '/':
                resp=num1/num2;
                printf("\nA divisao e:%f",resp);
                break;

                default:
                printf("\nOpcao invalida!");
        }
        printf("\n");
        printf("\nPressione uma tecla para fechar.");
        getch();

        return 0;
}
 