import javax.swing.*;
import java.awt.*;

public class PainelDesenho extends JPanel
{
	private final int X=200,Y=180;
	public final static int LH=0,LV=1,LD=2,BOX=3,LQ=4;
	private int shape;
	private int x1,x2,y1,y2,a,b;
	
  public void setValores(int a1,int a2,int b1,int b2) 
  {
	a1=(a1>X)?X:a1;
	a2=(a2>X)?X:a2;
	b1=(b1>Y)?X:b1;
	b2=(b2>Y)?X:b2;
	
	x1=(a1>=-X)?a1:-X;
	x2=(a2>=-X)?a2:-X;
	y1=(b1>=-Y)?b1:-Y;
	y2=(b2>=-Y)?b2:-Y;

	
	
  }
  
  public void setValoresFuncao(int a1,int a2,int A, int B)
  {
	a1=(a1>X)?X:a1;
	a2=(a2>X)?X:a2;
	x1=(a1>=-X)?a1:-X;
	x2=(a2>=-X)?a2:-X;
	a=A;b=B;

  }

  public void linhaHorizontal(Graphics g)
  {
	if(x1<=x2)
	  for(int i=x1;i<=x2;i++)
 	  	g.drawString(".",X+i,Y-y1);
	else
	   for(int i=x2;i<=x1;i++)
 	  	g.drawString(".",X+i,Y-y1);
  }

  public void linhaVertical(Graphics g)
  {
	if(y1<=y2)
	  for(int i=y1;i<=y2;i++)
 	  	g.drawString(".",x1+X,Y-i);
	else
	   for(int i=y2;i<=y1;i++)
 	  	g.drawString(".",x1+X,Y-i); 
  }

  public void linhaDiagonal(Graphics g)
  {
	int i=x1,j=y1;
	for(int x=0;i!=x2;x++)
	{
		g.drawString(".",i+X,Y-j);
		if(i<x2)i++;else i--;
		if(j<y2)j++;else j--; 
	}
	g.drawString(".",i+X,Y-j);   
  }

  public void box(Graphics g)
  {
	//desenha primeira coluna
	if(y1<=y2)
	  for(int i=y1;i<=y2;i++)
 	  	g.drawString(".",x1+X,Y-i);
	else
	   for(int i=y2;i<=y1;i++)
 	  	g.drawString(".",x1+X,Y-i);

	//desenha segunda coluna
	if(y1<=y2)
	  for(int i=y1;i<=y2;i++)
 	  	g.drawString(".",x2+X,Y-i);
	else
	   for(int i=y2;i<=y1;i++)
 	  	g.drawString(".",x2+X,Y-i);
	
	//desenha primeira linha
	if(x1<=x2)
	  for(int i=x1;i<=x2;i++)
 	  	g.drawString(".",X+i,Y-y1);
	else
	   for(int i=x2;i<=x1;i++)
 	  	g.drawString(".",X+i,Y-y1);

	//desenha segunda linha
	if(x1<=x2)
	  for(int i=x1;i<=x2;i++)
 	  	g.drawString(".",X+i,Y-y2);
	else
	   for(int i=x2;i<=x1;i++)
 	  	g.drawString(".",X+i,Y-y2);
	 
  }

 /* public void funcao(Graphics g,float coef)
  {
	
	int y;
	if (coef<1)
	{
	      if(x1<=x2)
		for(int i=x1;i<=x2;i++)
		{
			y=a*i+b;
			g.drawString(".",i+X,Y-y);
		}
	      else
		for(int i=x1;i<=x2;i--)
		{
			y=a*i+b;
			g.drawString(".",i+X,Y-y);
		}		
	}
	else
	{
	      if(x1<=x2)
		for(int i=x1;i<=x2;i++)
		{
			y=a*i+b;
			g.drawString(".",i+X,Y-y)
		}
	      else
		for(int i=x1;i<=x2;i--)
		{
			y=a*i+b;
			g.drawString(".",i+X,Y-y)
		}		
	}
	
  }func(ainda em teste)*/

  public void linhaQualquer(Graphics g)
  {
	
	y1=a*x1+b;
	y2=a*x2+b;
        
	int i,dx,dy,npontos;
        float incx,incy,x,y;
        dx=(x2-x1);
        dy=(y2-y1);
        if (Math.abs(dx)>Math.abs(dy))
                npontos=Math.abs(dx);
        else
                npontos=Math.abs(dy);
        incx=(float)dx/npontos;
        incy=(float)dy/npontos;
        x=x1;y=y1;

        for(i=0;i<=npontos;i++)
        {
		g.drawString(".",(int)x+X,Y-(int)y);
                x=x+incx;
                y=y+incy;
        }

  }

  public void draw(int s)
  {
	shape=s;
        repaint();
  }
  
  public void desenhaCartesiano(Graphics g)
  {
	g.drawLine(X,0,X,400);
	g.drawLine(0,Y,500,Y);
  }


  public void paintComponent(Graphics g)
  {
	desenhaCartesiano(g);
	if(shape==LH)
        	linhaHorizontal(g);
	else
	  if(shape==LV)
		 linhaVertical(g);
	  else
	   if(shape==LD)
		  linhaDiagonal(g);
	   else
	    if(shape==BOX)
			box(g);
 		else
	      	  if(shape==LQ)
			  linhaQualquer(g);
  }

}
