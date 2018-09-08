PROGRAM SUBCADEIA;
USES CRT;
VAR I,J,H,N:INTEGER;
    A: STRING;
    R:CHAR;

BEGIN
CLRSCR;
REPEAT

       WRITE('DIGITE A CADEIA: ');
       READLN(A);

       WRITE('DIGITE DOIS INTEIROS: ');
       READLN(J,H);

            WRITE('"') ;
            FOR I:=J TO ((LENGTH(A)-H)+1) DO
               WRITE(A[I]);
            WRITELN('"');

       WRITE('DESEJA CONTINUAR?(S/N): ');
       READLN(R)
UNTIL R <> 'S'
END.

