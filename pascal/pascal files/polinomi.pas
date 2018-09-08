program polinomio;
uses wincrt;
var g,c,i,x:integer;
    a: array[1..10] of real;
    s:real;

begin
clrscr;
     write('Digite o grau do polinomio: ');
     readln(g);
     write('Entre com o valor de x do polinomio: ');
     readln(x);
     while g >= 0 do
       begin

         if x<>0 then

           if x>0 then
              for i:=g downto 0 do
                begin
                   write('Digite o coeficiente de grau ',i,': ');
                   readln(c);
                   a[i+1]:= c * exp((ln(x))*i);
                end

           else
            if x<0 then
             begin
              for i:=g downto 0 do
               if (i mod 2) =0 then
                begin
                   write('Digite o coeficiente de grau ',i,': ');
                   readln(c);
                   a[i+1]:= c * exp((ln(abs(x)))*i);
                end
               else
                begin
                   write('Digite o coeficiente de grau ',i,': ');
                   readln(c);
                   a[i+1]:= -c * exp((ln(abs(x)))*i);
                end;
              end;

              s:=0;
              write('P(',x,')=');
              for i:=g downto 0 do
                begin
                  write('+');
                  write('(',a[i+1]:2:0, ')');
                  s:=s+(a[i+1]);
                end;
                writeln('=',s:2:0 );

         if x=0 then
            begin
                 for i:=g downto 0 do
                   begin
                      write('Digite o coeficiente de grau ',i,': ');
                      readln(c);
                      a[1]:=c
                   end;
                   writeln('P(',x,')=',a[1]:2:0);
            end;


             write('Digite novo grau do polinomio: ');
             readln(g);
             write('Entre com o valor de x do polinomio: ');
             readln(x);
      end;
end.

