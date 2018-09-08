
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
#include<vcl.h>
#include<math.h>

int a[25][80],x1,x2,y1,y2;
struct coordenada { int lin,col;};
struct fila { int topo; coordenada c[256];};

void consmatriz()
{
        int i,j;
        for(i=0;i<23;i++)
         for(j=0;j<80;j++)
          {
           a[i][j]=0;
           printf("%d",a[i][j]);
          }

}
void desenha()
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
                a[int(y)][int(x)]=1;
                gotoxy(x,y);
                printf("%d",a[int(y)][int(x)]);
                x=x+incx;
                y=y+incy;
        }
}

bool vazia (fila &f)
{
        return (f.topo==0)?true:false;
}

void enfileirar(fila &f, coordenada val)
{
        f.c[f.topo++]=val;
}

void desenfileirar(fila &f)
{

        for(int i=0;i<f.topo-1;i++)
                f.c[i]=f.c[i+1];
        --f.topo;

}

void recebe(fila &f,coordenada &val)
{
        val.lin=f.c[0].lin;
        val.col=f.c[0].col;
}

void testar(fila &f,coordenada &val)
{
        if(a[val.lin][val.col]==0)
        {
                a[val.lin][val.col]=1;
                gotoxy(val.col,val.lin);printf("1");
                enfileirar(f,val);
        }
        recebe(f,val);
}

void main()
{
        int i,n;
        coordenada val;
        fila f;
        consmatriz();

        gotoxy(1,24);
        printf("Qts vertices possui a figura: ");
        gotoxy(31,24);
        scanf("%d",&n);
        gotoxy(1,24);
        printf("                                       ");

        for(i=1;i<=n;i++)
        {               gotoxy(1,24);
                        printf("Vertice %d(x1,y1,x2,y2): ",i);
                        gotoxy(25,24);
                        scanf("%d%d%d%d",&x1,&y1,&x2,&y2);
                        gotoxy(1,24);
                        printf("                                               ");
                        desenha();
        }

        gotoxy(1,24);
        printf("Ponto inicial(col//lin): ");
        gotoxy(25,24);
        scanf("%d%d",&val.col,&val.lin);
        gotoxy(1,24);
        printf("                                ");

        enfileirar(f,val);

        while(vazia(f)==false)
        {
                val.lin-=1;
                testar(f,val);
                val.lin+=1;
                testar(f,val);
                val.col-=1;
                testar(f,val);
                val.col+=1;
                testar(f,val);

                desenfileirar(f);
        }

        gotoxy(1,24);
        printf("Checar ponto (col//lin): ");
        gotoxy(25,24);
        scanf("%d%d",&val.col,&val.lin);
        gotoxy(1,24);
        printf("                                ");
        
        gotoxy(val.col,val.lin);
        printf("P");

        if (a[val.lin][val.col]==1)
        {
                gotoxy(1,24);
                printf("O ponto esta na figura.");
        }
        else
        {
                gotoxy(1,24);
                printf("O ponto nao esta na figura.");
        }
        getch();
        gotoxy(1,24);
        printf("                                ");

        getch();
}

