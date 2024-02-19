import java.util.HashMap;

public class TablaSimbolos {
	
	private static HashMap<String,TIPO> TablaSimbolos;
	
	static {TablaSimbolos = new HashMap<String,TIPO>();}
	
	public static void insertar(String id, TIPO t){
		TablaSimbolos.put(id,t);
	}
	
	public static TIPO buscar(String id){
		return TablaSimbolos.get(id);
	}
	

}

