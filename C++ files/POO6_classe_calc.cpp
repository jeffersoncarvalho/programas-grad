
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
#include<math.h>
class calculadora
{
        protected:
                float x;
        public:
                calculadora()
                {x=0;}
                void limpar()
                {x=0;}
                void atrib(float a)
                {x=a;}
                float add(float a)
                {return x+=a;}
                float sub(float a)
                {return x-=a;}
                float mul(float a)
                {return x*=a;}
                float div(float a)
                {return x/=a;}
                float ret()
                {return x;}
};
class cientifica:public calculadora
{
        public:
                float sen()
                {return sin(x);}
                float cosseno()
                {return cos(x);}
                float inv()
                {       if(x!=0)
                                return 1/x;
                        return 0;
                }
};
void main()
{

        cientifica *c;
        c=new cientifica;

        int op;
        float n;
        do
        {
                printf("\n1.Sub;");
                printf("\n2.Add;");
                printf("\n3.Mul;");
                printf("\n4.Div;");
                printf("\n5.Sin;");
                printf("\n6.Cos;");
                printf("\n7.Inv;");
                printf("\n8.ATRIBUIR;");
                printf("\n9.LIMPAR;");
                printf("\n10.SAIR;");
                printf("\nOpcao: ");
                scanf("%d",&op);

                switch(op)
                {
                        case 1:
                        printf("\nValor: ");
                        scanf("%f",&n);
                        printf("\nResposta= %10.2f",c->sub(n));
                        getch();
                        clrscr();
                        break;
                        case 2:
                        printf("\nValor: ");
                        scanf("%f",&n);
                        printf("\nResposta= %10.2f",c->add(n));
                        getch();
                        clrscr();
                        break;
                        case 3:
                        printf("\nValor: ");
                        scanf("%f",&n);
                        printf("\nResposta= %10.2f",c->mul(n));
                        getch();
                        clrscr();
                        break;
                        case 4:
                        printf("\nValor: ");
                        scanf("%f",&n);
                        printf("\nResposta= %10.2f",c->div(n));
                        getch();
                        clrscr();
                        break;
                        case 5:
                        printf("\nSeno(%10.2f)= %10.10f",c->ret(),c->sen());
                        getch();
                        clrscr();
                        break;
                        case 6:
                        printf("\nCosseno(%10.2f)= %10.10f",c->ret(),c->cosseno());
                        getch();
                        clrscr();
                        break;
                        case 7:
                        if(c->inv()!=0)
                                printf("\n1/%10.2f= %10.10f",c->ret(),c->inv());
                        else
                                printf("\nDIVISIO BY ZERO!");
                        getch();
                        clrscr();
                        break;
                        case 8:
                        printf("\nValor: ");
                        scanf("%f",&n);
                        c->atrib(n);
                        getch();
                        clrscr();
                        break;
                        case 9:
                        c->limpar();
                        printf("\nOK!");
                        getch();
                        clrscr();
                        break;
                }
        }while(op!=10);


}
