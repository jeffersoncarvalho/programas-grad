
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
struct fila
{
        int dado[10];
        int fim;
}f;

bool cheia(fila &f)
{
        return (f.fim==10)?true:false;
}
bool vazia(fila &f)
{
        return (f.fim==0)?true:false;
}

void enfileirar(fila &f,int valor)
{
        if(cheia(f)!=true)
                f.dado[f.fim++]=valor;
        else
                printf("Fila Cheia!");
}

void desenfileirar(fila &f)
{
        int valor;
        if(vazia(f)!=true)
        {
                valor=f.dado[0];
                for(int i=0;i<f.fim-1;i++)
                        f.dado[i]=f.dado[i+1];
                --f.fim;
        }
        else
                printf("Fila Vazia!");
}

void exibefila(fila f)
{
        for(int i=0;i<f.fim;i++)
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

