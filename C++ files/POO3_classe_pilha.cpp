
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>

class pilha
{
        protected:
                int dado[10],topo,valor;
        public:
           pilha()
           {topo=0;}
           bool cheia()
           { return (topo==10)?true:false;};
           bool vazia()
           { return (topo==0)?true:false;};
           bool empilhar(int valor)
           {
                if(cheia()!=true)
                { dado[topo++]=valor;
                  return true;
                }
                else
                  return false;
           }
           bool desempilhar(int &valor)
           {
                if(vazia()!=true)
                { valor=dado[--topo];
                  return true;
                }
                valor=0;
                return false;
           }
           void mostrar()
           {
                for(int i=0;i<topo;i++)
                        printf("%d,",dado[i]);
           }
};

int main()
{
        pilha p;
        int op,valor;

        do
        {
                printf("\n1.Empilhar;");
                printf("\n2.Desempilhar;");
                printf("\n3.Sair.");
                printf("\nOpcao: ");
                scanf("%d",&op);

                switch (op)
                {
                        case 1:
                                printf("\nValor: ");
                                scanf("%d",&valor);

                                if(p.empilhar(valor)==true)
                                        printf("\nOk.");
                                else
                                        printf("\nPilha Cheia!");
                                p.mostrar();
                                getch();
                                break;
                        case 2:
                                printf("\nValor: ");
                                scanf("%d",&valor);
                                if(p.desempilhar(valor)==true)
                                        printf("\nOk.");
                                else
                                        printf("\nPilha Vazia!");
                                p.mostrar();
                                getch();
                                break;
                 }
        clrscr();
        }while(op!=3);
        return 0;
}
