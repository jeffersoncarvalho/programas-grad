program prim;
uses crt;
var n,i : integer;
    primo:boolean;
begin
clrscr;
     write('Digite um n�: ');
     readln(n);
     while n <> 0 do
     begin
           primo:=true;
           for i:=2 to n-1 do
           if (n mod i)=0 then
           primo:=false;
           if (primo=true) and (n <>1) then
           writeln('O n� � primo')
           else
           writeln('O n� n�o � primo');
           write('Digite novo n�: ');
           readln(n);
     end;
end.
