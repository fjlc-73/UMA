%%
%int
%x def
%x defnombre
%x defvalor
%x correctoignore
%x falsoignore
%x falso
%x ignore
%x ifdef
%{ 
	String nombre = "";
	String valor = "";
%}
%%

<falso> {
	#endif {yybegin(correctoignore);}
	[^] {}
}

<falsoignore> {
	\n {yybegin(falso);}
	[^] {}
	
}

<correctoignore> {
	\n {yybegin(YYINITIAL);}
	[^] {}
	
}

<ignore> {
	\n {yybegin(YYINITIAL); System.out.print(yytext());}
	[^] {}
	
}

<defnombre> {
	[A-Za-z][A-Za-z0-9_]* {nombre=yytext(); yybegin(defvalor);}
	[^]          {System.out.print("ERROR_DEFINICION"); yybegin(ignore);}

}

<defvalor> {
	\n {yybegin(YYINITIAL); Prep.put(nombre, valor);}
	[^]          {valor = valor + yytext();}

}

<ifdef> {
	[A-Za-z][A-Za-z0-9_]* {if(Prep.get(yytext()).compareTo("ID_NO_DEFINIDO")==0){yybegin(falsoignore);} else {yybegin(correctoignore);} }
	[^]          {System.out.print("ERROR_IFDEF"); yybegin(ignore);}
	

}


#define[ ] {yybegin(defnombre); nombre="";}

[ ]+#define {System.out.print(yytext());}

\$\{.*\} {System.out.print(Prep.get(yytext().substring(2,yytext().length()-1)));}

#endif {yybegin(correctoignore);}

#ifdef[ ] {yybegin(ifdef);}


[ ]+#ifdef {System.out.print(yytext());}

[ ]+#endif {System.out.print(yytext());}

[^]          {System.out.print(yytext());}
			
	
	

