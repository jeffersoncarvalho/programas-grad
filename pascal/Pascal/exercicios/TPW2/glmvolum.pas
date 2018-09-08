Program Volumes;
Uses wincrt;     
Var R, altura: real;
       volume: real; 
Begin           
   Writeln('Digite o valor de R:');
   read(R);       
   Write('Digite o valor da altura: ');    
   readln(altura);       
   Volume:= 3.14159*R*R*altura;
   Write('Volume = ',Volume:5:1, 'cm3');
End.