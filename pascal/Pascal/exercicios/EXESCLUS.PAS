PROGRAM EXCLUSAO;
USES CRT;
VAR I,J,N: INTEGER;
    A:ARRAY[1..100] OF INTEGER;

BEGIN
CLRSCR;
     WRITE('DIGITE QTD DE N§ DO VETOR: ');
     READLN(N);
     WHILE N>0 DO
     BEGIN
          FOR I:=1 TO N DO
          BEGIN
               WRITE('DIGITE O ',I,'§ N§ DO VETOR: ');
               READLN(A[I]);
          END;
          WRITE('DIGITE A ORDEM DO N§ A SER EXCLUIDO: ');
          READLN(J);
          IF (J>=1) AND (J<=N) THEN
          BEGIN
          IF (J>1) AND (J<N) THEN
          BEGIN
               FOR I:=1 TO J-1 DO
                   WRITE(A[I],',');
               FOR I:=J+1 TO N DO
                   WRITE(A[I],',');
          END
          ELSE
              IF J=1 THEN
                 FOR I:=2 TO N DO
                     WRITE(A[I],',')
              ELSE
                  FOR I:=1 TO N-1 DO
                     WRITE(A[I],',');
          END
          ELSE
              WRITELN('ORDEM INVALIDA SEU BOCO!!!!');

     WRITE('DIGITE NOVA QTD DE N§ DO VETOR: ');
     READLN(N);
     END;
END.

