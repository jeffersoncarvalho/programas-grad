
#pragma hdrstop
#include <condefs.h>


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
int main(int argc, char* argv[])
{
        float nota[100],media,soma;
        int j,cont,negativo;
        negativo=0;
        j=0;
        cont=0;
        do
        {

                        printf("\nDigite a nota do aluno %d:",j+1);
                        scanf("%f",&nota[j]);
                        if (nota[j]>=0)
                        {
                                soma+=nota[j];
                                cont++;
                        }
                        else
                                negativo=1;
                        j++;

        }while(negativo==0);

        media=soma/(cont);

        printf("A media a turma foi: %5.2f",media);
        getch();



        return 0;
}
