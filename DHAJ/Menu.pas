unit Menu;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, VSSComm32;

type
  TFConfiguracao = class(TForm)
    Label1: TLabel;
    CbPorta: TComboBox;
    Label2: TLabel;
    CbTaxa: TComboBox;
    Label3: TLabel;
    CbStop: TComboBox;
    Label4: TLabel;
    CbParidade: TComboBox;
    btOK: TButton;
    btCancelar: TButton;
    CbPalavra: TComboBox;
    Label5: TLabel;
    Label6: TLabel;
    EdADados: TEdit;
    procedure btOKClick(Sender: TObject);
    procedure btCancelarClick(Sender: TObject);
    procedure FormShow(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FConfiguracao: TFConfiguracao;

implementation

uses Principal;

{$R *.dfm}

procedure TFConfiguracao.btOKClick(Sender: TObject);

begin

  FPrincipal.VSSComm321.StopComm;
  // Configurção da porta
  case cbPorta.ItemIndex of
    0: FPrincipal.VSSComm321.CommPort:=Com1;
    1: FPrincipal.VSSComm321.CommPort:=Com2;
    2: FPrincipal.VSSComm321.CommPort:=Com3;
    3: FPrincipal.VSSComm321.CommPort:=Com4;
  end;
  // Configuração da velocidade
  case cbTaxa.ItemIndex of
    0: FPrincipal.VSSComm321.BaudRate:=____110;
    1: FPrincipal.VSSComm321.BaudRate:=____300;
    2: FPrincipal.VSSComm321.BaudRate:=____600;
    3: FPrincipal.VSSComm321.BaudRate:=___1200;
    4: FPrincipal.VSSComm321.BaudRate:=___2400;
    5: FPrincipal.VSSComm321.BaudRate:=___4800;
    6: FPrincipal.VSSComm321.BaudRate:=___9600;
    7: FPrincipal.VSSComm321.BaudRate:=__14400;
    8: FPrincipal.VSSComm321.BaudRate:=__19200;
    9: FPrincipal.VSSComm321.BaudRate:=__38400;
    10: FPrincipal.VSSComm321.BaudRate:=__56000;
    11: FPrincipal.VSSComm321.BaudRate:=_128000;
    12: FPrincipal.VSSComm321.BaudRate:=_256000;
  end;
  // Configuração do StopBit.
  case cbStop.ItemIndex of
    0: FPrincipal.VSSComm321.StopBits:=_1;
    1: FPrincipal.VSSComm321.StopBits:=_1_5;
    2: FPrincipal.VSSComm321.StopBits:=_2;
  end;
  // Coonfiguração da Paridade.
  case cbParidade.ItemIndex of
    0: FPrincipal.VSSComm321.Parity:=Even;
    1: FPrincipal.VSSComm321.Parity:=Mark;
    2: FPrincipal.VSSComm321.Parity:=None;
    3: FPrincipal.VSSComm321.Parity:=Odd;
    4: FPrincipal.VSSComm321.Parity:=Space;
  end;
  // Configuração da tamanho da palavra.
  case cbPalavra.ItemIndex of
    0: FPrincipal.VSSComm321.DataBits:=_4;
    1: FPrincipal.VSSComm321.DataBits:=_5;
    2: FPrincipal.VSSComm321.DataBits:=_6;
    3: FPrincipal.VSSComm321.DataBits:=_7;
    4: FPrincipal.VSSComm321.DataBits:=_8;
  end;

  //
  FPrincipal.atribValorAreaDados(StrToInt(EdADados.Text));//pegando o tamnaho da área de dados

  FPrincipal.Vsscomm321.StartComm;

  FConfiguracao.Close;
end;

procedure TFConfiguracao.btCancelarClick(Sender: TObject);
begin
  FConfiguracao.Close;
end;

procedure TFConfiguracao.FormShow(Sender: TObject);
begin
  FPrincipal.Enabled:=false;
end;

procedure TFConfiguracao.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin
 FPrincipal.Enabled:=true;
end;

end.
