Program exer03;
{Ler uma medição em polegadas, calcular e exibi-la em centímetros (1’’ = 2,54 cm).}
Uses wincrt;
Var
        P, C : Real;
Begin                                          
        clrscr;
        write ('Digite o num. em polegadas ou 0 para finalizar: ');
        readln (P);
        while p<>0 do
        begin
             C := P * 2.54;
             writeln ('O número em centímetros corresponde a ',C:10:5,' cm.');
        write ('Digite o num. em polegadas ou 0 para finalizar: ');
        readln (P);
        end;
        donewincrt;
end.
