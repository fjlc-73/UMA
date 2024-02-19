import java.io.FileReader;
import java.io.IOException;
import java.io.FileReader;

class Parser {

private static int token;
private static Yylex lex;
private static int yylex() {
	int token = 0;
	try {
		token = lex.yylex();
	} catch (IOException e) {
		System.out.println("IOException");
		System.exit(0);
	}
	return token;
}

public static void main(String[] arg) {
    if (arg.length>0) {
        try {
            lex = new Yylex(new FileReader(arg[0]));
	    token = yylex();
	    axioma ();
        } catch (IOException e) {
        } 
    }
}

	private static void axioma ()
	{
		switch(token){
		case Yytoken.WHILE :
		case Yytoken.DO:
		case Yytoken.IDENT:
		case Yytoken.ALL:
		case Yytoken.EOF:
			Yytoken.regla(0);
			listaSent();
			break;		
		default: 
			Yytoken.error(token);					
		}
	}
	
	private static void listaSent ()
	{
		switch(token){
		case Yytoken.WHILE :
		case Yytoken.DO:
		case Yytoken.IDENT:
		case Yytoken.ALL:
			Yytoken.regla(1);
			sent();
			listaSent();
			break;
		case Yytoken.EOF:
		case Yytoken.CLL:
			Yytoken.regla(2);
			break;
		default: 
			Yytoken.error(token);
		}
	}
	
	private static void sent ()
	{
		switch(token){
		case Yytoken.WHILE:
			Yytoken.regla(3);
			token = yylex();
			if(token == Yytoken.AP) {token = yylex(); cond();} else {Yytoken.error(token);}
			if(token == Yytoken.CP) {token = yylex(); sent();} else {Yytoken.error(token);}
			break;
		case Yytoken.DO:
			Yytoken.regla(4);
			token = yylex();
			sent();
			if(token == Yytoken.WHILE) {token = yylex();} else {Yytoken.error(token);}
			if(token == Yytoken.AP) {token = yylex(); cond();} else {Yytoken.error(token);}
			if(token == Yytoken.CP) {token = yylex();} else {Yytoken.error(token);}
			if(token == Yytoken.PYC) {token = yylex();} else {Yytoken.error(token);}
			break;
		case Yytoken.IDENT:
			Yytoken.regla(5);
			token = yylex();
			if(token == Yytoken.IGUAL) {token = yylex(); var();} else {Yytoken.error(token);}
			if(token == Yytoken.PYC) {token = yylex();} else {Yytoken.error(token);}
			break;
		case Yytoken.ALL:
			Yytoken.regla(6);
			token = yylex();
			listaSent();
			if(token == Yytoken.CLL) {token = yylex();} else {Yytoken.error(token);}	
			break;						
		default: 
			Yytoken.error(token);
		}
	}
	
		private static void cond ()
	{
		switch(token){
		case Yytoken.IDENT:
		case Yytoken.NUMERO:
			Yytoken.regla(7);
			var();
			if(token == Yytoken.MENOR) {token = yylex(); var();} else {Yytoken.error(token);}
			break;				
		default: 
			Yytoken.error(token);
		}
	}

		private static void var ()
	{
		switch(token){
		case Yytoken.IDENT:
			Yytoken.regla(8);
			token = yylex();
			break;
		case Yytoken.NUMERO:
			Yytoken.regla(9);
			token = yylex();
			break;
		default: 
			Yytoken.error(token);
		}
	}

}
