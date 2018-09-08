
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdlib.h>
#include<conio.h>
#include<stdio.h>
class inteiro
{
        private:
                int n,tentativas;
        public:
                void novojogo()
                {
                        randomize();
                        n=random(100)+1;
                        tentativas=0;
                }
                int jogada(x)
                {
                        tentativas++;

                        if(x==n)
                                return 0;
                        if(x<n)
                                return -1;
                        if(x>n)
                                return +1;
                }
                int content()
                {
                        return tentativas;
                }

};

int main()
{
        inteiro i;
        int x,resp;
        i.novojogo();

        do{
                printf("\nNumero: ");
                scanf("%d",&x);
                resp=i.jogada(x);
                if(resp<0)
                        printf("\nSeu numero e menor.");
                if(resp>0)
                        printf("\nSeu numero e maior.");
        }while(resp!=0);

        printf("\nACERTOU!");
        printf("\nTentativas: %d",i.content());

        getch();

        return 0;
}
