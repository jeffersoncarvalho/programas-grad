program prim;
uses crt;
var n,i : integer;
    primo:boolean;
begin
clrscr;
     write('Digite um nß: ');
     readln(n);
     while n <> 0 do
     begin
           primo:=true;
           for i:=2 to n-1 do
           if (n mod i)=0 then
           primo:=false;
           if (primo=true) and (n <>1) then
           writeln('O nß Ç primo')
           else
           writeln('O nß n∆o Ç primo');
           write('Digite novo nß: ');
           readln(n);
     end;
end.
