unit UPlotagem;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  ExtCtrls, StdCtrls, Buttons;

type
  TForm1 = class(TForm)
    Image1: TImage;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    EX1: TEdit;
    EY1: TEdit;
    EX2: TEdit;
    EY2: TEdit;
    BtnPlota: TButton;
    BtnBox: TButton;
    Bevel1: TBevel;
    Bevel2: TBevel;
    Label5: TLabel;
    Label6: TLabel;
    EdCentroX: TEdit;
    EdCentroY: TEdit;
    Label7: TLabel;
    EdRaio: TEdit;
    btnCircle: TButton;
    Label8: TLabel;
    Label9: TLabel;
    Bevel3: TBevel;
    Label10: TLabel;
    Label11: TLabel;
    Label12: TLabel;
    Label13: TLabel;
    EdElix: TEdit;
    EdEliy: TEdit;
    EdElia: TEdit;
    Label14: TLabel;
    EdElib: TEdit;
    btnEllipse: TButton;
    Bevel4: TBevel;
    Label15: TLabel;
    EdParA: TEdit;
    Label16: TLabel;
    EdParB: TEdit;
    btDesenhaGradeElipse: TButton;
    Label17: TLabel;
    btnLimpa: TButton;
    ShapeCor: TShape;
    BitBtn1: TBitBtn;
    Bevel6: TBevel;
    Bevel7: TBevel;
    Label18: TLabel;
    Label19: TLabel;
    EdPula: TEdit;
    EdPinta: TEdit;
    checkTraco: TCheckBox;
    Label20: TLabel;
    CheckPree: TCheckBox;

    procedure desenhaPonto(x1:integer;y1:integer);
    procedure desenhaLinha(x1:integer;y1:integer;x2:integer;y2:integer);
    procedure desenhaColuna(x1:integer;y1:integer;x2:integer;y2:integer);
    procedure desenhaDiagonal(x1:integer;y1:integer;x2:integer;y2:integer);
    procedure desenhaBox(x1:integer;y1:integer;x2:integer;y2:integer);
    procedure retaQualquer(x1:integer;y1:integer;x2:integer;y2:integer);
    procedure validaCoordenadas(var x1:integer;var y1:integer;var x2:integer;var y2:integer);
    procedure desenhaCircle(Centrox:integer;Centroy:integer;raio:integer);
    procedure desenhaElipse(Centrox:integer;Centroy:integer;a:integer;b:integer);
    procedure limpaTela();
    procedure selecionaCor();
    procedure alimentaVetTracejado();
    procedure preencheBox(a1:integer;b1:integer;a2:integer;b2:integer);
    procedure preencheCurvas(a:integer;b:integer);

    procedure BtnPlotaClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure BtnBoxClick(Sender: TObject);
    procedure btnCircleClick(Sender: TObject);
    procedure btnEllipseClick(Sender: TObject);
    procedure btDesenhaGradeElipseClick(Sender: TObject);
    procedure btnLimpaClick(Sender: TObject);
    procedure BitBtn1Click(Sender: TObject);
    procedure checkTracoClick(Sender: TObject);
    procedure CheckPreeClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;
  corMundial:TColor;
  incCor,tamVetTraco:integer;
  vetTraco:array[1..20] of byte;
  Tracejado,Preenchido:boolean;
  const X:integer=250;
  const Y:integer=150;

implementation

{$R *.DFM}

procedure TForm1.desenhaPonto(x1:integer;y1:integer);
begin
        Image1.Canvas.Pixels[X+x1,Y-y1]:=corMundial;
end;

procedure TForm1.desenhaLinha(x1:integer;y1:integer;x2:integer;y2:integer);
var i,cont:integer;
begin
cont:=0;
{não apague!!}
  if((x1=x2)and(y1=y2)) then
   desenhaPonto(x1,y1)
  else
  {se não é ponto}
   begin
    if(Tracejado=True) then
      if ( x1<=x2 ) then
        begin
          for i := x1 to x2 do
           begin
                if(vetTraco[cont mod (tamVetTraco-1)]=1) then
                        Image1.Canvas.Pixels[i+X,Y-y1] := corMundial;
                cont:=cont+1;
           end;
        end
      else
        begin
          for i := x2 to x1 do
           begin
                if(vetTraco[cont mod (tamVetTraco-1)]=1) then
                        Image1.Canvas.Pixels[i+X,Y-y1] := corMundial;
                cont:=cont+1;
           end;
        end
       {se eu não quero tracejado...}
    else
       if ( x1<=x2 ) then
           for i := x1 to x2 do
            Image1.Canvas.Pixels[i+X,Y-y1] := corMundial
       else
           for i := x2 to x1 do
            Image1.Canvas.Pixels[i+X,Y-y1] := corMundial;
   end;

end;

procedure TForm1.desenhaColuna(x1:integer;y1:integer;x2:integer;y2:integer);
var i,cont:integer;
begin
    cont:=0;
    if((x1=x2)and(y1=y2)) then
     desenhaPonto(x1,y1)
    else
     begin
     {se eu quero tracejado}
      if(tracejado=True) then
       if ( y1<=y2 ) then
          for i := y1 to y2 do
           begin
            if(vetTraco[cont mod (tamVetTraco-1)]=1) then
                Image1.Canvas.Pixels[x1+X,Y-i] := corMundial;
             cont:=cont+1;
           end
       else
          for i := y2 to y1 do
           begin
            if(vetTraco[cont mod (tamVetTraco-1)]=1) then
                Image1.Canvas.Pixels[x1+X,Y-i] := corMundial;
             cont:=cont+1;
           end
       else
      {se eu não quero tracejado}
        if ( y1<=y2 ) then
           for i := y1 to y2 do
             Image1.Canvas.Pixels[x1+X,Y-i] := corMundial
        else
           for i := y2 to y1 do
             Image1.Canvas.Pixels[x1+X,Y-i] := corMundial;
     end;

end;

procedure TForm1.desenhaDiagonal(x1:integer;y1:integer;x2:integer;y2:integer);
var i,j,cont:integer;
begin
  cont:=0;

  if((x1=x2)and(y1=y2)) then
   desenhaPonto(x1,y1)
  else
   begin
   {se eu quero tracejado}
    if(tracejado=True) then
     begin
       i:=x1;
       j:=y1;
       repeat
          if(vetTraco[cont mod (tamVetTraco-1)]=1) then
                Image1.Canvas.Pixels[i+X,Y-j]:=corMundial;
          cont:=cont+1;

          if(i<x2) then
            i:=i+1
          else
            i:=i-1;
          if(j<y2) then
            j:=j+1
          else
            j:=j-1
       until(i=x2);
     end
    {se eu não quero tracejado}
    else
     begin
         i:=x1;
         j:=y1;
       repeat
          Image1.Canvas.Pixels[i+X,Y-j]:=corMundial;
          if(i<x2) then
            i:=i+1
          else
            i:=i-1;
          if(j<y2) then
            j:=j+1
          else
            j:=j-1
       until(i=x2);
     end;
   end;{else primeiro}

end;

procedure TForm1.desenhaBox(x1:integer;y1:integer;x2:integer;y2:integer);
var i,cont:integer;
begin
   cont:=0;

   if((x1=x2) and (y1=y2)) then
    desenhaPonto(x1,y1)
   else
   {se eu quero tracejado}
    begin
    if(tracejado=True) then
     begin
        {desenha primeira coluna}
	if(y1<=y2) then
	  for i:=y1 to y2 do
          begin
           if(vetTraco[cont mod (tamVetTraco-1)]=1) then

 	  	Image1.Canvas.Pixels[x1+X,Y-i]:=corMundial;
                cont:=cont+1;
           end
	else
	   for i:=y2 to y1 do
           begin
            if(vetTraco[cont mod (tamVetTraco-1)]=1) then

 	  	Image1.Canvas.Pixels[x1+X,Y-i]:=corMundial;
                cont:=cont+1;
            end;

             cont:=0;
	{desenha segunda coluna}
	if(y1<=y2) then
	  for i:=y1 to y2 do
          begin
           if(vetTraco[cont mod (tamVetTraco-1)]=1) then
 	  	Image1.Canvas.Pixels[x2+X,Y-i]:=corMundial  ;
                cont:=cont+1;
           end
	else
	   for i:=y2 to y1 do
            begin
             if(vetTraco[cont mod (tamVetTraco-1)]=1) then
 	  	Image1.Canvas.Pixels[x2+X,Y-i]:=corMundial;
                cont:=cont+1;
            end;

         cont:=0;
	{desenha primeira linha}
	if(x1<=x2) then
	  for i:=x1 to x2 do
           begin
            if(vetTraco[cont mod (tamVetTraco-1)]=1) then
 	  	Image1.Canvas.Pixels[X+i,Y-y1]:=corMundial;
                cont:=cont+1 ;
           end
	else
	   for i:=x2 to x1 do
           begin
            if(vetTraco[cont mod (tamVetTraco-1)]=1) then
 	  	Image1.Canvas.Pixels[X+i,Y-y1]:=corMundial;
                cont:=cont+1;
           end;
            cont:=0;
	{desenha segunda linha}
	if(x1<=x2) then
	  for i:=x1 to x2 do
           begin
            if(vetTraco[cont mod (tamVetTraco-1)]=1) then
 	  	Image1.Canvas.Pixels[X+i,Y-y2]:=corMundial;
                cont:=cont+1;
            end
	else
	   for i:=x2 to x1 do
            begin
             if(vetTraco[cont mod (tamVetTraco-1)]=1) then
 	  	Image1.Canvas.Pixels[X+i,Y-y2]:=corMundial;
                cont:=cont+1;
             end;
     end
    else
    {seu não quero tracejado}
     begin
        {desenha primeira coluna}
	if(y1<=y2) then
	  for i:=y1 to y2 do
 	  	Image1.Canvas.Pixels[x1+X,Y-i]:=corMundial
	else
	   for i:=y2 to y1 do
 	  	Image1.Canvas.Pixels[x1+X,Y-i]:=corMundial;

	{desenha segunda coluna}
	if(y1<=y2) then
	  for i:=y1 to y2 do
 	  	Image1.Canvas.Pixels[x2+X,Y-i]:=corMundial
	else
	   for i:=y2 to y1 do
 	  	Image1.Canvas.Pixels[x2+X,Y-i]:=corMundial;

	{desenha primeira linha}
	if(x1<=x2) then
	  for i:=x1 to x2 do
 	  	Image1.Canvas.Pixels[X+i,Y-y1]:=corMundial
	else
	   for i:=x2 to x1 do
 	  	Image1.Canvas.Pixels[X+i,Y-y1]:=corMundial;

	{desenha segunda linha}
	if(x1<=x2) then
	  for i:=x1 to x2 do
 	  	Image1.Canvas.Pixels[X+i,Y-y2]:=corMundial
	else
	   for i:=x2 to x1 do
 	  	Image1.Canvas.Pixels[X+i,Y-y2]:=corMundial;
     end;
    end;{else}
end;

procedure TForm1.retaQualquer(x1:integer;y1:integer;x2:integer;y2:integer);
var i,dx,dy,npontos,tempx,tempy,cont:integer;
    incx,incy,xReal,yReal:real;
begin
      cont:=0;

      if((x1=x2)and(y1=y2)) then
        desenhaPonto(x1,y1)
      else
       begin
        if(Tracejado=True) then
         begin
                        dx:=(x2-x1);
                        dy:=(y2-y1);
                        if (abs(dx)>abs(dy)) then
                                npontos:=abs(dx)
                        else
                                npontos:=abs(dy);
                        incx:=dx/npontos;
                        incy:=dy/npontos;
                        xReal:=x1;yReal:=y1;

                for i:=0 to npontos do
                begin
                        tempx:=Trunc(xReal);
                        tempy:=Trunc(yReal);
                        if(vetTraco[cont mod (tamVetTraco-1)]=1) then
                                Image1.Canvas.Pixels[X+tempx,Y-tempy]:=corMundial;
                        cont:=cont+1;
                        xReal:=xReal+incx;
                        yReal:=yReal+incy;
                end;

         end
         {se eu não quero tracejado}
        else
         begin

                        dx:=(x2-x1);
                        dy:=(y2-y1);
                        if (abs(dx)>abs(dy)) then
                                npontos:=abs(dx)
                        else
                                npontos:=abs(dy);
                        incx:=dx/npontos;
                        incy:=dy/npontos;
                        xReal:=x1;yReal:=y1;

                for i:=0 to npontos do
                begin
                        tempx:=Trunc(xReal);
                        tempy:=Trunc(yReal);
                        Image1.Canvas.Pixels[X+tempx,Y-tempy]:=corMundial;
                        xReal:=xReal+incx;
                        yReal:=yReal+incy;
                end;

         end;
       end;{primeiro}

end;

procedure TForm1.validaCoordenadas(var x1:integer;var y1:integer;var x2:integer;var y2:integer);
begin
        if(x1>250) then
         x1:=250;
        if(x1<-250) then
         x1:=-250;

        if(x2>250) then
         x2:=250;
        if(x2<-250) then
         x2:=-250;

        if(y1>150) then
         y1:=150;
        if(y1<-150) then
         y1:=-150;

        if(y2>150) then
         y2:=150;
        if(y1<-150) then
         y2:=-150;



end;

procedure TForm1.desenhaCircle(Centrox:integer;Centroy:integer;raio:integer);
var yCircle:real;
    xCircle,cont:integer;
begin
        cont:=0;

        if(raio<0) then
         raio:=0;
        if(raio>X) then
         raio:=X;

        if(raio=0) then
         desenhaPonto(Centrox,Centroy)
        else
         if(tracejado=True) then
          begin
                for xCircle:=0 to Trunc(raio/sqrt(2)) do
                begin
                 yCircle:=sqrt( raio*raio - sqr(xCircle));

                     if(vetTraco[cont mod (tamVetTraco-1)]=1) then
                       begin
                        Image1.Canvas.Pixels[X+xCircle+Centrox,Y-Trunc(yCircle)-Centroy]:=corMundial;
                        Image1.Canvas.Pixels[X+Trunc(yCircle)+Centrox,Y-xCircle-Centroy]:=corMundial;
                        Image1.Canvas.Pixels[X+(-xCircle)+Centrox,Y-Trunc(yCircle)-Centroy]:=corMundial;
                        Image1.Canvas.Pixels[X+Trunc(-yCircle)+Centrox,Y-xCircle-Centroy]:=corMundial;

                        Image1.Canvas.Pixels[X+xCircle+Centrox,Y-Trunc(-yCircle)-Centroy]:=corMundial;
                        Image1.Canvas.Pixels[X+Trunc(yCircle)+Centrox,Y-(-xCircle)-Centroy]:=corMundial;
                        Image1.Canvas.Pixels[X+(-xCircle)+Centrox,Y-Trunc(-yCircle)-Centroy]:=corMundial;
                        Image1.Canvas.Pixels[X+Trunc(-yCircle)+Centrox,Y-(-xCircle)-Centroy]:=corMundial;
                       end;
                     cont:=cont+1;
                 end;

          end
         else
          begin
                for xCircle:=0 to Trunc(raio/sqrt(2)) do
                begin
                 yCircle:=sqrt( raio*raio - sqr(xCircle));



                 Image1.Canvas.Pixels[X+xCircle+Centrox,Y-Trunc(yCircle)-Centroy]:=corMundial;
                 Image1.Canvas.Pixels[X+Trunc(yCircle)+Centrox,Y-xCircle-Centroy]:=corMundial;
                 Image1.Canvas.Pixels[X+(-xCircle)+Centrox,Y-Trunc(yCircle)-Centroy]:=corMundial;
                 Image1.Canvas.Pixels[X+Trunc(-yCircle)+Centrox,Y-xCircle-Centroy]:=corMundial;

                 Image1.Canvas.Pixels[X+xCircle+Centrox,Y-Trunc(-yCircle)-Centroy]:=corMundial;
                 Image1.Canvas.Pixels[X+Trunc(yCircle)+Centrox,Y-(-xCircle)-Centroy]:=corMundial;
                 Image1.Canvas.Pixels[X+(-xCircle)+Centrox,Y-Trunc(-yCircle)-Centroy]:=corMundial;
                 Image1.Canvas.Pixels[X+Trunc(-yCircle)+Centrox,Y-(-xCircle)-Centroy]:=corMundial;

                end;

          end;{tracejado}


end;




procedure TForm1.desenhaElipse(Centrox:integer;Centroy:integer;a:integer;b:integer);
var xEllipse,yEllipseInt,cont1,cont2:integer;
    yEllipse:real;
begin
        cont1:=0 ;
        cont2:=0;

   if((a=0)and (b=0)) then
    desenhaPonto(Centrox,Centroy)
   else
    if(b=0) then
     desenhaLinha(Centrox+a,Centroy,Centrox-a,Centroy)
    else
      if(a=0) then
        desenhaColuna(Centrox,Centroy+b,Centrox,Centroy-b)
      else
       if(a=b) then
         desenhaCircle(Centrox,Centroy,a)
       else
        begin{inicio de todas as elipses}
         if(tracejado=True) then
          begin
                if(a>b) then{Elipse Deitada}
         begin
           for xEllipse:=0 to a do
            begin
                yEllipse:=b*sqrt(1-(xEllipse*xEllipse)/(a*a));

                if(vetTraco[cont1 mod (tamVetTraco-1)]=1) then
                begin
                Image1.Canvas.Pixels[X+xEllipse+Centrox,Y-Trunc(yEllipse)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-Trunc(yEllipse)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-Trunc(-yEllipse)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(xEllipse)+Centrox,Y-Trunc(-yEllipse)-Centroy]:=corMundial;
                end;
                cont1:=cont1+1;
            end;
            {o próximo for aumenta a definição da elipse}
            for yEllipseInt:=0 to b do
            begin
                xEllipse:=Trunc(a*sqrt(1-(yEllipseInt*yEllipseInt)/(b*b)));

                if(vetTraco[cont2 mod (tamVetTraco-1)]=1) then
                begin
                Image1.Canvas.Pixels[X+xEllipse+Centrox,Y-yEllipseInt-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-yEllipseInt-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-(-yEllipseInt)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(xEllipse)+Centrox,Y-(-yEllipseInt)-Centroy]:=corMundial;
                end;
                cont2:=cont2+1;
            end;
         end{fim da elipse deitada}

         else{elipse em pé}
         begin
           for yEllipseInt:=0 to b do
            begin
                xEllipse:=Trunc(a*sqrt(1-(yEllipseInt*yEllipseInt)/(b*b)));

                if(vetTraco[cont1 mod (tamVetTraco-1)]=1) then
                begin
                Image1.Canvas.Pixels[X+xEllipse+Centrox,Y-yEllipseInt-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-yEllipseInt-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-(-yEllipseInt)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(xEllipse)+Centrox,Y-(-yEllipseInt)-Centroy]:=corMundial;
                end;
                cont1:=cont1+1;
            end;
            {o próximo for aumenta a definição da elipse}
            for xEllipse:=0 to a do
            begin
                yEllipse:=b*sqrt(1-(xEllipse*xEllipse)/(a*a));

                if(vetTraco[cont2 mod (tamVetTraco-1)]=1) then
                begin
                Image1.Canvas.Pixels[X+xEllipse+Centrox,Y-Trunc(yEllipse)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-Trunc(yEllipse)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-Trunc(-yEllipse)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(xEllipse)+Centrox,Y-Trunc(-yEllipse)-Centroy]:=corMundial;
                end;
                cont2:=cont2+1;
            end;
         end;{fim da elipse em pé}

          end{do tracejado}

         else
          begin{não tracejado}
                if(a>b) then{Elipse Deitada}
         begin
           for xEllipse:=0 to a do
            begin
                yEllipse:=b*sqrt(1-(xEllipse*xEllipse)/(a*a));

                Image1.Canvas.Pixels[X+xEllipse+Centrox,Y-Trunc(yEllipse)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-Trunc(yEllipse)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-Trunc(-yEllipse)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(xEllipse)+Centrox,Y-Trunc(-yEllipse)-Centroy]:=corMundial;

            end;
            {o próximo for aumenta a definição da elipse}
            for yEllipseInt:=0 to b do
            begin
                xEllipse:=Trunc(a*sqrt(1-(yEllipseInt*yEllipseInt)/(b*b)));

                Image1.Canvas.Pixels[X+xEllipse+Centrox,Y-yEllipseInt-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-yEllipseInt-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-(-yEllipseInt)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(xEllipse)+Centrox,Y-(-yEllipseInt)-Centroy]:=corMundial;
            end;
         end{fim da elipse deitada}

         else{elipse em pé}
         begin
           for yEllipseInt:=0 to b do
            begin
                xEllipse:=Trunc(a*sqrt(1-(yEllipseInt*yEllipseInt)/(b*b)));

                Image1.Canvas.Pixels[X+xEllipse+Centrox,Y-yEllipseInt-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-yEllipseInt-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-(-yEllipseInt)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(xEllipse)+Centrox,Y-(-yEllipseInt)-Centroy]:=corMundial;
            end;
            {o próximo for aumenta a definição da elipse}
            for xEllipse:=0 to a do
            begin
                yEllipse:=b*sqrt(1-(xEllipse*xEllipse)/(a*a));

                Image1.Canvas.Pixels[X+xEllipse+Centrox,Y-Trunc(yEllipse)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-Trunc(yEllipse)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(-xEllipse)+Centrox,Y-Trunc(-yEllipse)-Centroy]:=corMundial;
                Image1.Canvas.Pixels[X+(xEllipse)+Centrox,Y-Trunc(-yEllipse)-Centroy]:=corMundial;

            end;
         end;{fim da elipse em pé}

          end;{do não tracejado}
        end;{fim das elipses ufa,que programa mais devasso!!}



end;

procedure TForm1.limpaTela();
var 
    corTemp:TColor;
    tracoTemp:boolean;
begin


        Image1.Canvas.Rectangle(Image1.Canvas.ClipRect);
        corTemp:=corMundial;
        corMundial:=clTeal;
        tracoTemp:=Tracejado;
        Tracejado:=False;
        desenhaLinha(-250,0,250,0);
        desenhaColuna(0,150,0,-150);
        tracejado:=tracoTemp;
        corMundial:=corTemp;


end;

procedure TForm1.selecionaCor();
begin


                if(incCor=1) then
                begin
                        CorMundial:=ClBlack;
                        ShapeCor.Brush.Color:=CorMundial;
                end
                else
                if(incCor=2) then
                begin
                        CorMundial:=ClBlue;
                        ShapeCor.Brush.Color:=CorMundial;
                end
                else
                if(incCor=3) then
                begin
                        CorMundial:=ClTeal;
                        ShapeCor.Brush.Color:=CorMundial;
                end
                else
                if(incCor=4) then
                begin
                        CorMundial:=ClPurple;
                        ShapeCor.Brush.Color:=CorMundial;
                end
                else
                if(incCor=5) then
                begin
                        CorMundial:=ClGreen;
                        ShapeCor.Brush.Color:=CorMundial;
                end
                else
                if(incCor=6) then
                begin
                        CorMundial:=ClYellow;
                        ShapeCor.Brush.Color:=CorMundial;
                end
                else
                if(incCor=7) then
                begin
                        CorMundial:=ClWhite;
                        ShapeCor.Brush.Color:=CorMundial;
                end
                else
                begin
                         CorMundial:=ClOlive;
                         ShapeCor.Brush.Color:=CorMundial;
                         incCor:=0;
                end;

end;

procedure TForm1.alimentaVetTracejado();
var i,pinta,pula:integer;

begin
        pinta:=StrToInt(edPinta.Text);
        pula:=StrToInt(edPula.Text);

        for i:=0 to pinta-1 do
                vetTraco[i]:=1;
         for i:=pinta to (pinta+pula)-1 do
                vetTraco[i]:=0;
         tamVetTraco:=pinta+pula;
end;

procedure TForm1.preencheBox(a1:integer;b1:integer;a2:integer;b2:integer);
var i,j,menorb,maiorb,menora,maiora:integer;
begin
        if(a1<=a2) then
         begin
                menora:=a1;
                maiora:=a2;
         end
        else
         begin
                maiora:=a1;
                menora:=a2;
         end;

         if(b1<=b2) then
         begin
                menorb:=b1;
                maiorb:=b2;
         end
        else
         begin
                maiorb:=b1;
                menorb:=b2;
         end;

        for i:= menorb to maiorb do
         for j:=menora to maiora do
                Image1.Canvas.Pixels[X+j,Y-i]:=corMundial;

end;

procedure TForm1.preencheCurvas(a:integer;b:integer);
begin
        if(Image1.Canvas.Pixels[X+a,Y-b]<>corMundial) then
        begin
                Image1.Canvas.Pixels[X+a,Y-b]:=corMundial;
                preencheCurvas(a+1,b);
                preencheCurvas(a-1,b);
                preencheCurvas(a,b+1);
                preencheCurvas(a,b-1);
        end;
end;


{Eventos}
procedure TForm1.BtnPlotaClick(Sender: TObject);
var x1,x2,y1,y2:integer;
var coef:real;
begin
        coef:=0;
        x1:=StrToInt(EX1.Text);
        x2:=StrToInt(EX2.Text);
        y1:=StrToInt(EY1.Text);
        y2:=StrToInt(EY2.Text);

        validaCoordenadas(x1,y1,x2,y2);

        {testa validade do coef}
         if((x1-x2)<>0) then
          begin
           coef := ((y1-y2)/(x1-x2));
           coef:=abs(coef);
          end;

        if (y1=y2) then
                desenhaLinha(x1,y1,x2,y2)
        else
             if (x1=x2) then
                 desenhaColuna(x1,y1,x2,y2)
             else
               if(coef=1) then
                  desenhaDiagonal(x1,y1,x2,y2)
               else
                    retaQualquer(x1,y1,x2,y2);

end;

procedure TForm1.FormCreate(Sender: TObject);
begin
        Image1.Canvas.Rectangle(Image1.Canvas.clipRect);
        EX2.Text:='0';
        EY1.Text:='0';
        EY2.Text:='0';
        EX1.Text:='0';
        EdCentrox.Text:='0';
        EdCentroy.Text:='0';
        EdRaio.Text:='0';
        EdElia.Text:='0';
        EdElib.Text:='0';
        EdElix.Text:='0';
        EdEliy.Text:='0';
        EdParA.Text:='5';
        EdParB.Text:='5';
        EdPula.Text:='5';
        EdPinta.Text:='5';
        incCor:=1;
        Tracejado:=False;
        Preenchido:=False;
        corMundial:=clTeal;
        desenhaLinha(-250,0,250,0);
        desenhaColuna(0,150,0,-150);
        corMundial:=clBlack;
end;

procedure TForm1.BtnBoxClick(Sender: TObject);
var x1,x2,y1,y2:integer;
begin
        x1:=StrToInt(EX1.Text);
        x2:=StrToInt(EX2.Text);
        y1:=StrToInt(EY1.Text);
        y2:=StrToInt(EY2.Text);
        if(preenchido=False) then
                desenhaBox(x1,y1,x2,y2)
        else
                preencheBox(x1,y1,x2,y2);
end;

procedure TForm1.btnCircleClick(Sender: TObject);
begin

     desenhaCircle(StrToInt(EdCentroX.Text),StrToInt(EdCentroY.Text),StrToInt(EdRaio.Text));
   if(preenchido=True) then
     preencheCurvas(StrToInt(EdCentroX.Text),StrToInt(EdCentroY.Text));


end;

procedure TForm1.btnEllipseClick(Sender: TObject);
var ValA,ValB:integer;
begin
        valA:=StrToInt(EdElia.Text);
        valB:=StrToInt(EdElib.Text);

        if(valA>X) then
         valA:=X;
        if(ValA<0) then
         valA:=0;
        if(valB>Y) then
         valB:=Y;
        if(ValB<0) then
         valB:=0;

        desenhaElipse(StrToInt(EdElix.Text),StrToInt(EdEliy.Text),valA,valB);
        if(preenchido=True) then
                preencheCurvas(StrToInt(EdElix.Text),StrToInt(EdEliy.Text));
end;

procedure TForm1.btDesenhaGradeElipseClick(Sender: TObject);
var parB,ParA,valA,valB,tempValA:integer;
begin
        parA:=StrToInt(EdParA.Text);
        parB:=StrToInt(EdParB.Text);
        valA:=StrToInt(EdElia.Text);
        valB:=StrToInt(EdElib.Text);


        if(valA>X) then
         valA:=X;
        if(ValA<0) then
         valA:=0;
        if(valB>Y) then
         valB:=Y;
        if(ValB<0) then
         valB:=0;

        {teste de validade}
        if(parA<=0) then
         parA:=1;
        if(parB<=0) then
         parB:=1;
        if(parA>valA) then
         parA:=valA;
        if(parB>ValB) then
         parB:=valB;
        {fim do teste}

         TempValA:=ValA;

        {desenha elipses em pé}
        repeat
         desenhaElipse(StrToInt(EdElix.Text),StrToInt(EdEliy.Text),valA,valB);
         valA:=vaLA-parA;
        until(valA<0);
        {desenha elipses deitadas}
        repeat
         valA:=tempValA;
         desenhaElipse(StrToInt(EdElix.Text),StrToInt(EdEliy.Text),valA,valB);
         valB:=vaLB-parB;
        until(valB<0);

end;

procedure TForm1.btnLimpaClick(Sender: TObject);
begin
        limpaTela();
end;

procedure TForm1.BitBtn1Click(Sender: TObject);
begin
          incCor:=incCor+1;
           selecionaCor()

end;


procedure TForm1.checkTracoClick(Sender: TObject);
begin
   if(checkTraco.State=cbChecked) then
    begin
         Tracejado:=True;
         alimentaVetTracejado();
         EdPula.Enabled:=False;
         EdPinta.Enabled:=False;
    end
   else
    begin
        EdPula.Enabled:=True;
        EdPinta.Enabled:=True;
        Tracejado:=False;
    end;

end;

procedure TForm1.CheckPreeClick(Sender: TObject);
begin
 if(checkPree.State=cbChecked) then
    begin
         Preenchido:=True;
         Tracejado:=False;
         checkTraco.State:=cbUnchecked;
         checkTraco.Enabled:=false;

    end
   else
    begin
        checkTraco.Enabled:=True;
        Preenchido:=False;
    end;
end;

end.
