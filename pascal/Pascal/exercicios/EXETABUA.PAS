program tabuada;
uses crt;
var n,i : integer;

begin
clrscr;
     Write('Digite um n§: ');
     readln(n);
     while n >0 do
     begin
          i:=1;
          for i:= 1 to 10 do
          writeln(i,'x',n,'=',n*i);

          write('Digite novo n§: ');
          readln(n);
     end;
end.