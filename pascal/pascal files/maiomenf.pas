program maior_menor_c_funcao;
uses wincrt;
type vetor=array[1..100] of integer;
var  a:vetor;
     n,i: integer;

function maior(b:vetor;m:integer):integer;
var ma:integer;
begin
   ma:=b[1];
   for i:=1 to m do
    if b[i]>=ma then
     begin
      ma:=b[i];
      maior:=ma;
     end;
end;

function menor(c:vetor;o:integer):integer;
var me:integer;
begin
     me:=c[1];
     for i:=1 to o do
      if c[i]<=me then
       begin
         me:=c[i];
         menor:=me;
       end;
end;

begin
     write('Digite nº de elementos do vetor: ');
     readln(n);
     for i:=1 to n do
      begin
           write('Digite o ',i,'º elemento do vetor: ');
           readln(a[i]);
      end;

     writeln('O maior elemento é: ',maior(a,n));
     writeln('O menor elemento é: ',menor(a,n));
end.

