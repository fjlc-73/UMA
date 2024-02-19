import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Prep {

	protected static HashMap<String,String> hm = new HashMap<String,String>();
	
	public static void put(String variable, String valor) {
		hm.put(variable, valor);
	}
	
	public static String get(String variable) {
		String valor = hm.get(variable);
		return valor!=null ? valor : "ID_NO_DEFINIDO";
	}

	public static void dump() {
		System.out.println("-------------");
		for (String item : hm.keySet()) {
		    System.out.println(item+"="+hm.get(item));
		}	
	}

	
    public static void main(String arg[]) {
        if (arg.length>0) {
            Yylex lex = null;
            try {
                lex = new Yylex(new FileReader(arg[0]));
	            lex.yylex();
	        } catch (IOException e) {
	        } 
        }
    }

}
