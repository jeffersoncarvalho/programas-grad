Program contale2;
uses wincrt;
var a:string;
    aux:char;
    i,j,me,s:integer;
    cl:boolean;

begin
     write('Digite uma palavra: ');
     readln(a);


     for i:=1 to ord(a[0]) do
      begin
           me:=i;
           for j:=i+1 to ord(a[0]) do
               if a[j] < a[me] then
                  me:=j;
           aux:=a[i];
           a[i]:=a[me];
           a[me]:=aux;
      end;
            writeln(a);
      cl:=true;
      s:=0;
      for i:=1 to ord(a[0]) do
       begin
            if  cl then
             begin
                  s:=s+1;
                  cl:=false;
             end;
            if a[i] <> a[i+1] then
             cl:=true;
       end;

       writeln('O nº de letras distintas na palavras é de: ',s);
end.