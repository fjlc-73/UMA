import java.io.FileReader;
import java.io.IOException;

public class wc {
    protected static int sumaLinea = 0;
    protected static int sumaPalabra = 0;
    protected static int sumaChar = 0;
    
    public static void addLinea() {
    	sumaLinea += 1;
    }
    
    public static void addPalabra() {
    	sumaPalabra += 1;
    }
    
    public static void addChar(int x) {
    	sumaChar += x;
    }
    
    public static int getSumaLinea() {
    	return sumaLinea;
    }
    
    public static int getSumaPalabra() {
    	return sumaPalabra;
    }
    
    public static int getSumaChar() {
    	return sumaChar;
    }
    
    public static void reset() {
        sumaLinea = 0;	
        sumaPalabra = 0;
        sumaChar = 0;
    }
    
    
    public static void main(String arg[]) {
    
        if (arg.length>0) {
            try {
                Yylex lex = new Yylex(new FileReader(arg[0]));
                int yytoken = 0;
		while (  (yytoken = lex.yylex()) != Yylex.YYEOF  ) {
                    if (yytoken == 2)  {
                       wc.addPalabra();
                    } else if (yytoken == 3) {
                    	wc.addLinea();
                    } else if (yytoken == 4) {
                    	wc.addChar(1);
                    }  
                }
                       System.out.println(" " + wc.getSumaLinea() + " " + wc.getSumaPalabra() + " " + wc.getSumaChar() + " wc.lex");
                       wc.reset();                
                
	    } catch (IOException e) {
	    }
        }
    }

}
