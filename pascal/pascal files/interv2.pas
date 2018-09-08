program intervalo;
uses wincrt;
var a:string;
    i,j,h: integer;
    r:char;

begin
clrscr;
 repeat
       write('Digite uma cadeia: ');
       readln(a);
       write('Digite dois inteiros: ');
       readln(j,h);
       write('"');
        for i:=j to (j+(h-1)) do
            write(a[i]);
       writeln('"');
       write('Deseja continuar?(s\n): ');
       readln(r);
 until r <> 's'
end.

