
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
#include<vcl.h>
int main(int argc, char* argv[])
{
        int cont,lin,col,infinito,fimtela,comtela,x=1;
        float tempo;
        char car;
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
                        //clrscr();
                        gotoxy(col,lin);
                        Sleep(tempo);
                        if(x==1)
                           car='G';
                        if(x==2)
                           car='E';
                        if(x==3)
                           car='T';
                        if(x==4)
                           car='R';
                        if(x==5)
                           car='O';
                        if(x==6)
                        {
                                car=2;
                                x=0;
                        }
                        printf("%c",car);

                        if (col==80);
                        {
                                comtela=0;
                                fimtela=1;
                        }
                        x+=1;
                 }
                lin+=1;
                tempo-=5;
                if(tempo==0)
                        tempo=50;
                if(fimtela==1)
                 for(col=80;col>=1;col--)
                 {
                        Sleep(tempo);
                        //clrscr();
                        gotoxy(col,lin);
                        Sleep(tempo);
                        if(x==1)
                           car='O';
                        if(x==2)
                           car='R';
                        if(x==3)
                           car='T';
                        if(x==4)
                           car='E';
                        if(x==5)
                           car='G';
                        if(x==6)
                        {
                                car=2;
                                x=0;
                        }
                        printf("%c",car);
                        if(col==1)
                        {
                                comtela=1;
                                fimtela=0;
                        }
                        x+=1;
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
        return 0;
}
