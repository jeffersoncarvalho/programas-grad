Program exerc13;
{Calcular o cosseno ou seno de um ângulo em radianos utilizando a série de
Taylor com erro inferior a 10-6 (0,000001).}
Uses wincrt;
Var i,z,v,sinal :longint;
    exp,num,den,d,ang,x :real;
Begin
     clrscr;
     write('Entre com o ângulo:  ');
     readln(ang);
     x:=(ang*pi)/180;
     z:=3;
     v:=3;
     sinal:=1;
     num:=1;
     den:=1;
     while num/den >= 0.000001 do
     begin
          num:=1;
          den:=1;
          for i:=1 to z do
              num:=num*x;
          for i:=v downto 1 do
               den:=den*i;
          sinal:=sinal*(-1);
          exp:=(num/den*sinal)+exp;
          z:=z+2;
          v:=z;
     end;
     write ('O Seno de ',ang:4:2,' é ',x+exp:4:3,'.');
     readkey;
     donewincrt;
end.