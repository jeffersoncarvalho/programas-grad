Program Exerc02;

uses wincrt;
var cpf,ndp:integer;
    renda,desc,ir:real;
begin
   write ('Digite CPF: ');
   readln (cpf);
   while cpf <> 0 do
   begin
      write ('Digite o numero de dependentes: ');
      readln (ndp);
      desc:=ndp*5;
      writeln ('Seu desconto � de: ',desc:5:2); {alterado para writeln}
      write ('Digite sua renda: ');
      readln (renda);
      renda:=renda-desc;
      if renda < 1000 then   {then e n�o them}
         writeln ('Isento do IR')   {retirei o begin..end por conter apenas um comando}
      else
         if (renda <= 2000) then {alterado...}
         begin
            ir:=renda*0.05;
            writeln ('Seu imposto � de: ',ir:5:2);
         end {sem ponto e virgula}
         else
            if (renda <= 4000) then {alterado...}
            begin
               ir:=renda*0.1;
               writeln ('Seu imposto � de: ',ir:5:2);
            end {sem virgula}
            else
               begin    {N�o h� necessidade de novo teste(if.) pois � a ultima situa��o}
                  ir:=renda*0.15;
                  writeln ('Seu imposto � de: ',ir:5:2);
               end;
      write ('Digite CPF ou zero para finalizar: ');
      readln (cpf);
   end;
   donewincrt;
end.

