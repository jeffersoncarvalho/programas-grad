program minusmais;
uses wincrt;
var a:string;
    n,i,b,j: integer;
    r:char;
begin
clrscr;
 repeat
       write('Digite uma cadeia em letras minusculas: ');
       readln(a);
         write('"');
         for i:=1 to length(a) do
          begin
             n:= ord(a[i])-32;
             write(chr(n));
          end;
         writeln('"');
         write('Deseja continuar?(s\n): ');
         readln(r);
 until r <> 's'
end.
