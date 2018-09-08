program Redes;

uses
  Forms,
  Principal in 'Principal.pas' {FPrincipal},
  Menu in 'Menu.pas' {FConfiguracao},
  CRC in 'CRC.PAS';

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TFPrincipal, FPrincipal);
  Application.CreateForm(TFConfiguracao, FConfiguracao);
  Application.Run;
end.
