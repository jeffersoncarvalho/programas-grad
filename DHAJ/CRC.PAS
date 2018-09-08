unit CRC;

interface

uses SysUtils,StrUtils;

type vetBytes = array[1..4] of byte;
     vetDoisBytes = array[1..2] of byte; 
     vetDados = array[0..511] of byte;
  TCRC=class
  private
     function byteParaBinario(num: Byte):String;
     function dividePolinomio(dividendo:String;divisor:String):String;
     function wordParaBinario(num: word):String;//devolve um binário de 8 bits
     function pot(base : Byte; exp: Byte):word;
     function binarioParaCRC(numbin : String): Word;
     function crcParaBinario(crc: Word):String;
  public


     function calculaCRC(versao: Byte; hLen: Word; totalLen : Word; identificacao:Word; Source: vetBytes; Destination: vetBytes; infoAdicionais : Byte ; vetDados: array of Byte):Word;//retorna BCC
     function crcCorreto(versao: Byte; hLen: Word; totalLen : Word; identificacao:Word; Source: vetBytes; Destination: vetBytes; infoAdicionais : Byte ; vetDados: array of Byte; crc : Word):Boolean;
  end;


implementation





function TCRC.byteParaBinario(num: Byte):String;//devolve um binário de 8 bits
var numBin:String;
    resto,quociente,i:Byte;
begin
  numBin:='';

  while(num>=2) do
  begin
    resto:=num mod 2;
    quociente:= num div 2;
    numBin:=numBin+IntToStr(resto);
    num:=quociente;
  end;
    numBin:=numBin+IntToStr(num);
    for i:=0 to (8-length(numBin)) do
     numBin:=numBin+'0';

    byteParaBinario:=reverseString(numBin) ;
end;

function TCRC.crcParaBinario(crc: Word):String;
var numBin:String;
    resto,quociente:Word;
begin
  numBin:='';

  while(crc>=2) do
  begin
    resto:=crc mod 2;
    quociente:= crc div 2;
    numBin:=numBin+IntToStr(resto);
    crc:=quociente;
  end;
    numBin:=numBin+IntToStr(crc);


    crcParaBinario:=reverseString(numBin) ;
end;

function TCRC.wordParaBinario(num: word):String;//devolve um binário de 16 bits
var numBin:String;
    resto,quociente,i:word;
begin
  numBin:='';

  while(num>=2) do
  begin
    resto:=num mod 2;
    quociente:= num div 2;
    numBin:=numBin+IntToStr(resto);
    num:=quociente;
  end;
    numBin:=numBin+IntToStr(num);
    for i:=0 to (16-length(numBin)) do
     numBin:=numBin+'0';

    wordParaBinario:=reverseString(numBin) ;
end;

//esta funcao devolve-me apenas o resto da divisão de D´(x) po G(x)!!!
function TCRC.calculaCRC(versao: Byte; hLen: Word; totalLen : Word; identificacao : Word; Source: vetBytes; Destination: vetBytes;infoAdicionais:Byte;vetDados: array of Byte):word;//retorna BCC
var Dx,Gx,resto: String;
    i: integer;
begin

  Dx:='';
  Gx:='11000000000000101';

  //agrupar os bytes em uma longa cadeia de binarios

   Dx:=Dx+byteParaBinario(versao);
   Dx:=Dx+wordParaBinario(hlen);
   Dx:=Dx+wordParaBinario(totalLen);
   Dx:=Dx+wordParaBinario(identificacao);

   for i:=1 to 4 do
    Dx:=Dx+byteParaBinario(Source[i]);

   for i:=1 to 4 do
    Dx:=Dx+byteParaBinario(Destination[i]);

   Dx:=Dx+byteParaBinario(infoAdicionais);

   for i:=0 to Length(vetDados)-1 do
    Dx:=Dx+byteParaBinario(vetDados[i]);


  //ao adicionar 16 caracteres 0 ao final da cadeia de dados, automaticamente estou multiplicando o polinômio por xE16.

      Dx:=Dx+'0000000000000000';//suponhamos que aqui seja D´(x)..e é!

  //pego o resto
  resto:=dividePolinomio(Dx,Gx);

  //tranformo meu resto em um inteiro
  calculaCRC:= binarioParaCrc(resto); //valor do bcc
end;

function TCRC.crcCorreto(versao: Byte; hLen: Word; totalLen : Word; identificacao : Word; Source: vetBytes; Destination: vetBytes;infoAdicionais:Byte; vetDados: array of Byte; CRC:word):boolean;
var Dx,resto,teste:String;
    resultado:boolean;
    i:integer;
begin
   Dx:='';

   //recalculo outra vez o D(x)
   Dx:=Dx+byteParaBinario(versao);
   Dx:=Dx+wordParaBinario(hlen);
   Dx:=Dx+wordParaBinario(totalLen);
   Dx:=Dx+wordParaBinario(identificacao);

   for i:=1 to 4 do
    Dx:=Dx+byteParaBinario(Source[i]);

   for i:=1 to 4 do
    Dx:=Dx+byteParaBinario(Destination[i]);

   Dx:=Dx+byteParaBinario(infoAdicionais);

   for i:=0 to Length(vetDados)-1 do
    Dx:=Dx+byteParaBinario(vetDados[i]);

   //agora uno o meu Dx com o crc
   teste:=crcParaBinario(crc);
   Dx:=Dx+crcParaBinario(crc);


  resto:=dividePolinomio(Dx,'11000000000000101');
  resultado:=true;

  for i:=1 to Length(resto) do
   if(resto[i]='1')then
    resultado:=false;

  crcCorreto:=resultado;
end;

function TCRC.dividePolinomio(dividendo:String;divisor:String):String;
var i,maiorGrauDivisor,maiorGrauDividendo,tempGrau:integer;
    tempString,resto:String;
begin


  dividendo:=reverseString(dividendo);
  divisor:=reverseString(divisor);

  tempString:='';
  resto:='';
  maiorGrauDividendo:=length(Dividendo)-1;
  maiorGrauDivisor:=length(Divisor)-1;

  for i:=1 to length(dividendo) do
   tempString:=tempString + '0';

  //for i:=1 to length(divisor) do
   //resto:=resto + '0';

  while(maiorGrauDividendo>=maiorGrauDivisor) do
  begin
        tempGrau:=maiorGrauDividendo-maiorGrauDivisor;
        for i:=1 to length(divisor) do
          if(divisor[i]='1') then
           tempString[tempGrau+i]:='1';

        for i:=1 to length(dividendo) do
        begin
          if((tempString[i]='1') and (dividendo[i]='1')) then
          begin
            tempString[i]:='0';
            dividendo[i]:='0';
          end;
          if((tempString[i]='1') and (dividendo[i]='0')) then
          begin
            tempString[i]:='0';
            dividendo[i]:='1';
          end;
        end;

        maiorGrauDividendo:=0;
        for i:=1 to length(dividendo) do
         if(dividendo[i]='1') then
          if(i-1>maiorGrauDividendo) then
            maiorGrauDividendo:=i-1;

  end;//while

   for i:=maiorGrauDivisor downto 1 do
    resto:=resto+dividendo[i];

   dividePolinomio:=resto;

end;

function TCRC.binarioParaCRC(numBin : String): Word;
var i,numCRC:Word;
begin
  numBin:=reverseString(numBin);
  numCRC:=0;
  for i:=1 to length(numBin) do
   if(numBin[i]='1')then
    numCRC:=numCRC+pot(2,i-1);

  binarioParaCRC:=numCRC;

end;

function TCRC.pot(base : Byte; exp: Byte):Word;
var i,resposta: word;
begin
  resposta:=1;

  if(exp<>0)then
    for i:=1 to exp do
     resposta:=resposta*base;

  pot:=resposta;
end;


end.
