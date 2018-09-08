program somadepares;
uses crt;
var i,n,soma: integer;

begin
clrscr;
       write('Digite n§: ');
       readln(n);
       while n > 0 do
       begin
       soma:=0;
       for i:=1 to n-1 do
       if(i mod 2) = 0 then
       soma:= soma +i;
       writeln('A soma dos n§ pares de 1 at‚ ',n,' ‚: ',soma);
       write('Digite novo n§: ');
       readln(n)

       end;
end.
