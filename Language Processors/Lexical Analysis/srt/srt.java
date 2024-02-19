import java.io.FileReader;
import java.io.IOException;

public class srt {

    public static int num_subtitulos=0;
    public static int tiempo_total=0;
    public static int num_lineas=0;
    public static int num_palabras=0;
	
    public static void main(String arg[]) {
        if (arg.length>0) {
            Yylex lex = null;
            try {
                lex = new Yylex(new FileReader(arg[0]));
	            lex.yylex();
	            System.out.println("NUM_SUBTITULOS " + num_subtitulos);
	            System.out.println("TIEMPO_TOTAL " + tiempo_total);
	            System.out.println("NUM_LINEAS " + num_lineas);
	            System.out.println("NUM_PALABRAS " + num_palabras);
	        } catch (IOException e) {
	        } 
        }
    }

}
