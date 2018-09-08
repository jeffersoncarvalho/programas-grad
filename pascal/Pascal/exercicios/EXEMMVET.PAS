PROGRAM MMVETOR;
USES CRT;
TYPE VETOR=ARRAY[1..100] OF INTEGER;

VAR  A:VETOR;
     N,I,MAIOR,MENOR:INTEGER;

BEGIN
CLRSCR;
     WRITE('DIDGITE QTD DE N§: ');
     READLN(N);
     WHILE N>0 DO
     BEGIN
          FOR I:=1 TO N DO
          BEGIN
               WRITE('DIGITE O ',I,'§ N§: ');
               READLN(A[I]);
          END;
     MENOR:=A[1];
     MAIOR:=A[1];
           FOR I:=2 TO N DO
           BEGIN
                IF A[I] < MENOR THEN
                   MENOR:=A[I]
                ELSE
                    IF A[I] > MAIOR THEN
                       MAIOR:=A[I];
           END;

           WRITELN('O MAIOR N§ E: ',MAIOR);
           WRITELN('O MENOR N§ E: ',MENOR);

     WRITE('DIGITE NOVA QTD DE N§: ');
     READLN(N);
     END;
END.