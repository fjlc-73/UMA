%%
%{ 
	String nombre = "";
	String valor = ""; 
%}
%int
%x var
%x varcomma
%x ech
%%

<var> {
	\$[A-Za-z_]+[A-Za-z0-9_]* {valor=valor.concat(TablaSimbolos.get(yytext()));}
	;|\n {TablaSimbolos.put(nombre, valor); nombre=""; valor=""; yybegin(YYINITIAL);}
	\\; {valor=valor.concat(yytext());}
	. {valor=valor.concat(yytext());}

}

<varcomma> {
	\$[A-Za-z_]+[A-Za-z0-9_]* {valor=valor.concat(TablaSimbolos.get(yytext()));}
	\\\$ {valor=valor.concat(yytext());}
	\\; {valor=valor.concat(yytext());}
	\\\" {valor=valor.concat(yytext());}
	\" {TablaSimbolos.put(nombre, valor); nombre=""; valor=""; yybegin(YYINITIAL);}
	.|\n {valor=valor.concat(yytext());}


}

<ech> {
	\$[A-Za-z_]+[A-Za-z0-9_]* {System.out.print(TablaSimbolos.get(yytext()));}
	\n {yybegin(YYINITIAL);}
	. {System.out.print(yytext());}

}

echo {System.out.print("echo"); yybegin(ech);}

[A-Za-z_]+[A-Za-z0-9_]*=         { nombre=yytext().substring(0, yytext().length()-1); yybegin(var);
                
           } 
           
[A-Za-z_]+[A-Za-z0-9_]*=\"         { nombre=yytext().substring(0, yytext().length()-2); yybegin(varcomma);
                
           }
.|\n          {
			
	} 
	

