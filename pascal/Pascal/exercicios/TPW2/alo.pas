

program mediasalarial;

   uses wincrt;
   var salario, reajuste, novosal, totreaj, media : real;
       numsal : integer;

       begin
	 clrscr;
	 totreaj := 0;
	 numsal  := 0;
	  write (' entre com o reajuste de %' );
	  readln ( reajuste );
	  write (' entre com o salario : ' );
	  readln  ( salario );

	  while ( salario >0 ) do
	   begin
	      novosal:= salario+(salario*reajuste)/100;
	      totreaj:=totreaj+novosal;
	      numsal:=numsal+1;
	      writeln(' salario reajutado: ' ,novosal:10:2);
	      writeln (' entre com o salario: ' );
	      readln ( salario);
	   end;

	      if ( numsal > 0) then
		begin
		   media := totreaj/numsal;
		   write ( ' media dos salarios: ', media:10:2);
		 end
		 else
		    write(' não existe dados a serem processados' );
		    repeat until keypressed;
             end.





	       











