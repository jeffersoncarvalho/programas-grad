
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
int fibo(int n,int a,int b,int cont)
{
        int c;
        cont+=1;
        c=a+b;
        a=b;
        b=c;
        if (n==1)
                return 0;
        else
         if (n==2)
                return 1;
         else
                if(cont==n)
                        return c;
                else
                        return fibo(n,a,b,cont);
}
void main()
{
        int x,resp;
        printf("Digite a ordem do enesimo termo de fibonacci procurado: ");
        scanf("%d",&x);
        resp=fibo(x,0,1,2);
        printf("O %d termo de fibonacci e %d:",x,resp);
        getch();
}

