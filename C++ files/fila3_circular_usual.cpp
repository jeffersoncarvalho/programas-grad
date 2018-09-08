
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
struct filac
{
        int prim,ult;
        int dado[10];
}f;

int tamanho(filac f)
{
        return f.ult-f.prim+1;
}

void enfileirar(filac &f,int valor)
{
        if(tamanho(f)<=10)
                f.dado[++f.ult%10]=valor;
        else
                printf("Fila Cheia!");
}

void desenfileirar(filac &f)
{
        int valor;
        if(tamanho(f)>0)
        {
                f.dado[f.prim++%10];
        }
        else
                printf("Fila Vazia!");
}

void exibefila(filac f)
{
        for(int i=f.prim+1;i<=f.ult;i++)
                printf("%d,",f.dado[i]);
}

void main()
{
        int valor,opcao;;

        do{
                printf("\n1.Enfileirar;");
                printf("\n2.Desenfileirar;");
                printf("\n3.SAIR");
                printf("\n");
                scanf("%d",&opcao);

                switch (opcao)
                {
                        case 1:
                        printf("Valor: ");
                        scanf("%d",&valor);
                        enfileirar(f,valor);
                        exibefila(f);
                        getch();
                        break;

                        case 2:
                        desenfileirar(f);
                        exibefila(f);
                        getch();
                        break;
                }

           clrscr();
          }while(opcao!=3);
}

