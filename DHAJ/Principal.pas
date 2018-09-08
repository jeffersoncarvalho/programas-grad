//No Gambiarra!!
unit Principal;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, VSSComm32, StdCtrls, Menus,  ExtCtrls, Buttons, StrUtils,CRC,
  FileCtrl, Mask, Math, ComCtrls;

type

  Pacote = record
    versao : Byte;
    hLen :  vetDoisBytes;
    totalLen :  vetDoisBytes;
    identificacao :  vetDoisBytes;
    BCC:vetDoisBytes;
    endOrigem:vetBytes;
    endDestino: vetBytes;
    infoAdicionais: Byte;
    areaDados : vetDados;
  end;

  Pacote_Resposta=record
    versao : Byte;
    hLen :  vetDoisBytes;
    totalLen :  vetDoisBytes;
    identificacao :  vetDoisBytes;
    BCC:vetDoisBytes;
    endOrigem:vetBytes;
    endDestino: vetBytes;
    infoAdicionais: Byte;
  end;

     //totallen=1+2+2+2+2+4+4+1+areaDados=18+areaDados


  TFPrincipal = class(TForm)
    VSSComm321: TVSSComm32;
    MainMenu1: TMainMenu;
    Configuraes1: TMenuItem;
    Configuracao1: TMenuItem;
    SaveDialog1: TSaveDialog;
    Panel1: TPanel;
    FileListBox1: TFileListBox;
    DirectoryListBox1: TDirectoryListBox;
    DriveComboBox1: TDriveComboBox;
    FilterComboBox1: TFilterComboBox;
    bbtEnviar: TBitBtn;
    Label1: TLabel;
    meEndOr: TMaskEdit;
    meEndDest: TMaskEdit;
    Label2: TLabel;
    Timer1: TTimer;
    lblMensagem: TLabel;
    Label3: TLabel;
    Memo1: TMemo;
    Button1: TButton;
    AtualizaIPs: TButton;
    ProgressBar1: TProgressBar;
    Timer2: TTimer;
    lbPer: TLabel;

    procedure FormCreate(Sender: TObject);
    procedure Configuracao1Click(Sender: TObject);
    procedure bbtEnviarClick(Sender: TObject);
    //**********Em relacao ao Arquivo**************//
    function pegarNomeArquivo(nomeTotalArquivo: String):String;
    function pegarTamanhoArquivo():longword;
    procedure transformarArquivo();
    //*********Em relaçao ao pacote***********//
    procedure atribValorAreaDados(tad: word);
    procedure pegarEndOrigemDestino(var vetOri: vetBytes;var vetDest: vetBytes);
    function  calculaXor(pacote_recebido: Pacote):Word;
    function  montarPacote(pacote_recebido: Pointer; tam:word):Pacote;
    procedure salvarPacote();
    function wordParaVetDoisBytes(numword:word):vetDoisBytes;
    function vetDoisBytesParaWord(x:vetDoisBytes):word;
    function enviarPacoteRespota(pacote_recebido: Pointer;tipoResposta:Byte):Pacote_resposta;
    function montarPacoteRespota(pacote_recebido: Pointer):Pacote_resposta;
    function comparaIpRecebidoIpMeu(IpRecebido:vetBytes):boolean;
    function registradorNacks(numPacote:word):boolean;

    procedure VSSComm321ReceiveData(Buffer: Pointer; BufferLength: Word);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure Button1Click(Sender: TObject);
    procedure AtualizaIPsClick(Sender: TObject);
    procedure Timer1Timer(Sender: TObject);
    procedure Timer2Timer(Sender: TObject);
    function  calculaPorcentagem(qtdAtual:word;qtdTotal:word):word;

  private
    { Private declarations }

  public
    { Public declarations }

  end;

var
  FPrincipal: TFPrincipal;
  nomeArquivo: String;
  tamVetorArquivo: longword;
  vetPacotes: array of Pacote;//vetor de pacotes a enviar.
  TamAreaDados: word;//Tamanho da área da dados do pacote.
  qtdPacotes: word;//qtd de pacotes a enviar
  ideCRC: TCRC;

  vetPacotesRecebidos: array of Pacote;
  qtdPacotesRecebidos: word;
  proximoPacoteAEnviar:word;
  meuIp:vetBytes;
  contaTimeOut:word;
  contaNacks:array[1..2] of word;

implementation

uses Menu;

{$R *.dfm}


//******************************************************
procedure TFPrincipal.FormCreate(Sender: TObject);
var vetDestinoAux:vetBytes;
begin
  VSSComm321.StartComm(); //inicio conexão
  TamAreaDados:= 512;
  qtdPacotesRecebidos:=0;
  SetLength(vetPacotesRecebidos,20000);
  pegarEndOrigemDestino(meuIP,vetDestinoAux);
  contaTimeOut:=0;
end;
//******************************************************
procedure TFPrincipal.atribValorAreaDados(tad: word);
begin
  TamAreaDados:=tad;
end;
//*******************************************************
procedure TFPrincipal.Configuracao1Click(Sender: TObject);
begin
  FConfiguracao.Show;
end;
//****************************************************
procedure TFPrincipal.bbtEnviarClick(Sender: TObject);
begin
  bbtEnviar.Enabled:=false;
  qtdPacotesRecebidos:=0;
  Memo1.Lines.Clear;
  contaTimeOut:= 0;
  ProgressBar1.Position:=0;
  ProgressBar1.Min:=0;
  contaNacks[1]:=0;
  contaNacks[2]:=0;

  transformarArquivo(); //transforma arquivo em vetor de bytes

  ProximoPacoteAEnviar:=0;

    if(qtdPacotes=1) then
      vetPacotes[ProximoPacoteAEnviar].infoAdicionais:=04//último pacote
    else
      vetPacotes[ProximoPacoteAEnviar].infoAdicionais:=01;//pacote dados

    lblMensagem.Caption:='Enviando Pacote nº'+intToStr(ProximoPacoteAEnviar);
    Memo1.Lines.Add('Enviando pacote '+intToStr(ProximoPacoteAEnviar));
    ProximoPacoteAEnviar:=ProximoPacoteAEnviar+1;
    Timer1.Enabled:=true;
    progressBar1.StepBy(1);
    VSSComm321.WriteCommData(@vetPacotes[ProximoPacoteAEnviar-1],530);
end;
//************************************************************************************************************************************
//O metodo abaixo tira apenas o nome do arquivo sem o seu caminho total.
function TFprincipal.pegarNomeArquivo(nomeTotalArquivo: String): String;
var i:word;
    tempString: String;
begin
  tempString:='';
  i:=length(nomeTotalArquivo);
  if (i<>0) then
   while(nomeTotalArquivo[i]<>'\') do
   begin
     tempString:=tempString+nomeTotalArquivo[i];
     i:=i-1;
   end;
   pegarNomeArquivo:=reverseString(tempString);
end;
//*****************************************************************
//O metodo abaixo pegar o tamanho do arquivo em bytes.
function TFprincipal.pegarTamanhoArquivo():longword;
var tam,fSource:longword;
begin
  tam:=0;
  fSource:= FileOpen(FileListBox1.FileName,fmOpenRead);
  if(fSource>0) then
  begin
    tam:= FileSeek(fsource,0,2);
  end;
  pegarTamanhoArquivo:=tam;
  FileClose(fSource);
end;
//*****************************************************************************
//procedimento que transforma o arquivo em um vetor de bytes...
procedure TFprincipal.transformarArquivo();
var arqByte: File of Byte;
    auxByte:Byte;
    i,pacoteN:longword;
    vetOrigemAux,vetDestinoAux:vetBytes;
begin
  tamVetorArquivo:=pegarTamanhoArquivo();
  nomeArquivo:=pegarNomeArquivo(FileListBox1.FileName);


  if(TamVetorArquivo<=tamAreaDados)then
    qtdPacotes:=1
  else
   begin
    if((TamVetorArquivo mod tamAreaDados)=0) then
      qtdPacotes:=TamVetorArquivo div tamAreaDados
    else
      qtdPacotes:=(TamVetorArquivo div tamAreaDados)+1;
   end;
    SetLength(vetPacotes,qtdPacotes+2);
  //acima, calculo quantos pacotes serão necessários.

   if(TamVetorArquivo<=tamAreaDados)then
    vetPacotes[1].totalLen:=wordParaVetDoisBytes(tamVetorArquivo+18)
   else
   begin
      for i:=1 to qtdPacotes-1 do
        vetPacotes[i].totalLen:=wordParaVetDoisBytes(tamAreaDados+18);
      vetPacotes[qtdPacotes].totalLen:=wordParaVetDoisBytes((tamVetorArquivo-(tamAreaDados*(qtdPacotes-1)))+18);
   end;
  //acima, calculo o tamanho da área de dados

  pegarEndOrigemDestino(vetOrigemAux,vetDestinoAux);
  meuIp:=vetOrigemAux;

  //****Pacote 0, com a qtd de pacotes
   vetPacotes[0].totalLen:=wordParaVetDoisBytes(20);

   vetPacotes[0].areaDados[0]:=(qtdPacotes+2) mod 256;
   vetPacotes[0].areaDados[1]:=(qtdPacotes+2) div 256;
   vetPacotes[0].endOrigem:=vetOrigemAux;
   vetPacotes[0].endDestino:=vetDestinoAux;
   //setando versao em cada pacote
   vetPacotes[0].versao:=4;
   //setando hlen
   vetPacotes[0].hLen:=wordParaVetDoisBytes(7);
   //setando identificação
   vetPacotes[0].identificacao:=wordParaVetDoisBytes(0);
   //usando o XOR
   vetPacotes[0].BCC:=wordParaVetDoisBytes(calculaXor(vetPacotes[0]));

  //****

  PacoteN:=1;
  AssignFile(arqByte,FileListBox1.FileName);
  reset(arqByte);
  i:=0;
  while not Eof(arqByte) do
  begin
    if ((i mod tamAreaDados=0)and(i<>0)) then
    begin
      //************
        //colocando endereço de destino e origem no pacote
        vetPacotes[PacoteN].endOrigem:=vetOrigemAux;
        vetPacotes[PacoteN].endDestino:=vetDestinoAux;
        //setando versao em cada pacote
        vetPacotes[PacoteN].versao:=4;
       //setando hlen
        vetPacotes[PacoteN].hLen:=wordParaVetDoisBytes(7);
       //setando identificação
        vetPacotes[PacoteN].identificacao:=wordParaVetDoisBytes(PacoteN);
       //usando o XOR
       vetPacotes[PacoteN].BCC:=wordParaVetDoisBytes(calculaXor(vetPacotes[PacoteN]));
      //******************
      PacoteN:=PacoteN+1;
    end;
    Read(arqByte,auxByte);
    vetPacotes[PacoteN].areaDados[i mod tamAreaDados]:=auxByte;
    i:=i+1;
  end;
  closeFile(arqByte);

//***header do último pacote
       //colocando endereço de destino e origem no pacote
        vetPacotes[PacoteN].endOrigem:=vetOrigemAux;
        vetPacotes[PacoteN].endDestino:=vetDestinoAux;
        //setando versao em cada pacote
        vetPacotes[PacoteN].versao:=4;
       //setando hlen
        vetPacotes[PacoteN].hLen:=wordParaVetDoisBytes(7);
       //setando identificação
        vetPacotes[PacoteN].identificacao:=wordParaVetDoisBytes(PacoteN);
       //usando o XOR
       vetPacotes[PacoteN].BCC:=wordParaVetDoisBytes(calculaXor(vetPacotes[PacoteN]));
//***header do último pacote

//***********nome do arquivo e seu header
  for i:=1 to Length(nomeArquivo) do
    vetPacotes[qtdPacotes+1].areaDados[i-1]:=ord(nomeArquivo[i]);

    vetPacotes[qtdPacotes+1].totalLen:=wordParaVetDoisBytes(Length(nomeArquivo)+18);

   //colocando endereço de destino e origem no pacote
    vetPacotes[qtdPacotes+1].endOrigem:=vetOrigemAux;
    vetPacotes[qtdPacotes+1].endDestino:=vetDestinoAux;
   //setando versao em cada pacote
    vetPacotes[qtdPacotes+1].versao:=4;
   //setando hlen
    vetPacotes[qtdPacotes+1].hLen:=wordParaVetDoisBytes(7);
   //setando identificação
   vetPacotes[qtdPacotes+1].identificacao:=wordParaVetDoisBytes(qtdPacotes+1);
   //usando o XOR
   vetPacotes[qtdPacotes+1].BCC:=wordParaVetDoisBytes(calculaXor(vetPacotes[qtdPacotes+1]));

//**************nome do arquivo e seu header

qtdPacotes:=qtdPacotes+2;
progressBar1.Max:=qtdPacotes;

end;

//******************************************************************************************
procedure TFPrincipal.pegarEndOrigemDestino(var vetOri: vetBytes;var vetDest: vetBytes);
var i,aux:longword;
    num,endOr,endDest: String;
    bufferChar:Char;
    bufferNum: Byte;
begin

  endOr:=meEndOr.Text;
  endOr:=endOr+'.';

  endDest:=meEndDest.Text;
  endDest:=endDest+'.';

  num:='';
  aux:=1;

  for i:=1 to Length(endOr) do
  begin
    if (endOr[i]<>'.') then
    begin
      if(endOr[i]<>'') then
      num:=num+endOr[i];
    end
    else
    if(num <> '') then
     begin
        bufferNum:=StrToInt(num);
        bufferChar:=chr(bufferNum);
        vetOri[aux]:=ord(bufferChar);
        aux:=aux+1;
        num:='';
     end;
  end;

  num:='';
  aux:=1;

  for i:=1 to Length(endDest) do
  begin
    if (endDest[i]<>'.') then
    begin
      if(endDest[i]<>'') then
      num:=num+endDest[i];
    end
    else
    if(num <> '') then
     begin
        bufferNum:=StrToInt(num);
        bufferChar:=chr(bufferNum);
        vetDest[aux]:=ord(bufferChar);
        aux:=aux+1;
        num:='';
     end;
  end;

end;
//**************************************************************
//procedimento de teste

//*****************************************************************
procedure TFPrincipal.VSSComm321ReceiveData(Buffer: Pointer; BufferLength: Word);
var
     pacoteRespostarecebido:Pacote_resposta;
     //******************
     pacoteRecebido:Pacote;
     PacoteResp:Pacote_resposta;
     //*********************
     BccCalculado,bccRecebido,perc:word;
     numReal:double;

begin

        pacoteRespostarecebido:=montarPacoteRespota(buffer);
      if(comparaIpRecebidoIpMeu(pacoteRespostaRecebido.endDestino)) then
      begin
       //*****
        //tratando abaixo natureza dos pacotes.
        if((pacoteRespostarecebido.infoAdicionais=02)or(pacoteRespostarecebido.infoAdicionais=03)) then
        begin
          //****timers begin

            timer1.Enabled:=false;//já recebi, desligo o timer.
            Memo1.Lines.Add('Recebendo resposta do pacote '+IntToStr(ProximoPacoteAEnviar-1));
            if(pacoteRespostarecebido.infoAdicionais=02) then
            begin
                     Memo1.Lines.Add('Resposta do pacote '+IntToStr(ProximoPacoteAEnviar-1) +' OK!');

                    if(proximoPacoteAEnviar<>qtdPacotes) then
                    begin
                      lblMensagem.Caption:='Enviando Pacote nº'+intToStr(ProximoPacoteAEnviar);
                      memo1.Lines.Add('Enviando Pacote nº'+intToStr(ProximoPacoteAEnviar));
                      ProximoPacoteAEnviar:=ProximoPacoteAEnviar+1;
                        if((ProximoPacoteAEnviar)=qtdPacotes) then {(ProximoPacoteAEnviar)=qtdPacotes}
                        begin
                          vetPacotes[ProximoPacoteAEnviar-1].infoAdicionais:=04;//último pacote
                        end
                        else
                        begin
                          vetPacotes[ProximoPacoteAEnviar-1].infoAdicionais:=01;//outros pacotes
                        end;
                        timer1.Enabled:=true;//vou enviar, ligo o timer
                        numReal:=ProximoPacoteAEnviar/(progressBar1.Max);
                        numReal:=numReal*100;
                        perc:=Trunc(numReal);
                        lbPer.Caption:=IntToStr(perc)+'%';
                        progressBar1.StepBy(1);
                        VSSComm321.WriteCommData(@vetPacotes[ProximoPacoteAEnviar-1],530);//enviando um pacote de dados
                    end
                    else
                    begin
                      memo1.Lines.Add('Fim da transmissão');
                      lblMensagem.Caption:='Fim da transmissão.' ;
                       bbtEnviar.Enabled:=true;;
                    end;

            end//ACK
            else
            begin
                if(registradorNacks(ProximoPacoteAEnviar-1)<>true) then
                begin
                   memo1.Lines.Add('Pacote '+intToStr(ProximoPacoteAEnviar-1)+ ' com problema!' );
                   memo1.Lines.Add('Reenviando pacote '+intToStr(ProximoPacoteAEnviar-1));
                   lblMensagem.Caption:='Enviando Pacote nº'+intToStr(ProximoPacoteAEnviar-1);
                   timer1.Enabled:=true;//vou enviar, ligo o timer
                   VSSComm321.WriteCommData(@vetPacotes[ProximoPacoteAEnviar-1],530);//enviando um pacote de dados
                end;//ainda não estorei meu limite de nacks...
            end;//Nack
            memo1.Lines.Add('');

          //*****timers end
        end//recebe respostas(NACK ou ACk) um pacote de resposta

        else
        begin
             timer2.Enabled:=false;
             bbtEnviar.Enabled:=false;
             lblMensagem.Caption:='Recebendo Pacote nº'+intToStr(qtdPacotesRecebidos)+' de '+intToStr(progressBar1.max)+'.';


             pacoteRecebido:=montarPacote(buffer,bufferLength);//o pacote inteiro(não sei se tá certo)

                vetPacotesRecebidos[qtdPacotesRecebidos]:=pacoteRecebido;
                //********BCC******
                bccCalculado:=calculaXor(vetPacotesRecebidos[qtdPacotesRecebidos]);
                bccRecebido:=vetDoisBytesParaWord(vetPacotesRecebidos[qtdPacotesRecebidos].BCC);
                //*****************
              Memo1.Lines.add('Recebendo pacote '+intToStr(qtdPacotesRecebidos)+'...');
              Memo1.Lines.add('Verificando BCC do pacote '+intToStr(qtdPacotesRecebidos)+'...');


            if(bccCalculado=bccRecebido) then
             begin

                   if(qtdPacotesRecebidos=0) then
                   begin
                    progressBar1.Max:=vetPacotesRecebidos[0].areaDados[0]+vetPacotesRecebidos[0].areaDados[1]*256;
                   end;
                   progressBar1.StepBy(1);
                   numReal:=qtdPacotesRecebidos/(progressBar1.Max-1);
                   numReal:=numReal*100;
                   perc:=Trunc(numReal);
                   lbPer.Caption:=IntToStr(perc)+'%';



                   Memo1.Lines.add('BCC do pacote '+intToStr(qtdPacotesRecebidos)+' OK!');
                   qtdPacotesRecebidos:=qtdPacotesRecebidos+1;



                   Memo1.Lines.Add('Enviando ACK do pacote: '+intToStr(qtdPacotesRecebidos-1));
                   PacoteResp:=enviarPacoteRespota(buffer,02);//ACK!!
                   timer2.Enabled:=true;
                   if(pacoteRecebido.infoAdicionais<>04) then
                    memo1.lines.Add('Esperando próximo pacote...');
                   VSSComm321.WriteCommData(@PacoteResp,18);//envio uma resposta dizendo que recebi ok

                 if(pacoteRecebido.infoAdicionais=04) then
                 begin
                  Memo1.Lines.Add('Último pacote:'+intToStr(qtdPacotesRecebidos-1));
                  lblMensagem.Caption:='Fim da transmissão.' ;
                  timer2.Enabled:=false;
                  salvarPacote();
                 end;

             end//pacote recebido beleza
            else
             begin
                Memo1.Lines.Add('Pacote com problema:'+intToStr(qtdPacotesRecebidos));
                Memo1.Lines.add('Enviando NACK do Pacote:'+intToStr(qtdPacotesRecebidos));
                PacoteResp:=enviarPacoteRespota(buffer,03);//NACK!!
                timer2.Enabled:=true;
                memo1.lines.Add('Esperando o mesmo pacote...');
                VSSComm321.WriteCommData(@PacoteResp,18);//envio uma resposta dizendo que recebi papocad(o Parente deve ter balançado o cabo..)
             end;//aí o pacote papocou!!Pede pra mandar de novo
             Memo1.Lines.add(' ');
        end;//recebe um pacote de dados(último talvez)
       //***************
      end
      else
      begin
      //o pacote não é pra mim
      Memo1.Lines.Add('Recebi pacote, mas não é para mim.');
      end;
end;
//*****************************************************************
function  TFPrincipal.calculaXor(pacote_recebido: Pacote):Word;
var bcc:word;
    i,taminf:word;
begin
    bcc:=pacote_recebido.versao xor vetDoisBytesParaWord(pacote_recebido.hLen);
    bcc:=bcc xor vetDoisBytesParaWord(pacote_recebido.totalLen);
    bcc:=bcc xor vetDoisBytesParaWord(pacote_recebido.identificacao);

    bcc:=bcc xor pacote_recebido.endOrigem[1];
    bcc:=bcc xor pacote_recebido.endOrigem[2];
    bcc:=bcc xor pacote_recebido.endOrigem[3];
    bcc:=bcc xor pacote_recebido.endOrigem[4];

    bcc:=bcc xor pacote_recebido.endDestino[1];
    bcc:=bcc xor pacote_recebido.endDestino[2];
    bcc:=bcc xor pacote_recebido.endDestino[3];
    bcc:=bcc xor pacote_recebido.endDestino[4];

    //bcc:=bcc xor pacote_recebido.infoAdicionais;

    taminf:=vetDoisBytesParaWord(pacote_Recebido.totalLen)-18;
    for i:=0 to taminf-1 do
      bcc:=bcc xor pacote_recebido.areaDados[i];

   calculaXor:=bcc;

end;
//*************************************************************
function TFPrincipal.montarPacote(pacote_recebido: Pointer; tam:word):Pacote;
var
    Pacote_resposta:Pacote;
    Teste: ^Pacote;
begin


  Teste := pacote_recebido;

  Pacote_resposta.versao:=teste.versao;

  Pacote_resposta.hLen:=teste.hLen;

  Pacote_resposta.totalLen:=teste.totalLen;

  Pacote_resposta.identificacao:=teste.identificacao;

  Pacote_resposta.BCC:=teste.BCC;

  pacote_resposta.endOrigem:=teste.endOrigem;

  Pacote_resposta.endDestino:=teste.endDestino;

  Pacote_resposta.infoAdicionais:=teste.infoAdicionais;

  pacote_resposta.areaDados:=teste.areaDados;

  montarPacote:=Pacote_resposta;

end;
//***************************************************************
procedure TFPrincipal.salvarPacote();
var  arqSalvar: File of Byte;
     i:longword;
     j,taminf:word;
     nomeRecebidoArquivo,extensao:String;
begin
      nomeRecebidoArquivo:='';
      extensao:='';
      for i:=0 to vetDoisBytesParaWord(vetPacotesRecebidos[qtdPacotesRecebidos-1].totalLen)-19 do
       nomeRecebidoArquivo:=nomeRecebidoArquivo+chr(vetPacotesRecebidos[qtdPacotesRecebidos-1].areaDados[i]);


      for i:=Length(nomeRecebidoArquivo)-3 to Length(nomeRecebidoArquivo) do
       extensao:=extensao+nomeRecebidoArquivo[i];



      SaveDialog1.FileName:=nomeRecebidoArquivo;
      SaveDialog1.Filter:=extensao;

      SaveDialog1.Execute;
      AssignFile(arqSalvar,SaveDialog1.FileName);
      Rewrite(arqSalvar);
      for i:=1 to qtdPacotesRecebidos-2 do//já que o último é o nome do arquivo
      begin
       taminf:=vetDoisBytesParaWord(vetPacotesRecebidos[i].totalLen)-18;
       for j:=0 to taminf-1 do
       begin
         Write(arqSalvar,vetPacotesRecebidos[i].areaDados[j]) ;
       end;
      end;
      CloseFile(arqSalvar);

      bbtEnviar.Enabled:=true;
      qtdPacotesRecebidos:=0;

end;
//************************************************************
function TFPrincipal.wordParaVetDoisBytes(numword:word):vetDoisBytes;
var vetResposta:vetDoisBytes;
begin
   vetResposta[1]:= numword mod 256;
   vetResposta[2]:= numWord div 256;

   wordParaVetDoisBytes:=vetResposta;

end;
//************************************************************
function TFPrincipal.vetDoisBytesParaWord(x:vetDoisBytes):word;
var resp:word;
begin
   resp:=x[1]+x[2]*256;
   vetDoisBytesParaWord:=resp;
end;
//***********************************************************
procedure TFPrincipal.FormClose(Sender: TObject; var Action: TCloseAction);
begin
 VSSComm321.StopComm;
end;
//**********************************************************
function TFPrincipal.enviarPacoteRespota(pacote_recebido: Pointer;tipoResposta:Byte):Pacote_resposta;
var
    PacoteResposta:Pacote_Resposta;
    teste:^Pacote;
begin

   teste:=pacote_recebido;

   PacoteResposta.versao:=teste.versao;
   PacoteResposta.endOrigem:=teste.endDestino;
   PacoteResposta.endDestino:=teste.endOrigem;
   pacoteResposta.BCC:=teste.BCC;
   PacoteResposta.hLen:=teste.hLen;
   pacoteResposta.totalLen:=wordParaVetDoisBytes(18);
   PacoteResposta.infoAdicionais:=tipoResposta;
   PacoteResposta.identificacao:=teste.identificacao;


   enviarPacoteRespota:=PacoteResposta;

end;
//**********************************************
function TFPrincipal.montarPacoteRespota(pacote_recebido: Pointer):Pacote_resposta;
var
    PacoteResposta:Pacote_Resposta;
    teste:^Pacote;
begin

   teste:=pacote_recebido;

   PacoteResposta.versao:=teste.versao;
   PacoteResposta.endOrigem:=teste.endOrigem;
   PacoteResposta.endDestino:=teste.endDestino;
   PacoteResposta.identificacao:=teste.identificacao;
   pacoteResposta.BCC:=teste.BCC;
   PacoteResposta.hLen:=teste.hLen;
   pacoteResposta.totalLen:=teste.totalLen;
   PacoteResposta.infoAdicionais:=teste.infoAdicionais;

   montarPacoteRespota:=PacoteResposta;

end;
//***********************************************************
procedure TFPrincipal.Button1Click(Sender: TObject);
begin
   Memo1.Lines.Clear;
end;
//************************************************************
procedure TFPrincipal.AtualizaIPsClick(Sender: TObject);
var vetDestinoAux:vetBytes;
begin
  pegarEndOrigemDestino(meuIP,vetDestinoAux);
   showMessage('IPs Atualizados.');
end;
//**************************************************************
function TFPrincipal.comparaIpRecebidoIpMeu(IpRecebido:vetBytes):boolean;
var resp:boolean;
    i:byte;
begin
  resp:=true;
  for i:=1 to 4 do
   if(meuIP[i]<>IpRecebido[i])then
    resp:=false;

  comparaIpRecebidoIpMeu:=resp;
end;
//*************************************************************
procedure TFPrincipal.Timer1Timer(Sender: TObject);
begin
 if(contaTimeOut<>3) then
 begin
  memo1.Lines.Add('');
  memo1.Lines.Add('TimeOUT! reenviando pacote: '+intToStr(ProximoPacoteAEnviar-1));
  contaTimeout:=contaTimeout+1;
  timer1.Enabled:=true;
  VSSComm321.WriteCommData(@vetPacotes[ProximoPacoteAEnviar-1],530);//reenvio o pacote
 end
 else
 begin
  memo1.Lines.Add('');
  memo1.Lines.Add('3 TimeOuts. Abortando Transmissão');
  lblMensagem.Caption:='Fim da transmissão.' ;
  timer1.Enabled:=false;
  VssComm321.StopComm;
 end;
end;
//***************************************************
function TFPrincipal.registradorNacks(numPacote:word):boolean;
var resp:boolean;
begin
  resp:=false;
  if(numPacote<>contaNacks[1])then
  begin
   contaNacks[2]:=0;
   contaNacks[1]:=numPacote;
  end;
  contaNacks[2]:=contaNacks[2]+1;
  if(contaNacks[2]=3)then
  begin
    resp:=true;
    Memo1.Lines.Add('Máximo de NACK do pacote nº'+intTostr(contaNacks[1]));
    memo1.Lines.Add('Abortando Transmissão...');
    lblMensagem.Caption:='Fim da transmissão.' ;
    VSSComm321.StopComm;
  end;

  registradorNacks:=resp;

end;
//*****************************************************
procedure TFPrincipal.Timer2Timer(Sender: TObject);
begin
  memo1.lines.Add('TIMEOUT!Não recebi nenhum pacote.');
  memo1.lines.Add('Como eu não sou besta...');
  memo1.lines.Add('Fim da recepção.');
  VSSComm321.StopComm;
  timer2.Enabled:=false;
end;
//*********************************************************
function TFPrincipal.calculaPorcentagem(qtdAtual:word;qtdTotal:word):word;
var resp:word;
begin
  resp:=trunc((qtdAtual*100)/qtdTotal);
  calculaPorcentagem:=resp;
end;
end.
