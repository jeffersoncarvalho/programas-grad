
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<math.h>
#include<stdio.h>
#include<conio.h>
#include<vcl.h>
#include<stdlib.h>
int y1,x1,y2,x2,troca;
void desenha_linha()
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
                randomize();

                printf("%c",random(100));
                Sleep(50);
                //clrscr();
                x=x+incx;
                y=y+incy;
        }
}
void func_troca()
{

                if(troca==0)
                {
                        y2=1;
                        randomize();
                        x2=random(79);
                        x2+=2;
                }
                if(troca==1)
                {
                        y2=25;
                        randomize();
                        x2=random(79);
                        x2+=2;
                }
                if(troca==2)
                {
                        x2=1;
                        randomize();
                        y2=random(24);
                        y2+=2;
                }
                if(troca==3)
                {
                        x2=80;
                        randomize();
                        y2=random(24);
                        y2+=2;
                }
}
void main(void)
{
        int inf=1;

                y1=15;
                x1=80;
                troca=0;
                do
                {

                        func_troca();
                        desenha_linha();
                        y1=y2;
                        x1=x2;
                        troca+=1;
                        if (troca>3)
                                troca=0;
                                                            



                   }while(inf==1);

}

