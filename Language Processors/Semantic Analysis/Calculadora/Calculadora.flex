%%
%cup
// %debug
%%


0|[1-9][0-9]*         { return new java_cup.runtime.Symbol(sym.NUMBER, Integer.parseInt(yytext()));} 
           
\+         { return new java_cup.runtime.Symbol(sym.PLUS);}
\-	   { return new java_cup.runtime.Symbol(sym.MINUS);}
\*         { return new java_cup.runtime.Symbol(sym.TIMES);}
\/         { return new java_cup.runtime.Symbol(sym.DIVIDE);}
\(        { return new java_cup.runtime.Symbol(sym.LPAREN);}
\)        { return new java_cup.runtime.Symbol(sym.RPAREN);}
\r|\n        { return new java_cup.runtime.Symbol(sym.EOLN);}

[^]          {} 
	

