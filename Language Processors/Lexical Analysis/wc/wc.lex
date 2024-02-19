%%
%int
%%
\n         { 	wc.sumaChar+=1;
                return 3;
           } 
[[^]--[ \n]]+    { wc.sumaChar+=yytext().length();
                return 2;
                }
.          {
		return 4;	
	} 
