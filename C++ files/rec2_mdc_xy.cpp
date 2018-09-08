
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
int mdc(int x,int y)
{
        int r;
        r=x%y;
        x=y;
        y=r;
        if(r==0)
                return x;
        else
                return mdc(x,y);
}
void main()
{
        int resp,a,b;
        printf("Digite 2 numeros inteiros, sendo o primeiro maior que o segundo: ");
        scanf("%d%d",&a,&b);
        resp=mdc(a,b);
        printf("O mdc destes numeros e %d: ",resp);
        getch();
}
