
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<math.h>
#include<stdio.h>
#include<conio.h>
#include<vcl.h>
void desenha_linha(int x1,int y1,int x2,int y2)
{
        int i,dx,dy,npontos;
        float incx,incy,x,y;
        dx=(x2-x1);
        dy=(y2-y1);
        if (abs(dx)>abs(dy))
                npontos=abs(dx);
        else
                npontos=abs(dy);
        incx=(float)dx/npontos;
        incy=(float)dy/npontos;
        x=x1;y=y1;
        for(i=0;i<=npontos;i++)
        {
                gotoxy(x,y);
                Sleep(50);
                printf("\xDB");
                Sleep(50);
                x=x+incx;
                y=y+incy;
        }
}
void main()
{
        int a1,a2,b1,b2;
        printf("Digite as coordenadas(x,y) do primeiro ponto: ");
        scanf("%d%d",&a1,&b1);
        printf("\nDigite as coordenadas do segundo ponto: ");
        scanf("%d%d",&a2,&b2);
        clrscr();
        desenha_linha(a1,b1,a2,b2);
        getch();
}

