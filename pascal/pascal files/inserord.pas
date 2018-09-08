program inserord;
uses wincrt;
var a:array[1..10] of integer;
    i,j,me,n,aux:integer;

begin
     write('Digite qtd de nº: ');
     readln(n);

     for i:=1 to n do
      begin
           write('Digite o ',i,'º nº: ');
           readln(a[i]);
      end;

     for i:=1 to n do
      begin
           me:=i;
           for j:=i+1 to n do
               if a[j] < a[me] then
                  me:=j;
           aux:=a[i];
           a[i]:=a[me];
           a[me]:=aux;
      end;
      write('');
      for i:=1 to n do
          write(a[i],',');
          writeln;
end.