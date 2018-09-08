
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>

class fila
{
        protected:
                int dado[10],topo,valor;
        public:
           fila()
           {topo=0;}
           bool cheia()
           { return (topo==10)?true:false;};
           bool vazia()
           { return (topo==0)?true:false;};
           bool enfileirar(int valor)
           {
                if(cheia()!=true)
                { dado[topo++]=valor;
                  return true;
                }
                else
                  return false;
           }
           bool desenfileirar()
           {
                if(vazia()!=true)
                { valor=dado[0];
                  for(int i=0;i<topo-1;i++)
                        dado[i]=dado[i+1];
                  --topo;
                  return true;
                }
                else
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
        fila f;
        int op,valor;
        do
        {
                printf("\n1.Enfileirar;");
                printf("\n2.Desenfileirar;");
                printf("\n3.Sair.");
                printf("\nOpcao: ");
                scanf("%d",&op);

                switch (op)
                {
                        case 1:
                                printf("\nValor: ");
                                scanf("%d",&valor);

                                if(f.enfileirar(valor)==true)
                                        printf("\nOk.");
                                else
                                        printf("\Fila Cheia!");
                                f.mostrar();
                                getch();
                                break;
                        case 2:
                                if(f.desenfileirar()==true)
                                        printf("\nOk.");
                                else
                                        printf("\nFila Vazia!");
                                f.mostrar();
                                getch();
                                break;
                 }
        clrscr();
        }while(op!=3);
        return 0;
}
