
import prBookStore.DiscountAuthor;
import prBookStore.FlexSalesBookStore;

public class MainFlexSalesBookStore {

	public static void main(String[] args) {
		String [] s = {"George Orwell", "Isaac Asimov"};
		DiscountAuthor a = new DiscountAuthor(20, s);
		FlexSalesBookStore list = new FlexSalesBookStore(a);
		
		list.addBook("george orwell", "1984", 8.20);
		list.addBook("Philip K. Dick", "¿Sueñan los androides con ovejas eléctricas?", 3.50); 
		list.addBook("Isaac Asimov", "Fundación e Imperio", 9.40);
		list.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
		list.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
		list.addBook("Isaac Asimov", "La Fundación", 7.30);
		list.addBook("William Gibson", "Neuromante", 8.30);
		list.addBook("Isaac Asimov", "Segunda Fundación", 8.10);
		list.addBook("Isaac Newton", "arithmetica universalis", 7.50);
		list.addBook("George Orwell", "1984", 6.20);
		list.addBook("Isaac Newton", "Arithmetica Universalis", 10.50);
		
		System.out.println(list);
		
		list.deleteBook("George Orwell", "1984");
		list.deleteBook("Aldous Huxley", "Un Mundo Feliz");
		list.deleteBook("Isaac Newton", "Arithmetica Universalis");
		list.deleteBook ("James Gosling", "The Java Language Specification");
		
		System.out.println(list);
		
		System.out.println("FinalPrice(\"George Orwell\", \"1984\"):" + list.getFinalPrice("George Orwell", "1984"));
	    System.out.println("FinalPrice(\"Philip K. Dick\", \"¿Sueñan los androides con ovejas eléctricas?\"):" + list.getFinalPrice("Philip K. Dick", "¿Sueñan los androides con ovejas eléctricas?"));
	    System.out.println("FinalPrice(\"isaac asimov\", \"fundación e imperio\"):" + list.getFinalPrice("isaac asimov", "fundación e imperio"));
	    System.out.println("FinalPrice(\"Ray Bradbury\", \"Fahrenheit 451\"):" + list.getFinalPrice("Ray Bradbury", "Fahrenheit 451"));
	    System.out.println("FinalPrice(\"Aldous Huxley\", \"Un Mundo Feliz\"):" + list.getFinalPrice("Aldous Huxley", "Un Mundo Feliz"));
	    System.out.println("FinalPrice(\"Isaac Asimov\", \"La Fundación\"):" + list.getFinalPrice("Isaac Asimov", "La Fundación"));
	    System.out.println("FinalPrice(\"william gibson\", \"neuromante\"):" + list.getFinalPrice("william gibson", "neuromante"));
	    System.out.println("FinalPrice(\"Isaac Asimov\", \"Segunda Fundación\"):" + list.getFinalPrice("Isaac Asimov", "Segunda Fundación"));
	    System.out.println("FinalPrice(\"Isaac Newton\", \"Arithmetica Universalis\"):" + list.getFinalPrice("Isaac Newton", "Arithmetica Universalis"));
		
		
	}

}
