program exe35;
uses wincrt;
type vetor=array[1..10] of integer;
var  a:vetor;
     j,i,n:integer;




function maior(b:vetor;o:integer):integer;
var ma:integer;
begin
       ma:=b[1];
       for i:=1 to o do
         if b[i]>= ma then
          begin
           ma:=b[i];
           maior:=ma;
          end;
end;

function menor(b:vetor;o:integer):integer;
var me:integer;
begin
        me:=b[1];
        for i:=1 to o do
          if b[i]<=me then
           begin
              me:=b[i];
              menor:=me;
           end;
end;

function media(b:vetor;o:integer):real;
var soma:integer;
begin
     soma:=0;
     for i:=1 to o do
     soma:=soma+b[i];
     media:=soma/o;
end;

function desvio(b:vetor;o:integer;med:real):real;
var soma,dive:real;
begin

     soma:=0;
     for i:=1 to o do
      soma:=soma+sqr(b[i]-med);
     dive:=soma/(o-1);
     desvio:=sqrt(dive);
end;

function mediana(b:vetor;o:integer):real;
var aux:integer;
    medi:real;
begin
      for i:=1 to o-1 do
          for j:=o downto i+1 do
              if b[j] < b[j-1]  then
               begin
                    aux:=b[j-1];
                    b[j-1]:=b[j];
                    b[j]:=aux;
               end;


      if n mod 2 = 0 then
         medi:=(b[o div 2] + b[(o+2) div 2])/2
      else
         medi:= b[(o+1) div 2];

      mediana:=medi;
end;

begin;
      write('Digite a qtd de nº: ');
      readln(n);

      for i:=1 to n do
       begin
            write('Digite o ',i,'º valor: ');
            readln(a[i]);
       end;

       writeln('O maior termo é: ',maior(a,n));
       writeln('O menor termo é: ',menor(a,n));
       writeln('A média é: ',media(a,n):2:2);
       writeln('O desviopadrão é: ',desvio(a,n,media(a,n)):2:2);
       writeln('A mediana é: ',mediana(a,n):2:2);
end.
      


end.