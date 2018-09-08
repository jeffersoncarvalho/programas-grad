unit uMain;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  OpenGL, GLU, ExtCtrls, StdCtrls;

type
  Matriz=array[1..50,1..50] of byte;
  TfMain = class(TForm)
    Timer1: TTimer;
    Panel1: TPanel;
    procedure FormCreate(Sender: TObject);
    procedure FormShow(Sender: TObject);
    procedure FormDestroy(Sender: TObject);
    procedure SetDCPixelFormat;
    procedure Timer1Timer(Sender: TObject);
    procedure FormKeyDown(Sender: TObject; var Key: Word;
      Shift: TShiftState);
   procedure alimentaMatCenario(var mat:Matriz; s:String);
   function criaStringCenario():String;
   procedure desenhaCenario(mat:Matriz);
   procedure desenhaChao();
  private
    { Private declarations }
  protected
    { Public declarations }
  public

  end;
var
  fMain: TfMain;
  DC: HDC;
  hrc: HGLRC;
  Angulo: GLfloat = 0;
  matrizCenario:Matriz;
  GlobalI,GlobalJ,andar,passos:integer;
  olhoX,olhoY,olhoZ,alvoX,alvoY,alvoZ:real;
  //---------------------


implementation

{$R *.DFM}

procedure TfMain.SetDCPixelFormat;
var
  nPixelFormat: Integer;
  pfd: TPixelFormatDescriptor;
begin
  with pfd do begin
    nSize     := sizeof(pfd);
    nVersion  := 1;
    dwFlags   := PFD_DRAW_TO_WINDOW or PFD_SUPPORT_OPENGL or PFD_DOUBLEBUFFER;
    iPixelType:= PFD_TYPE_RGBA;
    cColorBits:= 16;
    cDepthBits:= 32;
    iLayerType:= PFD_MAIN_PLANE;
  end;
  nPixelFormat := ChoosePixelFormat(DC, @pfd);
  SetPixelFormat(DC, nPixelFormat, @pfd);
end;

procedure TfMain.FormCreate(Sender: TObject);
begin
  //****************
        alimentaMatCenario(matrizCenario,criaStringCenario());
        olhoX:=2;
        olhoZ:=0;
        olhoY:=0;
        alvoX:=2;
        alvoY:=0;
        alvoZ:=-4;
        andar:=1;
        passos:=0 ;
  //****************

  DC := GetDC(panel1.Handle);
  SetDCPixelFormat;
  hrc := wglCreateContext(DC);
  wglMakeCurrent(DC, hrc);
  glEnable(GL_CULL_FACE);
  //glPolygonMode(GL_FRONT, GL_POINT);
  //glPolygonMode(GL_FRONT, GL_LINE);
  glPolygonMode(GL_FRONT, GL_FILL);
end;

procedure TfMain.FormShow(Sender: TObject);
var Aspect: GLdouble;
begin
  Aspect := Width / Height;
  glMatrixMode(GL_PROJECTION);
  glLoadIdentity;
  gluPerspective(30.0, Aspect, 1, 80);//o último atributo refere-se ao tamanho do universo
  glViewport(0, 0, Panel1.Width, Panel1.Height);
end;

procedure TfMain.FormDestroy(Sender: TObject);
begin
  Timer1.Enabled := False;
  wglMakeCurrent(0, 0);
  wglDeleteContext(hrc);
  ReleaseDC(Panel1.Handle, DC);
end;

procedure PutCube(rX, rY, rZ, rSize: GLFloat);
var rS2: GLFloat;
begin
  rS2 := rSize/2;
  {
    glVertex3f(rX-rS2, rY+rS2, rZ-rS2); // A
    glVertex3f(rX+rS2, rY+rS2, rZ-rS2); // B
    glVertex3f(rX+rS2, rY-rS2, rZ-rS2); // C
    glVertex3f(rX-rS2, rY-rS2, rZ-rS2); // D
    glVertex3f(rX-rS2, rY+rS2, rZ+rS2); // E
    glVertex3f(rX-rS2, rY-rS2, rZ+rS2); // F
    glVertex3f(rX+rS2, rY+rS2, rZ+rS2); // H
    glVertex3f(rX+rS2, rY-rS2, rZ+rS2); // G
  }

    //6
  glBegin(GL_QUADS);
     glVertex3f(rX-rS2, rY-rS2, rZ-rS2); // D
     glVertex3f(rX+rS2, rY-rS2, rZ-rS2); // C
     glVertex3f(rX+rS2, rY-rS2, rZ+rS2); // G
     glVertex3f(rX-rS2, rY-rS2, rZ+rS2); // F



  //  glVertex3f(rX-rS2, rY+rS2, rZ-rS2);
  glEnd;

  //5
  glBegin(GL_QUADS);
    glVertex3f(rX-rS2, rY+rS2, rZ-rS2); // A
    glVertex3f(rX-rS2, rY+rS2, rZ+rS2); // E
    glVertex3f(rX+rS2, rY+rS2, rZ+rS2); // H
    glVertex3f(rX+rS2, rY+rS2, rZ-rS2); // B
  //  glVertex3f(rX-rS2, rY+rS2, rZ-rS2);
  glEnd;

  //1
  glBegin(GL_QUADS);
    glVertex3f(rX-rS2, rY+rS2, rZ-rS2); // A
    glVertex3f(rX-rS2, rY-rS2, rZ-rS2); // D
    glVertex3f(rX-rS2, rY-rS2, rZ+rS2); // F
    glVertex3f(rX-rS2, rY+rS2, rZ+rS2); // E


  //  glVertex3f(rX-rS2, rY+rS2, rZ-rS2);
  glEnd;

  //2
  glBegin(GL_QUADS);
    glVertex3f(rX-rS2, rY+rS2, rZ-rS2); // A
    glVertex3f(rX+rS2, rY+rS2, rZ-rS2); // B
    glVertex3f(rX+rS2, rY-rS2, rZ-rS2); // C
    glVertex3f(rX-rS2, rY-rS2, rZ-rS2); // D
  //  glVertex3f(rX-rS2, rY+rS2, rZ-rS2);
  glEnd;

  //3
  glBegin(GL_QUADS);
    glVertex3f(rX+rS2, rY-rS2, rZ-rS2); // C
    glVertex3f(rX+rS2, rY+rS2, rZ-rS2); // B
    glVertex3f(rX+rS2, rY+rS2, rZ+rS2); // H
    glVertex3f(rX+rS2, rY-rS2, rZ+rS2); // G
   // glVertex3f(rX+rS2, rY-rS2, rZ-rS2);
  glEnd;

  //4
  glBegin(GL_QUADS);
    glVertex3f(rX-rS2, rY+rS2, rZ+rS2); // E
    glVertex3f(rX-rS2, rY-rS2, rZ+rS2); // F
    glVertex3f(rX+rS2, rY-rS2, rZ+rS2); // G
    glVertex3f(rX+rS2, rY+rS2, rZ+rS2); // H


   // glVertex3f(rX+rS2, rY-rS2, rZ-rS2);
  glEnd;




end;

procedure TfMain.Timer1Timer(Sender: TObject);
begin
  glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT);
  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity;
  glTranslatef(0, 0, -30);//onde eu coloco o cubo no universo
  GluLookAt(olhoX,olhoY,olhoZ,alvox,alvoY,alvoZ,0,1,0);
  //glRotatef(Angulo,0, 1, 0);
  glColor3f(1,0,0);
  desenhaChao();
  glColor3f(0,1,0);
  desenhaCenario(matrizCenario);
  SwapBuffers(DC);
end;

procedure TfMain.alimentaMatCenario(var mat:Matriz; s:String);
var i,j:integer;
begin
       for i:=1 to GlobalI  do
        for j:=1 to GlobalJ do
         mat[i,j]:=StrToInt(s[(i-1)*GlobalJ+j]);
end;

function TfMain.criaStringCenario():String;
var s:String;
begin
        //frente
        s:=  '111110011111';
        s:=s+'100000000001';
        s:=s+'100001111100';
        s:=s+'100001000101';
        s:=s+'100000000001';
        s:=s+'100110000001';
        s:=s+'100000000001';
        s:=s+'101111111101';
        GlobalI:=8;
        GlobalJ:=12;

           criaStringCenario:=s;
end;
procedure TfMain.desenhaCenario(mat:Matriz);
var i,j:integer;
begin

        for i:=1 to GlobalI do
         for j:=1 to GlobalJ do
          if(mat[i,j]=1) then putCube(j,0,-i,1);


end;


procedure TfMain.FormKeyDown(Sender: TObject; var Key: Word;
  Shift: TShiftState);
begin
  {if (Key=VK_UP)
     then Angulo := Angulo -5
  else
   if(Key=VK_DOWN)
     then Angulo := Angulo + 5;}

     passos:=passos mod 10;

     if((andar=1)and(passos=0))then
     begin
        olhoY:=olhoY+0.02;
        alvoY:=alvoY+0.02;
        andar:=-1;
     end
     else
     if((andar=-1)and(passos=5)) then
     begin
        olhoY:=olhoY-0.02;
        alvoY:=alvoY-0.02;
        andar:=1;
     end;


     if(Key=VK_UP)
      then
        begin
                passos:=passos+1;
                olhoZ:=olhoZ-0.1;
                alvoZ:=alvoZ-0.1;
        end
      else
      if(Key=VK_DOWN)
       then
        begin
                passos:=passos+1;
                olhoZ:=olhoZ+0.1;
                alvoZ:=alvoZ+0.1;
        end
      else
       if(Key=VK_LEFT)
        then
        begin
         olhoX:=olhoX-0.1;//angulo:=angulo+1.5
         alvoX:=alvoX-0.1;
        end
       else
        if(Key=VK_RIGHT)
         then
         begin
          olhoX:=olhoX+0.1;//angulo:=angulo-1.5;
          alvoX:=alvoX+0.1;
         end;


end;
procedure TFmain.desenhaChao();
begin
  glBegin(GL_QUADS);
    glVertex3f(-1, -0.5, 0); // E
    glVertex3f(13, -0.5, 0); // F
    glVertex3f(13, -0.5, -9); // G
    glVertex3f(-1,-0.5,-9); // H


   // glVertex3f(rX+rS2, rY-rS2, rZ-rS2);
  glEnd;
end;

end.


