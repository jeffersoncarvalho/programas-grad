Program exer03;
{Ler uma medi��o em polegadas, calcular e exibi-la em cent�metros (1�� = 2,54 cm).}
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
             writeln ('O n�mero em cent�metros corresponde a ',C:10:5,' cm.');
        write ('Digite o num. em polegadas ou 0 para finalizar: ');
        readln (P);
        end;
        donewincrt;
end.
