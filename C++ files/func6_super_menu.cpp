
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
#include<math.h>
#include<vcl.h>

void par_impar(void)
{
        int num1;
        printf("\nEntre com um valor inteiro: ");
        scanf("%d",&num1);
        if (num1%2==0)
                printf("\nO numero e par.");
        else
                printf("\nO numero e impar.");
        getch();
}

void equacao(void)
{
        float delta,a,b,c,r1,r2;
        printf("\nEntre com os coeficientes:");
        scanf("%f%f%f",&a,&b,&c);
        delta = pow(b,2)-4*a*c;
        if (delta<0)
                printf("\nO numero nao possui raizes reais.");
        else
        {
                r1=(-b+sqrt(delta))/(2*a);
                r2=(-b-sqrt(delta))/(2*a);
                printf("\nAs raizes sao %f e %f.",r1,r2);
        }
        getch();


}

void calculadora(void)
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
}

void fatorial(void)
{
        int n,i,fat;
        printf("\nDigite um numero:");
        scanf("%d",&n);
        fat=1;
        for(i=1;i<=n;i++)
                fat*=i;
        printf("\nO fatorial de %d e igual a %d.",n,fat);
        getch();

}

void primos(void)
{
        int n,i,primo;
        primo=1;
        for(n=2;n<=200;n++)
        {
          for(i=2;i<n;i++)
           if ((n%i)==0)
               primo=0;
           if (primo==1)
               printf("%d,",n);
          primo=1;
        }

          getch();

}

void animacao(void)
{
        int cont,lin,col,infinito,fimtela,comtela;
        float tempo;
        tempo=50;
        infinito=1;
        lin=0;
        col=1;
        fimtela=0;
        comtela=1;
        cont=1;
        do
        {
                lin+=1;
                tempo-=5;
                if(tempo==0)
                        tempo=50;
                if(comtela==1)
                 for (col=1;col<=80;col++)
                 {
                        Sleep(tempo);
                        clrscr();
                        gotoxy(col,lin);
                        Sleep(tempo);
                        printf("\xDB");
                        if (col==80);
                        {
                                comtela=0;
                                fimtela=1;
                        }
                 }
                lin+=1;
                tempo-=5;
                if(tempo==0)
                        tempo=50;
                if(fimtela==1)
                 for(col=80;col>=1;col--)
                 {
                        Sleep(tempo);
                        clrscr();
                        gotoxy(col,lin);
                        Sleep(tempo);
                        printf("\xDB");
                        if(col==1)
                        {
                                comtela=1;
                                fimtela=0;
                        }
                 }
                 cont+=1;
                 if (cont==13)
                 {
                        lin=0;
                        col=1;
                        cont=1;
                 }
        }while(infinito=1);
        getch();
}

void main()
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
        printf("\n6:Animacao.");
        printf("\n7:SAIR");
        printf("\n");
        scanf("%d",&opcao);
        clrscr();

          switch(opcao)
          {
                case 1:
                par_impar();
                printf("\n");
                break;
                case 2:
                equacao();
                printf("\n");
                break;
                case 3:
                calculadora();
                printf("\n");
                break;
                case 4:
                fatorial();
                printf("\n");
                break;
                case 5:
                primos();
                printf("\n");
                break;
                case 6:
                animacao();
                printf("\n");
                break;
          }
        }while(opcao!=7);

}




