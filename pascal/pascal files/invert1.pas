program inverter1;
uses wincrt;
var cad1:string;
    i:integer;
    r:char;

begin
clrscr;
 repeat
       write('Digite uma cadeia: ');
       readln(cad1);

       write('"');
       for i:=length(cad1) downto 1 do
        write(cad1[i]);
       writeln('"');

       write('Deseja continuar?: ');
       readln(r);
 until r <> 's'
end.