program inverter;
uses wincrt;
var cad1,cad2: string;
    i:integer;
    r:char;

begin
clrscr;
 repeat
       write('Digite uma cadeia: ');
       readln(cad1);

       cad2:=' ';
       for i:=ord(cad1[0]) downto 1 do
        cad2:=cad2+cad1[i];

       writeln(cad2);
       write('Deseja continuar?: ');
       readln(r);
 until r <> 's'
end.