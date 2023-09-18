package friendsc;

public class Friends {
	private long a;
	private long b;
	private int pos;
	
	public Friends(long a,long b,int pos){
		this.a = a; this.b = b;
		this.pos = pos;
	}

	public String toString(){
		return pos+":("+ a + ","+ b+")";
	}
	public int pos(){
		return pos;
	}
}
