
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
#include<stdlib.h>

class conta
{
        protected:
                int nr;
                char *titular;
                float saldo;
        public:
                conta(int n,char t[])//BUILDER
                {
                        nr=n;titular=t;saldo=0;
                }
                int deposito(float v)
                {
                        saldo+=v;
                        return 1;
                }
                int saque (float v)
                {
                        if(v<=saldo)
                        {
                                saldo-=v;
                                return 1;
                        }
                        return 0;
                }
                float consulta()
                {
                        return saldo;
                }
                void posicao()
                {
                        printf("\n N da conta:%d",nr);
                        printf("\n Titular: %s",titular);
                        printf("\n Saldo: %10.2f", saldo);
                }
};

int main()
{
        conta *c;
        int op,n;
        float x;
        char *t;
        do
        {
                printf("\n1.Abrir Conta");
                printf("\n2.Saque");
                printf("\n3.Deposito");
                printf("\n4.Posicao");
                printf("\n5.Sair");
                printf("\nOpcao: ");
                scanf("%d",&op);

                switch(op)
                {
                        case 1:
                                fflush(stdin);
                                t=new char[40];
                                printf("\nTitular: ");
                                gets(t);
                                printf("\nN da conta: ");
                                scanf("%d",&n);
                                c=new conta(n,t);
                                getch();
                                clrscr();
                                break;
                        case 2:
                                printf("\nValor Saque: ");
                                scanf("%f",&x);
                                if(c->saque(x))
                                 printf("\nOk!");
                                else
                                 printf("\nFalhou.");
                                getch();
                                clrscr();
                                break;
                        case 3:
                                printf("Valor a depositar: ");
                                scanf("%f",&x);
                                if(c->deposito(x))
                                        printf("\nOk!");
                                getch();
                                clrscr();
                                break;
                        case 4:
                               c->posicao();
                               getch();
                               clrscr();
                               break;

                }
        }while(op!=5);
        return 0;
}