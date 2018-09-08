program intevalo3;
uses wincrt;
var cad1,cad2: string;
    i,a,b:integer;
    r:char;

begin
clrscr;
 repeat
       write('Digite uma cadeia: ');
       readln(cad1);
       write('Digite dois nº: ');
       readln(a,b);

       cad2:=' ';
       for i:=1 to (a-1) do
        cad2:=cad2+cad1[i];
       for i:=(a+b) to ord(cad1[0]) do
        cad2:=cad2+cad1[i];

       writeln(cad2);
       write('Deseja continuar?: ');
       readln(r);
 until r <> 's'
end.
