Program maiordeumalinha;
uses wincrt;
type matriz=array[1..20,1..20] of integer;
var  a:matriz;
     i,j,m,n,l:integer;


function maior(b:matriz;m1:integer;t:integer):integer;
var ma:integer;

begin
     i:=t;
     ma:=b[t,1];
     for j:=1 to m1 do
        if b[t,j] >= ma then
         begin
          ma:=b[t,j];
          maior:=ma;
         end;
end;
        




Begin
     write('Digite nº de linhas e colunas da matriz, respectivamente: ');
     readln(n,m);

     for i:=1 to n do
      for j:=1 to m do
       begin
            write('Digite o A[',i,',',j,'] elemento da matriz: ');
            readln(a[i,j]);
       end;

          writeln('*****MATRIZ A[i,j]*****');
      for i:=1 to n do
       begin
          for j:=1 to m do
           begin
                write('      ');
                write(a[i,j]);
                write('  ');
           end;
                writeln;
       end;

     write('Digite o valor de uma linha da matriz: ');
     readln(l);

     
     writeln('O maior valor encontrado na linha ',l,' foi: ',maior(a,m,l));
    
end.
