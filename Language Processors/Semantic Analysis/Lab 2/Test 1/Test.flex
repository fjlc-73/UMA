import java_cup.runtime.*;
%%   
%cup 
//%debug
%%      
0|[1-9][0-9]*				{ return new Symbol(sym.NUMERO, new Integer(yytext()) ); }
.|\n						{  }   

