%%
%int
%x com1
%x com2
%x com3
%%

<com3> {
	[ \t\n] {}
	\*\/ {yybegin(YYINITIAL);}
	[^] {JCom.com3++;}
}

<com2> {
	[ \t\n] {}
	\*\/ {yybegin(YYINITIAL);}
	[^] {JCom.com2++;}
}

<com1> {
	[ \t] {}
	\n {yybegin(YYINITIAL);}
	[^] {JCom.com1++;}
}


\/\*\* {yybegin(com3);}
\/\*   {yybegin(com2);}
\/\/   {yybegin(com1);}
\".*\" {}

[^]          {}
			
	
	

