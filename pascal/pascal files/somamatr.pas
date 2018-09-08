program somatriz2;
uses wincrt;
var a,b,c:array[1..10,1..10] of integer;
    m,n,i,j:integer;
    r:char;

begin
clrscr;
repeat
      write('Digite nº de linhas e coluna da matriz: ');
      readln(m,n);
      for i:=1 to m do
          for j:=1 to n do
           begin
                write('Digite o A[',i,',',j,'] elemento da 1¦ matriz: ');
                readln(a[i,j]);
           end;



            writeln('****MATRIZ A[i,j] ****');
      for i:=1 to m do
       begin
          for j:=1 to n do
           begin
                write('      ');
                write(a[i,j]);
                write('  ');
           end;
                writeln;
        end;


      for i:=1 to m do
          for j:=1 to n do
           begin
                write('Digite o B[',i,',',j,'] elemento da 2¦ matriz: ');
                readln(b[i,j]);
           end;

           writeln('****MATRIZ B[i,j]****');
      for i:=1 to m do
       begin
          for j:=1 to n do
           begin
                write('      ');
                write(b[i,j]);
                write('  ');
           end;
                writeln;
       end;


      for i:=1 to m do
          for j:=1 to n do
           begin
                c[i,j]:=a[i,j]+b[i,j];
           end;

           writeln('****MATRIZ SOMA****');
       for i:=1 to m do
       begin
          for j:=1 to n do
           begin
                write('      ');
                write(c[i,j]);
                write('  ');
           end;
                writeln;
       end;
       write('Deseja continuar(s\n)?: ');
       readln(r);
until r <> 's'
end.