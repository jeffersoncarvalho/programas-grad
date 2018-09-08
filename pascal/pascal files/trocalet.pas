program trocaletras;
uses wincrt;
var a:string;
    i:integer;
    n,x,r:char;

begin
clrscr;
 repeat
       write('Digite uma cadeia: ');
       readln(a);
       write('Digite uma silaba c\ duas letras pertencentes a cadeia: ');
       readln(n,x);
              write('"');
              for i:=1 to length(a) do
                begin
                 if a[i]=n then
                  a[i]:=x;
                  write(a[i]);
                end;
              writeln('"');
       write('Deseja continuar?(s\n): ');
       readln(r);
 until r <> 's'
end.

