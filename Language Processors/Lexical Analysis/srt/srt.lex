%%
%int
%x time1
%x time2
%x text1
%x text2
%{ 
	String oldtime = "";
	String newtime = "";
	int addtime=0;
%}
%%

<time2> {
	[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]\n {newtime = yytext(); 
	
		addtime = 3600*Integer.parseInt(newtime.substring(0,2)) + 60*Integer.parseInt(newtime.substring(3,5)) + Integer.parseInt(newtime.substring(6,8)) - 3600*Integer.parseInt(oldtime.substring(0,2)) - 60*Integer.parseInt(oldtime.substring(3,5)) - Integer.parseInt(oldtime.substring(6,8)); oldtime=""; newtime=""; yybegin(text1);}
	
	[ ] {}			
	
	[^]       {yybegin(YYINITIAL); oldtime=""; newtime=""; addtime=0;}

}

<time1> {
	[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9] { oldtime = yytext();}
	
	--> {yybegin(time2);}
	
	[ ] {}
	
	[^]          {yybegin(YYINITIAL);}

}

<text1> {
	
	[A-Za-z.?¿!¡áéíóú]+ {srt.num_palabras++; yybegin(text2);}
	[A-Za-z.?¿!¡áéíóú]+\n {srt.num_palabras++; srt.num_lineas++; yybegin(text2);}
	\n           {yybegin(YYINITIAL);}	
	[^]          {}
}

<text2> {
	[A-Za-z.?¿!¡áéíóú]+ {srt.num_palabras++;}
	[A-Za-z.?¿!¡áéíóú]+\n {srt.num_palabras++; srt.num_lineas++;}
	\n           {yybegin(YYINITIAL); srt.tiempo_total+=addtime; srt.num_subtitulos++;}	
	[^]          {}
}

[0-9]+\n {yybegin(time1);}

[^0-9\n]+[0-9]+[^0-9\n]*\n {}

[^0-9\n]*[0-9]+[^0-9\n]+\n {}


[^]          {}
			
	
	

