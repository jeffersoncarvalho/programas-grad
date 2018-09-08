program troca;
uses wincrt;
var a:array[1..20] of integer;
    i,j,n,x: integer;
    r:char;

begin
clrscr;
 repeat
      write('Digite a quantidade de inteiros da cadeia: ');
      readln(j);
      for i:=1 to j do
       begin
        write('Digite o ',i,'º nº da cadeia: ');
        readln(a[i]);
       end;

      write('Digite dois inteiros: ');
      readln(n,x);

       write('"');
       for i:=1 to j do
        begin
          if i=n then
            a[i]:=x;
            write(a[i]);
          end;
       writeln('"');

       write('Continua?(s\n): ');
       readln(r);
 until r <> 's'
end.