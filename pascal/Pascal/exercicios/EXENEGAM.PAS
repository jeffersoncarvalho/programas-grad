program media;
uses crt;
var n,q : integer;
    s,m : real;
    r:string[1];

begin
clrscr;
repeat
       write('Digite n§: ');
       readln(n);
       s:=0;
       q:=0;
       while n>=0 do
       begin
            s:= s+n;
            q:=q+1;
            write('Digite novo n§: ');
            readln(n);
       end;

       m:=s/q;
       writeln('A media ser :',m:2:2);
       write('Deseja continuar?: ');
       readln(r);
until r <> 's';
end.
