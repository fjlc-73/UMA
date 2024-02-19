%%
%{ 
	String nombre = "";
	String valor = "";
%}
%int
%x var
%x out
%%

<var> {
	; {TablaSimbolos.put(nombre, valor); nombre=""; valor=""; yybegin(YYINITIAL);}
	[A-Za-z_]+[A-Za-z0-9_]* {valor=valor.concat(TablaSimbolos.get(yytext()));}
	\"[ ]*(\\\")*[A-Za-z0-9_+ \\\*=;]*(\\\")*[ ]*\" {valor=valor.concat(yytext().substring(1,yytext().length()-1));}
	[^] {}


}


<out> {
	; {System.out.print(valor+"\");"); valor = ""; yybegin(YYINITIAL);}
	[A-Za-z_]+[A-Za-z0-9_]* {valor=valor.concat(TablaSimbolos.get(yytext()));}
	\"[ ]*(\\\")*[A-Za-z0-9_\+ \\]*(\\\")*[ ]*\" {valor=valor.concat(yytext().substring(1,yytext().length()-1));} 
	\( {System.out.print("(\"");}
	[^] {}


}

Prueba[0-9]+ {System.out.print(yytext()+"_rmj");}

\(String[ ]argv\[\]\) {System.out.print(yytext());}

\(String[ ]parametros\[\]\) {System.out.print(yytext());}

String[ ][A-Za-z_]+[A-Za-z0-9_]*    { nombre=yytext().substring(7, yytext().length()); yybegin(var);
                
           } 
           
[A-Za-z_]+[A-Za-z0-9_]*[ ]=    { nombre=yytext().substring(0, yytext().length()-2); yybegin(var);
                
           }

System.out.print 	{System.out.print("System.out.print"); yybegin(out);}	
System.out.println 	{System.out.print("System.out.println"); yybegin(out);}	


[^]          {System.out.print(yytext());
			
	} 
	

