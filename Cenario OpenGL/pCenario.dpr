program pCenario;

uses
  Forms,
  UsimulaOGL in '..\Simulação OpenGL\UsimulaOGL.pas' {fMain};

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TfMain, fMain);
  Application.Run;
end.
