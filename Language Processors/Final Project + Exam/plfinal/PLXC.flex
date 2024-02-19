
import java_cup.runtime.*;
%%

/*  Declaraciones */   
   
%cup

%xstate STRING
%xstate UNICODE

%{
  StringBuffer string = new StringBuffer();
%}
%%   

/* Expresiones y reglas */
   
    ";"		       {return new Symbol(sym.PYC);}
    "("		       {return new Symbol(sym.AP);}
    ")"                {return new Symbol(sym.CP);}
    "{"			{return new Symbol(sym.ALL);}
    "}"			{return new Symbol(sym.CLL);}
    "["			{return new Symbol(sym.AC);}
    "]"			{return new Symbol(sym.CC);}
    "+"			{return new Symbol(sym.MAS);}
    "-"			{return new Symbol(sym.MENOS);}
    "*"			{return new Symbol(sym.POR);}
    "/"			{return new Symbol(sym.DIV);}
    "=="		{return new Symbol(sym.IGUALDAD);}
    "=" 		{return new Symbol(sym.IGUAL);}
    "!="		{return new Symbol(sym.NOIGUAL);}
    "<"			{return new Symbol(sym.MENOR);}
    "<="		{return new Symbol(sym.MENORIGUAL);}
    ">"			{return new Symbol(sym.MAYOR);}
    ">="		{return new Symbol(sym.MAYORIGUAL);}
    "!"			{return new Symbol(sym.NOT);}
    "&&"		{return new Symbol(sym.AND);}
    "||"		{return new Symbol(sym.OR);}
    ","			{return new Symbol(sym.COMA);}
    "-->"		{return new Symbol(sym.IMPLICA);}
    \"			{string.setLength(0); yybegin(STRING);}
    if			{return new Symbol(sym.IF);}
    else 		{return new Symbol(sym.ELSE);}
    while		{return new Symbol(sym.WHILE);}
    do			{return new Symbol(sym.DO);}
    for			{return new Symbol(sym.FOR);}
    forall		{return new Symbol(sym.FORALL);}
    print		{return new Symbol(sym.PRINT);}
    int 		{return new Symbol(sym.INT);}
    float		{return new Symbol(sym.FLOAT);}
    char		{return new Symbol(sym.CHAR);}
    boolean		{return new Symbol(sym.BOOLEAN);}
    true		{return new Symbol(sym.TRUE);}
    false		{return new Symbol(sym.FALSE);}
    string 		{return new Symbol(sym.STRING);}
    "."length		{return new Symbol(sym.LEN);}
    0|[1-9][0-9]*	{return new Symbol(sym.ENTERO, yytext());}
    [1-9]"."[0-9]+E?-?[1-9]* {return new Symbol(sym.REAL, yytext());}
    '\\u[a-f0-9]+'   {return new Symbol(sym.CARACTER, String.valueOf(Integer.parseInt(yytext().substring(3,yytext().length()-1),16)));}
    '..?'		{return new Symbol(sym.CARACTER,String.valueOf((int)yytext().charAt(yytext().length()-2)));}
    [a-zA-Z][a-zA-Z0-9]* {String t = yytext(); if(TablaSimbolos.buscar(t)==null || !TablaSimbolos.buscar(t).esBool()) 
    			 {return new Symbol(sym.IDENT, t);} else {return new Symbol(sym.IDENTBOOL, t);}}
    \r|\n              {  }   
    \ |\t\f            {  }  
    [^]                {  }
    
<STRING> {
	\"	{yybegin(YYINITIAL); return new Symbol(sym.STR, string.toString());}
	[^\n\r\"\\]+ {string.append(yytext());}
	\\t	{string.append('\t');}
	\\n     {string.append('\n');}
	\\r     {string.append('\r');}
	\\\"    {string.append('\"');}
	\\\\      {string.append('\\');}
	\\u     {yybegin(UNICODE);}
}

<UNICODE>{
	[a-zA-Z0-9]{4} {int code = Integer.parseInt(yytext(),16); string.append((char)code); yybegin(STRING);}
}
    
