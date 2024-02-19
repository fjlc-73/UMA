import java.io.FileReader;
import java.io.IOException;
import java.io.FileReader;

class Parser {

public final static int EOF = Yylex.EOF;
public final static int NUMERO = Yylex.NUMERO ;         
public final static int COMA = Yylex.COMA;
public final static int AC = Yylex.AC; // Abre corchete
public final static int CC = Yylex.CC; // Cierra corchete
public final static int AP = Yylex.AP; // Abre parentesis
public final static int CP = Yylex.CP; // Cierra parentesis
public final static int NELEM = Yylex.NELEM;         
public final static int MAXLENGTH = Yylex.MAXLENGTH;         
public final static int MAXDEPTH = Yylex.MAXDEPTH;         
	
private static int token;
private static Yylex lex;
private static int yylex() {
	int token = 0;
	try {
		token = lex.yylex();
	} catch (IOException e) {
		System.out.println("ERROR");
		System.exit(0);
	}
	return token;
}

public static void main(String[] arg) {
    if (arg.length>0) {
        try {
            lex = new Yylex(new FileReader(arg[0]));
            token = yylex();
            axioma();
            System.out.println("CORRECTO");
        } catch (IOException e) {
        } 
    }
}

	private static void axioma ()
	{
		switch(token){
		case Parser.AC :
			token = yylex();
			var();
			if(token == Parser.CC) {token = yylex();} else {System.out.println("ERROR"); System.exit(0);}
			break;		
		default: 
			System.out.println("ERROR");System.exit(0);					
		}
	}
	
	private static void var ()
	{
		switch(token){
		case Parser.CC :
			break;
		case Parser.AC:
			axioma();
			var2();
			break;
		case Parser.NUMERO:
			token = yylex();
			var3();
			break;		
		default: 
			System.out.println("ERROR");System.exit(0);					
		}
	}
	
	private static void var2 ()
	{
		switch(token){
		case Parser.COMA :
			token = yylex();
			var4();
			break;
		case Parser.CC:
			break;	
		default: 
			System.out.println("ERROR");System.exit(0);					
		}
	}
	
	private static void var3 ()
	{
		switch(token){
		case Parser.COMA :
			token = yylex();
			if(token == Parser.NUMERO) {token = yylex();} else {System.out.println("ERROR");System.exit(0);}
			var3();
			break;
		case Parser.CC:
			break;	
		default: 
			System.out.println("ERROR");System.exit(0);					
		}
	}
	
	private static void var4 ()
	{
		switch(token){
		case Parser.AC :
			axioma();
			break;
		case Parser.NUMERO:
			token = yylex();
			var3();
			break;	
		default: 
			System.out.println("ERROR");System.exit(0);					
		}
	}

}
