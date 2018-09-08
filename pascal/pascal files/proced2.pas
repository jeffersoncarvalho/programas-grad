program maiuscula_c_procedure;
uses wincrt;
type nome=string;
var  a:nome;

procedure maiusculo(b,cad:nome);
var x,i:integer;

begin
     write('Digite uma cadeia: ');
     readln(b);

     for i:=1 to ord(b[0]) do
      begin
           if b[i] <> ' ' then
             begin
               x:=ord(b[i])-32;
               cad[i]:=chr(x);
             end
           else
               cad[i]:=' ';
      end;

     write('"');
     for i:=1 to length(b) do
      write(cad[i]);
     write('"');
end;

begin
 maiusculo(a,a);
end.