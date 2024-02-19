%%
%int
%x par1
%x par2
%x par3
%x par4
%{ 
	int num = 1;
	int numpar1 = 1;
	int numpar2 = 1;
	int numpar3 = 1;
	int numpar4 = 1;
	
%}
%%
<par4> {
	[0-9]+ {numpar4 = Integer.parseInt(yytext());}
	\+[0-9]+ {numpar4=numpar4+Integer.parseInt(yytext().substring(1,yytext().length()));}
	\*[0-9]+ {numpar4=numpar4*Integer.parseInt(yytext().substring(1,yytext().length()));}
	-[0-9]+ {numpar4=numpar4-Integer.parseInt(yytext().substring(1,yytext().length()));}
	\/[0-9]+ {numpar4=numpar4/Integer.parseInt(yytext().substring(1,yytext().length()));}
	\(      {}
	\)      {numpar3 = numpar3*numpar4; yybegin(par3);} 
	[^]          {}
}

<par3> {
	[0-9]+ {numpar3 = Integer.parseInt(yytext());}
	\+[0-9]+ {numpar3=numpar3+Integer.parseInt(yytext().substring(1,yytext().length()));}
	\*[0-9]+ {numpar3=numpar3*Integer.parseInt(yytext().substring(1,yytext().length()));}
	-[0-9]+ {numpar3=numpar3-Integer.parseInt(yytext().substring(1,yytext().length()));}
	\/[0-9]+ {numpar3=numpar3/Integer.parseInt(yytext().substring(1,yytext().length()));}
	\(      {yybegin(par4);}
	\)      {numpar2 = numpar2*numpar3; yybegin(par2);} 
	[^]          {}
}

<par2> {
	[0-9]+ {numpar2 = Integer.parseInt(yytext());}
	\+[0-9]+ {numpar2=numpar2+Integer.parseInt(yytext().substring(1,yytext().length()));}
	\*[0-9]+ {numpar2=numpar2*Integer.parseInt(yytext().substring(1,yytext().length()));}
	-[0-9]+ {numpar2=numpar2-Integer.parseInt(yytext().substring(1,yytext().length()));}
	\/[0-9]+ {numpar2=numpar2/Integer.parseInt(yytext().substring(1,yytext().length()));}
	\(      {yybegin(par3);}
	\)      {numpar1 = numpar1*numpar2; yybegin(par1);} 
	[^]          {}
}

<par1> {
	[0-9]+ {numpar1 = Integer.parseInt(yytext());}
	\+[0-9]+ {numpar1=numpar1+Integer.parseInt(yytext().substring(1,yytext().length()));}
	\*[0-9]+ {numpar1=numpar1*Integer.parseInt(yytext().substring(1,yytext().length()));}
	-[0-9]+ {numpar1=numpar1-Integer.parseInt(yytext().substring(1,yytext().length()));}
	\/[0-9]+ {numpar1=numpar1/Integer.parseInt(yytext().substring(1,yytext().length()));}
	\(      {yybegin(par2);}
	\)      {num = num*numpar1; yybegin(YYINITIAL);} 
	[^]          {}
}


[0-9]+ {num = Integer.parseInt(yytext());}
\+[0-9]+ {num=num+Integer.parseInt(yytext().substring(1,yytext().length()));}
\*[0-9]+ {num=num*Integer.parseInt(yytext().substring(1,yytext().length()));}
-[0-9]+ {num=num-Integer.parseInt(yytext().substring(1,yytext().length()));}
\/[0-9]+ {num=num/Integer.parseInt(yytext().substring(1,yytext().length()));}
\(      {yybegin(par1);}
;       {System.out.println(num); num = 1; numpar1=1; numpar2=1; numpar3=1; numpar4=1;}
[^]          {}
			
	
	

