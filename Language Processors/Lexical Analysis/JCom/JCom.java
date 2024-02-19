import java.io.FileReader;
import java.io.IOException;

public class JCom {

    public static int com1=0;
    public static int com2=0;
    public static int com3=0;
	
    public static void main(String arg[]) {
        if (arg.length>0) {
            Yylex lex = null;
            try {
                lex = new Yylex(new FileReader(arg[0]));
	            lex.yylex();
	            System.out.println("// " + com1);
	            System.out.println("/* " + com2);
	            System.out.println("/** " + com3);
	        } catch (IOException e) {
	        } 
        }
    }

}
