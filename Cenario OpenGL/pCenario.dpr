program pCenario;

uses
  Forms,
  UsimulaOGL in '..\Simula��o OpenGL\UsimulaOGL.pas' {fMain};

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TfMain, fMain);
  Application.Run;
end.
