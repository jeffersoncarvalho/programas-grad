program somasconsecutivas;
uses crt;
var n,i,soma: integer;

begin
clrscr;
       write('Digite um n§: ');
       readln(n);
       while n >0 do
       begin
            soma:=0;
            for i:=1 to n do
            soma:=soma+i;
            writeln('A soma dos n§ de 1 at‚ ',n,' ‚: ',soma);
       write('Digite novo n§');
       readln(n);
       end;
end.