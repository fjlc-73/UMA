import prJugs.Table;

public class MainTable {

	public static void main(String[] args) {
		Table t = new Table(7,5);
		
		
		t.fill(2);
		System.out.println(t);
		t.pourFrom(2);
		System.out.println(t);
		t.fill(2);
		System.out.println(t);
		t.pourFrom(2);
		System.out.println(t);
		t.empty(1);
		System.out.println(t);
		t.pourFrom(2);
		System.out.println(t);
		t.fill(2);
		System.out.println(t);
		t.pourFrom(2);
		
		System.out.println(t);
		
		
		
		
		

	}

}
