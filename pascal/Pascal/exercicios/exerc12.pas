Program exer12;
{Ler um inteiro N e afirmar se o mesmo é número perfeito, deficiente  ou
abundante. (Seja S a soma dos divisores de N exceto ele próprio.
N é deficiente se N < S, perfeito se  N = S e abundante se N > S)}
Uses wincrt;
Var
        n, i, soma : integer;
Begin
        clrscr;
        Write ('Digite o número:  ');
        Readln (n);
        soma := 0;
        for i := 1 to n - 1 do
                if (n mod i) = 0 then
                        soma := soma + i;
        if (n<soma) then
                write ('O número é deficiente.')
        else
            if (n=soma) then
                write ('O número é perfeito.')
            else
                write ('O número é abundante.');
        repeat until keypressed;
        donewincrt;
end.