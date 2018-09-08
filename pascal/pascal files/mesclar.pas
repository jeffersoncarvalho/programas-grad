program mesclar;
uses wincrt;
var a,b:string;
    i,j,h:integer;
    r:char;

begin
clrscr;
 repeat
       write('Digite uma cadeia qualquer: ');
       readln(a);
       write('Digite uma nº: ');
       readln(j);
       write('Digite outra cadeia: ');
       readln(b);
        write('"');
        for i:=1 to j-1 do
            write(a[i]);
            write(b);
        for i:=j to length(a) do
            write(a[i]);
        writeln('"');
       write('Deseja continuar?(s\n): ');
       readln(r);
 until r <> 's'
end.
