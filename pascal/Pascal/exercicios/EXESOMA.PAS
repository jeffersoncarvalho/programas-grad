program exesoma;
uses crt;
var soma, n, i: integer;

begin
clrscr;
     write('Digite um n§: ');
     readln(n);
     while n>0 do
     begin
          soma:=0;
          for i:=1 to n-1 do
          if(n mod i)=0 then
          soma:= soma + i;
     if (soma>n) then
        writeln('O n§ ‚ deficiente')
     else
         if (soma=n) then
            writeln('O n§ ‚ perfeito')
         else
             writeln('O n§ ‚ abundante');

     write('Digite novo n§: ');
     readln(n);
     end;
end.