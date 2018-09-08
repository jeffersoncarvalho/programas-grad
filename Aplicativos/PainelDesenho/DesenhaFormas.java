import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class DesenhaFormas extends JFrame implements ActionListener
{
	private JPanel painelBotoes;
	private JLabel labelX1,labelX2,labelY1,labelY2,labelA,labelB;
	private JTextField campoX1,campoX2,campoY1,campoY2,campoA,campoB;
	private JButton botaoDesenha, botaoBox,botaoFuncao;
	private PainelDesenho meuPainel;

  public DesenhaFormas()
  {
	super("Desenha Formas");
	
	meuPainel=new PainelDesenho();
 	meuPainel.setBackground(Color.white);

	labelX1=new JLabel("X1");
	labelX2=new JLabel("X2");
	labelY1=new JLabel("Y1");
	labelY2=new JLabel("Y2");
	labelA=new JLabel("a");
	labelB=new JLabel("b");
	
	campoX1=new JTextField(4);
	campoX2=new JTextField(4);
	campoY1=new JTextField(4);
	campoY2=new JTextField(4);
	campoA=new JTextField(4);
	campoB=new JTextField(4);

	campoX1.setText("0");
	campoX2.setText("0");
	campoY1.setText("0");
	campoY2.setText("0");
	campoA.setText("0");
	campoB.setText("0");


	
	botaoDesenha=new JButton("Desenha");
	botaoDesenha.addActionListener(this);
	
	botaoBox=new JButton("Caixa");
	botaoBox.addActionListener(this);

	botaoFuncao=new JButton("Função");
	botaoFuncao.addActionListener(this);

	painelBotoes=new JPanel();
	painelBotoes.setLayout(new GridLayout(15,1));
	
	painelBotoes.add(labelX1);
	painelBotoes.add(campoX1);
	painelBotoes.add(labelX2);
	painelBotoes.add(campoX2);
	painelBotoes.add(labelY1);
	painelBotoes.add(campoY1);
	painelBotoes.add(labelY2);
	painelBotoes.add(campoY2);
	painelBotoes.add(labelA);
	painelBotoes.add(campoA);
	painelBotoes.add(labelB);
	painelBotoes.add(campoB);

	painelBotoes.add(botaoDesenha);
	painelBotoes.add(botaoBox);
	painelBotoes.add(botaoFuncao);

	Container c=getContentPane();
	c.add(meuPainel,BorderLayout.CENTER);
        c.add(painelBotoes,BorderLayout.WEST);

	setSize(500,400);
	show();
	
  }
  public void actionPerformed(ActionEvent e)
  {
	int tempX1=0,tempX2=0,tempY1=0,tempY2=0,tempa=0,tempb=0;
	int coef=0;
	
	tempX1=Integer.parseInt(campoX1.getText());
	tempX2=Integer.parseInt(campoX2.getText());
	tempY1=Integer.parseInt(campoY1.getText());
	tempY2=Integer.parseInt(campoY2.getText());   
	tempa=Integer.parseInt(campoA.getText());
        tempb=Integer.parseInt(campoB.getText());
		 

   if(e.getSource()==botaoDesenha)
   {
	
	meuPainel.setValores(tempX1,tempX2,tempY1,tempY2);

	if((Math.abs(tempX1)-Math.abs(tempX2))!=0)
	{
	 coef=(int)(tempY2-tempY1)/(tempX2-tempX1);
	 coef=Math.abs(coef);
	}

	if(tempX1==tempX2)	
		meuPainel.draw(PainelDesenho.LV);
	else
	 if(tempY1==tempY2)
		  meuPainel.draw(PainelDesenho.LH);
	 else
	   if(coef==1)
		    meuPainel.draw(PainelDesenho.LD);
    
   }
   else
	if(e.getSource()==botaoBox)
	{
		meuPainel.setValores(tempX1,tempX2,tempY1,tempY2);
		meuPainel.draw(PainelDesenho.BOX);
	}
	else
	{
		
		 meuPainel.setValoresFuncao(tempX1,tempX2,tempa,tempb);
		 meuPainel.draw(PainelDesenho.LQ);
	}
		  
  }//fim

  public static void main (String args[])
  {
	DesenhaFormas app=new DesenhaFormas();
	
	app.addWindowListener(
				new WindowAdapter()
				{
				   public void windowClosing(WindowEvent e)
				   { System.exit(0);}
				}
			);	
 } 

 }

  class PainelDesenho extends JPanel
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