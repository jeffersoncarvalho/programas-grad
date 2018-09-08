program fibonacci;
uses crt;
var n,i,a,b,c : LONGINT;
    R: STRING [1];

begin
CLRSCR;
REPEAT
     repeat
           write ('Entre com n§: ');
           readln(n);
     until (n <=100) and (n >0);
     a:=0;
     b:=1 ;
     for i:=3 to n do
     begin
          c:= a+b;
          a:=b;
          b:=c;
     end;
         if n <3 then
            if n = 2 then
               writeln ('O ' ,n, ' termo de fibonacci ‚: ',b)
            else
               writeln ('O ' ,n, ' termo de fibonacci ‚: ',a)
         else
             writeln('O ' ,n, ' termo de fibonacci ‚:',c);
             WRITE('DESEJA CONTINUAR?: ');
             READLN(R);
UNTIL R <> 'S'
end.