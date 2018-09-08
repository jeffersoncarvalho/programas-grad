program exe33;
uses wincrt;
type poli=record
     grau:1..30;
     a:array[1..30] of real;
    end;
var b:poli;
    x,c,i:integer;
    s:real;




begin

     write('Digite o grau do polinomio: ');
     readln(b.grau);
     write('Entre com o valor de x do polinomio: ');
     readln(x);
     while b.grau >= 0 do
       begin

         if x<>0 then

           if x>0 then
              for i:=b.grau downto 0 do
                begin
                   write('Digite o coeficiente de grau ',i,': ');
                   readln(c);
                   b.a[i+1]:= c * exp((ln(x))*i);
                end

           else
            if x<0 then
             begin
              for i:=b.grau downto 0 do
               if (i mod 2) =0 then
                begin
                   write('Digite o coeficiente de grau ',i,': ');
                   readln(c);
                   b.a[i+1]:= c * exp((ln(abs(x)))*i);
                end
               else
                begin
                   write('Digite o coeficiente de grau ',i,': ');
                   readln(c);
                   b.a[i+1]:= -c * exp((ln(abs(x)))*i);
                end;
              end;

              s:=0;
              write('P(',x,')=');
              for i:=b.grau downto 0 do
                begin
                  write('+');
                  write('(',b.a[i+1]:2:0, ')');
                  s:=s+(b.a[i+1]);
                end;
                writeln('=',s:2:0 );

         if x=0 then
            begin
                 for i:=b.grau downto 0 do
                   begin
                      write('Digite o coeficiente de grau ',i,': ');
                      readln(c);
                      b.a[1]:=c
                   end;
                   writeln('P(',x,')=',b.a[1]:2:0);
            end;


             write('Digite novo grau do polinomio: ');
             readln(b.grau);
             write('Entre com o valor de x do polinomio: ');
             readln(x);
      end;
end.



