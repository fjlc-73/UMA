public class DECL extends EXP{
	
	public DECL(AST decl, AST asig, TIPO t){
		super(decl,asig);
		this.tipo = t;
	}
	
	
	public void ctd(){
		if(izq!=null){
			izq.ctd();
		}
		if(der!=null){
			der.ctd();
		} 
		
	}
}
