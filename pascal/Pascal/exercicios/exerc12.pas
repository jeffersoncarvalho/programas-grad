Program exer12;
{Ler um inteiro N e afirmar se o mesmo � n�mero perfeito, deficiente  ou
abundante. (Seja S a soma dos divisores de N exceto ele pr�prio.
N � deficiente se N < S, perfeito se  N = S e abundante se N > S)}
Uses wincrt;
Var
        n, i, soma : integer;
Begin
        clrscr;
        Write ('Digite o n�mero:  ');
        Readln (n);
        soma := 0;
        for i := 1 to n - 1 do
                if (n mod i) = 0 then
                        soma := soma + i;
        if (n<soma) then
                write ('O n�mero � deficiente.')
        else
            if (n=soma) then
                write ('O n�mero � perfeito.')
            else
                write ('O n�mero � abundante.');
        repeat until keypressed;
        donewincrt;
end.