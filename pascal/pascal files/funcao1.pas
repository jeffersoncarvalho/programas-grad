program media_desviopad_c_func;
uses wincrt;
type vetor=array[1..10] of integer;
var a:vetor;
    n,i:integer;

function media(b:vetor;r:integer):real;
var s:integer;
begin
     s:=0;
     for i:=1 to r do
      s:=s+b[i];
     media:=s/r;
end;

function desviopad(c:vetor;g:integer;m:real):real;
var soma,dive:real;
    s,i:integer;
begin

     soma:=0;
     for i:=1 to g do
      soma:=soma+sqr(c[i]-m);
     dive:=soma/(g-1);
     desviopad:=sqrt(dive);
end;

begin
clrscr;
     write('Digite a qtd de nº do vetor: ');
     readln(n);
     while n >=0 do
       begin
        for i:=1 to n do
         begin
            write('Digite o ',i,'º nº do vetor: ');
            readln(a[i]);
         end;

        writeln('O valor da media do vetor e: ',media(a,n):2:2);
        writeln('O valor do Desvio Padrao e: ',desviopad(a,n,media(a,n)):2:2);
        write('Digite nova qtd de nº do vetor: ');
        readln(n)
      end;
end.